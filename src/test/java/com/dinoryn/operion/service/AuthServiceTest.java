package com.dinoryn.operion.service;

import com.dinoryn.operion.dto.ChangePasswordRequest;
import com.dinoryn.operion.dto.ForgotPasswordRequest;
import com.dinoryn.operion.dto.LoginRequest;
import com.dinoryn.operion.dto.LoginResponse;
import com.dinoryn.operion.dto.ResetPasswordRequest;
import com.dinoryn.operion.entity.Employee;
import com.dinoryn.operion.entity.PasswordHistory;
import com.dinoryn.operion.entity.Role;
import com.dinoryn.operion.repository.EmployeeRepository;
import com.dinoryn.operion.repository.PasswordHistoryRepository;
import com.dinoryn.operion.security.JwtService;
import com.dinoryn.operion.service.AuthService;
import com.dinoryn.operion.service.EmailService;
import com.dinoryn.operion.service.impl.AuthServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.ArgumentCaptor;


@ExtendWith(MockitoExtension.class)
class AuthServiceTest {


    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EmailService emailService;

    @Mock
    private PasswordHistoryRepository passwordHistoryRepository;

    @Mock
    private Authentication authentication;

    @Mock
    private Employee employee;

    @InjectMocks
    private AuthServiceImpl authService;

    private LoginRequest loginRequest;
    private ForgotPasswordRequest forgotPasswordRequest;
    private ResetPasswordRequest resetPasswordRequest;
    private ChangePasswordRequest changePasswordRequest;

    @BeforeEach
    void setUp() {
        loginRequest = new LoginRequest();
        loginRequest.setEmail("john.doe@example.com");
        loginRequest.setPassword("password123");

        forgotPasswordRequest = new ForgotPasswordRequest();
        forgotPasswordRequest.setEmail("john.doe@example.com");

        resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setToken("reset-token-123");
        resetPasswordRequest.setNewPassword("newPassword456");

        changePasswordRequest = new ChangePasswordRequest();
        changePasswordRequest.setCurrentPassword("password123");
        changePasswordRequest.setNewPassword("newPassword456");
    }

    @Test
    void login_ShouldReturnLoginResponse_WhenValidCredentials() {
        when(employeeRepository.findByEmail(loginRequest.getEmail()))
                .thenReturn(Optional.of(employee));
        when(employee.getAccountLockedUntil()).thenReturn(null);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(employee.getId()).thenReturn(1L);
        when(employee.getEmail()).thenReturn("john.doe@example.com");
        when(employee.getRole()).thenReturn(Role.ADMIN);
        when(jwtService.generateToken(employee)).thenReturn("jwt-token");

        LoginResponse result = authService.login(loginRequest);

        assertNotNull(result);
        assertEquals("jwt-token", result.getToken());
        assertEquals(1L, result.getId());
        assertEquals("john.doe@example.com", result.getEmail());
        assertEquals("ADMIN", result.getRole());
        verify(employeeRepository).save(employee);
        assertEquals(0, employee.getFailedLoginAttempts());
        assertNull(employee.getAccountLockedUntil());
    }

    @Test
    void login_ShouldThrowException_WhenAccountLocked() {
        when(employeeRepository.findByEmail(loginRequest.getEmail()))
                .thenReturn(Optional.of(employee));
        when(employee.getAccountLockedUntil()).thenReturn(LocalDateTime.now().plusMinutes(30));

        assertThrows(RuntimeException.class, () -> authService.login(loginRequest));
        verify(authenticationManager, never()).authenticate(any());
    }

    @Test
    void login_ShouldThrowException_WhenInvalidCredentials() {
        when(employeeRepository.findByEmail(loginRequest.getEmail()))
                .thenReturn(Optional.of(employee));
        when(employee.getAccountLockedUntil()).thenReturn(null);
        
        // Use Answer to make getFailedLoginAttempts return the value that was set
        final int[] failedAttempts = {3};
        when(employee.getFailedLoginAttempts()).thenAnswer(invocation -> failedAttempts[0]);
        doAnswer(invocation -> {
            failedAttempts[0] = invocation.getArgument(0);
            return null;
        }).when(employee).setFailedLoginAttempts(anyInt());
        
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        assertThrows(BadCredentialsException.class, () -> authService.login(loginRequest));
        verify(employeeRepository).save(employee);
        verify(employee).setFailedLoginAttempts(4);
        verify(employee, never()).setAccountLockedUntil(any());
        verify(jwtService, never()).generateToken(any());
    }

