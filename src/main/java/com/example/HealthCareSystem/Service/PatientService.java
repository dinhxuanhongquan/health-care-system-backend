package com.example.HealthCareSystem.Service;

import com.example.HealthCareSystem.Dto.Request.PatientCreateReq;
import com.example.HealthCareSystem.Dto.Request.PatientUpdateReq;
import com.example.HealthCareSystem.Dto.Response.PatientRes;
import com.example.HealthCareSystem.Entity.Patient;
import com.example.HealthCareSystem.Entity.User;
import com.example.HealthCareSystem.Mapper.PatientMapper;
import com.example.HealthCareSystem.Repository.PatientRepository;
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
public class PatientService {
    PatientRepository patientRepository;
    PatientMapper patientMapper;
    UserRepository userRepository;

    @Transactional
    public PatientRes create(PatientCreateReq req){
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Patient patient = patientMapper.toPatient(req);
        patient.setUser(user);
        patient.setCreatedAt(LocalDateTime.now());
        patient.setUpdatedAt(LocalDateTime.now());
        return patientMapper.toPatientRes(patientRepository.save(patient));
    }

    @Transactional(readOnly = true)
    public PatientRes getById(String patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return patientMapper.toPatientRes(patient);
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ADMIN')")
    public List<PatientRes> getAll(){
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(patientMapper::toPatientRes)
                .toList();
    }

    @Transactional
    public PatientRes update(String patientId, PatientUpdateReq req){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patientMapper.updatePatient(patient, req);
        patient.setUpdatedAt(LocalDateTime.now());
        return patientMapper.toPatientRes(patientRepository.save(patient));
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(String patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        patientRepository.delete(patient);
        log.info("Patient with ID {} deleted successfully", patientId);
    }
}
