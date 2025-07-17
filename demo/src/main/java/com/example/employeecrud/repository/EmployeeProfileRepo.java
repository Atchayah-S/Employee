package com.example.employeecrud.repository;

import com.example.employeecrud.dao.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeProfileRepo extends JpaRepository<EmployeeProfile,Long> {

}
