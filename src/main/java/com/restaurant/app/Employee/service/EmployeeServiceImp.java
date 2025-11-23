package com.restaurant.app.Employee.service;

import com.restaurant.app.Employee.entity.Employee;
import com.restaurant.app.Employee.repository.EmployeeRepository;
import com.restaurant.app.User.entity.User;
import com.restaurant.app.User.service.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImp implements EmployeeService
{
    //Inyeccion de dependencia
    private final EmployeeRepository employeeRepository;
    private final UserService userService;

    //Get all employees
    @Override
    public List<Employee> getAllEmployees ()
    {
        return employeeRepository.findAll();
    }

    //Get employee by id
    @Override
    public Employee getEmployeeById (@NonNull Long id)
    {
        return employeeRepository
                   .findById(id)
                   .orElseThrow(
                       () -> new EntityNotFoundException("Employee not found with id: " + id)
                   );
    }
    //Get employee by user id
    @Override
    public Employee getEmployeeByUserId (@NonNull Long userId)
    {
        return employeeRepository
                   .findByUserId(userId)
                   .orElseThrow(
                       () -> new EntityNotFoundException("Employee not found with user id: " + userId)
                   );
    }

    //Get employees by role
    @Override
    public List<Employee> getEmployeesByRole (@NonNull String role)
    {
        return employeeRepository
                   .findByRole(role)
                   .orElseThrow(
                       () -> new EntityNotFoundException("Employees not found with role: " + role)
                   );
    }

    //Get employees by dni
    @Override
    public Employee getEmployeeByDni (@NonNull String dni)
    {
        return employeeRepository
                   .findByDni(dni)
                   .orElseThrow(
                       () -> new EntityNotFoundException("Employee not found with dni: " + dni)
                   );
    }

    //Create employee
    @Override
    public Employee createEmployee ( Employee employee)
    {
        //Si existe el empleado con dni registrado, lanza Excepcion
        employeeRepository.findByDni(employee.getDni())
                          .ifPresent(
                              error -> {
                              throw new EntityExistsException("Employee already exists with dni: " + employee.getDni());
                          });

        //Verifico si vino user en del front
        User user = employee.getUser();
        if(user == null)
            throw new IllegalArgumentException("User data is required to create an employee.");
        //Si no existe, lo guarda


        return employeeRepository.save(employee);
    }

    //Update employee
    @Override
    public Employee updateEmployee (@NonNull Long id, @Valid @NonNull Employee employee)
    {
        //Si existe el empleado con id, lo actualizo
        return employeeRepository
                .findById(id)
                .map(employeeDb -> {
                    //Actualizo los datos del empleado
                    //Valido los datos de Person con la notacion @NotBlank en la clase Person
                    employeeDb.setName(employee.getName());
                    employeeDb.setLastName(employee.getLastName());
                    employeeDb.setDni(employee.getDni());
                    employeeDb.setPhone(employee.getPhone());
                    employeeDb.setEmail(employee.getEmail());
                    employeeDb.setRole(employee.getRole());

                    //Valido los datos de Address con la notacion @Valid en la clase Person
                    employeeDb.setAddress(employee.getAddress());

                    employeeDb.setUser(employee.getUser());

                    return employeeRepository.save(employeeDb);
                })
                .orElseThrow(
                    () -> new EntityNotFoundException("Employee not found with id: " + id)
                );
    }

    //Delete employee
    @Override
    public void deleteEmployee (@NonNull Long id)
    {
        //Obtengo el empleado por id
        Employee employee = employeeRepository
                                .findById(id)
                                .orElseThrow(
                                    () -> new EntityNotFoundException("Employee not found with id: " + id)
                                );

        //Si existe el empleado con id, lo elimino
        try {
            employeeRepository.delete(employee);
        }catch (IllegalArgumentException e)
        {
            //Si no existe el empleado con id, lanza Excepcion
            throw new EntityNotFoundException("Employee not found with id: " + id);
        }

    }
}
