package com.dinoryn.worksphere.security;

import com.dinoryn.worksphere.dto.ErrorResponse;
import tools.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final EmployeeUserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {


        String authHeader = request.getHeader("Authorization");


        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }


        String jwt = authHeader.substring(7);


        try {

            String username = jwtService.extractUsername(jwt);


            if (username != null &&
                    SecurityContextHolder.getContext()
                            .getAuthentication() == null) {


                UserDetails userDetails =
                        userDetailsService.loadUserByUsername(username);


                if (jwtService.isTokenValid(jwt, userDetails)) {

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );


                    SecurityContextHolder.getContext()
                            .setAuthentication(authToken);
                }
            }


        } catch (ExpiredJwtException e) {

            sendErrorResponse(
                    response,
                    HttpServletResponse.SC_UNAUTHORIZED,
                    "Unauthorized",
                    "JWT token expired"
            );

            return;


        } catch (UsernameNotFoundException e) {

            sendErrorResponse(
                    response,
                    HttpServletResponse.SC_UNAUTHORIZED,
                    "Unauthorized",
                    "User associated with token no longer exists"
            );

            return;


        } catch (JwtException e) {

            sendErrorResponse(
                    response,
                    HttpServletResponse.SC_UNAUTHORIZED,
                    "Unauthorized",
                    "Invalid JWT token"
            );

            return;
        }


        filterChain.doFilter(request, response);
    }


    private void sendErrorResponse(
            HttpServletResponse response,
            int status,
            String error,
            String message
    ) throws IOException {


        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                status,
                error,
                message
        );


        response.setStatus(status);
        response.setContentType("application/json");

        response.getWriter()
                .write(objectMapper.writeValueAsString(errorResponse));
    }
}