package com.restaurant.app.User.controller;

import com.restaurant.app.User.entity.User;
import com.restaurant.app.User.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//Notacion para mapear la url con la clase
//La notacion restcontroller, permite convertir el objeto a json y viceversa, porque es un rest controller
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController
{
    //Inyeccion de dependencia de UserService
    private final UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
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
    public List<User> getAllUsers(){
        return userService.getAllUsers();
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
