package com.example.carx_testing.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties
public class UserDTO implements Serializable {

    private int money;

    private String country;
}
