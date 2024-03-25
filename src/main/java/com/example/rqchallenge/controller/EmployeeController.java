package com.example.rqchallenge.controller;

import com.example.rqchallenge.entity.Employee;
import com.example.rqchallenge.entity.EmployeeResponse;
import com.example.rqchallenge.service.IEmployeeService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController implements  IEmployeeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IEmployeeService employeeService;

    @Override
    public ResponseEntity<List<Employee>> getAllEmployees() throws IOException {
        List<Employee> empList = employeeService.getAllEmployees();

        return ResponseEntity.ok(empList);
    }

    @Override
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) {
        logger.info("Searching Employee Name starting with {}", searchString);
        if (employeeService.getEmployeesByNameSearch(searchString).size() > 0) {
            return new ResponseEntity<>(employeeService.getEmployeesByNameSearch(searchString), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(String id) {
        Optional<Employee> response = employeeService.getEmployeeById(id);
        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
        int highestSalary =  employeeService.getHighestSalaryOfEmployees();
        if (highestSalary > 0) {
            return new ResponseEntity<>(highestSalary, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
        List<String> empList =  employeeService.getTopTenHighestEarningEmployeeNames();
        if (empList.size() > 0) {
            return new ResponseEntity<>(empList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        EmployeeResponse employeeResponse = employeeService.addEmployee(employee);
        if (employeeResponse.getStatus() == "success") {
            return new ResponseEntity<>(employeeResponse.getEmployee(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @Override
    public ResponseEntity<String> deleteEmployeeById(String id) {
        if (employeeService.deleteEmployeeById(id)) {
            return new ResponseEntity<>("Employee Deleted Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
