package demo;

import model.Patient;
import service.RiskAssessmentService;

public class ConsoleDemo {

    public static void main(String[] args) {

        RiskAssessmentService service = new RiskAssessmentService();

        Patient p1 = new Patient("P001", "John Doe", 72);
        p1.addChronicCondition("Diabetes");
        p1.incrementMissedAppointments();
        p1.incrementMissedAppointments();

        Patient p2 = new Patient("P002", "Maria Lopez", 45);
        p2.addChronicCondition("Asthma");

        Patient p3 = new Patient("P003", "Alex Chen", 29);

        System.out.println(service.assessRisk(p1));
        System.out.println("---------------------");
        System.out.println(service.assessRisk(p2));
        System.out.println("---------------------");
        System.out.println(service.assessRisk(p3));
    }
}
