package model;

import java.util.ArrayList;
import java.util.List;


public class Patient {

    private String id;
    private String name;
    private int age;
    private List<String> chronicConditions;
    private int missedAppointments;
    private RiskProfile riskProfile;


    public Patient(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.chronicConditions = new ArrayList<>();
        this.missedAppointments = 0;
        this.riskProfile = new RiskProfile(this);
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<String> getChronicConditions() {
        return chronicConditions;
    }

    public int getMissedAppointments() {
        return missedAppointments;
    }
    
    public RiskProfile getRiskProfile() {
        return riskProfile;
    }


    // Behavior
    public void addChronicCondition(String condition) {
        chronicConditions.add(condition);
    }

    public void incrementMissedAppointments() {
        missedAppointments++;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", chronicConditions=" + chronicConditions +
                ", missedAppointments=" + missedAppointments +
                '}';
    }
}
