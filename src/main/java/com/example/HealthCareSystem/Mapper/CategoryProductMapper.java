package com.example.HealthCareSystem.Mapper;

import com.example.HealthCareSystem.Dto.Request.CategoryProductCreateReq;
import com.example.HealthCareSystem.Dto.Request.CategoryProductUpdateReq;
import com.example.HealthCareSystem.Dto.Response.CategoryProductRes;
import com.example.HealthCareSystem.Entity.CategoryProduct;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryProductMapper {
    CategoryProduct toCategoryProduct(CategoryProductCreateReq req);

    CategoryProductRes toCategoryProductRes(CategoryProduct categoryProduct);

    void updateCategoryProduct(@MappingTarget CategoryProduct categoryProduct, CategoryProductUpdateReq req);
}
