package com.example.HealthCareSystem.Mapper;

import com.example.HealthCareSystem.Dto.Request.DoctorWorkingHoursCreateReq;
import com.example.HealthCareSystem.Dto.Request.DoctorWorkingHoursUpdateReq;
import com.example.HealthCareSystem.Dto.Response.DoctorWorkingHoursRes;
import com.example.HealthCareSystem.Entity.DoctorWorkingHours;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {DoctorMapper.class})
public interface DoctorWorkingHoursMapper {
    DoctorWorkingHours toDoctorWorkingHours(DoctorWorkingHoursCreateReq req);

    DoctorWorkingHoursRes toDoctorWorkingHoursRes(DoctorWorkingHours doctorWorkingHours);

    void updateDoctorWorkingHours(@MappingTarget DoctorWorkingHours doctorWorkingHours, DoctorWorkingHoursUpdateReq req);
}
