package com.example.Employee_Management_app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Employee_Management_app.model.Employee;
import com.example.Employee_Management_app.repository.EmployeeRepository;
import com.example.Employee_Management_app.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    Employee employee;
    @BeforeEach
    public void setup(){
        employee = new Employee();
        employee.setId(1L);
        employee.setName("satyabrata panda");
        employee.setEmail("satya@gmail.com");
        employee.setDepartment("CS");
        employee.setSalary(100000);
        employee.setDesignation("developer");
    }

    //Create
    @Test
    public void testCreateEmployeeMethod(){
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee savedEmployee = employeeService.createEmployee(employee);

        assertNotNull(savedEmployee);
        assertEquals(employee.getName(), savedEmployee.getName());
    }

    //GetById
    @Test
    public void testGetEmployeeById(){
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Employee foundEmployee = employeeService.getEmployeeById(1L);
        assertNotNull(foundEmployee);
        assertEquals("satyabrata panda", foundEmployee.getName());
    }

    //Update
    @Test
    public void testUpdateEmployee(){

        when(employeeRepository.findById(1l)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee updatedEmployee = employeeService.updatEmployee(1L, employee);

        assertNotNull(updatedEmployee);
        assertEquals(employee.getName(), updatedEmployee.getName());
    }

    //Delete
    @Test
    public void testDeleteEmployee(){
        when(employeeRepository.existsById(1L)).thenReturn(true);
        boolean deleteStatus = employeeService.deleteEmployee(1L);
        assertEquals(true, deleteStatus);
        Mockito.verify(employeeRepository).deleteById(1L);
    }

}
