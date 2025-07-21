package com.example.employeecrud.repository;

import com.example.employeecrud.dao.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address,Long> {

}
