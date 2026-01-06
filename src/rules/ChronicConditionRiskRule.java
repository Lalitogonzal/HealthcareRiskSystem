package rules;

import model.Patient;

public class ChronicConditionRiskRule implements RiskRule {
	
	@Override
	public int apply(Patient patient) {
		return patient.getChronicConditions().size() * 3;
	}
	
	@Override
	public String getExplanation(Patient patient) {
		int count = patient.getChronicConditions().size();
		if (count > 0) {
			return count + " chronic conditions (+" + (count * 3) + ")";

		}
		return "";
	}
}
