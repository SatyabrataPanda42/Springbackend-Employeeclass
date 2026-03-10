package com.example.Employee_Management_app.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Employee_Management_app.model.Employee;
@SpringBootTest
public class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository employeeRepository;

    Employee savedEmployee;

    @BeforeEach
    public void setup(){
        employeeRepository.deleteAll();

        Employee employee = new Employee();
        employee.setName("satyabrata panda");
        employee.setEmail("satya@gmail.com");
        employee.setDepartment("CS");
        employee.setSalary(100000);
        employee.setDesignation("developer");

        savedEmployee = employeeRepository.save(employee);
    }

    @AfterEach
    public void tearDown(){
        System.out.println("After each test");
    }

    //Save Test
    @Test
    public void testSaveEmployee(){
        assertNotNull(savedEmployee);
        assertEquals("satyabrata panda", savedEmployee.getName());
        assertEquals("satya@gmail.com", savedEmployee.getEmail());
        assertEquals("CS", savedEmployee.getDepartment());
        assertEquals(100000, savedEmployee.getSalary());
        assertEquals("developer", savedEmployee.getDesignation());
    }

    //Find All
    @Test
    public void testFindAllMethod(){
        Employee employee2 = new Employee();
        employee2.setName("ravi");
        employee2.setEmail("ravi@gmail.com");
        employee2.setDepartment("CS");
        employee2.setSalary(100000);
        employee2.setDesignation("developer");

        employeeRepository.save(employee2);

        List<Employee> employees = employeeRepository.findAll();

        assertEquals(2, employees.size());
    }

    //Find by ID
    @Test
    public void testFindByIdMethod(){
        Employee foundEmployee = employeeRepository.findById(savedEmployee.getId()).orElse(null);

        assertNotNull(foundEmployee);
        assertEquals("satyabrata panda", foundEmployee.getName());
        assertEquals("satya@gmail.com", foundEmployee.getEmail());
    }

    //Update
    @Test
    public void testUpdateMethod(){
        savedEmployee.setDepartment("Data science");
        savedEmployee.setSalary(120000);

        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        assertEquals("Data science", updatedEmployee.getDepartment());
        assertEquals(120000, updatedEmployee.getSalary());
    }

    //Delete
    @Test
    public void testDeleteByIdMethod() {
        employeeRepository.deleteById(savedEmployee.getId());
        assertEquals(0, employeeRepository.findAll().size());
        Employee deletedEmployee = employeeRepository.findById(savedEmployee.getId()).orElse(null);
        assertNull(deletedEmployee);
    }
}
