package com.example.employeecrud.controllers;

import com.example.employeecrud.dao.Address;
import com.example.employeecrud.dto.AddressDto;
import com.example.employeecrud.services.AddressService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("/api/Address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @PostMapping("/addAddress/employee/{empId}")
    public AddressDto addAddress(@PathVariable long empId,@RequestBody Address address){
        return addressService.addAddress(empId,address);
    }
    @PutMapping("/updateAddress/{id}")
    public AddressDto updateAddress(@PathVariable long id,@RequestBody Address address){
        return addressService.updateAddress(id,address);
    }
    @GetMapping("/fetchAddress/Employee/{empId}")
    public List<AddressDto> fetchAddressByEmp(@PathVariable long empId){
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
