package application;

public class ChequeTransactionsHistory extends TransactionsHistory{
	//
	String transactionType;
	//
	public ChequeTransactionsHistory(String transactionDate, String transactionDescription, double transactionAmount,
			String transactionID, String transactionType) {
		//
		super (transactionDate, transactionDescription, transactionAmount, transactionID);
		//
		this.setTransactionType(transactionType);;
	}

	@Override
	public String getTransactionType() {
	
		return transactionType;
	}

	@Override
	public void setTransactionType(String transactionType) {
		
		this.transactionType = transactionType;
		
	}
	
}
