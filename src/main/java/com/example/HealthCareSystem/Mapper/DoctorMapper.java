package com.example.HealthCareSystem.Mapper;

import com.example.HealthCareSystem.Dto.Request.DoctorCreateReq;
import com.example.HealthCareSystem.Dto.Request.DoctorUpdateReq;
import com.example.HealthCareSystem.Dto.Response.DoctorRes;
import com.example.HealthCareSystem.Entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface DoctorMapper {
    Doctor toDoctor(DoctorCreateReq req);

    DoctorRes toDoctorRes(Doctor doctor);

    void updateDoctor(@MappingTarget Doctor doctor, DoctorUpdateReq req);
}
