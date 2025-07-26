package com.example.employeecrud.services;

import com.example.employeecrud.dao.*;
import com.example.employeecrud.dto.EmployeeDto;
import com.example.employeecrud.exceptions.InvalidDataException;
import com.example.employeecrud.exceptions.ResourceNotFoundException;
import com.example.employeecrud.mapper.EmployeeMapper;
import com.example.employeecrud.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.employeecrud.utitlity.DataValidation.*;
import static com.example.employeecrud.mapper.EmployeeMapper.EmployeesToEmployeeDto;

@Service
public class EmpServiceImplementation implements EmpService{
    @Autowired
    private EmployeesRepo emprepo;
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private EmpProfileService empProfileService;
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private SalaryInfoService salaryInfoService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public EmployeeDto CreateEmployee(Employees employee) {
        Department dept = null;

        if (employee.getDepartment() != null && employee.getDepartment().getDeptID() != 0) {
            dept = departmentRepo.findById(employee.getDepartment().getDeptID())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Department does not exist with id: " + employee.getDepartment().getDeptID()));
        }

        employee.setDepartment(dept);
        String error = validateData(employee, emprepo, null);
        if (!error.isEmpty()) {
            throw new InvalidDataException(error);
        }
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));

        if(employee.getAddressList()!=null){
            for(Address address:employee.getAddressList()){
                address.setEmployee(employee);
            }
        }
        Employees emp = emprepo.save(employee);
        if(employee.getEmployeeProfile()!=null){
        empProfileService.saveProfile(emp.getEmp_id(), employee.getEmployeeProfile());}
        if(employee.getSalaryInfo()!=null){
            salaryInfoService.addSalaryInfo(emp.getEmp_id(),employee.getSalaryInfo());
        }

        return EmployeesToEmployeeDto(emp);
    }


    public List<EmployeeDto> addAllEmployees(List<Employees> employeesList){
        ArrayList<String> Errors=new ArrayList<>();
        for(int i=0;i<employeesList.size();i++){
            String error= validateData(employeesList.get(i),emprepo,null);
            if(!error.isEmpty())
                Errors.add("Error found in record: "+(i+1)+"\n"+error+"\n");
            else{
                Department dept=null;
                long id=employeesList.get(i).getDepartment().getDeptID();
                if (employeesList.get(i).getDepartment() != null && id != 0) {
                    dept = departmentRepo.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Department does not exist with id: " + id));
                }

                employeesList.get(i).setDepartment(dept);
            }
        }
        if(!Errors.isEmpty()){
            throw new InvalidDataException(Errors.toString());
        }
        for(Employees employee: employeesList) {
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            if (!employee.getAddressList().isEmpty()) {
                for (Address address : employee.getAddressList()) {
                    address.setEmployee(employee);
                }
                }
        }
        List<Employees> savedList=emprepo.saveAll(employeesList);
        for (Employees employees : savedList) {
            if (employees.getEmployeeProfile() != null) {
                empProfileService.saveProfile(employees.getEmp_id(), employees.getEmployeeProfile());
            }
            if(employees.getSalaryInfo()!=null){
                salaryInfoService.addSalaryInfo(employees.getEmp_id(),employees.getSalaryInfo());
            }
        }
        return EmployeeMapper.EmployeesToEmployeeDtoList(employeesList);
    }

    public EmployeeDto FetchById(Long id){
        Employees emp=emprepo.findById(id).orElse(null);
        if(emp==null){
            throw new ResourceNotFoundException("No Employee with id:"+id+" found");
        }
        return EmployeesToEmployeeDto(emp);
    }

    public EmployeeDto updateEmployee(Long id, Employees updatedData){
        Employees existingData=emprepo.findById(id).orElse(null);
        if(existingData!=null){
            existingData.setName(updatedData.getName());
            existingData.setEmail(updatedData.getEmail());
            existingData.setPhone(updatedData.getPhone());
            existingData.setPassword(passwordEncoder.encode(updatedData.getPassword()));
            if(updatedData.getDepartment()!=null && updatedData.getDepartment().getDeptID()!=0){
                Department dept=departmentRepo.findById(updatedData.getDepartment().getDeptID())
                        .orElseThrow(()->new ResourceNotFoundException("Department not exists with id: "));
            existingData.setDepartment(dept);
            }
            String error= validateData(existingData,emprepo,String.valueOf(existingData.getEmp_id()));
            if(!error.isEmpty())
                throw new InvalidDataException(error);
            if(updatedData.getAddressList()!=null){
            List<Address> addressList=updatedData.getAddressList();
            for(Address updatedAddress:addressList){
                Address existingAddress=addressRepo.findById(updatedAddress.getId()).orElse(null);
                if(existingAddress==null){
                    updatedAddress.setEmployee(existingData);
                    existingData.getAddressList().add(updatedAddress);}
                else{
                    existingAddress.setStreetName(updatedAddress.getStreetName());
                    existingAddress.setDoorNo(updatedAddress.getDoorNo());
                    existingAddress.setCity(updatedAddress.getCity());
                    existingAddress.setZipCode(updatedAddress.getZipCode());
                }
            }}
            if (updatedData.getEmployeeProfile()!=null){
                empProfileService.updateEmployeeProfile(existingData.getEmployeeProfile().getProfileId(), updatedData.getEmployeeProfile());
            }
            if(updatedData.getSalaryInfo()!=null){
                salaryInfoService.updateSalaryInfo(id,updatedData.getSalaryInfo());
            }
            emprepo.save(existingData);
        }
        if(existingData==null)
            throw new ResourceNotFoundException("Employee with id: "+id+" not found");
        return EmployeesToEmployeeDto(existingData);
    }


    public String deleteEmployee(Long id){
        Employees emp=emprepo.findById(id).orElse(null);
        if(emp==null){
            throw new ResourceNotFoundException("No Employee with id:"+id+" found");
        }
        emprepo.deleteById(id);
        return "Employee deleted successfully";
    }

    @Override
    public List<EmployeeDto> fetchAllEmployee() {
        List<Employees> employees = emprepo.findAll();
        return EmployeeMapper.EmployeesToEmployeeDtoList(employees);
        }
    @Override
    public EmployeeDto assignProject(Long empId,Long projId){
        Employees employee=emprepo.findById(empId).
                orElseThrow(()->new ResourceNotFoundException("Employee with id: "+empId+" not found"));
        Project project=projectRepo.findById(projId).orElseThrow(()->new ResourceNotFoundException("Project with id: "+projId+" not found"));
        employee.getProjects().add(project);
        project.getEmployeesList().add(employee);
        projectRepo.save(project);
        emprepo.save(employee);
        return EmployeeMapper.EmployeesToEmployeeDto(employee);
    }
}
