package com.example.rqchallenge.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    @JsonProperty("id")
    private String id;
    @JsonProperty("employee_name")
    private String employeeName;
    @JsonProperty("employee_salary")
    private double employeeSalary;
    @JsonProperty("employee_age")
    private int employeeAge;
}
