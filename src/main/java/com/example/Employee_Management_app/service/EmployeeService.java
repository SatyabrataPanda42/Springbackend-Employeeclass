package com.example.Employee_Management_app.service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Employee_Management_app.Exception.ResourceNotFoundException;
import com.example.Employee_Management_app.model.Employee;
import com.example.Employee_Management_app.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    //Create
    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    //GetAll
    public Page<Employee> getAllEmployee(Pageable pageable){
        return employeeRepository.findAll(pageable);
    }

    //GetbyID
    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: "+id));
    }

    //update
    public Employee updatEmployee(Long id, Employee employee){
        Employee existing = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: "+id));
        if(existing != null){
            existing.setName(employee.getName());
            existing.setEmail(employee.getEmail());
            existing.setDepartment(employee.getDepartment());
            existing.setSalary(employee.getSalary());
            existing.setDesignation(employee.getDesignation());
            return employeeRepository.save(existing);
        }
        return null;
    }

    //Delete
    public boolean deleteEmployee(Long id){
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
            return true;
        }else{
            throw new ResourceNotFoundException("Employee not found with id: "+id);
        }
    }
}
