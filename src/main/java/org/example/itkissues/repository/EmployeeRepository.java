package org.example.itkissues.repository;

import org.example.itkissues.model.Employee;
import org.example.itkissues.projections.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e.firstName || ' ' || e.lastName AS fullName, e.position AS position, d.name AS departmentName FROM Employee e JOIN e.department d")
    List<EmployeeProjection> findAllProjected();
}