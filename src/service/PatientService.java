package service;

import model.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientService {

    private List<Patient> patients;

    public PatientService() {
        this.patients = new ArrayList<>();
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public Patient getPatientById(String id) {
        for (Patient p : patients) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public List<Patient> getAllPatients() {
        return patients;
    }
    
}
