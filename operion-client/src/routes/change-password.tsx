import { createFileRoute, useNavigate } from "@tanstack/react-router";
import { useEffect } from "react";

export const Route = createFileRoute("/change-password")({
  component: ChangePasswordRedirect,
});

function ChangePasswordRedirect() {
  const navigate = useNavigate();

  useEffect(() => {
    navigate({ to: "/profile", replace: true });
  }, [navigate]);

  return null;
}
