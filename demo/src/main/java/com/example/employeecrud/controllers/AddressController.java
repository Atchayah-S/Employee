package com.example.employeecrud.controllers;

import com.example.employeecrud.Security.JwtUtils;
import com.example.employeecrud.dao.Address;
import com.example.employeecrud.dto.AddressDto;
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
    public AddressDto addAddress(HttpServletRequest request, @RequestBody Address address){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        long empId=jwtUtils.extractEmployeeId(token);
        return addressService.addAddress(empId,address);
    }
    @PutMapping("/updateAddress/{id}")
    public AddressDto updateAddress(@PathVariable long id,@RequestBody Address address){
        return addressService.updateAddress(id,address);
    }
    @GetMapping("/fetchAddress/Employee")
    public List<AddressDto> fetchAddressByEmp(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        long empId=jwtUtils.extractEmployeeId(token);
        return addressService.fetchAddressByEmpId(empId);
    }
    @GetMapping("/fetchAddress/Address/{id}")
    public Address fetchAddress(@PathVariable long id){
        return addressService.fetchAddressById(id);
    }
    @DeleteMapping("/deleteAddress/{id}")
    public String deleteAddress(@PathVariable long id){
        addressService.deleteAddress(id);
        return "Address Deleted Successfully";
    }
}
