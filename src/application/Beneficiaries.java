package application;

public class Beneficiaries {
	//
	private String beneficiaryName;
	private String beneficiaryAccountNumber;
	private String beneficiaryBank;
	// beneficiary Constructor
	public Beneficiaries(String beneficiaryName, String beneficiaryAccountNumber, String beneficiaryBank) {
		//
		this.setBeneficiaryName(beneficiaryName);
		this.setBeneficiaryAccountNumber(beneficiaryAccountNumber);
		this.setBeneficiaryBank(beneficiaryBank);
		//
	}
	//
	/*
	 * Setters and getters
	 * */
	// setter and getters for beneficiary name
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	// setter and getters for beneficiary account number
	public String getBeneficiaryAccountNumber() {
		return beneficiaryAccountNumber;
	}
	public void setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
	}
	// setter and getters for beneficiary bank
	public String getBeneficiaryBank() {
		return beneficiaryBank;
	}
	public void setBeneficiaryBank(String beneficiaryBank) {
		this.beneficiaryBank = beneficiaryBank;
	}
}
