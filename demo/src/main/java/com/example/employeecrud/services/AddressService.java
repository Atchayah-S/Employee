package com.example.employeecrud.services;

import com.example.employeecrud.dao.Address;
import com.example.employeecrud.dto.AddressDto;

import java.util.List;

public interface AddressService {
    public List<AddressDto> fetchAddressByEmpId(Long id);
    public Address fetchAddressById(Long id);
    public AddressDto updateAddress(Long id,Address updatedAddress);
    public void deleteAddress(Long id);
    public AddressDto addAddress(Long empId,Address address);

}
