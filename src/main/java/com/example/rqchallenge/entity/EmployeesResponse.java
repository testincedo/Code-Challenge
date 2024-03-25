package com.example.rqchallenge.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeesResponse {
    @JsonProperty("status")
    private String status;
    @JsonProperty("data")
    private List<Employee> employeesList;
    @JsonProperty("message")
    private String message;
}
