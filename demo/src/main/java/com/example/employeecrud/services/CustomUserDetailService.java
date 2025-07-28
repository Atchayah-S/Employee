package com.example.employeecrud.services;

import com.example.employeecrud.dao.Employees;
import com.example.employeecrud.exceptions.ResourceNotFoundException;
import com.example.employeecrud.repository.EmployeesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
@Component

public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private EmployeesRepo employeesRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employees employee=employeesRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User: "+username+" not found"));
        return new User(employee.getEmail(),employee.getPassword(), Collections.singleton(new SimpleGrantedAuthority("USERROLE")));
    }
}