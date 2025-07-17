package com.example.employeecrud.mapper;

import com.example.employeecrud.dao.EmployeeProfile;
import com.example.employeecrud.dao.Employees;
import com.example.employeecrud.dto.EmployeeDto;
import com.example.employeecrud.dto.ProfileDto;

import java.util.List;

public class ProfileMapper {
    public static ProfileDto toProfileDto(EmployeeProfile profile){
        ProfileDto profileDto=new ProfileDto();
        if(profile!=null){
        profileDto.setProfileId(profile.getProfileId());
        profileDto.setJoinDate(profile.getJoinDate());
        profileDto.setRole(profile.getRole());
        profileDto.setWorkLocationType(profile.getWorkLocationType());
        profileDto.setEmpId(profile.getEmployee().getEmp_id());
            }
        return profileDto;
    }
    public static List<ProfileDto> toProfileDtoList(List<EmployeeProfile> profilesList) {
        return profilesList.stream()
                .map(ProfileMapper::toProfileDto)
                .toList();
    }
}
