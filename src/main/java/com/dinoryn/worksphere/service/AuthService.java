package com.dinoryn.worksphere.service;

import com.dinoryn.worksphere.dto.LoginRequest;
import com.dinoryn.worksphere.dto.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

}