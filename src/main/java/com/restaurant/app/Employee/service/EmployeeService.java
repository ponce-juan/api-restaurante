package com.restaurant.app.Employee.service;

import com.restaurant.app.Employee.entity.Employee;
import lombok.NonNull;

import java.util.List;

public interface EmployeeService
{
    List<Employee> getAllEmployees();
    Employee getEmployeeById(@NonNull Long id);
    Employee getEmployeeByUserId(@NonNull Long userId);
    Employee getEmployeeByDni(@NonNull String dni);
    List<Employee> getEmployeesByRole(@NonNull String role);
    Employee createEmployee(@NonNull Employee employee);
    Employee updateEmployee(@NonNull Long id, @NonNull Employee employee);
    void deleteEmployee(@NonNull Long id);
}
