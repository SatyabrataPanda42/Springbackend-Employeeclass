package com.example.Employee_Management_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Employee_Management_app.model.Employee;
import com.example.Employee_Management_app.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(final EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    //create
    @PostMapping("/createemployee")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee){
        return ResponseEntity.status(201).body(employeeService.createEmployee(employee));
    }

    //Getall
    @GetMapping("/allemployee")
    public ResponseEntity <Page<Employee>> getAllEmployee(Pageable pagable){
        Page<Employee> employee = employeeService.getAllEmployee(pagable);
        return ResponseEntity.ok(employee);
    }
    
    //GetbyId
    @GetMapping("/getbyid/{id}")
     public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.notFound().build();
    }

    //UpdatebyId
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatEmployee(@PathVariable Long id, @Valid @RequestBody Employee employee){
        Employee updated = employeeService.updatEmployee(id, employee);
        if(updated != null){
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        boolean deleted = employeeService.deleteEmployee(id);
        if(deleted){
            return ResponseEntity.ok("Employee deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
    
}
