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
    public final String ADDRESS_ADDED_SUCCESSFULLY="Address Added successfully";
    public final String ADDRESS_FETCHED_SUCCESSFULLY="Address fetched successfully";
    public final String ADDRESS_UPDATED_SUCCESSFULLY="Address updated successfully";
    public final String ADDRESS_DELETED_SUCCESSFULLY="Address Deleted Successfully";
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
                .message(ADDRESS_ADDED_SUCCESSFULLY)
                .data(addressService.addAddress(empId,address))
                .statusCode(201)
                .status(HttpStatus.CREATED)
                .success(true)
                .build();
    }
    @PutMapping("/{id}")
    public GenericResponseEntity<AddressDto> updateAddress(@PathVariable long id,@RequestBody Address address){
        return GenericResponseEntity.<AddressDto>builder()
                .message(ADDRESS_UPDATED_SUCCESSFULLY)
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
                .message(ADDRESS_FETCHED_SUCCESSFULLY)
                .data(addressService.fetchAddressByEmpId(empId))
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }
    @GetMapping("/{id}")
    public GenericResponseEntity<Address> fetchAddress(@PathVariable long id){
        return GenericResponseEntity.<Address>builder()
                .message(ADDRESS_FETCHED_SUCCESSFULLY)
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
                .message(ADDRESS_DELETED_SUCCESSFULLY)
                .data(null)
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(false)
                .build();
    }
}
