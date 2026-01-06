package app;

import model.Appointment;
import model.Patient;
import model.RiskProfile;
import service.AppointmentService;
import service.PatientService;
import service.RiskAssessmentService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/** Eduardo Gonzalez
 * 	01/04/2026n
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Services
        PatientService patientService = new PatientService();
        AppointmentService appointmentService = new AppointmentService();
        RiskAssessmentService riskService = new RiskAssessmentService();

        // --- Sample Patients & Appointments ---
        Patient p1 = new Patient("P001", "John Doe", 68);
        p1.addChronicCondition("Diabetes");
        p1.addChronicCondition("Hypertension");

        Patient p2 = new Patient("P002", "Jane Smith", 52);
        p2.addChronicCondition("Asthma");

        patientService.addPatient(p1);
        patientService.addPatient(p2);

        Appointment a1 = new Appointment("A001", p1, LocalDate.of(2025, 12, 1));
        Appointment a2 = new Appointment("A002", p1, LocalDate.of(2025, 12, 15));
        Appointment a3 = new Appointment("A003", p2, LocalDate.of(2025, 12, 10));

        a2.setAttended(false); // missed

        appointmentService.addAppointment(a1);
        appointmentService.addAppointment(a2);
        appointmentService.addAppointment(a3);

        // --- Optional: Add new patient via console ---
        System.out.print("Add a new patient? (y/n): ");
        String addPatient = scanner.nextLine();
        if (addPatient.equalsIgnoreCase("y")) {
            System.out.print("Enter patient ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter patient name: ");
            String name = scanner.nextLine();
            System.out.print("Enter patient age: ");
            int age = Integer.parseInt(scanner.nextLine());

            Patient newPatient = new Patient(id, name, age);
            System.out.print("How many chronic conditions? ");
            int cc = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < cc; i++) {
                System.out.print("Condition " + (i + 1) + ": ");
                newPatient.addChronicCondition(scanner.nextLine());
            }
            patientService.addPatient(newPatient);

            // Add one appointment
            System.out.print("Enter appointment ID: ");
            String apptId = scanner.nextLine();
            System.out.print("Enter appointment date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            Appointment newAppt = new Appointment(apptId, newPatient, date);
            System.out.print("Did the patient attend? (y/n): ");
            String attended = scanner.nextLine();
            if (attended.equalsIgnoreCase("n")) newAppt.setAttended(false);
            appointmentService.addAppointment(newAppt);
        }

        // --- Assess risk for all patients ---
        List<Patient> allPatients = patientService.getAllPatients();

        // Sort patients by risk descending
        allPatients.forEach(riskService::assessRisk);

        allPatients.sort((pA, pB) ->
                Integer.compare(
                        pB.getRiskProfile().getRiskScore(),
                        pA.getRiskProfile().getRiskScore()
                )
        );


        // --- Print polished console dashboard ---
        
        System.out.println("\n=== PATIENT RISK DASHBOARD ===");
        for (Patient patient : allPatients) {
            RiskProfile profile = riskService.assessRisk(patient);

            // Highlight HIGH or CRITICAL
            String alert = "";
            if (profile.getRiskLevel().name().equals("HIGH") || profile.getRiskLevel().name().equals("CRITICAL")) {
                alert = " !!! HIGH RISK !!!";
            }

            System.out.println("Patient: " + patient.getName() + " (ID: " + patient.getId() + ")" + alert);
            System.out.println("Age: " + patient.getAge());
            System.out.println("Chronic Conditions: " + patient.getChronicConditions());
            System.out.println("Appointments:");
            List<Appointment> patientAppts = appointmentService.getAppointmentsByPatient(patient);
            if (patientAppts.isEmpty()) {
                System.out.println("  No appointments scheduled.");
            } else {
                for (Appointment appt : patientAppts) {
                    System.out.println("  - " + appt.getDate() + " | Attended: " + appt.isAttended());
                }
            }
            System.out.println("Risk Score: " + profile.getRiskScore());
            System.out.println("Risk Level: " + profile.getRiskLevel());
            System.out.println("Explanation: " + profile.getExplanation());
            System.out.println("Suggested Actions: " + profile.getSuggestedAction());
            System.out.println("-------------------------------");
        }

        scanner.close();
    }
}
