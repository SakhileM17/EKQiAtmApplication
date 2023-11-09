package bankAccounts;

public abstract class TransactionsHistory {
	//
	String transactionDate;
	String transactionDescription;
	double transactionAmount;
	String transactionType;
	String transactionID;
	//
	public TransactionsHistory(String transactionDate, String transactionDescription, double transactionAmount,
	String transactionID){
		//
		this.setTransactionDate(transactionDate);
		this.setTransactionDescription(transactionDescription);;
		this.setTransactionAmount(transactionAmount);
		this.setTransactionID(transactionID);
	}
	//
	public String getTransactionDate() {
		
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		//
		this.transactionDate = transactionDate;
	}
	//
	public String getTransactionDescription() {
		//
		return transactionDescription;
	}
	public void setTransactionDescription(String transactionDescription) {
		//
		this.transactionDescription = transactionDescription;
	}
	//
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	//
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	//
	public abstract String getTransactionType();
	//
	public abstract void setTransactionType(String transactionType);
	//
}
