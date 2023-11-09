package application;
/*
 * This class contains bank Account parent class
 * */
public abstract class BankAccounts {
	//
	String userName;
	String userSName;
	String userPhone;
	//
	public BankAccounts(String userName, String userSName, String userPhone)
	{
		this.userName = userName;
		this.userSName = userSName;
		this.userPhone = userPhone;
	}
	// getter for user name inherited for parent class
	
		public String getUserName() {
			return userName;
		}
		// getter for user surname inherited from parent class
		
		public String getUserSName() {
			// 
			return userSName;
		}
		
		public String getUserPhone() {
			//
			return userPhone;
		}
		//
		public abstract String getAccountType();
}
