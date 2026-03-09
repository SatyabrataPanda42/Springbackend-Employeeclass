package com.example.Employee_Management_app.controller;

import com.example.Employee_Management_app.model.Employee;
import com.example.Employee_Management_app.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(final EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    // Create Employee (ADMIN only)
    @PostMapping("/createemployee")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee){
        return ResponseEntity.status(201).body(employeeService.createEmployee(employee));
    }

    // Get All Employees (Public)
    @GetMapping("/allemployee")
    public ResponseEntity<Page<Employee>> getAllEmployee(Pageable pageable){
        Page<Employee> employee = employeeService.getAllEmployee(pageable);
        return ResponseEntity.ok(employee);
    }

    // Get Employee By ID (USER or ADMIN)
    @GetMapping("/getbyid/{id}")
    // @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);

        if (employee != null) {
            return ResponseEntity.ok(employee);
        }

        return ResponseEntity.notFound().build();
    }

    // Update Employee (ADMIN only)
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id,
                                            @Valid @RequestBody Employee employee){

        Employee updated = employeeService.updatEmployee(id, employee);

        if(updated != null){
            return ResponseEntity.ok(updated);
        }

        return ResponseEntity.notFound().build();
    }

    // Delete Employee (ADMIN only)
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){

        boolean deleted = employeeService.deleteEmployee(id);

        if(deleted){
            return ResponseEntity.ok("Employee deleted successfully");
        }

        return ResponseEntity.notFound().build();
    }
}