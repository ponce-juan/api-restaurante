package com.restaurant.app.User.controller;

import com.restaurant.app.Company.entity.Company;
import com.restaurant.app.Company.service.CompanyService;
import com.restaurant.app.Employee.entity.Employee;
import com.restaurant.app.Employee.repository.EmployeeRepository;
import com.restaurant.app.Employee.service.EmployeeService;
import com.restaurant.app.User.dto.UserCreateDTO;
import com.restaurant.app.User.dto.UserDTO;
import com.restaurant.app.User.dto.UserMapper;
import com.restaurant.app.User.entity.User;
import com.restaurant.app.User.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

//Notacion para mapear la url con la clase
//La notacion restcontroller, permite convertir el objeto a json y viceversa, porque es un rest controller
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController
{
    //Inyeccion de dependencia de UserService
    private final UserService userService;
    private final EmployeeService employeeService;
    private final CompanyService companyService;

    @PostMapping
    public UserDTO createUser(@RequestBody UserCreateDTO dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        if(dto.getEmployeeId() != null){
            Employee  employee = employeeService.getEmployeeById(dto.getEmployeeId());
            user.setEmployee(employee);
        }

        if(dto.getCompanyId() != null){
            Company company = companyService.getCompanyById(dto.getCompanyId());
            user.setCompany(company);
        }

        User saved = userService.createUser(user);
        return UserMapper.toDTO(saved);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id){

        return userService.getUserById(id);
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable("username") String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers()
                            .stream()
                            .map(UserMapper::toDTO)
                            .collect(Collectors.toList());
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }

}
