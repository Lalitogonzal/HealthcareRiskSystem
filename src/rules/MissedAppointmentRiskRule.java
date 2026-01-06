package rules;

import model.Patient;

public class MissedAppointmentRiskRule implements RiskRule {
	
	@Override
	public int apply(Patient patient) {
		int missed = patient.getMissedAppointments();
		return missed * 2;
	}
	
	@Override
	public String getExplanation(Patient patient) {
		int missed = patient.getMissedAppointments();
		if (missed > 0) {
			return missed + " missed appointment (+" + (missed * 2) + ")";
		}
		return "";
	}
}
