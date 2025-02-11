package org.example.itkissues.service;

import org.example.itkissues.model.Employee;
import org.example.itkissues.projections.EmployeeProjection;
import org.example.itkissues.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeProjection> getAllEmployeesProjection() {
        return employeeRepository.findAllProjected();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}