    @Test
    void login_ShouldLockAccountAfter5FailedAttempts() {
        when(employeeRepository.findByEmail(loginRequest.getEmail()))
                .thenReturn(Optional.of(employee));
        when(employee.getAccountLockedUntil()).thenReturn(null);
        
        // Use Answer to make getFailedLoginAttempts return the value that was set
        final int[] failedAttempts = {4};
        when(employee.getFailedLoginAttempts()).thenAnswer(invocation -> failedAttempts[0]);
        doAnswer(invocation -> {
            failedAttempts[0] = invocation.getArgument(0);
            return null;
        }).when(employee).setFailedLoginAttempts(anyInt());
        
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        assertThrows(BadCredentialsException.class, () -> authService.login(loginRequest));
        verify(employeeRepository).save(employee);
        verify(employee).setFailedLoginAttempts(5);
        verify(employee).setAccountLockedUntil(any(LocalDateTime.class));
        verify(jwtService, never()).generateToken(any());
    }

    @Test
    void login_ShouldUnlockAccountAfterSuccessfulLogin() {
        when(employeeRepository.findByEmail(loginRequest.getEmail()))
                .thenReturn(Optional.of(employee));
        when(employee.getAccountLockedUntil()).thenReturn(null);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(employee.getId()).thenReturn(1L);
        when(employee.getEmail()).thenReturn("john.doe@example.com");
        when(employee.getRole()).thenReturn(Role.ADMIN);
        when(jwtService.generateToken(employee)).thenReturn("jwt-token");

        LoginResponse result = authService.login(loginRequest);

        assertNotNull(result);
        verify(employeeRepository).save(employee);
        verify(employee).setFailedLoginAttempts(0);
        verify(employee).setAccountLockedUntil(null);
    }

    @Test
    void forgotPassword_ShouldSendResetToken_WhenEmailExists() {
        when(employeeRepository.findByEmail(forgotPasswordRequest.getEmail()))
                .thenReturn(Optional.of(employee));

        authService.forgotPassword(forgotPasswordRequest);

        verify(employeeRepository).save(employee);
        verify(employee).setPasswordResetToken(anyString());
        verify(employee).setPasswordResetTokenExpiry(any(LocalDateTime.class));
        verify(emailService).sendPasswordResetEmail(eq(forgotPasswordRequest.getEmail()), anyString());
    }

