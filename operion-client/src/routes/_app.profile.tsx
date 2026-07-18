import { createFileRoute } from "@tanstack/react-router";
import { useState } from "react";
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { z } from "zod";
import { toast } from "sonner";
import { Key, FolderKanban } from "lucide-react";
import { PageHeader } from "@/components/common/page-header";
import { Card, CardContent } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { StatusBadge } from "@/components/common/status-badge";
import {
  Dialog,
  DialogContent,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog";
import { useAuthStore } from "@/stores/auth-store";
import { attendanceApi } from "@/lib/api/attendance";
import { leavesApi } from "@/lib/api/leaves";
import { employeesApi } from "@/lib/api/employees";
import { changePassword } from "@/lib/api/auth";
import { apiErrorMessage } from "@/lib/api/client";
import { initials, fmtDate, fmtTime } from "@/lib/format";

const changePasswordSchema = z.object({
  currentPassword: z.string().min(1, "Current password is required"),
  newPassword: z.string()
    .min(8, "Password must be at least 8 characters")
    .regex(/[A-Z]/, "Password must contain at least one uppercase letter")
    .regex(/[a-z]/, "Password must contain at least one lowercase letter")
    .regex(/\d/, "Password must contain at least one number")
    .regex(/[@$!%*?&]/, "Password must contain at least one special character (@$!%*?&)"),
  confirmPassword: z.string().min(8, "Password must be at least 8 characters"),
}).refine((data) => data.newPassword === data.confirmPassword, {
  message: "Passwords do not match",
  path: ["confirmPassword"],
});
type ChangePasswordValues = z.infer<typeof changePasswordSchema>;

export const Route = createFileRoute("/_app/profile")({
  head: () => ({ meta: [{ title: "Profile — Operion" }] }),
  component: ProfilePage,
});

function ProfilePage() {
  const user = useAuthStore((s) => s.user);
  const [changePasswordOpen, setChangePasswordOpen] = useState(false);
  const attendance = useQuery({
    queryKey: ["attendance", "me", "profile"],
    queryFn: () => attendanceApi.mine({ size: 5, sort: "date,desc" }),
  });
  const leaves = useQuery({
    queryKey: ["leaves", "me", "profile"],
    queryFn: () => leavesApi.mine({ size: 5, sort: "createdAt,desc" }),
  });
  const employeeDetails = useQuery({
    queryKey: ["employee", user?.id],
    queryFn: () => employeesApi.get(user!.id),
    enabled: !!user,
  });

  if (!user) return null;

  return (
    <div className="space-y-6">
      <PageHeader 
        title="My profile" 
        description="Your account details and recent activity."
        actions={
          <Button variant="outline" onClick={() => setChangePasswordOpen(true)}>
            <Key className="h-4 w-4 mr-2" /> Change password
          </Button>
        }
      />

      <Card className="border-border/60">
        <CardContent className="flex items-center gap-4 p-6">
          <div className="flex h-16 w-16 items-center justify-center rounded-full bg-primary text-lg font-semibold text-primary-foreground">
            {initials(user.firstName, user.lastName)}
          </div>
          <div className="min-w-0 flex-1">
            <div className="text-xl font-semibold">
              {user.firstName} {user.lastName}
            </div>
            <div className="text-sm text-muted-foreground">{user.email}</div>
            <div className="mt-1 flex flex-wrap gap-x-4 gap-y-1 text-xs text-muted-foreground">
              {employeeDetails.data?.departmentName && (
                <span>Department: {employeeDetails.data.departmentName}</span>
              )}
              {employeeDetails.data?.position && (
                <span>Position: {employeeDetails.data.position}</span>
              )}
              {employeeDetails.data?.phone && (
                <span>Phone: {employeeDetails.data.phone}</span>
              )}
            </div>
          </div>
          <StatusBadge value={user.role} />
        </CardContent>
      </Card>

      <div className="grid gap-4 lg:grid-cols-2">
        <Card className="border-border/60">
          <CardContent className="p-5">
            <h3 className="mb-3 text-sm font-semibold uppercase tracking-wide text-muted-foreground">
              Recent attendance
            </h3>
            <div className="space-y-2">
              {(attendance.data?.content ?? []).length === 0 ? (
                <p className="text-sm text-muted-foreground">No records.</p>
              ) : (
                attendance.data!.content.map((a) => (
                  <div key={a.id} className="flex items-center justify-between text-sm">
                    <div>
                      <div className="font-medium">{fmtDate(a.date)}</div>
                      <div className="text-xs text-muted-foreground">
                        {a.clockIn ? fmtTime(a.clockIn) : "—"} ·{" "}
                        {a.clockOut ? fmtTime(a.clockOut) : "—"}
                      </div>
                    </div>
                    <StatusBadge value={a.status} />
                  </div>
                ))
              )}
            </div>
          </CardContent>
        </Card>

        <Card className="border-border/60">
          <CardContent className="p-5">
            <h3 className="mb-3 text-sm font-semibold uppercase tracking-wide text-muted-foreground">
              Recent leave
            </h3>
            <div className="space-y-2">
              {(leaves.data?.content ?? []).length === 0 ? (
                <p className="text-sm text-muted-foreground">No records.</p>
              ) : (
                leaves.data!.content.map((l) => (
                  <div key={l.id} className="flex items-center justify-between text-sm">
                    <div>
                      <div className="font-medium">{l.leaveType}</div>
                      <div className="text-xs text-muted-foreground">
                        {fmtDate(l.startDate)} → {fmtDate(l.endDate)}
                      </div>
                    </div>
                    <StatusBadge value={l.status} />
                  </div>
                ))
              )}
            </div>
          </CardContent>
        </Card>
      </div>

      <ChangePasswordDialog open={changePasswordOpen} onOpenChange={setChangePasswordOpen} />
    </div>
  );
}

function ChangePasswordDialog({
  open,
  onOpenChange,
}: {
  open: boolean;
  onOpenChange: (v: boolean) => void;
}) {
  const qc = useQueryClient();
  const form = useForm<ChangePasswordValues>({
    resolver: zodResolver(changePasswordSchema),
    defaultValues: { currentPassword: "", newPassword: "", confirmPassword: "" },
  });

  const mutation = useMutation({
    mutationFn: (values: ChangePasswordValues) =>
      changePassword({ currentPassword: values.currentPassword, newPassword: values.newPassword }),
    onSuccess: () => {
      toast.success("Password changed successfully");
      form.reset();
      onOpenChange(false);
    },
    onError: (err) => toast.error(apiErrorMessage(err, "Failed to change password")),
  });

  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="max-w-md">
        <DialogHeader>
          <DialogTitle>Change password</DialogTitle>
        </DialogHeader>
        <form
          className="space-y-4"
          onSubmit={form.handleSubmit((values) => mutation.mutate(values))}
          noValidate
        >
          <div className="space-y-1.5">
            <Label htmlFor="currentPassword">Current password</Label>
            <Input
              id="currentPassword"
              type="password"
              autoComplete="current-password"
              placeholder="••••••••"
              {...form.register("currentPassword")}
            />
            {form.formState.errors.currentPassword ? (
              <p className="text-xs text-destructive">{form.formState.errors.currentPassword.message}</p>
            ) : null}
          </div>

          <div className="space-y-1.5">
            <Label htmlFor="newPassword">New password</Label>
            <Input
              id="newPassword"
              type="password"
              autoComplete="new-password"
              placeholder="••••••••"
              {...form.register("newPassword")}
            />
            {form.formState.errors.newPassword ? (
              <p className="text-xs text-destructive">{form.formState.errors.newPassword.message}</p>
            ) : null}
          </div>

          <div className="space-y-1.5">
            <Label htmlFor="confirmPassword">Confirm new password</Label>
            <Input
              id="confirmPassword"
              type="password"
              autoComplete="new-password"
              placeholder="••••••••"
              {...form.register("confirmPassword")}
            />
            {form.formState.errors.confirmPassword ? (
              <p className="text-xs text-destructive">{form.formState.errors.confirmPassword.message}</p>
            ) : null}
          </div>

          <DialogFooter>
            <Button
              type="button"
              variant="outline"
              onClick={() => onOpenChange(false)}
              disabled={mutation.isPending}
            >
              Cancel
            </Button>
            <Button type="submit" disabled={mutation.isPending}>
              {mutation.isPending ? "Changing…" : "Change password"}
            </Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
}
