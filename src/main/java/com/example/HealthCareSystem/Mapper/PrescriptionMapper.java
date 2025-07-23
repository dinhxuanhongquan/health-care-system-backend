package com.example.HealthCareSystem.Mapper;

import com.example.HealthCareSystem.Dto.Request.PrescriptionCreateReq;
import com.example.HealthCareSystem.Dto.Request.PrescriptionUpdateReq;
import com.example.HealthCareSystem.Dto.Response.PrescriptionRes;
import com.example.HealthCareSystem.Entity.Prescriptions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {PatientMapper.class, MedicalRecordsMapper.class, DoctorMapper.class})
public interface PrescriptionMapper {
    Prescriptions toPrescription(PrescriptionCreateReq request);

    PrescriptionRes toPrescriptionRes(Prescriptions prescription);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updatePrescription(@MappingTarget Prescriptions prescription, PrescriptionUpdateReq request);

}
