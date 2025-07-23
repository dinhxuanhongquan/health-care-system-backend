package com.example.HealthCareSystem.Mapper;

import com.example.HealthCareSystem.Dto.Request.HealthMetricsCreateReq;
import com.example.HealthCareSystem.Dto.Request.HealthMetricsUpdateReq;
import com.example.HealthCareSystem.Dto.Response.HealthMetricsRes;
import com.example.HealthCareSystem.Entity.HealthMetrics;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {PatientMapper.class})
public interface HealthMetricsMapper {
    HealthMetrics toHealthMetrics(HealthMetricsCreateReq req);

    HealthMetricsRes toHealthMetricsRes(HealthMetrics healthMetrics);

    void updateHealthMetrics(@MappingTarget HealthMetrics healthMetrics, HealthMetricsUpdateReq req);
}
