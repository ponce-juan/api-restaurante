package com.restaurant.app.Auth.service;

import com.restaurant.app.Auth.dto.LoginRequest;
import lombok.NonNull;


public interface AuthService {

  String login(@NonNull LoginRequest loginRequest);


}
