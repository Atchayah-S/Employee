package com.example.employeecrud.controllers;

import com.example.employeecrud.Security.JwtUtils;
import com.example.employeecrud.dao.Address;
import com.example.employeecrud.dto.AddressDto;
import com.example.employeecrud.dto.GenericResponseEntity;
import com.example.employeecrud.services.AddressService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("/api/Address")
public class AddressController {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AddressService addressService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponseEntity<AddressDto> addAddress(HttpServletRequest request, @RequestBody Address address){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        long empId=jwtUtils.extractEmployeeId(token);
        return GenericResponseEntity.<AddressDto>builder()
                .message("Address created successfully")
                .data(addressService.addAddress(empId,address))
                .statusCode(201)
                .status(HttpStatus.CREATED)
                .success(true)
                .build();
    }
    @PutMapping("/{id}")
    public GenericResponseEntity<AddressDto> updateAddress(@PathVariable long id,@RequestBody Address address){
        return GenericResponseEntity.<AddressDto>builder()
                .message("Address updated successfully")
                .data(addressService.updateAddress(id,address))
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }
    @GetMapping
    public GenericResponseEntity<List<AddressDto>> fetchAddressByEmp(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        long empId=jwtUtils.extractEmployeeId(token);
        return GenericResponseEntity.<List<AddressDto>>builder()
                .message("Address fetched successfully")
                .data(addressService.fetchAddressByEmpId(empId))
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }
    @GetMapping("/{id}")
    public GenericResponseEntity<Address> fetchAddress(@PathVariable long id){
        return GenericResponseEntity.<Address>builder()
                .message("Address fetched successfully")
                .data(addressService.fetchAddressById(id))
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();
    }
    @DeleteMapping("/{id}")
    public GenericResponseEntity<String> deleteAddress(@PathVariable long id){
        addressService.deleteAddress(id);
        return GenericResponseEntity.<String>builder()
                .message("Address Deleted Successfully")
                .data(null)
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(false)
                .build();

    }
}
