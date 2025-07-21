package com.example.employeecrud.dto;

import lombok.Data;

@Data
public class AddressDto {
    private long id;
    private String doorNo;
    private String streetName;
    private String city;
    private int zipCode;
}
