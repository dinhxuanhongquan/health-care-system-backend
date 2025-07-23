package com.example.HealthCareSystem.Mapper;

import com.example.HealthCareSystem.Dto.Request.PatientCreateReq;
import com.example.HealthCareSystem.Dto.Request.PatientUpdateReq;
import com.example.HealthCareSystem.Dto.Response.PatientRes;
import com.example.HealthCareSystem.Entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface PatientMapper {
    Patient toPatient(PatientCreateReq req);

    PatientRes toPatientRes(Patient patient);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updatePatient(@MappingTarget Patient patient, PatientUpdateReq req);
}
