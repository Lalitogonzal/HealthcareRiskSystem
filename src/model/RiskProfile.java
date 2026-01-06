package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RiskProfile {

    private Patient patient;

    // Version 1 fields (kept)
    private int riskScore;
    private RiskLevel riskLevel;
    private String explanation;
    private String suggestedAction;

    // Version 2 fields (new)
    private List<Integer> scoreHistory = new ArrayList<>();
    private LocalDate lastEvaluated;

    public RiskProfile(Patient patient) {
        this.patient = patient;
    }

    // ===== Version 2 Core Logic =====

    public void updateRisk(int newScore, RiskLevel level, String explanation, String action) {
        this.riskScore = newScore;
        this.riskLevel = level;
        this.explanation = explanation;
        this.suggestedAction = action;
        this.lastEvaluated = LocalDate.now();
        this.scoreHistory.add(newScore);
    }

    public int getRiskTrend() {
        if (scoreHistory.size() < 2) return 0;
        return scoreHistory.get(scoreHistory.size() - 1)
             - scoreHistory.get(scoreHistory.size() - 2);
    }

    // ===== Getters =====

    public Patient getPatient() {
        return patient;
    }

    public int getRiskScore() {
        return riskScore;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getSuggestedAction() {
        return suggestedAction;
    }

    public List<Integer> getScoreHistory() {
        return scoreHistory;
    }

    public LocalDate getLastEvaluated() {
        return lastEvaluated;
    }

    @Override
    public String toString() {
        return "RiskProfile for " + patient.getName() +
                "\nRisk Score: " + riskScore +
                "\nRisk Level: " + riskLevel +
                "\nRisk Trend: " + getRiskTrend() +
                "\nExplanation:\n" + explanation +
                "\nSuggested Action: " + suggestedAction;
    }
}