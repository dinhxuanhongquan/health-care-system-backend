package com.example.HealthCareSystem.Mapper;

import com.example.HealthCareSystem.Dto.Request.MedicalProductCreateReq;
import com.example.HealthCareSystem.Dto.Request.MedicalProductUpdateReq;
import com.example.HealthCareSystem.Dto.Response.MedicalProductRes;
import com.example.HealthCareSystem.Entity.MedicalProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {CategoryProductMapper.class})
public interface MedicalProductMapper {
    MedicalProduct toMedicalProduct(MedicalProductCreateReq req);

    MedicalProductRes toMedicalProductRes(MedicalProduct medicalProduct);

    @Mapping(target = "createdAt", ignore = true)
    void updateMedicalProduct(@MappingTarget MedicalProduct medicalProduct, MedicalProductUpdateReq req);
}
