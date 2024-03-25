package com.example.rqchallenge.service;

import com.example.rqchallenge.entity.Employee;
import com.example.rqchallenge.entity.EmployeeResponse;
import com.example.rqchallenge.entity.EmployeesResponse;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeService  implements  IEmployeeService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Employee> getAllEmployees() {
        logger.info("Calling external employee API to fetch employee details");
    EmployeesResponse employeesResponse =  restTemplate.getForObject("/employees", EmployeesResponse.class);
        return employeesResponse.getEmployeesList();
    }

    @Override
    public Optional<Employee> getEmployeeById(String id) {
        EmployeeResponse employeeResponse =  restTemplate.getForObject("/employee/{id}",
                EmployeeResponse.class, id);
        return Optional.ofNullable(employeeResponse.getEmployee());
    }

    @Override
    public EmployeeResponse addEmployee(Employee employee) {
        EmployeeResponse employeeResponse =  restTemplate.postForObject("/create", employee, EmployeeResponse.class);
        return employeeResponse;
    }

    @Override
    public List<Employee> getEmployeesByNameSearch(String searchString) {
        logger.info("Searching employees by matching name for a given string");
        List<Employee> employeeList = getAllEmployees();
        List<Employee> resultList = employeeList.stream().parallel().filter(employee -> employee.getEmployeeName()
                .contains(searchString)).collect(Collectors.toList());
        return resultList;
    }

    @Override
    public Integer getHighestSalaryOfEmployees() {
        Optional<Employee> emp = getAllEmployees().stream()
                .sorted(Comparator.comparingDouble(Employee::getEmployeeSalary).reversed()).findFirst();
        return (int)emp.get().getEmployeeSalary();
    }

    @Override
    public List<String> getTopTenHighestEarningEmployeeNames() {
        List<String> empList = getAllEmployees().stream()
                .sorted(Comparator.comparingDouble(Employee::getEmployeeSalary).reversed()).limit(10).map(
                        emp -> emp.getEmployeeName()).collect(Collectors.toList());
        return empList;
    }

    @Override
    public boolean deleteEmployeeById(String id) {
        restTemplate.delete("/delete/{id}", id);
        return true;
    }
}
