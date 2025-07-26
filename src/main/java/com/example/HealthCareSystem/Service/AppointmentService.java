package com.example.HealthCareSystem.Service;

import com.example.HealthCareSystem.Dto.Request.AppointmentCreateReq;
import com.example.HealthCareSystem.Dto.Request.AppointmentUpdateReq;
import com.example.HealthCareSystem.Dto.Response.AppointmentRes;
import com.example.HealthCareSystem.Entity.Appointment;
import com.example.HealthCareSystem.Entity.Doctor;
import com.example.HealthCareSystem.Entity.Patient;
import com.example.HealthCareSystem.Mapper.AppointmentMapper;
import com.example.HealthCareSystem.Repository.AppointmentRepository;
import com.example.HealthCareSystem.Repository.DoctorRepository;
import com.example.HealthCareSystem.Repository.PatientRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppointmentService {
    AppointmentRepository appointmentRepository;
    AppointmentMapper appointmentMapper;
    PatientRepository patientRepository;
    DoctorRepository doctorRepository;

    @Transactional
    public AppointmentRes create(AppointmentCreateReq req){
        Doctor doctor = doctorRepository.findById(req.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        Patient patient = patientRepository.findById(req.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Appointment appointment = appointmentMapper.toAppointment(req);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setUpdatedAt(LocalDateTime.now());
        appointment.setCompletedAt(LocalDateTime.now());

        Appointment saved = appointmentRepository.save(appointment);
        return appointmentMapper.toAppointmentRes(saved);
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ADMIN')")
    public List<AppointmentRes> getAll() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(appointmentMapper::toAppointmentRes)
                .toList();
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public AppointmentRes getById(String appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        return appointmentMapper.toAppointmentRes(appointment);
    }

    @Transactional
    public AppointmentRes update(String appointmentId, AppointmentUpdateReq req) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointmentMapper.updateAppointment(appointment, req);
        appointment.setUpdatedAt(LocalDateTime.now());

        Appointment updated = appointmentRepository.save(appointment);
        return appointmentMapper.toAppointmentRes(updated);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(String appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointmentRepository.delete(appointment);
    }

}
