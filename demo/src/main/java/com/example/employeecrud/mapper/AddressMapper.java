package com.example.employeecrud.mapper;

import com.example.employeecrud.dao.Address;
import com.example.employeecrud.dto.AddressDto;
import lombok.Data;

import java.util.List;

@Data
public class AddressMapper {
    public static AddressDto toAddressDto(Address address){
        AddressDto addressDto=new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setDoorNo(address.getDoorNo());
        addressDto.setStreetName(address.getStreetName());
        addressDto.setCity(address.getCity());
        addressDto.setZipCode(address.getZipCode());
        return addressDto;
    }
    public static List<AddressDto> toAddressDtoList(List<Address> addressList) {
        return addressList.stream()
                .map(AddressMapper::toAddressDto)
                .toList();
    }

}
