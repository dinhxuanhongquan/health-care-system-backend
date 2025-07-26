package com.example.HealthCareSystem.Service;

import com.example.HealthCareSystem.Dto.Request.CategoryProductCreateReq;
import com.example.HealthCareSystem.Dto.Request.CategoryProductUpdateReq;
import com.example.HealthCareSystem.Dto.Response.CategoryProductRes;
import com.example.HealthCareSystem.Entity.CategoryProduct;
import com.example.HealthCareSystem.Mapper.CategoryProductMapper;
import com.example.HealthCareSystem.Repository.CategoryProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {
    CategoryProductRepository repository;
    CategoryProductMapper mapper;

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryProductRes create(CategoryProductCreateReq req){
        return mapper.toCategoryProductRes(
                repository.save(mapper.toCategoryProduct(req))
        );
    }

    @Transactional(readOnly = true)
    public List<CategoryProductRes> getAll() {
        return repository.findAll().stream()
                .map(mapper::toCategoryProductRes)
                .toList();
    }

    @Transactional(readOnly = true)
    public CategoryProductRes getById(String categoryId) {
        CategoryProduct categoryProduct = repository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return mapper.toCategoryProductRes(categoryProduct);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryProductRes update(String categoryId, CategoryProductUpdateReq req) {
        CategoryProduct existingCategory = repository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        mapper.updateCategoryProduct(existingCategory, req);
        return mapper.toCategoryProductRes(repository.save(existingCategory));
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(String categoryId) {
        if (!repository.existsById(categoryId)) {
            throw new RuntimeException("Category not found");
        }
        repository.deleteById(categoryId);
    }
}
