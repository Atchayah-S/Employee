package com.example.employeecrud.services;

import com.example.employeecrud.dao.Employees;
import com.example.employeecrud.dto.EmployeeDto;
import com.example.employeecrud.exceptions.EmployeeNotFouncException;
import com.example.employeecrud.exceptions.InvalidDataException;
import com.example.employeecrud.mapper.EmployeeMapper;
import com.example.employeecrud.repository.EmployeesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.employeecrud.utitlity.DataValidation.*;
import static com.example.employeecrud.mapper.EmployeeMapper.EmployeesToEmployeeDto;

@Service
public class EmpServiceImplementation implements EmpService{
    @Autowired
    private EmployeesRepo emprepo;
    @Override
    public EmployeeDto CreateEmployee(Employees employee) {
        String error= validateData(employee,emprepo,null);
        if(!error.isEmpty())
            throw new InvalidDataException(error);

        Employees emp=emprepo.save(employee);
        return EmployeesToEmployeeDto(emp);
    }

    public List<EmployeeDto> addAllEmployees(List<Employees> employeesList){
        ArrayList<String> Errors=new ArrayList<>();
        for(int i=0;i<employeesList.size();i++){
            String error= validateData(employeesList.get(i),emprepo,null);
            if(!error.isEmpty())
                Errors.add("Error found in record: "+(i+1)+"\n"+error+"\n");
        }
        if(!Errors.isEmpty()){
            throw new InvalidDataException(Errors.toString());
        }
        emprepo.saveAll(employeesList);
        return EmployeeMapper.EmployeesToEmployeeDtoList(employeesList);
    }

    public EmployeeDto FetchById(Long id){
        Employees emp=emprepo.findById(id).orElse(null);
        if(emp==null){
            throw new EmployeeNotFouncException("No Employee with id:"+id+" found");
        }
        return EmployeesToEmployeeDto(emp);
    }

    public EmployeeDto updateEmployee(Long id,Employees updatedData){
        Employees existingData=emprepo.findById(id).orElse(null);
        if(existingData!=null){
            existingData.setName(updatedData.getName());
            existingData.setEmail(updatedData.getEmail());
            existingData.setPhone(updatedData.getPhone());
            existingData.setPassword(updatedData.getPassword());
            String error= validateData(existingData,emprepo,String.valueOf(existingData.getEmp_id()));
            if(!error.isEmpty())
                throw new InvalidDataException(error);
            emprepo.save(existingData);
        }
        assert existingData != null;
        return EmployeesToEmployeeDto(existingData);
    }

    public String deleteEmployee(Long id){
        Employees emp=emprepo.findById(id).orElse(null);
        if(emp==null){
            throw new EmployeeNotFouncException("No Employee with id:"+id+" found");
        }
        emprepo.deleteById(id);
        return "Employee deleted successfully";
    }

    @Override
    public List<EmployeeDto> fetchAllEmployee() {
        List<Employees> employees = emprepo.findAll();
        return EmployeeMapper.EmployeesToEmployeeDtoList(employees);
        }
}
