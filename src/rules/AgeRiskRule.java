package rules;

import model.Patient;

public class AgeRiskRule implements RiskRule {
	
	@Override
	public int apply(Patient patient) {
		if (patient.getAge() >= 65) {
			return 3;
		}
		return 0;
	}
	
	@Override
	public String getExplanation(Patient patient) {
		if (patient.getAge() >= 65) {
		    return "Patient is 65 or older (+3)";
		}
		return "";
	}
}
