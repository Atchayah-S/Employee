package com.example.employeecrud.repository;

import com.example.employeecrud.dao.SalaryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryInfoRepo extends JpaRepository<SalaryInfo,Long> {
}
