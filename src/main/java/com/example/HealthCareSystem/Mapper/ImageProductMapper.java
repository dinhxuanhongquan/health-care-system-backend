package com.example.HealthCareSystem.Mapper;

import com.example.HealthCareSystem.Dto.Request.ImageProductCreateReq;
import com.example.HealthCareSystem.Dto.Request.ImageProductUpdateReq;
import com.example.HealthCareSystem.Dto.Response.ImageProductRes;
import com.example.HealthCareSystem.Entity.ImageProduct;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {MedicalProductMapper.class})
public interface ImageProductMapper {
    ImageProduct toImageProduct(ImageProductCreateReq req);

    ImageProductRes toImageProductRes(ImageProduct imageProduct);

    void updateImageProduct(@MappingTarget ImageProduct imageProduct, ImageProductUpdateReq req);
}
