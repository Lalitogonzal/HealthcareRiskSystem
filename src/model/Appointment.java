package model;

import java.time.LocalDate;

public class Appointment {

    private String id;
    private Patient patient;
    private LocalDate date;
    private boolean attended;

    public Appointment(String id, Patient patient, LocalDate date) {
        this.id = id;
        this.patient = patient;
        this.date = date;
        this.attended = true; // default: attended
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
        if (!attended) {
            patient.incrementMissedAppointments(); // auto-update patient
        }
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id='" + id + '\'' +
                ", patient=" + patient.getName() +
                ", date=" + date +
                ", attended=" + attended +
                '}';
    }
}
