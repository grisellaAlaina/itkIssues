package org.example.itkissues.service;

import org.example.itkissues.model.Employee;
import org.example.itkissues.projections.EmployeeProjection;
import java.util.List;

public interface EmployeeService {
    List<EmployeeProjection> getAllEmployeesProjection();
    Employee saveEmployee(Employee employee);
}