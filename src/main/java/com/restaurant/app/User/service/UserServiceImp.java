package com.restaurant.app.User.service;

import com.restaurant.app.User.entity.User;
import com.restaurant.app.User.repository.UserRepository;
import com.restaurant.app.Utils.StringUtils;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService
{
    //Inyeccion de dependencia de UserRepository
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //Get all users
    @Override
    public List<User> getAllUsers (){
        return userRepository.findAll();
    }

    //Get
    @Override
    public User getUserById(@NonNull Long id){
        return userRepository
               .findById(id)
               .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Override
    public User getUserByUsername(@NotBlank String username){
        if(StringUtils.isNullOrBlank(username))
            throw new IllegalArgumentException("Username cannot be null or empty");

        return userRepository.findByUsername(username);
    }


    //Create
    @Override
    public User createUser (@NonNull User user){
        //Verifico si existe el usuario con dicho username
        if(userRepository.findByUsername(user.getUsername()) != null){
            throw new EntityExistsException("User already exists with username: " + user.getUsername());
        }
        //Verifico que los campos de User no sean nulos o vacios
        if(StringUtils.isNullOrBlank(user.getUsername())){
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if(StringUtils.isNullOrBlank(user.getPassword())){
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        //Encripto la contraseña
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //Si los campos estan completos, guardo el usuario
        return userRepository.save(user);
    }

    //Update
    @Override
    @Transactional
    public User updateUser(@NonNull Long id, @NonNull User user){
        //Verifico si existe el usuario con dicho id
        User userDb = getUserById(id);

        //Al ser un metodo PUT, actualizo todos los campos con el nuevo objeto
        userDb.setUsername(StringUtils.isNullOrBlank(user.getUsername()) ? userDb.getUsername() : user.getUsername());
        userDb.setPassword(StringUtils.isNullOrBlank(user.getPassword()) ? userDb.getPassword() : user.getPassword());

        //Encripto la contraseña
        userDb.setPassword(passwordEncoder.encode(userDb.getPassword()));

        return userRepository.save(userDb);
    }

    //Delete
    @Override
    public void deleteUser(@NonNull Long id){
        //Obtengo el usuario de la db, lanza EntityNotFoundException si no existe
        User userDb = getUserById(id);

        userRepository.deleteById(id);
    }

}
