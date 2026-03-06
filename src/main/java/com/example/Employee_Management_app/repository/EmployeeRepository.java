package com.example.Employee_Management_app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Employee_Management_app.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{}
