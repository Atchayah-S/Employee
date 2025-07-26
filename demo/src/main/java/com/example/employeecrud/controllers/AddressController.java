package com.example.employeecrud.controllers;

import com.example.employeecrud.Security.JwtUtils;
import com.example.employeecrud.dao.Address;
import com.example.employeecrud.dto.AddressDto;
import com.example.employeecrud.dto.GenericResponseEntity;
import com.example.employeecrud.services.AddressService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/addAddress")
    public GenericResponseEntity<AddressDto> addAddress(HttpServletRequest request, @RequestBody Address address){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        long empId=jwtUtils.extractEmployeeId(token);
        return GenericResponseEntity.<AddressDto>builder()
                .message("Address created successfully")
                .data(addressService.addAddress(empId,address))
                .success(true)
                .build();
    }
    @PutMapping("/updateAddress/{id}")
    public GenericResponseEntity<AddressDto> updateAddress(@PathVariable long id,@RequestBody Address address){
        return GenericResponseEntity.<AddressDto>builder()
                .message("Address updated successfully")
                .data(addressService.updateAddress(id,address))
                .success(true)
                .build();

    }
    @GetMapping("/fetchAddress/Employee")
    public GenericResponseEntity<List<AddressDto>> fetchAddressByEmp(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        long empId=jwtUtils.extractEmployeeId(token);
        return GenericResponseEntity.<List<AddressDto>>builder()
                .message("Address fetched successfully")
                .data(addressService.fetchAddressByEmpId(empId))
                .success(true)
                .build();

    }
    @GetMapping("/fetchAddress/Address/{id}")
    public GenericResponseEntity<Address> fetchAddress(@PathVariable long id){
        return GenericResponseEntity.<Address>builder()
                .message("Address fetched successfully")
                .data(addressService.fetchAddressById(id))
                .success(true)
                .build();
    }
    @DeleteMapping("/deleteAddress/{id}")
    public GenericResponseEntity<String> deleteAddress(@PathVariable long id){
        addressService.deleteAddress(id);
        return GenericResponseEntity.<String>builder()
                .message("Address Deleted Successfully")
                .data(null)
                .success(false)
                .build();

    }
}
