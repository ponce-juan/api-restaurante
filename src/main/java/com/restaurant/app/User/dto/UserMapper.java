package com.restaurant.app.User.dto;

import com.restaurant.app.User.entity.User;

public class UserMapper {
    public static UserDTO toDTO (User user){
        if(user==null) return null;

        return UserDTO.builder()
                .id(user.getId())
                .role(user.getEmployee().getRole())
                .username(user.getUsername())
                .employeeId(user.getEmployee() != null ? user.getEmployee().getId() : null)
                .companyId(user.getCompany() != null ? user.getCompany().getId() : null)
                .build();
    }

    public static User toEntity (UserDTO dto){
        if(dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());

        return user;
    }
}
