package com.example.HealthCareSystem.Mapper;

import com.example.HealthCareSystem.Dto.Request.PrescriptionItemCreateReq;
import com.example.HealthCareSystem.Dto.Request.PrescriptionItemUpdateReq;
import com.example.HealthCareSystem.Dto.Response.PrescriptionItemRes;
import com.example.HealthCareSystem.Entity.PrescriptionItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {PrescriptionMapper.class, MedicalProductMapper.class})
public interface PrescriptionItemMapper {
    PrescriptionItem toPrescriptionItem(PrescriptionItemCreateReq req);
    
    PrescriptionItemRes toPrescriptionItemRes(PrescriptionItem prescriptionItem);
    
    @Mapping(target = "createdAt", ignore = true)
    void updatePrescriptionItem(@MappingTarget PrescriptionItem prescriptionItem, PrescriptionItemUpdateReq req);
}
