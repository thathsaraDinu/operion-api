package com.dinoryn.worksphere.security;

import com.dinoryn.worksphere.entity.Employee;
import com.dinoryn.worksphere.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeUserDetailsService
        implements UserDetailsService {


    private final EmployeeRepository employeeRepository;


    @Override
    public UserDetails loadUserByUsername(String email){

        Employee employee =
                employeeRepository.findByEmail(email)
                        .orElseThrow(
                                () -> new UsernameNotFoundException(
                                        "Employee not found"
                                )
                        );


        return new EmployeeUserDetails(employee);
    }
}