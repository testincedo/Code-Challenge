package com.example.rqchallenge;

import static org.springframework.test.util.AssertionErrors.assertEquals;

import com.example.rqchallenge.entity.Employee;
import com.example.rqchallenge.service.IEmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    IEmployeeService employeeService;

  @Test
  void testGetEmployee() throws JSONException, IOException, URISyntaxException {
        Optional<Employee> emp = employeeService.getEmployeeById("1");
        String json = readFileFromResources("response/employee.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Employee expected = objectMapper.readValue(json, Employee.class);
        assertEquals("Validating employee get data", expected.getEmployeeName(), emp.get().getEmployeeName());
    }

    @Test
    void testEmployeeNameSearch() {
        List<Employee> empList = employeeService.getEmployeesByNameSearch("Michael");
        assertEquals("Checking List Size", 1, empList.size());
    }

    @Test
    void testHighestSalaryOfEmployee() {
        Integer hSalary = employeeService.getHighestSalaryOfEmployees();
        assertEquals("Testing highest Salary", 725000, hSalary);
    }

    public static String readFileFromResources(String filename) throws URISyntaxException, IOException {
        URL resource = EmployeeServiceTest.class.getClassLoader().getResource(filename);
        byte[] bytes = Files.readAllBytes(Paths.get(resource.toURI()));
        return new String(bytes);
    }

}
