package com.example.HealthCareSystem.Mapper;

import com.example.HealthCareSystem.Dto.Request.MedicalRecordsCreateReq;
import com.example.HealthCareSystem.Dto.Request.MedicalRecordsUpdateReq;
import com.example.HealthCareSystem.Dto.Response.MedicalRecordsRes;
import com.example.HealthCareSystem.Entity.MedicalRecords;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {PatientMapper.class, AppointmentMapper.class, DoctorMapper.class})
public interface MedicalRecordsMapper {
    MedicalRecords toMedicalRecords(MedicalRecordsCreateReq req);

    MedicalRecordsRes toMedicalRecordsRes(MedicalRecords medicalRecords);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateMedicalRecords(@MappingTarget MedicalRecords medicalRecords, MedicalRecordsUpdateReq req);
}
