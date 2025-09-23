package com.restaurant.app.Employee.controller;

import com.restaurant.app.Employee.entity.Employee;
import com.restaurant.app.Employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController
{
    //Inyeccion de dependencia
    private final EmployeeService employeeService;

    public EmployeeController (EmployeeService employeeService)
    {
        this.employeeService = employeeService;
    }

    //Get all employees
    @GetMapping
    public List<Employee> getAllEmployees ()
    {
        return employeeService.getAllEmployees();
    }

    //Get employee by id
    @GetMapping("/{id}")
    public Employee getEmployeeById (@PathVariable Long id)
    {
        return employeeService.getEmployeeById(id);
    }

    //Get employee by user id
    @GetMapping("/user/{userId}")
    public Employee getEmployeeByUserId (@PathVariable Long userId)
    {
        return employeeService.getEmployeeByUserId(userId);
    }

    //Get employees by role
    @GetMapping("/role/{role}")
    public List<Employee> getEmployeesByRole (@PathVariable String role)
    {
        return employeeService.getEmployeesByRole(role);
    }

    //Get employee by dni
    @GetMapping("/dni/{dni}")
    public Employee getEmployeeByDni (@PathVariable String dni)
    {
        return employeeService.getEmployeeByDni(dni);
    }

    //Create employee
    @PostMapping
    public Employee createEmployee (@Valid @RequestBody Employee employee)
    {
        return employeeService.createEmployee(employee);
    }

    //Update employee
    @PutMapping("/{id}")
    public Employee updateEmployee (@PathVariable Long id, @Valid @RequestBody Employee employee)
    {
        return employeeService.updateEmployee(id, employee);
    }

    //Delete employee
    @DeleteMapping("/{id}")
    public void deleteEmployee (@PathVariable Long id)
    {
        employeeService.deleteEmployee(id);
    }




}
