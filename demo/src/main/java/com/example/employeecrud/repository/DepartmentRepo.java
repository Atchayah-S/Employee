package com.example.employeecrud.repository;

import com.example.employeecrud.dao.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface DepartmentRepo extends JpaRepository<Department,Long> {
    boolean existsByDeptName(String deptName);
}
