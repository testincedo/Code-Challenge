package com.example.rqchallenge.service;

import com.example.rqchallenge.entity.Employee;
import com.example.rqchallenge.entity.EmployeeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IEmployeeService {
    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(String id);

    EmployeeResponse addEmployee(Employee employee);

    List<Employee> getEmployeesByNameSearch(String searchString);

    Integer getHighestSalaryOfEmployees();

    List<String> getTopTenHighestEarningEmployeeNames();

    boolean deleteEmployeeById(String id);
}
