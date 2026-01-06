package service;

import model.Appointment;
import model.Patient;

import java.util.ArrayList;
import java.util.List;

public class AppointmentService {

    private List<Appointment> appointments;

    public AppointmentService() {
        this.appointments = new ArrayList<>();
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public List<Appointment> getAppointmentsByPatient(Patient patient) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments) {
            if (a.getPatient().equals(patient)) {
                result.add(a);
            }
        }
        return result;
    }

    public List<Appointment> getAllAppointments() {
        return appointments;
    }
}
