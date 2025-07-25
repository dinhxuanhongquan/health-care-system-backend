package com.example.HealthCareSystem.Service;

import com.example.HealthCareSystem.Dto.Request.DoctorCreateReq;
import com.example.HealthCareSystem.Dto.Request.DoctorUpdateReq;
import com.example.HealthCareSystem.Dto.Response.DoctorRes;
import com.example.HealthCareSystem.Entity.Doctor;
import com.example.HealthCareSystem.Entity.User;
import com.example.HealthCareSystem.Mapper.DoctorMapper;
import com.example.HealthCareSystem.Repository.DoctorRepository;
import com.example.HealthCareSystem.Repository.UserRepository;
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
public class DoctorService {
    DoctorMapper doctorMapper;
    DoctorRepository doctorRepository;
    UserRepository userRepository;

    @Transactional
    public DoctorRes create(DoctorCreateReq req){
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Doctor doctor = doctorMapper.toDoctor(req);
        doctor.setUser(user);
        doctor.setCreatedAt(LocalDateTime.now());
        doctor.setUpdatedAt(LocalDateTime.now());

        Doctor saved = doctorRepository.save(doctor);
        return doctorMapper.toDoctorRes(saved);
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ADMIN')")
    public List<DoctorRes> getAll(){
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream()
                .map(doctorMapper::toDoctorRes)
                .toList();
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ADMIN')")
    public DoctorRes getById(String doctorId){
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return doctorMapper.toDoctorRes(doctor);
    }

    @Transactional
    public DoctorRes update(String doctorId, DoctorUpdateReq req){
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctorMapper.updateDoctor(doctor, req);
        doctor.setUpdatedAt(LocalDateTime.now());
        Doctor updatedDoctor = doctorRepository.save(doctor);
        return doctorMapper.toDoctorRes(updatedDoctor);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(String doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        doctorRepository.delete(doctor);
    }
}
