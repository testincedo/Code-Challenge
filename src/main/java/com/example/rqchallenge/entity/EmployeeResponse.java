package com.example.rqchallenge.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponse {
    @JsonProperty("status")
    private String status;
    @JsonProperty("data")
    private Employee employee;
    @JsonProperty("message")
    private String message;
}
