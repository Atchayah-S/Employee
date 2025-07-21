package com.example.employeecrud.services;

import com.example.employeecrud.dao.Address;
import com.example.employeecrud.dao.Employees;
import com.example.employeecrud.dto.AddressDto;
import com.example.employeecrud.exceptions.EmployeeNotFoundException;
import com.example.employeecrud.exceptions.ResourceNotFoundException;
import com.example.employeecrud.mapper.AddressMapper;
import com.example.employeecrud.repository.AddressRepo;
import com.example.employeecrud.repository.EmployeesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImplementation implements AddressService{
    @Autowired
    private EmployeesRepo employeesRepo;
    @Autowired
    private AddressRepo addressRepo;
    @Override
    public List<AddressDto> fetchAddressByEmpId(Long empId) {
        Employees employee=employeesRepo.findById(empId).
                orElseThrow(()->new EmployeeNotFoundException("no employee found with id "+empId));
        List<Address> address=employee.getAddressList();
        return AddressMapper.toAddressDtoList(address);
    }

    @Override
    public Address fetchAddressById(Long id) {
        return addressRepo.findById(id).
                orElseThrow(()->new ResourceNotFoundException("No Address found with id "+id));
    }

    @Override
    public AddressDto updateAddress(Long id, Address updatedAddress) {
        Address existingAddress=addressRepo.findById(id).
                orElseThrow(()->new ResourceNotFoundException("No Address found with id "+id));
        existingAddress.setDoorNo(updatedAddress.getDoorNo());
        existingAddress.setStreetName(updatedAddress.getStreetName());
        existingAddress.setCity(updatedAddress.getCity());
        existingAddress.setZipCode(updatedAddress.getZipCode());
        return AddressMapper.toAddressDto(addressRepo.save(existingAddress));
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("No Address found with id "+id));
        addressRepo.deleteById(id);
    }

    @Override
    public AddressDto addAddress(Long empId,Address address) {
        Employees employee=employeesRepo.findById(empId).
                orElseThrow(()->new EmployeeNotFoundException("No employee found with id: "+empId));
        address.setEmployee(employee);
        return AddressMapper.toAddressDto(addressRepo.save(address));
    }
}
