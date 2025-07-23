package com.example.HealthCareSystem.Mapper;

import com.example.HealthCareSystem.Dto.Request.AppointmentCreateReq;
import com.example.HealthCareSystem.Dto.Request.AppointmentUpdateReq;
import com.example.HealthCareSystem.Dto.Response.AppointmentRes;
import com.example.HealthCareSystem.Entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {PatientMapper.class, DoctorMapper.class})
public interface AppointmentMapper {
    Appointment toAppointment(AppointmentCreateReq req);

    AppointmentRes toAppointmentRes(Appointment appointment);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateAppointment(@MappingTarget Appointment appointment, AppointmentUpdateReq req);
}
