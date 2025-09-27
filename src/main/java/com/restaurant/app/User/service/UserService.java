package com.restaurant.app.User.service;

import com.restaurant.app.User.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

import java.util.List;

public interface UserService
{
    List<User> getAllUsers();
    User getUserById(@NonNull Long id);
    User getUserByUsername(@NotBlank String username);
    User createUser(@NonNull User user);
    User updateUser(@NonNull Long id, @NonNull User user);
    void deleteUser(@NonNull Long id);
}
