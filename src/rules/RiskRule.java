package rules;

import model.Patient;

public interface RiskRule {
	
	int apply(Patient patient);
	
	String getExplanation(Patient patient);
}
