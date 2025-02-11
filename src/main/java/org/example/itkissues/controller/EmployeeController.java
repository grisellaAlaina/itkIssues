package org.example.itkissues.controller;

import org.example.itkissues.model.Employee;
import org.example.itkissues.projections.EmployeeProjection;
import org.example.itkissues.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeProjection> getAllEmployees() {
        return employeeService.getAllEmployeesProjection();
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }
}