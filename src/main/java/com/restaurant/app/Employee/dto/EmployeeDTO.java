package com.restaurant.app.Employee.dto;

import com.restaurant.app.User.dto.UserDTO;
import com.restaurant.app.common.embedded.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String role;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String dni;
    private Address address;
    private UserDTO userDTO;
}
