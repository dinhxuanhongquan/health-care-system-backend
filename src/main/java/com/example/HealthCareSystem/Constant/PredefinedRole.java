package com.example.HealthCareSystem.Constant;

import java.util.Set;

public class PredefinedRole {
    public static final String ROlE_DOCTOR = "DOCTOR";
    public static final String ROLE_PATIENT = "PATIENT";
    public static final String ROLE_ADMIN = "ADMIN";

    private PredefinedRole() {
        // Private constructor to prevent instantiation
    }
    public static Set<String> getAllRoles(){
        return Set.of(ROlE_DOCTOR, ROLE_PATIENT, ROLE_ADMIN);
    }
}
