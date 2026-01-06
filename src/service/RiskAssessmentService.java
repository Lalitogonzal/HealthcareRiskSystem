package service;

import model.Patient;

import model.RiskLevel;
import model.RiskProfile;
import rules.*;

import java.util.ArrayList;
import java.util.List;

/*
 * Version 2:
 * - Rule-based risk evaluation
 * - Explainable scoring (no black box)
 * - Easily extensible for ML models later
 */


public class RiskAssessmentService {

    private List<RiskRule> rules = new ArrayList<>();

    public RiskAssessmentService() {
        rules.add(new AgeRiskRule());
        rules.add(new MissedAppointmentRiskRule());
        rules.add(new ChronicConditionRiskRule());
    }

    public RiskProfile assessRisk(Patient patient) {

        int totalScore = 0;
        StringBuilder explanation = new StringBuilder();

        for (RiskRule rule : rules) {
            int ruleScore = rule.apply(patient);
            totalScore += ruleScore;

            String ruleExplanation = rule.getExplanation(patient);
            if (!ruleExplanation.isEmpty()) {
                explanation.append("- ").append(ruleExplanation).append("\n");
            }
        }

        RiskLevel level = determineRiskLevel(totalScore);
        String action = determineSuggestedAction(patient, level);

        RiskProfile profile = patient.getRiskProfile();
        profile.updateRisk(totalScore, level, explanation.toString(), action);

        return profile;
    }

    private RiskLevel determineRiskLevel(int score) {
        if (score < 5) return RiskLevel.LOW;
        if (score < 10) return RiskLevel.MEDIUM;
        if (score < 15) return RiskLevel.HIGH;
        return RiskLevel.CRITICAL;
    }

    private String determineSuggestedAction(Patient patient, RiskLevel level) {
        StringBuilder actions = new StringBuilder();

        switch (level) {
            case LOW -> actions.append("Regular monitoring. ");
            case MEDIUM -> actions.append("Schedule follow-up check. ");
            case HIGH -> actions.append("Immediate follow-up recommended. ");
            case CRITICAL -> actions.append("URGENT clinician intervention required. ");
        }

        if (patient.getMissedAppointments() > 0) {
            actions.append("Send extra reminders. ");
        }

        if (patient.getChronicConditions().size() > 1) {
            actions.append("Flag for clinician review. ");
        }

        if (patient.getAge() >= 65) {
            actions.append("Consider shorter appointment windows. ");
        }

        return actions.toString();
    }
}