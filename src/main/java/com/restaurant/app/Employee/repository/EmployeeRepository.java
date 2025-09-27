package com.restaurant.app.Employee.repository;

import com.restaurant.app.Employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
    Optional<Employee> findByUserId(Long userId);
    Optional<List<Employee>> findByRole(String role);
    Optional<Employee> findByDni(String dni);
}