    @Test
    void forgotPassword_ShouldThrowException_WhenEmailNotFound() {
        when(employeeRepository.findByEmail(forgotPasswordRequest.getEmail()))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> authService.forgotPassword(forgotPasswordRequest));
        verify(emailService, never()).sendPasswordResetEmail(any(), any());
    }

    @Test
    void resetPassword_ShouldResetPassword_WhenTokenValid() {
        when(employeeRepository.findByPasswordResetToken(resetPasswordRequest.getToken()))
                .thenReturn(Optional.of(employee));
        when(employee.getPasswordResetTokenExpiry()).thenReturn(LocalDateTime.now().plusMinutes(30));
        when(employee.getPassword()).thenReturn("oldEncodedPassword");
        when(employee.getEmail()).thenReturn("john.doe@example.com");
        when(employee.getPasswordVersion()).thenReturn(0);
        when(passwordHistoryRepository.findRecentPasswordsByEmployeeId(any(), any()))
                .thenReturn(List.of());
        when(passwordHistoryRepository.findByEmployeeIdOrderByCreatedAtDesc(any()))
                .thenReturn(List.of());
        when(passwordEncoder.encode(resetPasswordRequest.getNewPassword())).thenReturn("newEncodedPassword");

        authService.resetPassword(resetPasswordRequest);

        verify(passwordEncoder).encode(resetPasswordRequest.getNewPassword());
        verify(passwordHistoryRepository).save(any(PasswordHistory.class));
        verify(employeeRepository).save(employee);
        verify(employee).setPassword("newEncodedPassword");
        verify(employee).setPasswordResetToken(null);
        verify(employee).setPasswordResetTokenExpiry(null);
        verify(employee).setPasswordVersion(1);
        verify(emailService).sendPasswordChangedNotification("john.doe@example.com");
    }

    @Test
    void resetPassword_ShouldThrowException_WhenTokenInvalid() {
        when(employeeRepository.findByPasswordResetToken(resetPasswordRequest.getToken()))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> authService.resetPassword(resetPasswordRequest));
        verify(passwordEncoder, never()).encode(any());
    }

    @Test
    void resetPassword_ShouldThrowException_WhenTokenExpired() {
        when(employeeRepository.findByPasswordResetToken(resetPasswordRequest.getToken()))
                .thenReturn(Optional.of(employee));
        when(employee.getPasswordResetTokenExpiry()).thenReturn(LocalDateTime.now().minusMinutes(30));

        assertThrows(RuntimeException.class, () -> authService.resetPassword(resetPasswordRequest));
        verify(passwordEncoder, never()).encode(any());
    }

    @Test
    void resetPassword_ShouldThrowException_WhenPasswordReused() {
        PasswordHistory oldHistory = new PasswordHistory();
        oldHistory.setPasswordHash("oldEncodedPassword");
        
        when(employeeRepository.findByPasswordResetToken(resetPasswordRequest.getToken()))
                .thenReturn(Optional.of(employee));
        when(employee.getPasswordResetTokenExpiry()).thenReturn(LocalDateTime.now().plusMinutes(30));
        when(passwordHistoryRepository.findRecentPasswordsByEmployeeId(any(), any()))
                .thenReturn(List.of(oldHistory));
        when(passwordEncoder.matches(resetPasswordRequest.getNewPassword(), "oldEncodedPassword"))
                .thenReturn(true);

        assertThrows(RuntimeException.class, () -> authService.resetPassword(resetPasswordRequest));
        verify(passwordEncoder, never()).encode(any());
        verify(passwordHistoryRepository, never()).save(any());
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void changePassword_ShouldChangePassword_WhenCurrentPasswordCorrect() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employee.getPassword()).thenReturn("oldEncodedPassword");
        when(employee.getEmail()).thenReturn("john.doe@example.com");
        when(employee.getPasswordVersion()).thenReturn(0);
        when(passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), "oldEncodedPassword"))
                .thenReturn(true);
        when(passwordHistoryRepository.findRecentPasswordsByEmployeeId(any(), any()))
                .thenReturn(List.of());
        when(passwordHistoryRepository.findByEmployeeIdOrderByCreatedAtDesc(any()))
                .thenReturn(List.of());
        when(passwordEncoder.encode(changePasswordRequest.getNewPassword())).thenReturn("newEncodedPassword");

        authService.changePassword(1L, changePasswordRequest);

        verify(passwordEncoder).encode(changePasswordRequest.getNewPassword());
        verify(passwordHistoryRepository).save(any(PasswordHistory.class));
        verify(employeeRepository).save(employee);
        verify(employee).setPassword("newEncodedPassword");
        verify(employee).setPasswordVersion(1);
        verify(emailService).sendPasswordChangedNotification("john.doe@example.com");
    }

    @Test
    void changePassword_ShouldThrowException_WhenCurrentPasswordIncorrect() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), employee.getPassword()))
                .thenReturn(false);

        assertThrows(RuntimeException.class, () -> authService.changePassword(1L, changePasswordRequest));
        verify(passwordEncoder, never()).encode(any());
        verify(passwordHistoryRepository, never()).save(any());
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void changePassword_ShouldThrowException_WhenPasswordReused() {
        PasswordHistory oldHistory = new PasswordHistory();
        oldHistory.setPasswordHash("oldEncodedPassword");
        
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), employee.getPassword()))
                .thenReturn(true);
        when(passwordHistoryRepository.findRecentPasswordsByEmployeeId(any(), any()))
                .thenReturn(List.of(oldHistory));
        when(passwordEncoder.matches(changePasswordRequest.getNewPassword(), "oldEncodedPassword"))
                .thenReturn(true);

        assertThrows(RuntimeException.class, () -> authService.changePassword(1L, changePasswordRequest));
        verify(passwordEncoder, never()).encode(any());
        verify(passwordHistoryRepository, never()).save(any());
        verify(employeeRepository, never()).save(any());
    }
}