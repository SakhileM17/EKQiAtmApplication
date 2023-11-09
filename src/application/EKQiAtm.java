package application;
//
import javafx.stage.Stage;
//
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
//
import javafx.scene.shape.Circle;

//
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ComboBox;
//
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
//
import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//
import javafx.fxml.Initializable;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
//
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
//
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
//
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//
import java.io.FileInputStream; 
import java.io.FileNotFoundException;

//FXML Controller

/*
 * This project is an ATM (Automated Teller Machine) simulation that emulates the functionality of a real-world
ATM. It provides users with the ability to perform various banking operations such as 
account balance checking, cash withdrawals, deposits, and more
 * 
 * */

/*Key Features 
 * 1. Withdraw funds.
 * 2. Transfer Funds from Cheque Account to Savings Account
 * 3. Pay and Create beneficiairies 
 * 4. Purchase Airtime or play the lottery
 * 5. Transaction History 
 * 
 * By Sakhile Mkhalele 2023
 * */

public class EKQiAtm extends Application implements Initializable {
	/*
	 * Login Details
	 * The login detials of the application is the default cheque account number which is 2023.
	 * If user wants to change login pin you can alter the cheque account number at the cheque account constructor below
	 * */
	
	//chequeAccount(String userName, String userSname, String userPhone,String accountType, int chequeAccountNo, double chequeBalance
	ChequeAccount cheque = new ChequeAccount("Sakhile", "Mkhalele", "0834181424", "Cheque", 2023, 500.66);
	
	// Savings account (String userName, String userSname, String userPhone,String accountType, int savingsAccountNo, double chequeBalance
	SavingsAccount savings = new SavingsAccount("Sakhile", "Mkhalele", "0834181424", "Savings", 1030, 800);
	//
	ArrayList<Beneficiaries> newBeneficiary = new ArrayList<>();
	Beneficiaries beneficiaries = new Beneficiaries(null, null, null);
	//
	DecimalFormat moneyFormat = new DecimalFormat("'R'#,###,###,##0.00");
	//
	LocalDate transactionDate = LocalDate.now();//this line of code used get the current date
	LocalTime transactionTime = LocalTime.now();// this line of code is used to get current time
	DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm:ss"); // this line of code sets the time format 
	//
	@Override
	public void start(Stage primaryStage) {
		try {
			//
			StackPane root = (StackPane)FXMLLoader.load(getClass().getResource("ekqiAtm.fxml"));
			//
			Scene scene = new Scene(root,800,700);
			scene.getStylesheets().add(getClass().getResource("ekqiAtm.css").toExternalForm());
			//
			primaryStage.setTitle("EKQiBank");
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			
			//
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
		/*
		 * 
		 * */
		labelAnchoeMaindate.setText(formattedDate);
		labelAnchoeMainTime.setText(formattedTime);
		
		labelAnchoeMaindate1.setText(formattedDate);
		labelAnchoeMainTime1.setText(formattedTime);
		
		imageEKQILogo.setVisible(true);
		imageEKQILogo1.setVisible(true);
		
		/*
		 * Application index page
		 * */
		
		anchorMainContainer.setVisible(true);
		anchorLogin.setVisible(false);
		anchorIndex.setVisible(true); 
		buttonVirtualCard.setOnAction(indexVirtualCardListener); // Button to enter application
		//
		/*
		 * Bank account Login
		 * */
		
		labelErrorPin.setVisible(false); 
		labelErrorAttempts.setVisible(false);
		
		button0.setOnAction(keyPadListener);
		button1.setOnAction(keyPadListener);
		button2.setOnAction(keyPadListener);
		button3.setOnAction(keyPadListener);
		button4.setOnAction(keyPadListener);
		button5.setOnAction(keyPadListener);
		button6.setOnAction(keyPadListener);
		button7.setOnAction(keyPadListener);
		button8.setOnAction(keyPadListener);
		button9.setOnAction(keyPadListener);
		
		buttonSubmit.setOnAction(buttonSubmitListener); // submits pin from passwrod field
		buttonClear.setOnAction(buttonClearListener); // clears password fiel
		buttonCancel.setOnAction(buttonCancelListener); //
		/*
		 * Cheque Account Main menu
		 * */
		
		anchorChequeMenu.setVisible(false);
		anchorChequeWithdraw.setVisible(false);
		
		// Cheque Menu Buttons
		buttonChequeWithdraw.setOnAction(chequeWithdrawListener); //withdraw
		buttonChequeTransfer.setOnAction(buttonChequeTransferListener); //transfer
		buttonChequePay.setOnAction(buttonChequePayListener); //Pay
		buttonChequeMenuTransactionHistory.setOnAction(buttonChequeMenuTransactionHistoryListener);
		
		buttonChequeMenuCancel.setOnAction(chequeMenuCancelListener); //cancel
		
		labelWithdrawAmountError.setVisible(false); // show when user wants withdraw amount larger than balance
		
		// Cheque Withdraw keypad
		
		buttonChequeWithdraw0.setOnAction(chequeMenuKeypadListener);
		buttonChequeWithdraw1.setOnAction(chequeMenuKeypadListener);
		buttonChequeWithdraw2.setOnAction(chequeMenuKeypadListener);
		buttonChequeWithdraw3.setOnAction(chequeMenuKeypadListener);
		buttonChequeWithdraw4.setOnAction(chequeMenuKeypadListener);
		buttonChequeWithdraw5.setOnAction(chequeMenuKeypadListener);
		buttonChequeWithdraw6.setOnAction(chequeMenuKeypadListener);
		buttonChequeWithdraw7.setOnAction(chequeMenuKeypadListener);
		buttonChequeWithdraw8.setOnAction(chequeMenuKeypadListener);
		buttonChequeWithdraw9.setOnAction(chequeMenuKeypadListener);
		
		buttonChequeWithdrawSubmit.setOnAction(chequeMenuButtonSubmitListener);
	    buttonChequeWithdrawClear.setOnAction(chequeMenuButtonClearListener );
	    
	    
	    buttonChequeBack.setOnAction(buttonChequeBackListener);		
	    /*
	     * Cheque withdraw successfull 
	     * */
	    anchorCheqeuWithdrawSuccessfull.setVisible(false);
	    
	    buttonChequeWithdrawSuccessfullView.setOnAction(chequeWithdrawSuccessfullViewListener);
	    buttonChequeWithdrawSuccessfullExit.setOnAction(chequeWithdrawSuccessfullExitListener);
	    /*
	     * Cheque withdraw confirmation 
	     * */
	    
	    anchorChequeWithdrawConfirm.setVisible(false);
	    
	    
	    buttonChequeWithdrawConfirm.setOnAction(chequeWithdrawConfirmListener);
	    buttonChequeWithdrawBack.setOnAction(chequeWithdrawBackListener);
	    
	    /*
	     * Cheque withdraw transaction Details
	     **/
	    anchorChequeWithdrawTransactionDetails.setVisible(false);
	    
	    buttonChequeWithdrawTransactionDetailsYes.setOnAction(chequeWithdrawTransactionDetailsYesListener);
	    buttonChequeWithdrawTransactionDetailsNo.setOnAction(chequeWithdrawTransactionDetailsNoListener);
	    
	    /*
	     * Cheque transfer 
	     * */
	    anchorChequeTransfer.setVisible(false);
	    anchorChequeTransferConfirm.setVisible(false);
	    
	   
	    buttonChequeTransfer0.setOnAction(chequeTransferKeypadListener);
		buttonChequeTransfer1.setOnAction(chequeTransferKeypadListener);
		buttonChequeTransfer2.setOnAction(chequeTransferKeypadListener);
		buttonChequeTransfer3.setOnAction(chequeTransferKeypadListener);
		buttonChequeTransfer4.setOnAction(chequeTransferKeypadListener);
		buttonChequeTransfer5.setOnAction(chequeTransferKeypadListener);
		buttonChequeTransfer6.setOnAction(chequeTransferKeypadListener);
		buttonChequeTransfer7.setOnAction(chequeTransferKeypadListener);
		buttonChequeTransfer8.setOnAction(chequeTransferKeypadListener);
		buttonChequeTransfer9.setOnAction(chequeTransferKeypadListener);
		
		buttonChequeTransferSubmit.setOnAction(chequeTransferButtonSubmitListener);
	    buttonChequeTransferClear.setOnAction(chequeTransferButtonClearListener );
	    
	    buttonChequeTransferBack.setOnAction(buttonChequeTransferBackListener);	
		
	    /*
	     * Cheqe Transfer Confirm
	     * */
	    anchorCheqeuTransferSuccessfull.setVisible(false);
	    
	    buttonChequeTransferConfirmConfirm.setOnAction(buttonChequeTransferConfrimConfirmListener);
	    buttonChequeTransferConfirmBack.setOnAction(buttonChequeTransferConfirmBackListener);
	    
	    /*
	     * Cheque transfer transaction details
	     * */
	    anchorChequeTransferTransactionDetails.setVisible(false);
	    
	    buttonChequeTransferSuccessfullView.setOnAction(chequeTransferSuccessfullViewListener);
	    buttonChequeTransferSuccessfullExit.setOnAction(buttonChequeTransferSuccessfullExitListener );
	    
	    buttonChequeTransferTransactionDetailsYes.setOnAction(buttonChequeTransferTransactionDetailsYesListener);
	    buttonChequeTransferTransactionDetailsNo.setOnAction(buttonChequeTransferTransactionDetailsNoListener);
	    //
	    
	    /*
	     * Cheque Pay
	     */
	    anchorChequePay.setVisible(false);
	    
	    buttonChequePay.setOnAction(buttonChequePayListener);
	    buttonChequePayMenuCancel.setOnAction(buttonChequePayMenuCancelListener );
	    
	    /*
	     * cheque pay : Once Off
	     * 
	     * */
	    
	    anchorChequePayOnceOff.setVisible(false);
	    gridPaneChequePayOnceRecipientOff.setVisible(false);
	    
	    buttonChequePayMenuOnceOff.setOnAction(buttonChequePayMenuOnceOffListener);
	    
	    buttonChequePayOnceOff0.setOnAction(buttonChequePayOnceOffRecipientKeyPadListener);
		buttonChequePayOnceOff1.setOnAction(buttonChequePayOnceOffRecipientKeyPadListener);
		buttonChequePayOnceOff2.setOnAction(buttonChequePayOnceOffRecipientKeyPadListener);
		buttonChequePayOnceOff3.setOnAction(buttonChequePayOnceOffRecipientKeyPadListener);
		buttonChequePayOnceOff4.setOnAction(buttonChequePayOnceOffRecipientKeyPadListener);
		buttonChequePayOnceOff5.setOnAction(buttonChequePayOnceOffRecipientKeyPadListener);
		buttonChequePayOnceOff6.setOnAction(buttonChequePayOnceOffRecipientKeyPadListener);
		buttonChequePayOnceOff7.setOnAction(buttonChequePayOnceOffRecipientKeyPadListener);
		buttonChequePayOnceOff8.setOnAction(buttonChequePayOnceOffRecipientKeyPadListener);
		buttonChequePayOnceOff9.setOnAction(buttonChequePayOnceOffRecipientKeyPadListener);
	    
		buttonChequePayAmountOnceOffProceed.setOnAction(buttonChequePayOnceOffProceedListener);
	    buttonChequePayOnceOffClear.setOnAction(buttonChequePayOnceOffClearListener);
	    // Grid pan for key pad listener for user recipient account details
		
	    gridPaneChequePayAmountOnceOff.setVisible(false);
		textFieldChequePayOnceOffAmount.setDisable(true);
		
		buttonChequePayAmountOnceOff0.setOnAction(buttonChequePayOnceOffAmountKeyPadListener);
		buttonChequePayAmountOnceOff1.setOnAction(buttonChequePayOnceOffAmountKeyPadListener);
		buttonChequePayAmountOnceOff2.setOnAction(buttonChequePayOnceOffAmountKeyPadListener);
		buttonChequePayAmountOnceOff3.setOnAction(buttonChequePayOnceOffAmountKeyPadListener);
		buttonChequePayAmountOnceOff4.setOnAction(buttonChequePayOnceOffAmountKeyPadListener);
		buttonChequePayAmountOnceOff5.setOnAction(buttonChequePayOnceOffAmountKeyPadListener);
		buttonChequePayAmountOnceOff6.setOnAction(buttonChequePayOnceOffAmountKeyPadListener);
		buttonChequePayAmountOnceOff7.setOnAction(buttonChequePayOnceOffAmountKeyPadListener);
		buttonChequePayAmountOnceOff8.setOnAction(buttonChequePayOnceOffAmountKeyPadListener);
		buttonChequePayAmountOnceOff9.setOnAction(buttonChequePayOnceOffAmountKeyPadListener);
		
		buttonChequePayAmountOnceOffClear.setOnAction(buttonChequePayOnceOffAmountClearListener);
		buttonChequePayAmountOnceOffSubmit.setOnAction(buttonChequePayOnceOffSubmitListener);
		
		buttonChequePayOnceOffBack.setOnAction(buttonChequePayOnceOffBackListener);	
		
		/*
		 * Cheque Once Off Payment confirmation
		 * */
		
		anchorChequePayOnceOffConfirmation.setVisible(false);
	    
		/*Cheque once of pay confirmation
		 * */
		anchorCheqeuPayOnceOffSuccessfull.setVisible(false);
		
		buttonChequePayOnceOffConfirmConfirm.setOnAction(buttonChequePayOnceOffConfirmConfirmListener);
		buttonChequePayOnceOffConfirmBack.setOnAction(buttonChequePayOnceOffConfirmBackListener);
		
		buttonChequePayOnceOffSuccessfullView.setOnAction(buttonChequePayOnceOffSuccessfullViewListener);
		buttonChequePayOnceOffSuccessfullExit.setOnAction(buttonChequePayOnceOffSuccessfullExitListener);
	    /*
	     * Cheque once of payment transaction details
	     * */
		
		anchorChequePayOnceOffTransactionDetails.setVisible(false);
	    
		buttonChequePayOnceOffTransactionDetailsYes.setOnAction(buttonChequePayOnceOffTransactionDetailsYesListener);
		buttonChequePayOnceOffTransactionDetailsNo.setOnAction(buttonChequePayOnceOffTransactionDetailsNoListener);
		
		/*
		 * Cheque Pay - Create beneficiary 
		 * */
		
		anchorChequePayCreateBeneficiary.setVisible(false);
		buttonChequePayMenuCreateBeneficiary.setOnAction(buttonChequePayMenuCreateBeneficiaryListener);
		
		// GridPane for beneficiary name
		 buttonChequeCreateBeneficiaryName0.setOnAction(keypadChequeCreateBeneficiaryNameListener);
	     buttonChequeCreateBeneficiaryName1.setOnAction(keypadChequeCreateBeneficiaryNameListener);
	     buttonChequeCreateBeneficiaryName2.setOnAction(keypadChequeCreateBeneficiaryNameListener);
	     buttonChequeCreateBeneficiaryName3.setOnAction(keypadChequeCreateBeneficiaryNameListener);
	     buttonChequeCreateBeneficiaryName4.setOnAction(keypadChequeCreateBeneficiaryNameListener);
	     buttonChequeCreateBeneficiaryName5.setOnAction(keypadChequeCreateBeneficiaryNameListener);
	     buttonChequeCreateBeneficiaryName6.setOnAction(keypadChequeCreateBeneficiaryNameListener);
	     buttonChequeCreateBeneficiaryName7.setOnAction(keypadChequeCreateBeneficiaryNameListener);
	     buttonChequeCreateBeneficiaryName8.setOnAction(keypadChequeCreateBeneficiaryNameListener);
	     buttonChequeCreateBeneficiaryName9.setOnAction(keypadChequeCreateBeneficiaryNameListener);
	     
	     buttonChequePayCreateBeneficiaryNameProceed.setOnAction(buttonChequeCreateBeneficiaryNameProceedListener);
	     buttonChequeCreateBeneficiaryNameClear.setOnAction(buttonChequeCreateBeneficiaryNameClearListener);
	     
	     // beneficiary account no
	     
	     buttonChequeCreateBeneficiaryAccountNo0.setOnAction(keypadChequeCreateBeneficiaryAccountListener);
	     buttonChequeCreateBeneficiaryAccountNo2.setOnAction(keypadChequeCreateBeneficiaryAccountListener);
	     buttonChequeCreateBeneficiaryAccountNo3.setOnAction(keypadChequeCreateBeneficiaryAccountListener);
	     buttonChequeCreateBeneficiaryAccountNo4.setOnAction(keypadChequeCreateBeneficiaryAccountListener);
	     buttonChequeCreateBeneficiaryAccountNo5.setOnAction(keypadChequeCreateBeneficiaryAccountListener);
	     buttonChequeCreateBeneficiaryAccountNo6.setOnAction(keypadChequeCreateBeneficiaryAccountListener);
	     buttonChequeCreateBeneficiaryAccountNo7.setOnAction(keypadChequeCreateBeneficiaryAccountListener);
	     buttonChequeCreateBeneficiaryAccountNo8.setOnAction(keypadChequeCreateBeneficiaryAccountListener);
	     buttonChequeCreateBeneficiaryAccountNo9.setOnAction(keypadChequeCreateBeneficiaryAccountListener);
	     
	     buttonChequeCreateBeneficiaryAccountProceed.setOnAction(buttonChequeCreateBeneficiaryAccountProceedListener);
	     buttonChequeCreateBeneficiaryAccountClear.setOnAction(buttonChequeCreateBeneficiaryAccountClearListener);
	     
	     // beneficiary Bank Name
	     
	     buttonChequeCreateBeneficiaryBankName00.setOnAction(keypadChequeCreateBeneficiaryBankNameListener);
	     buttonChequeCreateBeneficiaryBankName1.setOnAction(keypadChequeCreateBeneficiaryBankNameListener);
	     buttonChequeCreateBeneficiaryBankName2.setOnAction(keypadChequeCreateBeneficiaryBankNameListener);
	     buttonChequeCreateBeneficiaryBankName3.setOnAction(keypadChequeCreateBeneficiaryBankNameListener);
	     buttonChequeCreateBeneficiaryBankName4.setOnAction(keypadChequeCreateBeneficiaryBankNameListener);
	     buttonChequeCreateBeneficiaryBankName5.setOnAction(keypadChequeCreateBeneficiaryBankNameListener);
	     buttonChequeCreateBeneficiaryBankName6.setOnAction(keypadChequeCreateBeneficiaryBankNameListener);
	     buttonChequeCreateBeneficiaryBankName7.setOnAction(keypadChequeCreateBeneficiaryBankNameListener);
	     buttonChequeCreateBeneficiaryBankName8.setOnAction(keypadChequeCreateBeneficiaryBankNameListener);
	     buttonChequeCreateBeneficiaryBankName9.setOnAction(keypadChequeCreateBeneficiaryBankNameListener);
	     
	     buttonChequeCreateBeneficiaryBankNameProceed.setOnAction(buttonChequeCreateBeneficiaryBankNameProceedListener);
	     buttonChequeCreateBeneficiaryBankNameClear.setOnAction(buttonChequeCreateBeneficiaryBankNameClearListener);
	     
	     buttonChequePayCreateBeneficiaryBack.setOnAction(buttonChequePayCreateBeneficiaryBackListener);
	     /*
	      * Cheque Create beneficiary - confrimation Page
	      * */
	     
	     anchorChequeCreateBeneficiaryConfirmation.setVisible(false);
	     
	     buttonChequeCrateBeneficiaryConfirmBack.setOnAction(buttonChequeCrateBeneficiaryConfirmBackListener);
	     buttonChequeCrateBeneficiaryConfirmConfirm.setOnAction(buttonChequeCrateBeneficiaryConfirmConfirmListener);
	     /*
	      * Cheque Pay - Create beneficiary successfull
	      * */
	     
	     anchorChequePayCreateBenefciarySuccessfull.setVisible(false);
	     buttonChequePayCreateBeneficiarySuccessfullYes.setOnAction(buttonChequePayCreateBeneficiarySuccessfullYesListener);
	     buttonChequePayCreateBeneficiarySuccessfullExit.setOnAction(buttonChequePayCreateBeneficiarySuccessfullExitListener);
	     
	     
	     /*
	      * Cheque Purchase 
	      * */
	     
	     anchorContainerPurchase.setVisible(false);
	     anchorChequePurchase.setVisible(false);
	     buttonChequePurchase.setOnAction(buttonChequePurchaseListener);
	     
	     buttonChequePurchaseAirtime.setOnAction(buttonChequePurchaseAirtimeListener);
	     buttonChequePurchaseLottoCancel.setOnAction(buttonChequePurchaseLottoCancelListener );
	     //
	 	buttonChequePurchaseAirtimeNumber0.setOnAction(buttonChequePurchaseAirtimeNumberListener );
	    buttonChequePurchaseAirtimeNumber1.setOnAction(buttonChequePurchaseAirtimeNumberListener);
	    buttonChequePurchaseAirtimeNumber2.setOnAction(buttonChequePurchaseAirtimeNumberListener);
	    buttonChequePurchaseAirtimeNumber3.setOnAction(buttonChequePurchaseAirtimeNumberListener);
	    buttonChequePurchaseAirtimeNumber4.setOnAction(buttonChequePurchaseAirtimeNumberListener);
	    buttonChequePurchaseAirtimeNumber5.setOnAction(buttonChequePurchaseAirtimeNumberListener);
	    buttonChequePurchaseAirtimeNumber6.setOnAction(buttonChequePurchaseAirtimeNumberListener);
	    buttonChequePurchaseAirtimeNumber7.setOnAction(buttonChequePurchaseAirtimeNumberListener);
	    buttonChequePurchaseAirtimeNumber8.setOnAction(buttonChequePurchaseAirtimeNumberListener);
	    buttonChequePurchaseAirtimeNumber9.setOnAction(buttonChequePurchaseAirtimeNumberListener);
	    
	    buttonChequePurchaseAirtimeNumberProceed.setOnAction(buttonChequePurchaseAirtimeNumberProceedListener);
	    buttonChequePurchaseAirtimeNumberClear.setOnAction(buttonChequePurchaseAirtimeNumberClearListener);
	    buttonChequePayPurchaseAirtimeBack.setOnAction(buttonChequePayPurchaseAirtimeBackListener );
	    //
	    
	    buttonChequePurchaseAirtimeAmount0.setOnAction(buttonChequePurchaseAirtimeAmountListener);
	    buttonChequePurchaseAirtimeAmount1.setOnAction(buttonChequePurchaseAirtimeAmountListener);
	    buttonChequePurchaseAirtimeAmount2.setOnAction(buttonChequePurchaseAirtimeAmountListener);
	    buttonChequePurchaseAirtimeAmount3.setOnAction(buttonChequePurchaseAirtimeAmountListener);
	    buttonChequePurchaseAirtimeAmount4.setOnAction(buttonChequePurchaseAirtimeAmountListener);
	    buttonChequePurchaseAirtimeAmount5.setOnAction(buttonChequePurchaseAirtimeAmountListener);
	    buttonChequePurchaseAirtimeAmount6.setOnAction(buttonChequePurchaseAirtimeAmountListener);
	    buttonChequePurchaseAirtimeAmount7.setOnAction(buttonChequePurchaseAirtimeAmountListener);
	    buttonChequePurchaseAirtimeAmount8.setOnAction(buttonChequePurchaseAirtimeAmountListener);
	    buttonChequePurchaseAirtimeAmount9.setOnAction(buttonChequePurchaseAirtimeAmountListener);
	    
	    buttonChequePurchaseAirtimeAmountProceed.setOnAction(buttonChequePurchaseAirtimeAmountProceedListener);
	    buttonChequePurchaseAirtimeAmountClear.setOnAction(buttonChequePurchaseAirtimeAmountClearListener);
	    //
	    /*
	     * Cheque Purchases Airtime Confirm 
	     * */
	    
	    anchorChequePurchaseAirtimeConfirm.setVisible(false);
	    
	    buttonChequePurchaseAirtimeConfirmConfirm.setOnAction(buttonChequePurchaseAirtimeConfirmConfirmListener);
	    buttonChequePurchaseAirtimeConfirmBack.setOnAction(buttonChequePurchaseAirtimeConfirmBackListener);
	    /*
	     * Cheque purchases air time transaction succesfull
	     * */
	    anchorCheqeuPurchaseAirtimeSuccessfull.setVisible(false);
	    
	    buttonChequePurchaseAirtimeSuccessfullYes.setOnAction(buttonChequePurchaseAirtimeSuccessfullYesListener );
	    buttonChequePurchaseAirtimeSuccessfullNo.setOnAction(buttonChequePurchaseAirtimeSuccessfullNoListener );
	    //
	    anchorPaneChequePurchasePlayLotto.setVisible(false);
	    
	    buttonChequePurchaseLotto.setOnAction(buttonChequePurchaseLottoListener);
	    
	    buttonChequePayPurchaseLottoPlay.setOnAction(buttonChequePayPurchaseLottoPlayListener );
	    
	    buttonChequePayPurchaseLottoProceed.setOnAction(buttonChequePayPurchaseLottoProceedListener);
	    buttonChequePayPurchaseLottoBack.setOnAction(buttonChequePayPurchaseLottoBackListener);
	    
	    /*
	     * Cheque Purchase lotto successfull purchase
	     * */
	    
	    buttonChequePurchaseLottoSuccessfullYes.setOnAction(buttonChequePurchaseLottoSuccessfullYesListener);
	    
	    /*
	     * Cheque Transaction History 
	     * */
	    
	    anchorChequeTransactionHistory.setVisible(false);
	    
	    buttonChequeTransactionHistoryBack.setOnAction(buttonChequeTransactionHistoryBackListener);
	    
	}
	
	/*
	 * Application index , that contains virtual card 
	 * */
	
	@FXML AnchorPane anchorMainContainer = new AnchorPane();
	
	
	Date date = new Date(); // Replace this with your specific date
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", new Locale("en"));
	
	SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");

    // Format the date as a string
    String formattedTime = sdfTime.format(date);
	
	String formattedDate = sdf.format(date);
	//AnchorPane
	@FXML
	AnchorPane anchorIndex = new AnchorPane();
	
	//button
	@FXML
	Button buttonVirtualCard = new Button ();
	
	@FXML Label labelChequePurchaseAirtimebalance = new Label();
	
	private EventHandler<ActionEvent> indexVirtualCardListener = new EventHandler<ActionEvent>() {
    	
    	//Index Virtual Listener, when user clicks on cardless Transaction login stage will show
    	@Override 
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.print(number);
    		//
        	try {
        		//
        		anchorIndex1();
        		
        	} catch (Exception e) {
    			// 
    			e.printStackTrace();
    		}
    	}
    } ; 
    
    @FXML Label labelAnchoeMaindate = new Label();
    
    @FXML Label labelAnchoeMainTime = new Label();
    
    @FXML Label labelAnchoeMaindate1 = new Label();
    
    @FXML Label labelAnchoeMainTime1 = new Label();
    
    @FXML ImageView imageEKQILogo = new ImageView();
    
    @FXML ImageView imageEKQILogo1 = new ImageView();
    
    public void anchorIndex1() {
    	//
    	
    	// insert date 
    	anchorMainContainer.setVisible(true);
		anchorLogin.setVisible(true); //User can access Login stage
		anchorIndex.setVisible(false); //  index stage set to not visible
    	//
    }
    /*
     * Application login 
     * */
    
    @FXML
    AnchorPane anchorLogin = new AnchorPane();
    
    @FXML
    PasswordField passwordFieldPin = new PasswordField();
    
    @FXML
    Label labelErrorPin = new Label(); // Displays error message to user for incorrect pin
    
    @FXML
    Label labelErrorAttempts = new Label(); 
    
    // Login Key Pad buttons 
    @FXML
    Button button0 = new Button();
    @FXML
    Button button1 = new Button();
    @FXML
    Button button2 = new Button();
    @FXML
    Button button3 = new Button();
    @FXML
    Button button4 = new Button();
    @FXML
    Button button5 = new Button();
    @FXML
    Button button6 = new Button();
    @FXML
    Button button7 = new Button();
    @FXML
    Button button8 = new Button();
    @FXML
    Button button9 = new Button();
    @FXML
    Button buttonSubmit = new Button();
    @FXML
    Button buttonClear = new Button();
    
    @FXML Button buttonCancel = new Button();
    
    //Keypad listener so that user can input pin into password field using keypad
    private EventHandler<ActionEvent> keyPadListener = new EventHandler<ActionEvent>() {
    	
    	@Override 
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		passwordFieldPin.appendText(number); // shows the user inputs in the password field
    		System.out.print(number);
    		
    	}
    } ; 
    
  //Keypad listener so that user can input pin into password field using keypad
    private EventHandler<ActionEvent> buttonCancelListener = new EventHandler<ActionEvent>() {
    	
    	@Override 
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		passwordFieldPin.clear(); // shows the user inputs in the password field
    		System.out.print(number);
    		// insert date 
        	anchorMainContainer.setVisible(true);
    		anchorLogin.setVisible(false); //User can access Login stage
    		anchorIndex.setVisible(true); //  index stage set to not visible
        	//
    		
    	}
    } ; 
    
    public void home() {
    	// Home method that takes user to index page of the application
    	anchorIndex.setVisible(true); //  index stage set to not visible
    	//
    	anchorMainContainer.setVisible(true);
		anchorLogin.setVisible(false); //User can access Login stage
		anchorChequeMenu.setVisible(false);
		
		// anchors below are to make all anchors not visible 
		anchorCheqeuWithdrawSuccessfull.setVisible(false);
		anchorChequeWithdrawConfirm.setVisible(false);
		anchorChequeWithdraw.setVisible(false);
		anchorChequeWithdrawTransactionDetails.setVisible(false);
		
		anchorChequeTransferTransactionDetails.setVisible(false);
    	anchorCheqeuTransferSuccessfull.setVisible(false);
    	anchorChequeTransfer.setVisible(false);
    	anchorChequeTransferConfirm.setVisible(false);
    	
    	anchorChequePayOnceOff.setVisible(false);
    	anchorChequePay.setVisible(false);
		anchorChequePayOnceOffConfirmation.setVisible(false);
		anchorCheqeuPayOnceOffSuccessfull.setVisible(false);
		anchorChequePayOnceOffTransactionDetails.setVisible(false);
		
		anchorChequePayCreateBeneficiary.setVisible(false);
    	
    }
    
    //
    public int loginAttempts = 3;
    
    //submit button so that user can submit their pin to proceed to next transaction
    private EventHandler<ActionEvent> buttonSubmitListener = new EventHandler<ActionEvent>() {
    	
    	@Override 
    	public void handle(ActionEvent actionEvent) {
    
    			try {
    				//
    	    		Button sourceComponent = (Button)actionEvent.getSource();
    	    		String number = sourceComponent.getText();
    	    		
    	    		System.out.print(number);
    	    		/*sends user to cheque account stage
    	    		 * add option to access savings account from cheque account menu
    	    		 * */
    	    		String accountPin = passwordFieldPin.getText();
    	    		passwordFieldPin.setPromptText("Enter Pin");
    	    		//
    	       		int pin = Integer.parseInt(accountPin);    	
    	       		//
    	   			if ( pin == cheque.getChequeAccountNo()) {
    	    			//
    	    			System.out.println("Login Sucessfull ");
    	    			chequeAcountMenu();
    	    			passwordFieldPin.clear(); // clears the user inputs in the password field
    	    			labelErrorPin.setVisible(false);
    	    			labelErrorAttempts.setVisible(false);
    	    			//
    	    			loginAttempts = 3;
    	    			//
    	    		} else {
    	    			//
    	    			System.err.println("\nLogin Unsucessfull , incorrect pin ");
    	    			
    	    			labelErrorPin.setText("Incorrect pin please, please enter correct pin.");
    	    			labelErrorPin.setVisible(true);
    	    			
    	    			labelErrorAttempts.setText("Login attempts remaining : " + loginAttempts);
    	    			labelErrorAttempts.setVisible(true);
    	    			//
    	    	    	passwordFieldPin.clear(); // clears the user inputs in the password field
    	    	    	//
    	    	    	if (loginAttempts == 1) {
        					//
        					System.err.println("Last Login Attempt or else account will be blocked");
        					labelErrorPin.setText("Last Login Attempt or else account will be blocked");
        					passwordFieldPin.clear(); // clears the user inputs in the password field
        					//
        				} else if (loginAttempts == 0) {
        					//
        					labelErrorPin.setText("Account Blocked, please visit your nearest EKQi Branch.");
        					System.err.println("\nAccount Blocked, please visit your nearest EKQi Branch. ");
        					buttonSubmit.setDisable(true);
        					//
        				}
    	    		}
    	   			//
    	   			loginAttempts--;
    	   			
    	   			System.err.println("Login Attempts " + loginAttempts);
    	   			//
    	   		} catch (Exception e) {
    	   			//
    	   			labelErrorPin.setText("Please enter an your pin");
    	   			labelErrorPin.setVisible(true);
    	   			System.err.println("Error : " + e + " Please enter an integer value");
    	   			//
    	   		}
    	    	
    		//
    	}
    } ; 
    
  //submit button so that user can submit their pin to proceed to next transaction
    private EventHandler<ActionEvent> buttonClearListener = new EventHandler<ActionEvent>() {
    	
    	@Override 
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		passwordFieldPin.clear(); // shows the user inputs in the password field
    		System.out.print(number);
    		//
    	}
    } ; 
    
    /*
     * Cheque Main Menu 
     * 1. Withdraw 
     * */
    int amount;
    private int randomID = 0;  // this line of code creates a random transactionID
    
   
    //AnchorPane for cheque account menu
    @FXML
    AnchorPane anchorChequeMenu = new AnchorPane();
    
    // Cheque menu buttons
    @FXML Button buttonChequeWithdraw = new Button(); // to withdraw funds from cheque Account
    
    @FXML Button buttonChequeTransfer = new Button(); // to transfer funds
    
    @FXML Button buttonChequePay = new Button(); // to transfer funds
    
    @FXML Button buttonChequePurchase = new Button(); // to purchase
    
    @FXML Button buttonChequeMenuTransactionHistory = new Button();
    
    @FXML 
    Button buttonChequeMenuCancel = new Button(); // cancel button to exit the application
    
    //Label for cheque account menu
    @FXML
    Label labelUserName = new Label(); // Displays account holder name 
    
    @FXML
    Label labelChequeMenuNo = new Label(); // Displays cheque account no 
    
    @FXML 
    Label labelChequeMenuBalance = new Label(); // Displays cheque account balance 
    
    /*Global cheque account methods
     * */
    
    // global delete last tranaction method
    public void deleteLastTransaction() {
    	//Delete last transaction if user decieds not to continue with transaction
    	int beneficiaryCode = newChequeTransactions.size() - 1; // Get the index of the last element
    	//
    	if (beneficiaryCode >= 0 && beneficiaryCode < newChequeTransactions.size()) {
			//
    		newChequeTransactions.remove(beneficiaryCode);
			System.out.println("Beneficiary removed successfully");
			//
		}
    }
    
    // cheque account menu 
    public void chequeAcountMenu() {
    	//
		labelChequeMenuBalance.setText("Cheque Balance : " + moneyFormat.format(cheque.getChequeBalance()));
		//
    	String chequeAccountNo = Integer.toString(cheque.getChequeAccountNo()); // converts account number to string
		labelChequeMenuNo.setText("Cheque Account No : " + chequeAccountNo);
    	// displays cheque account men
    	anchorLogin.setVisible(false); 
    	anchorChequePay.setVisible(false);
		anchorChequeMenu.setVisible(true);
		anchorMainContainer.setVisible(true);
		
		// Cheque menu sub headings and headings
		labelUserName.setText(cheque.getUserName());
		//
    }
    
    // cheque Menu : withdraw button to access withdrawal option
    private EventHandler<ActionEvent> chequeWithdrawListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		chequeWithdraw();
    	}
    };
    
    // Cheque Menu : Transfer button listener
    private EventHandler<ActionEvent> buttonChequeTransferListener = new EventHandler<ActionEvent>() {
    	//
    	@Override
    	public void handle (ActionEvent actionEvent){
    		//
			Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		chequeTransfer();
    		//
    	}
    };
    
    // Cheque Menu : Transfer button listener
    private EventHandler<ActionEvent> buttonChequePayListener = new EventHandler<ActionEvent>() {
    	//
    	@Override
    	public void handle (ActionEvent actionEvent){
    		//
			Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		chequePay();
    	}
    };
    
    // Cheque Menu : Purchases listener
    private EventHandler<ActionEvent> buttonChequePurchaseListener = new EventHandler<ActionEvent>() {
    	//
    	@Override
    	public void handle (ActionEvent actionEvent){
    		//
			Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		purchase();
    		//
    	}
    };
    
    public void purchase() {
    	//
    	anchorContainerPurchase.setVisible(true);
    	anchorChequePurchase.setVisible(true);
    	anchorPurchaseAirtime.setVisible(false);
    	
    }
    
 // Cheque Menu : Purchases listener
    private EventHandler<ActionEvent> buttonChequeMenuTransactionHistoryListener = new EventHandler<ActionEvent>() {
    	//
    	@Override
    	public void handle (ActionEvent actionEvent){
    		//
			Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		chequeTransactionHistory();
    		//
    	}
    };
    
    // cheque cancel event listener to exit applicaton
    private EventHandler<ActionEvent> chequeMenuCancelListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		home();
    	}
    };
    
    /*
     * 1. Withdraw
     * */
    
    @FXML
    Label labelWithdrawChequeNo = new Label();
    
    @FXML
    Label labelWithdrawChequeBalance = new Label();
    //
    
    @FXML
    Label labelWithdrawAmountError = new Label();
    
    // Cheque withdraw method 
    
    public void chequeWithdraw() {
    	//
    	String chequeAccountNo = Integer.toString(cheque.getChequeAccountNo()); // converts account number to string
    	labelWithdrawChequeNo.setText("Cheque Account No : " + chequeAccountNo);
    	//
    	
    	labelWithdrawChequeBalance.setText("Cheque Account Balance : " + moneyFormat.format(cheque.getChequeBalance()));
    	//
    	anchorChequeMenu.setVisible(false);
    	anchorChequeWithdraw.setVisible(true);
    	//
    	amountTextField.setPromptText("Enter Amount");
    }
    
    // AnchorPane for cheque withdrawal
    @FXML
    AnchorPane anchorChequeWithdraw = new AnchorPane();
    
    // Buttons for cheque withdrawkeypad
    @FXML
    Button buttonChequeWithdraw0 = new Button();
    @FXML
    Button buttonChequeWithdraw1 = new Button();
    @FXML
    Button buttonChequeWithdraw2 = new Button();
    @FXML
    Button buttonChequeWithdraw3 = new Button();
    @FXML
    Button buttonChequeWithdraw4 = new Button();
    @FXML
    Button buttonChequeWithdraw5 = new Button();
    @FXML
    Button buttonChequeWithdraw6 = new Button();
    @FXML
    Button buttonChequeWithdraw7 = new Button();
    @FXML
    Button buttonChequeWithdraw8 = new Button();
    @FXML
    Button buttonChequeWithdraw9 = new Button();
    @FXML
    Button buttonChequeWithdrawSubmit = new Button();
    @FXML
    Button buttonChequeWithdrawClear = new Button();
    @FXML
    Button buttonChequeBack = new Button();
    
    //Cheque account Withdraw textfield 
    @FXML
    TextField amountTextField = new TextField();
    
    // cheque account withdraw keypad listeners 
    private EventHandler<ActionEvent> chequeMenuKeypadListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		amountTextField.appendText(number); // shows the user inputs in the password field
    		
    	}
    };
   
    // When user 
    private EventHandler<ActionEvent> chequeMenuButtonSubmitListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		try {
    			//
    			labelWithdrawAmountError.setVisible(false);
        		//
        		Button sourceComponent = (Button)actionEvent.getSource();
        		String number = sourceComponent.getText();
        		//
        		System.out.println(number);
        		// submits the amount the user would like to withdraw 
        		labelWithdrawChequeBalance.setText("Available Cheque Account Balance :" + moneyFormat.format(cheque.getChequeBalance()));
            	//
        		Double amount = Double.parseDouble(amountTextField.getText());
        		//
        		
            	if (cheque.getChequeBalance() - amount >= 0) {
            		//
            		labelChequeWithdrawConfirm.setText("Cheque Account No\t\t: " + cheque.getChequeAccountNo());
            		labelChequeWithdrawAmount.setText("Amount\t\t\t\t\t: " + moneyFormat.format(amount));      		
            		
            		// Creates an array which contains the transaction details, arrray is deleted if user cancles transaction
            		for (int i = 0; i < 1;i++) {
    					//
    					LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
    					String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
    					chequeTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
    					//
    					String transactionDescription = "Cash Withdrawal";// this line of code is the name of the transaction
    					chequeTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
    					//
    					double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
    					chequeTransactionsHistory.setTransactionAmount(transactionAmount);;
    					//
    					String transactionID = "TRA00" + randomID + "CH";
    					chequeTransactionsHistory.setTransactionID(transactionID);
    					randomID++;
    					//
    					String transactionType = "Debit";
    					chequeTransactionsHistory.setTransactionType(transactionType);
    					// line of code below creates a new array
    					newChequeTransactions.add(new ChequeTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
    					//
    					labelWithdrawAmountError.setVisible(false);
    				
    					
    				}
            		//
            		anchorChequeWithdrawConfirm.setVisible(true);
            		anchorChequeWithdraw.setVisible(false);
            		//
            		amountTextField.clear();
            		//
            		
            	} else {
            		//
            		labelWithdrawAmountError.setText("Maxium withdrawable amount : " +  moneyFormat.format(cheque.getChequeBalance()) );
            		labelWithdrawAmountError.setVisible(true);
            		System.err.println("Insufficient funds, Maxium withdrwable amount");
            		amountTextField.clear();
            		//
            		
            	}
        		
        	
    		} catch (Exception e) {
    			//
    			labelWithdrawAmountError.setText("Error. Please enter an Amount");
        		labelWithdrawAmountError.setVisible(true);
        		//
        		System.err.println("Error : " + e);
    			
    		}
    	}
    };
    
    
   
    
    // cheque account withdraw keypad listeners 
    private EventHandler<ActionEvent> chequeMenuButtonClearListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		amountTextField.clear(); // shows the user inputs in the password field
    		
    	}
    };
    
    // cheque withdraw back button to go back to cheque menu
    private EventHandler<ActionEvent>  buttonChequeBackListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		amountTextField.clear(); // shows the user inputs in the password field
    		// to go back to cheque menu
    		anchorChequeMenu.setVisible(true);
        	anchorChequeWithdraw.setVisible(false);
        	//
        	labelWithdrawAmountError.setVisible(false);
        	//
        	deleteLastTransaction();
    	}
    };
    /*
     * Cheque Withdraw confirm
     * */
    
    
    //AnchorPane for confirming cheque payment
    @FXML
    AnchorPane anchorChequeWithdrawConfirm = new AnchorPane();
    
    //button to confrim withdrawal
    @FXML
    Button buttonChequeWithdrawConfirm = new Button();
    @FXML
    Button buttonChequeWithdrawBack = new Button();
    
    //label for transaction AMount
    @FXML 
    Label labelChequeWithdrawAmount = new Label();
    
    @FXML
    Label labelChequeWithdrawConfirm = new Label();
   
    
    // cheque withdraw confirm button to confirm transaction
    
    private EventHandler<ActionEvent>  chequeWithdrawConfirmListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		amountTextField.clear(); // shows the user inputs in the password field
    		// 
    		anchorChequeWithdrawConfirm.setVisible(false);
    		anchorChequeMenu.setVisible(false);
        	anchorChequeWithdraw.setVisible(false);
        	anchorChequeWithdrawTransactionDetails.setVisible(true);
        	// crrent tranaction 
        	
        	/*
        	 * Gets amount from transaction History
        	 * */

        	int beneficiaryCode = newChequeTransactions.size() - 1; // Get the index of the last element
        	
        	if (beneficiaryCode >= 0 && beneficiaryCode < newChequeTransactions.size()) { // for each loop to display the current beneficiary list
    			// sets the new balance after subtracting amount from balance
        		double newChequeBalance = cheque.getChequeBalance() - newChequeTransactions.get(beneficiaryCode).getTransactionAmount();
    			cheque.setChequeBalance(newChequeBalance);
        		//
    			System.out.println("\nUnique Beneficiary code :" + beneficiaryCode);
    			System.out.println("Transaction TransactionID\t\t: " + newChequeTransactions.get(beneficiaryCode).getTransactionID());
    			System.out.println("Transaction Date\t\t\t: " +newChequeTransactions.get(beneficiaryCode).getTransactionDate());
    			System.out.println("Transaction Name\t\t\t: " +newChequeTransactions.get(beneficiaryCode).getTransactionDescription());
    			System.out.println("Transaction Amount\t\t: " + moneyFormat.format(newChequeTransactions.get(beneficiaryCode).getTransactionAmount()));
    			//
    			System.out.println("");
    			
    		}
        	//Displays transaction successful screen 
        	anchorChequeWithdrawTransactionDetails.setVisible(false);;
        	anchorCheqeuWithdrawSuccessfull.setVisible(true);
        	//
    	}
    };
    
    // Cheque Withdraw confirmation 
    private EventHandler<ActionEvent>  chequeWithdrawBackListener = new EventHandler<ActionEvent>() {
    	// deletes the last transaction if user chooses not to proceed with transaction
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		amountTextField.clear(); // shows the user inputs in the password field
    		// 
    		anchorChequeWithdraw.setVisible(true);
    		//
    		anchorLogin.setVisible(false);
    		anchorChequeWithdrawConfirm.setVisible(false);
    		anchorChequeMenu.setVisible(false);
        	anchorChequeWithdrawTransactionDetails.setVisible(false);
        	anchorCheqeuWithdrawSuccessfull.setVisible(false);
        	//
        	labelWithdrawAmountError.setVisible(false);
        	//
        	deleteLastTransaction(); //
        	//
        	
    	}
    };
    
    /*
     * Cheque withdraw successful displays the withdraw successfull page and gives user option to 
     * */
    
    //AnchorPane for cheque withdraw successfull
   @FXML
   AnchorPane anchorCheqeuWithdrawSuccessfull = new AnchorPane();
    
   @FXML
   Button buttonChequeWithdrawSuccessfullView = new Button(); // displays tranaction on screem
   
   @FXML
   Button buttonChequeWithdrawSuccessfullExit = new Button();
   
    
    private EventHandler<ActionEvent>  chequeWithdrawSuccessfullViewListener = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent actionEvent) {
			//
			try {
				//
				Button sourceComponent = (Button)actionEvent.getSource();
	    		String number = sourceComponent.getText();
	    		//
	    		System.out.println(number);
	    		//
	    		int beneficiaryCode = newChequeTransactions.size() - 1; // Get the index of the last element
				//
	    		labelChequeWithdrawTransactionDetailsBalance.setText("Cheque Account : " + cheque.getChequeAccountNo() + "\t\t\t\t\tNew Cheque Balance : " + moneyFormat.format(cheque.getChequeBalance()));
	    		//
				if (beneficiaryCode >= 0 && beneficiaryCode < newChequeTransactions.size()) { // for each loop to display the current beneficiary list
	    			//
					// Cheque Transaction details
	    	    	labelChequeWithdrawTransactionDetailsID.setText("TransactionID\t\t\t: "  + newChequeTransactions.get(beneficiaryCode).getTransactionID());
	    	    	labelChequeWithdrawTransactionDetailsDate.setText("Transaction Date\t\t: " + newChequeTransactions.get(beneficiaryCode).getTransactionDate());
	    	    	labelChequeWithdrawTransactionDetailsName.setText("Transaction Name\t\t: "  + newChequeTransactions.get(beneficiaryCode).getTransactionDescription());
	    	    	labelChequeWithdrawTransactionDetailsAmount.setText("Transaction Amount\t: "  +  moneyFormat.format(newChequeTransactions.get(beneficiaryCode).getTransactionAmount()));
					//
	    			System.out.println("\nUnique Beneficiary code :" + beneficiaryCode);
	    			System.out.println("TransactionID\t\t\t: " + newChequeTransactions.get(beneficiaryCode).getTransactionID());
	    			System.out.println("Transaction Date\t\t: " + newChequeTransactions.get(beneficiaryCode).getTransactionDate());	
	    			System.out.println("Transaction Name\t\t: " + newChequeTransactions.get(beneficiaryCode).getTransactionDescription());		
	    			System.out.println("Transaction Amount\t\t: " + moneyFormat.format(newChequeTransactions.get(beneficiaryCode).getTransactionAmount()));
	    	    	
	    	    	// Displays ID, date, name and amount    	
	    	    	anchorChequeWithdrawTransactionDetails.setVisible(true);
	    	    	anchorCheqeuWithdrawSuccessfull.setVisible(false);
	    			//
	    			System.out.println("");
	    		}
	    		
			
			} catch (Exception e) {
				//
				System.err.println("Error : " + e);
				
			}
		}
    	
    };
    
    private EventHandler<ActionEvent>  chequeWithdrawSuccessfullExitListener = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent actionEvent) {
			
			//
			Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		home();
		}
    	
    };
    /*
     * Cheque withdraw Transaction details 
     * */
   
    
    //Anchor for cheque transaction details
    @FXML
 	AnchorPane anchorChequeWithdrawTransactionDetails =  new AnchorPane();
    
    //Buttons for cheque transaction details
    @FXML Button buttonChequeWithdrawTransactionDetailsYes= new Button();
    
    @FXML Button buttonChequeWithdrawTransactionDetailsNo = new Button();
    
    //Labels for cheque withdraw transaction details
    @FXML Label labelChequeWithdrawTransactionDetailsBalance = new Label();
    
    @FXML  Label labelChequeWithdrawTransactionDetailsID = new Label();
    
    @FXML Label labelChequeWithdrawTransactionDetailsDate = new Label();
    
    @FXML Label labelChequeWithdrawTransactionDetailsName = new Label();
    
    @FXML  Label labelChequeWithdrawTransactionDetailsAmount = new Label();
    
    
    private EventHandler<ActionEvent>  chequeWithdrawTransactionDetailsYesListener = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent actionEvent) {
			
			//
			Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		// insert what you would like to happen
    		anchorChequeWithdrawTransactionDetails.setVisible(false);
    		chequeAcountMenu();
		}
    	
    };
    
    private EventHandler<ActionEvent>  chequeWithdrawTransactionDetailsNoListener = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent actionEvent) {
			
			//
			Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		// insert what you would like to happen
    		anchorChequeWithdrawTransactionDetails.setVisible(false);
    		home();
		}
    	
    };
    
    /*
     * Cheque Transfer 
     * 
     * */
    
    // Anchor cheque Transfer 
    @FXML
    AnchorPane anchorChequeTransfer = new AnchorPane();
    
    // Button Cheque Transfer 
    
    
    @FXML
    Button buttonChequeTransfer0 = new Button();
    @FXML
    Button buttonChequeTransfer1 = new Button();
    @FXML
    Button buttonChequeTransfer2 = new Button();
    @FXML
    Button buttonChequeTransfer3 = new Button();
    @FXML
    Button buttonChequeTransfer4 = new Button();
    @FXML
    Button buttonChequeTransfer5 = new Button();
    @FXML
    Button buttonChequeTransfer6 = new Button();
    @FXML
    Button buttonChequeTransfer7 = new Button();
    @FXML
    Button buttonChequeTransfer8 = new Button();
    @FXML
    Button buttonChequeTransfer9 = new Button();
    @FXML
    Button buttonChequeTransferSubmit = new Button();
    @FXML
    Button buttonChequeTransferClear = new Button();
    
    @FXML
    Button buttonChequeTransferBack = new Button();
    
    // Label Cheque Transfer
    @FXML
    Label labelTransferChequeNo = new Label();
    
    @FXML
    Label labelTransferSavingsNo = new Label();
    
    @FXML
    Label labelTransferChequeBalance = new Label();
    
    @FXML
    Label labelPromptTransferAmount = new Label();
    
    @FXML
    Label labelTransferAmountError = new Label();
    
    // TextField 
    
    @FXML
    TextField amountTransferTextField = new TextField();
   
    
    public void chequeTransfer() {
    	//
    	String chequeAccountNo = Integer.toString(cheque.getChequeAccountNo()); // converts account number to string
    	labelTransferChequeNo.setText("From Cheque Account No\t: " + chequeAccountNo);
    	//
    	String savingsAccountNo = Integer.toString(savings.savingsAccountNo); // converts account number to string
    	labelTransferSavingsNo.setText("To Savings Account No\t\t: " + savingsAccountNo + "\t\t\tSavings Account Balance\t:" + moneyFormat.format(savings.getSavingsBalance()));
    	//
    	labelTransferChequeBalance.setText("Available Cheque Account Balance\t: " + moneyFormat.format(cheque.getChequeBalance()));
    	//
    	anchorChequeTransfer.setVisible(true);
    	anchorChequeMenu.setVisible(false);
    	//
    	labelTransferAmountError.setVisible(false);
    	amountTransferTextField.setPromptText("Enter an Amount");
    }
    //
 // cheque account withdraw keypad listeners 
    private EventHandler<ActionEvent> chequeTransferKeypadListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		amountTransferTextField.appendText(number); // shows the user inputs in the password 
    		//
    	}
    };
    
    //cheque account transfer clear button listener
    private EventHandler<ActionEvent> chequeTransferButtonClearListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		amountTransferTextField.clear(); // shows the user inputs in the password field
    		
    	}
    };
    
    //cheque transfer back button to go back to cheque menu
    
    private EventHandler<ActionEvent>  buttonChequeTransferBackListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		amountTransferTextField.clear(); // shows the user inputs in the password field
    		// to go back to cheque menu
    		anchorChequeMenu.setVisible(true);
        	anchorChequeTransfer.setVisible(false);
    	}
    };
    
    //
    private EventHandler<ActionEvent> chequeTransferButtonSubmitListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		try {
        		//
        		Button sourceComponent = (Button)actionEvent.getSource();
        		String number = sourceComponent.getText();
        		//
        		System.out.println(number);
        		// submits the amount the user would like to withdraw 
        		
        		labelTransferChequeBalance.setText("Available Cheque Account Balance :" + moneyFormat.format(cheque.getChequeBalance()));
            	//
        		Double amount = Double.parseDouble(amountTransferTextField.getText());
        		//
            	if (cheque.getChequeBalance() - amount >= 0) {
            		//
            		labelChequeTransferChequeNoConfirm.setText("From Cheque Account No\t: " + cheque.getChequeAccountNo());
            		labelChequeTransferSavingsNoConfirm.setText("To Savings Account No\t\t: " + savings.getSavingsAccountNo());
            		labelChequeTransferAmount.setText("Amount\t\t\t\t\t: " + moneyFormat.format(amount));      		
            		
            		// Creates an array which contains the transaction details, arrray is deleted if user cancles transaction
            		for (int i = 0; i < 1;i++) {
    					//
    					LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
    					String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
    					chequeTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
    					//
    					String transactionDescription = "Transfer";// this line of code is the name of the transaction
    					chequeTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
    					//
    					double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
    					chequeTransactionsHistory.setTransactionAmount(transactionAmount);;
    					//
    					String transactionID = "TRA00" + randomID + "CH";
    					chequeTransactionsHistory.setTransactionID(transactionID);
    					randomID++;
    					//
    					String transactionType = "Debit";
    					chequeTransactionsHistory.setTransactionType(transactionType);
    					// line of code below creates a new array
    					newChequeTransactions.add(new ChequeTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
    					//
    					
    				}
            		//
            		anchorChequeTransferConfirm.setVisible(true);
            		anchorChequeTransfer.setVisible(false);
            		//
            		amountTransferTextField.clear();
            		//
            		
            	} else {
            		//
            		labelTransferAmountError.setText("Maxium withdrawable amount : " +  moneyFormat.format(cheque.getChequeBalance()) );
            		labelTransferAmountError.setVisible(true);
            		System.err.println("Insufficient funds, Maxium withdrwable amount");
            		amountTransferTextField.clear();
            		
            	}
        		
    
    			
    		} catch (Exception e) {
    			//
    			labelTransferAmountError.setText("Error : Please enter an amount. ");
        		labelTransferAmountError.setVisible(true);
        		//
        		System.err.println("Error : " + e );
    		}
    	}
    };
    
    
    /*
     * Cheque Transfer Confirmation
     * */
    
    // Anchor Cheque Transfer confirmation
    @FXML
    AnchorPane anchorChequeTransferConfirm = new AnchorPane();
    
    // Label Cheque Transfer 
    @FXML
    Label labelChequeTransferChequeNoConfirm = new Label();
    
    @FXML
    Label labelChequeTransferSavingsNoConfirm = new Label();
    
    @FXML
    Label labelChequeTransferAmount = new Label();
    
    // Button Cheque transfer confirmation
    
    @FXML
    Button buttonChequeTransferConfirmConfirm = new Button();
    
    @FXML 
    Button buttonChequeTransferConfirmBack = new Button();
    //
    
    //cheque transfer confrim button to go back to cheque menu
    
    
    
    private EventHandler<ActionEvent>  buttonChequeTransferConfrimConfirmListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		amountTransferTextField.clear(); // shows the user inputs in the password field
    		// 
    		anchorChequeTransferConfirm.setVisible(false);
    		anchorChequeMenu.setVisible(false);
        	anchorChequeTransfer.setVisible(false);
        	anchorCheqeuTransferSuccessfull.setVisible(true);
        	// crrent tranaction 
        	
        	/*
        	 * Gets amount from transaction History
        	 * */

        	int beneficiaryCode = newChequeTransactions.size() - 1; // Get the index of the last element
        	
        	if (beneficiaryCode >= 0 && beneficiaryCode < newChequeTransactions.size()) { // for each loop to display the current beneficiary list
    			// sets the new balance after subtracting amount from balance
        		double newChequeBalance = cheque.getChequeBalance() - newChequeTransactions.get(beneficiaryCode).getTransactionAmount();
    			cheque.setChequeBalance(newChequeBalance);
    			
    			double newSavingsBalance = savings.getSavingsBalance() + newChequeTransactions.get(beneficiaryCode).getTransactionAmount();
        		savings.setSavingsBalance(newSavingsBalance);
    			//
    			System.out.println("\nUnique Beneficiary code :" + beneficiaryCode);
    			System.out.println("Transaction TransactionID\t\t\t: " + newChequeTransactions.get(beneficiaryCode).getTransactionID());
    			System.out.println("Transaction Date\t\t\t: " +newChequeTransactions.get(beneficiaryCode).getTransactionDate());
    			System.out.println("Transaction Name\t\t\t: " +newChequeTransactions.get(beneficiaryCode).getTransactionDescription());
    			System.out.println("Transaction Amount\t\t\t: " + moneyFormat.format(newChequeTransactions.get(beneficiaryCode).getTransactionAmount()));
    			//
    			System.out.println("");
    			//
    		}
    	}
    };
    //cheque transfer back button to go back to cheque menu
    
    private EventHandler<ActionEvent>  buttonChequeTransferConfirmBackListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		amountTransferTextField.clear(); // shows the user inputs in the password field
    		// to go back to cheque menu
        	anchorChequeTransferConfirm.setVisible(false);
        	//
        	deleteLastTransaction(); 
        	chequeTransfer();
        	//
        	
        	
    	}
    };
    
    /*
     * Cheque Transfer successful
     * */
    
    //Anchor Cheque Transfer successful
    @FXML
    AnchorPane anchorCheqeuTransferSuccessfull = new AnchorPane();
    
    @FXML
    Button buttonChequeTransferSuccessfullPrint = new Button();
    
    @FXML
    Button buttonChequeTransferSuccessfullExit = new Button();
    
    @FXML
    Button buttonChequeTransferSuccessfullView = new Button();
    
    private EventHandler<ActionEvent>  chequeTransferSuccessfullViewListener = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent actionEvent) {
			
			//
			Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		int beneficiaryCode = newChequeTransactions.size() - 1; // Get the index of the last element
			//
    		labelChequeTransferTransactionDetailsChequeBalance.setText("Cheque Account : " + cheque.getChequeAccountNo() + "\t\t\t\t\tNew Cheque Balance : " + moneyFormat.format(cheque.getChequeBalance()));
    		labelChequeTransferTransactionDetailsSavingsBalance.setText("New Savings Balance : " + moneyFormat.format(savings.getSavingsBalance()));
    		//
			if (beneficiaryCode >= 0 && beneficiaryCode < newChequeTransactions.size()) { // for each loop to display the current beneficiary list
    			//
				// Cheque Transaction details
    	    	labelChequeTransferTransactionDetailsID.setText("TransactionID\t\t\t: "  + newChequeTransactions.get(beneficiaryCode).getTransactionID());
    	    	labelChequeTransferTransactionDetailsDate.setText("Transaction Date\t\t: " + newChequeTransactions.get(beneficiaryCode).getTransactionDate());
    	    	labelChequeTransferTransactionDetailsName.setText("Transaction Name\t\t: "  + newChequeTransactions.get(beneficiaryCode).getTransactionDescription());
    	    	labelChequeTransferTransactionDetailsAmount.setText("Transaction Amount\t: "  +  moneyFormat.format(newChequeTransactions.get(beneficiaryCode).getTransactionAmount()));
				//
    			System.out.println("\nUnique Beneficiary code :" + beneficiaryCode);
    			System.out.println("TransactionID\t\t\t: " + newChequeTransactions.get(beneficiaryCode).getTransactionID());
    			System.out.println("Transaction Date\t\t: " + newChequeTransactions.get(beneficiaryCode).getTransactionDate());	
    			System.out.println("Transaction Name\t\t: " + newChequeTransactions.get(beneficiaryCode).getTransactionDescription());		
    			System.out.println("Transaction Amount\t\t\t: " + moneyFormat.format(newChequeTransactions.get(beneficiaryCode).getTransactionAmount()));
    	    	
    	    	// Displays ID, date, name and amount    
    			anchorChequeTransferTransactionDetails.setVisible(true);
    	    	anchorCheqeuTransferSuccessfull.setVisible(false);
    			//
    			System.out.println("");
    		}
    		
		}
    	
    };
    
    private EventHandler<ActionEvent>  buttonChequeTransferSuccessfullExitListener = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent actionEvent) {
			
			//
			Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		home();
    		
		}
    	
    };
    /*
     * Cheque Transfer transaction details 
     * */
    
    @FXML
    AnchorPane anchorChequeTransferTransactionDetails = new AnchorPane();
    //
    
    //Buttons for cheque transaction details
    @FXML Button buttonChequeTransferTransactionDetailsYes = new Button();
    
    @FXML Button buttonChequeTransferTransactionDetailsNo = new Button();
    
    //Labels for cheque withdraw transaction details
    
    @FXML Label labelChequeTransferTransactionDetailsChequeBalance = new Label();
    
    @FXML Label labelChequeTransferTransactionDetailsSavingsBalance = new Label();
    
    
    @FXML Label labelChequeTransferTransactionDetailsID = new Label();
    
    @FXML Label labelChequeTransferTransactionDetailsDate = new Label();
    
    @FXML Label labelChequeTransferTransactionDetailsAmount = new Label();
    
    @FXML Label labelChequeTransferTransactionDetailsName = new Label();
    
    private EventHandler<ActionEvent>  buttonChequeTransferTransactionDetailsYesListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		amountTransferTextField.clear(); // shows the user inputs in the password field
    		// to go back to cheque menu
    		anchorChequeMenu.setVisible(true);
    		anchorChequeTransferTransactionDetails.setVisible(false);
        	//
        	
    	}
    };
    
    private EventHandler<ActionEvent>  buttonChequeTransferTransactionDetailsNoListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		amountTransferTextField.clear(); // shows the user inputs in the password field
    		// to go back to cheque menu
    		anchorChequeTransferTransactionDetails.setVisible(false);
    		anchorIndex.setVisible(true);
        	//
        	
    	}
    };
    
    /*
     * Cheque Pay
     * */
    
    // Anchor Cheque Pay
    
    @FXML AnchorPane anchorChequePay = new AnchorPane();
    
    // Button cheque Pay Menu buttons   
    
    @FXML Button buttonChequePayMenuOnceOff = new Button(); // To do a once off payment
    
    @FXML Button buttonChequePayMenuCreateBeneficiary = new Button(); // To create a beneficiary
    
    @FXML Button buttonChequePayMenuExisitingbeneficiary = new Button(); // To pay existing beneficiary
    
    @FXML Button buttonChequePayMenuCancel = new Button();
    
    //
    @FXML Label labelChequePayOnceOFFChequeNo = new Label();
    
    @FXML Label labelChequePayOnceOFFChequeBalance = new Label();
    
    // Prompts to for user to fill in amount and account recipient account details
    
    @FXML Label labelPromptChequePayOnceOffAmount = new Label();
    
    @FXML Label labelPromptChequePayOnceOffAccountNo = new Label();
    
    //
    @FXML Label labelChequePayOnceOffBalance = new Label();
    
    // TextField Cheque Pay Once Off
    
    @FXML TextField textFieldChequePayOnceOffAmount = new TextField();
   
    @FXML TextField textFieldChequePayOnceOffRecipientAccount = new TextField();
    
    private EventHandler<ActionEvent> buttonChequePayMenuOnceOffListener = new EventHandler<ActionEvent>() {
    	// Cheque pay - Once Off
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		chequePayOnceOffMenu();
    		
    	}
    };
    
   
    
    private EventHandler<ActionEvent> buttonChequePayMenuCancelListener = new EventHandler<ActionEvent>() {
    	// Cheque pay - Once Off
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		chequeAcountMenu();
    		
    	}
    };
    
    public void chequePayOnceOffMenu() {
    	//
    	// Opens the once off payment anchor
		anchorChequePayOnceOff.setVisible(true);
		anchorChequePay.setVisible(false);
		//
		textFieldChequePayOnceOffRecipientAccount.requestFocus();
		textFieldChequePayOnceOffRecipientAccount.setVisible(true);
		textFieldChequePayOnceOffRecipientAccount.setDisable(false);
		//
		gridPaneChequePayOnceRecipientOff.setVisible(true);
		
		gridPaneChequePayAmountOnceOff.setVisible(false);
		
		labelPromptChequePayOnceOffAccountNo.setVisible(true);
		labelPromptChequePayOnceOffAccountNo.setText("Please Enter recipient account number : ");
		//
		textFieldChequePayOnceOffAmount.setVisible(false);
		textFieldChequePayOnceOffAmount.setDisable(true);
		//
		labelPromptChequePayOnceOffAmount.setVisible(false);
		labelPromptChequePayOnceOffAmount.setText("Please Enter payement amount ");
		//
		labelChequePayOnceOFFChequeNo.setText("Cheque Account :" + cheque.getChequeAccountNo());
		labelChequePayOnceOffBalance.setText("Available Cheque Account Balance :" + moneyFormat.format(cheque.getChequeBalance()));
    	//
		
    }
    //opens payment confirmation page
    private EventHandler<ActionEvent> buttonChequePayOnceOffSubmitListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		try {
        		//
        		Button sourceComponent = (Button)actionEvent.getSource();
        		String number = sourceComponent.getText();
        		//
        		System.out.println(number);
        		//
        		textFieldChequePayOnceOffRecipientAccount.setDisable(true);
        		//
        		textFieldChequePayOnceOffAmount.requestFocus();
        		textFieldChequePayOnceOffAmount.setVisible(true);
        		textFieldChequePayOnceOffAmount.setDisable(false);
        		//
        		gridPaneChequePayAmountOnceOff.setVisible(true);
        		gridPaneChequePayOnceRecipientOff.setVisible(false);
        		//
        		labelPromptChequePayOnceOffAmount.setVisible(true);
        		labelPromptChequePayOnceOffAmount.setText("Please Enter payment amount ");
        		//
    		} catch (Exception e) {
    			//
    			labelChequePayOnceOffAmountError.setText("Please enter payment account");
        		labelChequePayOnceOffAmountError.setVisible(true);
    		}
    	}
    };
    
    
    
    
    
    public void chequePay() {
    	// Doesnt have account details like other cheque account options
    	anchorChequeMenu.setVisible(false);
    	//
    	anchorChequePay.setVisible(true);
    	anchorChequePayOnceOff.setVisible(false);
    	//
    	anchorChequePayCreateBeneficiary.setVisible(false); // hide the create beneficiary page
    	//
    	labelChequePayOnceOffAmountError.setVisible(false);
    	//
    	textFieldChequePayOnceOffAmount.setPromptText("Enter Amount");
    	textFieldChequePayOnceOffRecipientAccount.setPromptText("Enter recipient Account");
    }
    
    /*
     * Cheque Pay Once off
     * */
    
    // Anchor cheque Once off Payment
    
    @FXML AnchorPane anchorChequePayOnceOff = new AnchorPane();
    
    //Gridpane 
    
    @FXML GridPane gridPaneChequePayOnceRecipientOff = new GridPane();
    // Button cheque once off payement
    @FXML
    Button buttonChequePayOnceOff0 = new Button();
    @FXML
    Button buttonChequePayOnceOff1 = new Button();
    @FXML
    Button buttonChequePayOnceOff2 = new Button();
    @FXML
    Button buttonChequePayOnceOff3 = new Button();
    @FXML
    Button buttonChequePayOnceOff4 = new Button();
    @FXML
    Button buttonChequePayOnceOff5 = new Button();
    @FXML
    Button buttonChequePayOnceOff6 = new Button();
    @FXML
    Button buttonChequePayOnceOff7 = new Button();
    @FXML
    Button buttonChequePayOnceOff8 = new Button();
    @FXML
    Button buttonChequePayOnceOff9 = new Button();
    @FXML
    Button buttonChequePayAmountOnceOffProceed = new Button();
    @FXML
    Button buttonChequePayOnceOffClear = new Button();
    
    @FXML
    Button buttonChequePayOnceOffBack = new Button();
    
    @FXML GridPane gridPaneChequePayAmountOnceOff = new GridPane();
    
    @FXML
    Button buttonChequePayAmountOnceOff0 = new Button();
    @FXML
    Button buttonChequePayAmountOnceOff1 = new Button();
    @FXML
    Button buttonChequePayAmountOnceOff2 = new Button();
    @FXML
    Button buttonChequePayAmountOnceOff3 = new Button();
    @FXML
    Button buttonChequePayAmountOnceOff4 = new Button();
    @FXML
    Button buttonChequePayAmountOnceOff5 = new Button();
    @FXML
    Button buttonChequePayAmountOnceOff6 = new Button();
    @FXML
    Button buttonChequePayAmountOnceOff7 = new Button();
    @FXML
    Button buttonChequePayAmountOnceOff8 = new Button();
    @FXML
    Button buttonChequePayAmountOnceOff9 = new Button();
    @FXML
    Button buttonChequePayAmountOnceOffSubmit = new Button();
    @FXML
    Button buttonChequePayAmountOnceOffClear = new Button();
    
    
    // cheque account pay keypad listeners to amend reciepient account textfield
    private EventHandler<ActionEvent> buttonChequePayOnceOffRecipientKeyPadListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		textFieldChequePayOnceOffRecipientAccount.appendText(number);
    		//
    	}
    };
    
    // cheque account pay keypad listeners to amend amount user wants to make payment account textfield
    private EventHandler<ActionEvent> buttonChequePayOnceOffAmountKeyPadListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		textFieldChequePayOnceOffAmount.appendText(number);
        	//
    		
    		
    	}
    };
 
    
    //cheque account transfer clear button listener
    private EventHandler<ActionEvent> buttonChequePayOnceOffClearListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		textFieldChequePayOnceOffRecipientAccount.clear(); // shows the user inputs in the password field
    		
    	}
    };
    
    //cheque account transfer clear button listener
    private EventHandler<ActionEvent> buttonChequePayOnceOffAmountClearListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		textFieldChequePayOnceOffAmount.clear();
    		// shows the user inputs in the password field
    		
    	}
    };
    //
    //cheque Pay back button to go back to cheque menu
    
    private EventHandler<ActionEvent>  buttonChequePayOnceOffBackListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		textFieldChequePayOnceOffAmount.clear();
    		textFieldChequePayOnceOffRecipientAccount.clear(); // shows the user inputs in the password field
    		//
    		
    		chequePayOnceOffMenu(); // resets textFields and GridPanes
    		// to go back to Cheque menu
    		chequePay();
    	}
    };
    
 // 
    private EventHandler<ActionEvent> buttonChequePayOnceOffProceedListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		try {
    			//

        		//
        		Button sourceComponent = (Button)actionEvent.getSource();
        		String number = sourceComponent.getText();
        		//
        		System.out.println(number);
        		//
        		// submits the amount the user would like to withdraw 
        		labelWithdrawChequeBalance.setText("Available Cheque Account Balance :" + moneyFormat.format(cheque.getChequeBalance()));
            	//
        		Double amount = Double.parseDouble(textFieldChequePayOnceOffAmount.getText());
        		String recipientAccountNo = textFieldChequePayOnceOffRecipientAccount.getText();
        		//
            	if (cheque.getChequeBalance() - amount >= 0) {
            		//
            		labelChequePayOnceOffChequeNoConfirm.setText("Cheque Account No\t\t: " + cheque.getChequeAccountNo());
            		labelChequePayOnceOffRecipientNoConfirm.setText("Recipient Account No\t\t: " + recipientAccountNo);
            		labelChequePayOnceOffAmount.setText("Amount\t\t\t\t\t: " + moneyFormat.format(amount));      		
            		
            		// Creates an array which contains the transaction details, arrray is deleted if user cancles transaction
            		for (int i = 0; i < 1;i++) {
    					//
    					LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
    					String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
    					chequeTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
    					//
    					
    					String transactionDescription = "Payment to Account No : " + recipientAccountNo;// this line of code is the name of the transaction
    					chequeTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
    					//
    					double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
    					chequeTransactionsHistory.setTransactionAmount(transactionAmount);;
    					//
    					String transactionID = "TRA00" + randomID + "CH";
    					chequeTransactionsHistory.setTransactionID(transactionID);
    					randomID++;
    					//
    					String transactionType = "Debit";
    					chequeTransactionsHistory.setTransactionType(transactionType);
    					// line of code below creates a new array
    					newChequeTransactions.add(new ChequeTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
    					//
    				}
            		//
            		//
            		textFieldChequePayOnceOffRecipientAccount.clear();
            		textFieldChequePayOnceOffAmount.clear();
            		//
            		anchorChequePayOnceOffConfirmation.setVisible(true);
            		anchorChequePayOnceOff.setVisible(false);
            		//
            		
            	} else {
            		//
            		labelChequePayOnceOffAmountError.setText("Maxium withdrawable amount : " +  moneyFormat.format(cheque.getChequeBalance()) );
            		labelChequePayOnceOffAmountError.setVisible(true);
            		System.err.println("Insufficient funds, Maxium withdrwable amount");
            		//
            		textFieldChequePayOnceOffAmount.clear();
            		
            	}
        		
        	
        	
    		} catch (Exception e) {
    			//
        		labelChequePayOnceOffAmountError.setText("Please enter Recipient account");
        		labelChequePayOnceOffAmountError.setVisible(true);
    			
    		}
    	}
    };
    
    /*
     * Cheque once off payment confirmation
     * */
    
    
    @FXML AnchorPane anchorChequePayOnceOffConfirmation = new AnchorPane();
    
    // Label cheque menu once off payment
    @FXML Label labelChequePayOnceOffAmountError = new Label();
    
    @FXML Label labelChequePayOnceOffChequeNoConfirm = new Label();
    
    @FXML Label labelChequePayOnceOffAmount = new Label();
    
    @FXML Label labelChequePayOnceOffSavingsNoConfirm = new Label();
    
    @FXML Label labelChequePayOnceOffRecipientNoConfirm = new Label();
    
    // Buttons cheque once off payment 
    
    @FXML Button buttonChequePayOnceOffConfirmConfirm = new Button();
    
    @FXML Button buttonChequePayOnceOffConfirmBack = new Button();
    
//cheque transfer confrim button to go back to cheque menu
    
    private EventHandler<ActionEvent>  buttonChequePayOnceOffConfirmConfirmListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		textFieldChequePayOnceOffRecipientAccount.clear();
    		textFieldChequePayOnceOffAmount.clear();
    		//
    		// 
    		anchorChequePayOnceOffConfirmation.setVisible(false);
        	anchorCheqeuPayOnceOffSuccessfull.setVisible(true);
        	/*
        	 * Gets amount from transaction History
        	 * */

        	int beneficiaryCode = newChequeTransactions.size() - 1; // Get the index of the last element
        	
        	if (beneficiaryCode >= 0 && beneficiaryCode < newChequeTransactions.size()) { // for each loop to display the current beneficiary list
    			// sets the new balance after subtracting amount from balance
        		double newChequeBalance = cheque.getChequeBalance() - newChequeTransactions.get(beneficiaryCode).getTransactionAmount();
    			cheque.setChequeBalance(newChequeBalance);
    			
    			double newSavingsBalance = savings.getSavingsBalance() + newChequeTransactions.get(beneficiaryCode).getTransactionAmount();
        		savings.setSavingsBalance(newSavingsBalance);
    			//
    			System.out.println("\nUnique Beneficiary code :" + beneficiaryCode);
    			System.out.println("TransactionID\t\t\t: " + newChequeTransactions.get(beneficiaryCode).getTransactionID());
    			System.out.println("Transaction Date\t\t\t: " +newChequeTransactions.get(beneficiaryCode).getTransactionDate());
    			System.out.println("Transaction Name\t\t\t: " +newChequeTransactions.get(beneficiaryCode).getTransactionDescription());
    			System.out.println("Transaction Amount\t\t\t: " + moneyFormat.format(newChequeTransactions.get(beneficiaryCode).getTransactionAmount()));
    			//
    			System.out.println("");
    			//
    		}
    	}
    };
    //cheque transfer back button to go back to cheque menu
    
    private EventHandler<ActionEvent>  buttonChequePayOnceOffConfirmBackListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		chequePayOnceOffMenu(); // resets textFields and GridPanes
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		amountTransferTextField.clear(); // shows the user inputs in the password field
    		// to go back to cheque menu
    		// Opens the once off payment anchor
    		anchorChequePayOnceOff.setVisible(true);
    		anchorChequePay.setVisible(false);
    		
    		anchorChequeTransfer.setVisible(false);
        	anchorChequeTransferConfirm.setVisible(false);
        	
        	anchorChequePayOnceOffConfirmation.setVisible(false);
        	anchorCheqeuPayOnceOffSuccessfull.setVisible(false);
        	//
        	//
    		textFieldChequePayOnceOffRecipientAccount.clear();
    		textFieldChequePayOnceOffAmount.clear();
    		//
    		deleteLastTransaction();
    		//
        	//
        	
        	
    	}
    };
    
    /*
     * Cheque Once Off pay successfull
     * 
     * */
    
    //Anchorpane cheque once off pay
    @FXML AnchorPane anchorCheqeuPayOnceOffSuccessfull = new AnchorPane();
    
    // Button cheque once of pay
    @FXML Button buttonChequePayOnceOffSuccessfullView = new Button();
    
    @FXML Button buttonChequePayOnceOffSuccessfullExit = new Button();
    
    private EventHandler<ActionEvent>  buttonChequePayOnceOffSuccessfullViewListener = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent actionEvent) {
			
			//
			Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		int beneficiaryCode = newChequeTransactions.size() - 1; // Get the index of the last element
			//
    		labelChequeOnceOffPayTransactionDetailsChequeBalance.setText("Cheque Account : " + cheque.getChequeAccountNo() + "\t\t\t\t\tNew Cheque Balance : " + moneyFormat.format(cheque.getChequeBalance()));
    		//
			if (beneficiaryCode >= 0 && beneficiaryCode < newChequeTransactions.size()) { // for each loop to display the current beneficiary list
    			//
				// Cheque Transaction details
				labelChequeOnceOffTransactionDetailsID .setText("TransactionID\t\t\t: "  + newChequeTransactions.get(beneficiaryCode).getTransactionID());
				labelChequePayOnceOffTransactionDetailsDate.setText("Transaction Date\t\t: " + newChequeTransactions.get(beneficiaryCode).getTransactionDate());
    	    	labelChequePayOnceOffTransactionDetailsName.setText("Transaction Name\t\t: "  + newChequeTransactions.get(beneficiaryCode).getTransactionDescription());
    	    	labelChequePayOnceOffTransactionDetailsAmount.setText("Transaction Amount\t: "  +  moneyFormat.format(newChequeTransactions.get(beneficiaryCode).getTransactionAmount()));
				//
    			System.out.println("\nUnique Beneficiary code :" + beneficiaryCode);
    			System.out.println("TransactionID\t\t\t: " + newChequeTransactions.get(beneficiaryCode).getTransactionID());
    			System.out.println("Transaction Date\t\t: " + newChequeTransactions.get(beneficiaryCode).getTransactionDate());	
    			System.out.println("Transaction Name\t\t: " + newChequeTransactions.get(beneficiaryCode).getTransactionDescription());		
    			System.out.println("Transaction Amount\t\t: " + moneyFormat.format(newChequeTransactions.get(beneficiaryCode).getTransactionAmount()));
    	    	
    	    	// Displays ID, date, name and amount    
    			anchorChequePayOnceOffTransactionDetails.setVisible(true);
    	    	anchorCheqeuPayOnceOffSuccessfull.setVisible(false);
    			//
    			System.out.println("");
    		}
    		
		}
    	
    };
    
    private EventHandler<ActionEvent>  buttonChequePayOnceOffSuccessfullExitListener = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent actionEvent) {
			
			//
			Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		home();
    		//
    		
    		
		}
    	
    };
    /*
     * Cheque Pay once off , view transaction History
     * */
    
    // Anchors cheque once of payment transaction details
    @FXML AnchorPane anchorChequePayOnceOffTransactionDetails = new AnchorPane();
    
    // Labels cheque once off payment viwe transaction History
    
    @FXML Label labelChequeOnceOffPayTransactionDetailsChequeBalance = new Label();
    
    @FXML Label labelChequeOnceOffTransactionDetailsID = new Label();
    
    @FXML Label labelChequePayOnceOffTransactionDetailsName = new Label();
    
    @FXML Label labelChequePayOnceOffTransactionDetailsAmount =  new Label();
    
    @FXML Label labelChequePayOnceOffTransactionDetailsDate = new Label();
    
    // Buttons Vheque oce off payment transaction Details
    
    @FXML Button buttonChequePayOnceOffTransactionDetailsYes = new Button();
    
    @FXML Button buttonChequePayOnceOffTransactionDetailsNo = new Button();
    //
    private EventHandler<ActionEvent>  buttonChequePayOnceOffTransactionDetailsYesListener = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent actionEvent) {
			
			//
			Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		// insert what you would like to happen
    		anchorChequePayOnceOffTransactionDetails .setVisible(false);
    		chequeAcountMenu();
		}
    	
    };
    
    private EventHandler<ActionEvent>  buttonChequePayOnceOffTransactionDetailsNoListener = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent actionEvent) {
			
			//
			Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		// insert what you would like to happen
    		anchorChequePayOnceOffTransactionDetails.setVisible(false);
    		anchorIndex.setVisible(true); 
		}
    	
    };
    
    /*
	 * Cheque Pay - Create beneficiary 
	 * */
	
    // Anchor for cheque pay - Create beneficiary
    
	@FXML AnchorPane anchorChequePayCreateBeneficiary = new AnchorPane();
	
	// TextField - create Beneficiary
	
	@FXML TextField textFieldChequePayCreateBeneficiaryName = new TextField(); // texttField for beneficiary account number

	@FXML TextField textFieldChequePayCreateBeneficiaryAccountNo = new TextField(); // texttField for beneficiary account number
	
	@FXML TextField textFieldChequePayCreateBeneficiaryBankName = new TextField(); // texttField for beneficiary account number

	// GridPane cheque pay - Create beneficiary
	
	@FXML GridPane gridPaneChequePayCreateBeneficiaryName = new GridPane();
	
	@FXML GridPane gridPaneChequePayCreateBeneficiaryAccountNo = new GridPane();
	
	@FXML GridPane gridPaneChequePayCreateBeneficiaryBankName = new GridPane();
	
	// Label cheque pay - Create beneficiary
	
	@FXML Label labelChequePayCreateBeneficaryError = new Label();
	
	
	
    private EventHandler<ActionEvent> buttonChequePayMenuCreateBeneficiaryListener = new EventHandler<ActionEvent>() {
    	// buttonChequePayMenuCreateBeneficiary
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		// Opens the once off payment anchor
    		createBeneficiary();
    		//
    	}
    };
    
    public void createBeneficiary() {
		// create beneficiary
    	anchorChequePayCreateBeneficiary.setVisible(true); // Displays the create beneficiary page
		anchorChequePay.setVisible(false); //Hides cheque account pay menu
		
		// To display Create beneficiary keypad and textField 
		textFieldChequePayCreateBeneficiaryName.setPromptText("Beneficiary Name");
		textFieldChequePayCreateBeneficiaryName.setDisable(false);
		textFieldChequePayCreateBeneficiaryName.requestFocus(); // Focuses on the first available textField
		gridPaneChequePayCreateBeneficiaryName.setVisible(true);
		gridPaneChequePayCreateBeneficiaryName.setDisable(false);
		
		textFieldChequePayCreateBeneficiaryAccountNo.setDisable(true);
		gridPaneChequePayCreateBeneficiaryAccountNo.setVisible(false);
		gridPaneChequePayCreateBeneficiaryAccountNo.setDisable(true);
		
		textFieldChequePayCreateBeneficiaryBankName.setDisable(true);
		gridPaneChequePayCreateBeneficiaryBankName.setVisible(false);
		gridPaneChequePayCreateBeneficiaryBankName .setDisable(true);
		//
	}
    
    // Cheque Pay - Create beneficiary Name keypad listener
    
    @FXML Button buttonChequeCreateBeneficiaryName0 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryName1 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryName2 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryName3 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryName4 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryName5 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryName6 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryName7 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryName8 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryName9 = new Button();
    
    @FXML Button buttonChequePayCreateBeneficiaryNameProceed = new Button();
    @FXML Button buttonChequeCreateBeneficiaryNameClear = new Button();
    
    @FXML Button buttonChequePayCreateBeneficiaryBack = new Button();
    
    private EventHandler<ActionEvent> keypadChequeCreateBeneficiaryNameListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		
    		textFieldChequePayCreateBeneficiaryName.appendText(number);
    	}
    };
    
    private EventHandler<ActionEvent> buttonChequeCreateBeneficiaryNameClearListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		textFieldChequePayCreateBeneficiaryName.clear();
    	}
    };
    
    
    
    private EventHandler<ActionEvent> buttonChequeCreateBeneficiaryNameProceedListener = new EventHandler<ActionEvent>() {
    	// promots user to insert beneficiary account number 
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		// To display Create beneficiary keypad and textField 
    		
    		textFieldChequePayCreateBeneficiaryAccountNo.requestFocus();
    		textFieldChequePayCreateBeneficiaryAccountNo.setPromptText("Beneficiary Account No ");
    		textFieldChequePayCreateBeneficiaryAccountNo.setDisable(false);
    		gridPaneChequePayCreateBeneficiaryAccountNo.setVisible(true);
    		gridPaneChequePayCreateBeneficiaryAccountNo.setDisable(false);
    		
    		textFieldChequePayCreateBeneficiaryBankName.setDisable(true);
    		gridPaneChequePayCreateBeneficiaryBankName.setVisible(false);
    		gridPaneChequePayCreateBeneficiaryBankName .setDisable(true);
    		
    		textFieldChequePayCreateBeneficiaryName.setDisable(true); // Focuses on the first available textField
    		gridPaneChequePayCreateBeneficiaryName.setVisible(false);
    		gridPaneChequePayCreateBeneficiaryName.setVisible(true);
    		//
    		
    	}
    	
    	
    };
    
    private EventHandler<ActionEvent> buttonChequePayCreateBeneficiaryBackListener = new EventHandler<ActionEvent>() {
    	// promots user to insert beneficiary account number 
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		
    		textFieldChequePayCreateBeneficiaryName.clear();
    		textFieldChequePayCreateBeneficiaryBankName.clear();
    		textFieldChequePayCreateBeneficiaryAccountNo.clear();
    		
    		createBeneficiary(); // resets the create beneficiary textField and GridPane
    		//
    		chequePay();
    		
    	}
    	
    	
    };
    
 // Cheque Pay - Create beneficiary Account No keypad listener
    
    @FXML Button buttonChequeCreateBeneficiaryAccountNo0  = new Button();
    @FXML Button buttonChequeCreateBeneficiaryAccountNo1 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryAccountNo2 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryAccountNo3 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryAccountNo4 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryAccountNo5 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryAccountNo6 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryAccountNo7 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryAccountNo8 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryAccountNo9 = new Button();
    
    @FXML Button buttonChequeCreateBeneficiaryAccountProceed = new Button();
    @FXML Button buttonChequeCreateBeneficiaryAccountClear = new Button();
    
    // Cheque Pay - Create beneficiary AccountNo keypad listener
    
    private EventHandler<ActionEvent> keypadChequeCreateBeneficiaryAccountListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		textFieldChequePayCreateBeneficiaryAccountNo.appendText(number);
    	}
    };
    
    private EventHandler<ActionEvent> buttonChequeCreateBeneficiaryAccountClearListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		textFieldChequePayCreateBeneficiaryAccountNo.clear();
    	}
    };
    
    
    private EventHandler<ActionEvent> buttonChequeCreateBeneficiaryAccountProceedListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		textFieldChequePayCreateBeneficiaryBankName.requestFocus();
    		textFieldChequePayCreateBeneficiaryBankName.setPromptText("Bank Name :  ");
    		textFieldChequePayCreateBeneficiaryBankName.setDisable(false);
    		gridPaneChequePayCreateBeneficiaryBankName.setVisible(true);
    		gridPaneChequePayCreateBeneficiaryBankName.setDisable(false);
    		
    		textFieldChequePayCreateBeneficiaryAccountNo.setDisable(true);
    		gridPaneChequePayCreateBeneficiaryAccountNo.setVisible(false);
    		gridPaneChequePayCreateBeneficiaryAccountNo .setDisable(true);
    		
    		textFieldChequePayCreateBeneficiaryName.setDisable(true); // Focuses on the first available textField
    		gridPaneChequePayCreateBeneficiaryName.setVisible(false);
    		gridPaneChequePayCreateBeneficiaryName.setDisable(true);
    		//
    		
    	}
    };
    
    // keypad for bank name
    
    @FXML Button buttonChequeCreateBeneficiaryBankName00 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryBankName1 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryBankName2 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryBankName3 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryBankName4 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryBankName5 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryBankName6 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryBankName7 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryBankName8 = new Button();
    @FXML Button buttonChequeCreateBeneficiaryBankName9 = new Button();
    
    @FXML Button buttonChequeCreateBeneficiaryBankNameProceed = new Button();
    @FXML Button buttonChequeCreateBeneficiaryBankNameClear = new Button();
    
    private EventHandler<ActionEvent> keypadChequeCreateBeneficiaryBankNameListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		textFieldChequePayCreateBeneficiaryBankName.appendText(number);
    	}
    };
    private EventHandler<ActionEvent> buttonChequeCreateBeneficiaryBankNameClearListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		textFieldChequePayCreateBeneficiaryBankName.clear();
    	}
    };
    
    private EventHandler<ActionEvent> buttonChequeCreateBeneficiaryBankNameProceedListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		textFieldChequePayCreateBeneficiaryBankName.getText();
    		textFieldChequePayCreateBeneficiaryName.getText();
    		textFieldChequePayCreateBeneficiaryAccountNo.getText();
    		//
    		for (int i = 0; i < 1; i++) {
    			//
    			String beneficiaryName = textFieldChequePayCreateBeneficiaryName.getText();
    			beneficiaries.setBeneficiaryName(beneficiaryName);
    			//
    			String beneficiaryBank = textFieldChequePayCreateBeneficiaryBankName.getText(); 
    			beneficiaries.setBeneficiaryBank(beneficiaryBank);
    			//
    			String beneficiaryAccountNumber = textFieldChequePayCreateBeneficiaryAccountNo.getText();
    			beneficiaries.setBeneficiaryAccountNumber(beneficiaryAccountNumber);
    		
    			newBeneficiary.add(new Beneficiaries(beneficiaryName,beneficiaryAccountNumber, beneficiaryBank));
    			//
    			for(Beneficiaries beneficiary : newBeneficiary) {
					//
					System.out.println("Beneficary created successfully : ");
					//
					System.out.println("\n\tBeneficiary Name\t: " + beneficiary.getBeneficiaryName());
					System.out.println("\tBeneficiary Bank\t: " + beneficiary.getBeneficiaryBank());
					System.out.println("\tBeneficiary Account\t: " + beneficiary.getBeneficiaryAccountNumber());
					//
					labelChequeCrateBeneficiaryName.setText("Beneficiary Name\t\t:" + beneficiary.getBeneficiaryName());
					labelChequeCrateBeneficiaryAccount.setText("Beneficiary Bank\t\t:" + beneficiary.getBeneficiaryBank());
					labelChequeCrateBeneficiaryBank.setText("Beneficiary Account\t\t: " + beneficiary.getBeneficiaryAccountNumber());
					break;
					}
    		
    		}		
    		anchorChequeCreateBeneficiaryConfirmation.setVisible(true);
    		anchorChequePayCreateBeneficiary.setVisible(false);
    		
    	}
    };
    
    /*
     * Create beneficiary confirmation
     * */
    
    @FXML AnchorPane anchorChequeCreateBeneficiaryConfirmation = new AnchorPane();
    
    //Label 
    
    @FXML Label labelChequeCrateBeneficiaryName = new Label();
    
    @FXML Label labelChequeCrateBeneficiaryAccount = new Label();
    
    @FXML Label labelChequeCrateBeneficiaryBank = new Label();
    
    // Button
    
    
    
    @FXML Button buttonChequeCrateBeneficiaryConfirmBack= new Button();
    
    @FXML Button buttonChequeCrateBeneficiaryConfirmConfirm = new Button();
    
    public void displayChequeCreateBeneficiaryConfirmation() {
    	// Method displays the create beneficiary confirmation page
    	anchorChequeCreateBeneficiaryConfirmation.setVisible(false);
		anchorChequePayCreateBeneficiary.setVisible(true); 
		//
    }
    
    
    private EventHandler<ActionEvent> buttonChequeCrateBeneficiaryConfirmBackListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		textFieldChequePayCreateBeneficiaryBankName.clear();
			textFieldChequePayCreateBeneficiaryAccountNo.clear();
			textFieldChequePayCreateBeneficiaryName.clear(); 
			
    		
    		// To display Create beneficiary keypad and textField 
			textFieldChequePayCreateBeneficiaryName.setDisable(false);
    		textFieldChequePayCreateBeneficiaryName.setPromptText("Beneficiary Name");
    		textFieldChequePayCreateBeneficiaryName.requestFocus(); // Focuses on the first available textField
    		gridPaneChequePayCreateBeneficiaryName.setVisible(true);
    		gridPaneChequePayCreateBeneficiaryName.setDisable(false);
    		
    		
    		textFieldChequePayCreateBeneficiaryAccountNo.setDisable(true);
    		gridPaneChequePayCreateBeneficiaryAccountNo.setVisible(false);
    		gridPaneChequePayCreateBeneficiaryAccountNo.setDisable(true);
    		
    		textFieldChequePayCreateBeneficiaryBankName.setDisable(true);
    		gridPaneChequePayCreateBeneficiaryBankName.setVisible(false);
    		gridPaneChequePayCreateBeneficiaryBankName .setDisable(true);
    		//
    		displayChequeCreateBeneficiaryConfirmation(); // Method displays the create beneficiary confirmation page
    		//
    	}
    };
    
    private EventHandler<ActionEvent> buttonChequeCrateBeneficiaryConfirmConfirmListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		textFieldChequePayCreateBeneficiaryBankName.clear();
			textFieldChequePayCreateBeneficiaryAccountNo.clear();
			textFieldChequePayCreateBeneficiaryName.clear(); 
			//
			anchorChequePayCreateBenefciarySuccessfull.setVisible(true);
			anchorChequeCreateBeneficiaryConfirmation.setVisible(false);
    		//
    	}
    };
   
    
    
    /*
     * Cheque Pay - Create beneficiary successful
     * */
    
    // Anchor Cheque Pay - Create beneficiary successfull
    
    @FXML AnchorPane anchorChequePayCreateBenefciarySuccessfull = new AnchorPane();
    
    // Button Cheque Pay - Create beneficiary successfull
    
    @FXML Button buttonChequePayCreateBeneficiarySuccessfullExit = new Button();
    
    @FXML Button buttonChequePayCreateBeneficiarySuccessfullYes = new Button();
    
    /*
     * cheque create beneficiary - show beneficiary last two , 5 latest beneficiaries
     * */
    
    
    private EventHandler<ActionEvent> buttonChequePayCreateBeneficiarySuccessfullYesListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		textFieldChequePayCreateBeneficiaryBankName.clear();
			textFieldChequePayCreateBeneficiaryAccountNo.clear();
			textFieldChequePayCreateBeneficiaryName.clear(); 
			
    		// To display Create beneficiary keypad and textField 
			textFieldChequePayCreateBeneficiaryName.setDisable(false);
    		textFieldChequePayCreateBeneficiaryName.setPromptText("Beneficiary Name");
    		textFieldChequePayCreateBeneficiaryName.requestFocus(); // Focuses on the first available textField
    		gridPaneChequePayCreateBeneficiaryName.setVisible(true);
    		gridPaneChequePayCreateBeneficiaryName.setDisable(false);
    		
    		
    		textFieldChequePayCreateBeneficiaryAccountNo.setDisable(true);
    		gridPaneChequePayCreateBeneficiaryAccountNo.setVisible(false);
    		gridPaneChequePayCreateBeneficiaryAccountNo.setDisable(true);
    		
    		textFieldChequePayCreateBeneficiaryBankName.setDisable(true);
    		gridPaneChequePayCreateBeneficiaryBankName.setVisible(false);
    		gridPaneChequePayCreateBeneficiaryBankName .setDisable(true);
    		//
    		anchorChequePayCreateBenefciarySuccessfull.setVisible(false);
    		//
    		chequeAcountMenu();; // Method displays the create beneficiary confirmation page
    		//
    	}
    };
    
    private EventHandler<ActionEvent> buttonChequePayCreateBeneficiarySuccessfullExitListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		textFieldChequePayCreateBeneficiaryBankName.clear();
			textFieldChequePayCreateBeneficiaryAccountNo.clear();
			textFieldChequePayCreateBeneficiaryName.clear(); 
			
    		
    		// To display Create beneficiary keypad and textField 
			textFieldChequePayCreateBeneficiaryName.setDisable(false);
    		textFieldChequePayCreateBeneficiaryName.setPromptText("Beneficiary Name");
    		textFieldChequePayCreateBeneficiaryName.requestFocus(); // Focuses on the first available textField
    		gridPaneChequePayCreateBeneficiaryName.setVisible(true);
    		gridPaneChequePayCreateBeneficiaryName.setDisable(false);
    		
    		
    		textFieldChequePayCreateBeneficiaryAccountNo.setDisable(true);
    		gridPaneChequePayCreateBeneficiaryAccountNo.setVisible(false);
    		gridPaneChequePayCreateBeneficiaryAccountNo.setDisable(true);
    		
    		textFieldChequePayCreateBeneficiaryBankName.setDisable(true);
    		gridPaneChequePayCreateBeneficiaryBankName.setVisible(false);
    		gridPaneChequePayCreateBeneficiaryBankName .setDisable(true);
    		//
    		anchorChequePayCreateBenefciarySuccessfull.setVisible(false);
    		anchorIndex.setVisible(true); // Method displays the create beneficiary confirmation page
    		//
    	}
    };
    //
    public void displayBeneficiaries() {
    	
    	//
    	for (int i = 0; i <newBeneficiary.size(); i++) { // for each loop to display the current beneficiary list
			//
    		labelChequeCrateBeneficiaryName.setText("Beneficiary Name\t\t:" + newBeneficiary.get(i).getBeneficiaryName());
			labelChequeCrateBeneficiaryAccount.setText("Beneficiary Bank\t\t:" + newBeneficiary.get(i).getBeneficiaryAccountNumber());
			labelChequeCrateBeneficiaryBank.setText("Beneficiary Account\t\t: " + newBeneficiary.get(i).getBeneficiaryBank());
			
    		//
    		System.out.println("Unique Beneficiary code :" + i);
			System.out.println("Beneficiary Name\t\t: " + newBeneficiary.get(i).getBeneficiaryName());
			System.out.println("Beneficiary Account Number\t: " +newBeneficiary.get(i).getBeneficiaryAccountNumber());
			System.out.println("Beneficiary Bank\t\t: " + newBeneficiary.get(i).getBeneficiaryBank());
			//
			System.out.println("");
			//
		}
    	//
    }
    
    
    /*
     * Cheque Purchases menu
     * 
     * */
  //Buttons for cheque purchases menu
    @FXML Button buttonChequePurchaseAirtime = new Button();
    
    @FXML Button buttonChequePurchaseLotto = new Button();
    
    @FXML Button buttonChequePurchaseLottoCancel = new Button();
    
    
    
    //Anchor for cheque purchases menu
    @FXML AnchorPane anchorContainerPurchase = new AnchorPane();
    
    @FXML AnchorPane anchorChequePurchase = new AnchorPane();
    
    @FXML AnchorPane anchorPurchaseAirtime = new AnchorPane();
    
    @FXML AnchorPane anchorPaneChequePurchasePlayLotto = new AnchorPane();
    
    // GridPane for airtime purchases 
    
   @FXML GridPane gridChequePurchaseAirtimeNumber = new GridPane();
    
    @FXML GridPane gridPaneChequePurchasesAirtimeAmount = new GridPane();
    
    // TextFields
    @FXML TextField textFieldChequePurchasesAirtimeAmount = new TextField();
    
    @FXML TextField textFieldChequePurchasesAirtimeNumber = new TextField();
    //
    // Label for cheque airtime 
    @FXML Label labelChequeAirtimeAmount = new Label();
    
    @FXML Label labelChequePurchaseAirtimebalanceError = new Label();
    
    @FXML Label labelChequePurchaseAirtimeCuurentbalance = new Label();
    
    @FXML Label labelChequePurchaseNetworkProvider = new Label();
    
    // ComboBox for cheque purchases airtime
    String networkProviders[] = {"Mtn", "Vodacom", "Cellc", "Telkom"};
    
    @FXML ComboBox<String> comboBoxChequePurchaseAirtime = new ComboBox<String>(FXCollections.observableArrayList(networkProviders));
    
    private EventHandler<ActionEvent> buttonChequePurchaseAirtimeListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		chequePurchaseAirtime();
    		//
    	}
    };
	
	public void chequePurchaseAirtime() {
		//
		labelChequePurchaseAirtimeCuurentbalance.setText("Available Cheque Balance :" + moneyFormat.format(cheque.getChequeBalance()));

		//
		anchorContainerPurchase.setVisible(true);
		anchorChequePurchase.setVisible(false);
		//
		anchorPurchaseAirtime.setVisible(true);
		//
		comboBoxChequePurchaseAirtime.getItems().addAll(networkProviders);
		comboBoxChequePurchaseAirtime.setPromptText("Choose Provider");
		
		/*Airtime number , GridPane and TextField
		 * */ 
		//Number
		textFieldChequePurchasesAirtimeNumber.requestFocus();
		textFieldChequePurchasesAirtimeNumber.setVisible(true);
		
		gridChequePurchaseAirtimeNumber.setVisible(true);
		gridChequePurchaseAirtimeNumber.setDisable(false);
		//
	}
	// anchorPaneChequePurchasePlayLotto
	
	private EventHandler<ActionEvent> buttonChequePurchaseLottoCancelListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		anchorMainContainer.setVisible(true);
    		anchorContainerPurchase.setVisible(false);
    		anchorChequePurchase.setVisible(false);
    		//
    		
    		chequeAcountMenu();
    	}
    };
	
	
	
	private EventHandler<ActionEvent> buttonChequePurchaseLottoListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		chequePurchaseLotto();
    		//
    	}
    };
	//
	@FXML Button buttonChequePurchaseAirtimeNumber0 = new Button();
    @FXML Button buttonChequePurchaseAirtimeNumber1 = new Button();
    @FXML Button buttonChequePurchaseAirtimeNumber2 = new Button();
    @FXML Button buttonChequePurchaseAirtimeNumber3 = new Button();
    @FXML Button buttonChequePurchaseAirtimeNumber4 = new Button();
    @FXML Button buttonChequePurchaseAirtimeNumber5 = new Button();
    @FXML Button buttonChequePurchaseAirtimeNumber6 = new Button();
    @FXML Button buttonChequePurchaseAirtimeNumber7 = new Button();
    @FXML Button buttonChequePurchaseAirtimeNumber8 = new Button();
    @FXML Button buttonChequePurchaseAirtimeNumber9 = new Button();
    
    @FXML Button buttonChequePurchaseAirtimeNumberProceed = new Button();
    @FXML Button buttonChequePurchaseAirtimeNumberClear = new Button();
    @FXML Button buttonChequePayPurchaseAirtimeBack  = new Button();
	
    private EventHandler<ActionEvent> buttonChequePurchaseAirtimeNumberListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		textFieldChequePurchasesAirtimeNumber.appendText(number);
    		
    	}
    };
    
    private EventHandler<ActionEvent> buttonChequePurchaseAirtimeNumberProceedListener = new EventHandler<ActionEvent>() {
    	
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		//Number
    		textFieldChequePurchasesAirtimeAmount.requestFocus();
    		textFieldChequePurchasesAirtimeAmount.setVisible(true);
    		//
    		//
    		gridPaneChequePurchasesAirtimeAmount.setVisible(true);
    		gridPaneChequePurchasesAirtimeAmount.setDisable(false);
    		//
    		gridChequePurchaseAirtimeNumber.setVisible(false);
    		gridChequePurchaseAirtimeNumber.setDisable(true);
    		//
    		
    	}
    };
    //
    private EventHandler<ActionEvent> buttonChequePurchaseAirtimeNumberClearListener = new EventHandler<ActionEvent>() {
    	
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		textFieldChequePurchasesAirtimeNumber.clear();
    		//
    		
    		
    	}
    };
    
    private EventHandler<ActionEvent> buttonChequePayPurchaseAirtimeBackListener = new EventHandler<ActionEvent>() {
    	
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		textFieldChequePurchasesAirtimeNumber.clear();
    		//
    		anchorContainerPurchase.setVisible(true);
    		anchorChequePurchase.setVisible(true);
    		//
    		anchorPurchaseAirtime.setVisible(false);
    		//
//    		chequeAcountMenu();
    		//
    		
    	}
    };
    
	//
	@FXML Button buttonChequePurchaseAirtimeAmount0 = new Button();
    @FXML Button buttonChequePurchaseAirtimeAmount1 = new Button();
    @FXML Button buttonChequePurchaseAirtimeAmount2 = new Button();
    @FXML Button buttonChequePurchaseAirtimeAmount3 = new Button();
    @FXML Button buttonChequePurchaseAirtimeAmount4 = new Button();
    @FXML Button buttonChequePurchaseAirtimeAmount5 = new Button();
    @FXML Button buttonChequePurchaseAirtimeAmount6 = new Button();
    @FXML Button buttonChequePurchaseAirtimeAmount7 = new Button();
    @FXML Button buttonChequePurchaseAirtimeAmount8 = new Button();
    @FXML Button buttonChequePurchaseAirtimeAmount9 = new Button();
    
    @FXML Button buttonChequePurchaseAirtimeAmountProceed = new Button();
    @FXML Button buttonChequePurchaseAirtimeAmountClear = new Button();
	
    private EventHandler<ActionEvent> buttonChequePurchaseAirtimeAmountListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		textFieldChequePurchasesAirtimeAmount.appendText(number);
    		
    	}
    };
    
    
    
    private EventHandler<ActionEvent> buttonChequePurchaseAirtimeAmountProceedListener  = new EventHandler<ActionEvent>() {
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		// submits the amount the user would like to withdraw 
    		labelChequePurchaseAirtimebalance.setText("Available Cheque Account Balance :" + moneyFormat.format(cheque.getChequeBalance()));
        	//
    		Double amount = Double.parseDouble(textFieldChequePurchasesAirtimeAmount.getText());
    		String recipientPhoneNo = textFieldChequePurchasesAirtimeNumber.getText();
    		String networkProvider = comboBoxChequePurchaseAirtime.getValue();
    		//
        	if (cheque.getChequeBalance() - amount >= 0) {
        		//
        		chequePurchasesAirtimeConfirm();
        		//
        		labelChequePurchaseAirtimConfirmCheque.setText("Recipient Phone No\t\t: " + recipientPhoneNo);
        		labelChequePurchaseAirtimeAmount .setText("Amount\t\t\t\t\t: " + moneyFormat.format(amount));      		
        		labelChequePurchaseNetworkProvider.setText("Network Provider\t\t\t:" + networkProvider);
        		
        		// Creates an array which contains the transaction details, array is deleted if user cancles transaction
        		for (int i = 0; i < 1;i++) {
					//
					LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
					String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
					chequeTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
					//
					String transactionDescription = "Recipient Phone No : " + recipientPhoneNo;// this line of code is the name of the transaction
					chequeTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
					//
					double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
					chequeTransactionsHistory.setTransactionAmount(transactionAmount);;
					//
					String transactionID = "TRA00" + randomID + "CH";
					chequeTransactionsHistory.setTransactionID(transactionID);
					randomID++;
					//
					String transactionType = "Debit";
					chequeTransactionsHistory.setTransactionType(transactionType);
					// line of code below creates a new array
					newChequeTransactions.add(new ChequeTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
					//
				}
        		//
        		textFieldChequePurchasesAirtimeAmount.clear();
        		textFieldChequePurchasesAirtimeNumber.clear();
        		//
        		anchorChequePayOnceOffConfirmation.setVisible(true);
        		anchorChequePayOnceOff.setVisible(false);
        		//
        		
        	} else {
        		//
        		labelChequePurchaseAirtimebalanceError.setText("Maxium withdrawable amount : " +  moneyFormat.format(cheque.getChequeBalance()) );
        		labelChequePurchaseAirtimebalanceError.setVisible(true);
        		//
        		System.err.println("Insufficient funds, Maxium withdrwable amount");
        		//
        		textFieldChequePurchasesAirtimeAmount.clear();
        		textFieldChequePurchasesAirtimeNumber.clear();
        		//
        		
        	}
    		
    	}
    };
    
    @FXML Label labelAnchorChequeBalance = new Label();
    
    private EventHandler<ActionEvent> buttonChequePurchaseAirtimeAmountClearListener  = new EventHandler<ActionEvent>() {
    	
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		textFieldChequePurchasesAirtimeAmount.clear();
    		
    		
    	}
    };
    
    /*
     * Cheque Purchases Airtime Confirm 
     * */
    
    // Anchor Cheque Purchases airtime confirm
    @FXML AnchorPane anchorChequePurchaseAirtimeConfirm = new AnchorPane();
    
    // Labels cheque purchases airtime confirm
    @FXML Label labelChequePurchaseAirtimConfirmCheque = new Label();
    
    @FXML Label labelChequePurchaseAirtimeAmount = new Label();
    
    // Button cheque purchases airtime confirm 
    @FXML Button buttonChequePurchaseAirtimeConfirmBack = new Button();
    
    @FXML Button buttonChequePurchaseAirtimeConfirmConfirm = new Button();
    
    public void chequePurchasesAirtimeConfirm() {
    	//
    	anchorChequePurchaseAirtimeConfirm.setVisible(true);
    	anchorPurchaseAirtime.setVisible(false);
    }
    
    private EventHandler<ActionEvent> buttonChequePurchaseAirtimeConfirmBackListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		anchorChequePurchaseAirtimeConfirm.setVisible(false);
        	anchorPurchaseAirtime.setVisible(true);
        	//
        	deleteLastTransaction();
    		//
    	}
    };
    
    private EventHandler<ActionEvent> buttonChequePurchaseAirtimeConfirmConfirmListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		anchorChequePurchaseAirtimeConfirm.setVisible(false);
        	anchorPurchaseAirtime.setVisible(true);
        	//
        	chequePurchasesAirtimeTransactionSuccessfull();
    		
    	}
    };
    
    /*
     * Cheque purchases airtime transaction successfull
     * */
    
    // AnchorPane cheque purchases airtime transactio successfull
    @FXML AnchorPane anchorCheqeuPurchaseAirtimeSuccessfull = new AnchorPane();
    
    // Button cheque purchase airtime 
    
    @FXML Button buttonChequePurchaseAirtimeSuccessfullYes = new Button();
    
    @FXML Button buttonChequePurchaseAirtimeSuccessfullNo = new Button();
    
    //
    public void chequePurchasesAirtimeTransactionSuccessfull(){
    	//
    	anchorCheqeuPurchaseAirtimeSuccessfull.setVisible(true);
    	anchorPurchaseAirtime.setVisible(false);
    	
    }
    
    private EventHandler<ActionEvent> buttonChequePurchaseAirtimeSuccessfullYesListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		anchorContainerPurchase.setVisible(false);
    		anchorMainContainer.setVisible(true);
    		//
    		anchorChequeMenu.setVisible(true);
    		anchorChequePayOnceOffConfirmation.setVisible(false);
    		anchorCheqeuPurchaseAirtimeSuccessfull.setVisible(false);
    	}
    };
    
    private EventHandler<ActionEvent> buttonChequePurchaseAirtimeSuccessfullNoListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		anchorMainContainer.setVisible(true);
    		anchorContainerPurchase.setVisible(false);
    		anchorLogin.setVisible(true);
    		//
    		anchorCheqeuPurchaseAirtimeSuccessfull.setVisible(false);
    		anchorCheqeuPayOnceOffSuccessfull.setVisible(false);
        	//
    		
    	}
    };
    
    /*
     * Cheque purchase play lotto
     * */
    
    // Label 
    
    @FXML Label shapeLottoText1 = new Label();
    @FXML Label shapeLottoText2 = new Label();
    @FXML Label shapeLottoText3 = new Label();
    @FXML Label shapeLottoText4 = new Label();
    @FXML Label shapeLottoText5 = new Label();
    
    @FXML Label labelChequePurchaseLottoCuurentbalance = new Label();
    
    @FXML Label labelChequePurchaseBalanceError = new Label();
    
    @FXML Label labelChequePurchasePlayLottoDescription = new Label();
    
    @FXML Label labelChequePurchasePlayLottoDescription5 = new Label();
    
    // Shapes
    
    @FXML Circle shapeLottoNo1 = new Circle();
    @FXML Circle shapeLottoNo2 = new Circle();
    @FXML Circle shapeLottoNo3 = new Circle();
    @FXML Circle shapeLottoNo4 = new Circle();
    @FXML Circle shapeLottoNo5 = new Circle();
    
    // Button Cheque purchase play lotto
   
    @FXML Button buttonChequePayPurchaseLottoProceed = new Button();
    
    @FXML Button buttonChequePayPurchaseLottoBack = new Button();
    
    // Lotto
 	public void chequePurchaseLotto() {
 		//
 		anchorPaneChequePurchasePlayLotto.setVisible(true);
 		//
 		labelChequePurchaseLottoCuurentbalance.setText("Available Cheque Account Balance :" + moneyFormat.format(cheque.getChequeBalance()));
 		
 		labelChequePurchasePlayLottoDescription.setVisible(true);  
 	    labelChequePurchasePlayLottoDescription5.setVisible(true);
 	    
 	    buttonChequePayPurchaseLottoPlay.setVisible(true);
 	    buttonChequePayPurchaseLottoProceed.setVisible(true);
 	    buttonChequePayPurchaseLottoBack.setVisible(true);
 	    //
 		labelChequePurchaseBalanceError.setVisible(false);
 		//
 		shapeLottoNo1.setVisible(false);
 		shapeLottoNo2.setVisible(false);
 		shapeLottoNo3.setVisible(false);
 		shapeLottoNo4.setVisible(false);
 		shapeLottoNo5.setVisible(false);
 		//
 		shapeLottoText1.setVisible(false);
 		shapeLottoText2.setVisible(false);
 		shapeLottoText3.setVisible(false);
 		shapeLottoText4.setVisible(false);
 		shapeLottoText5.setVisible(false);
 		//
		labelChequePurchaseLottoNumbers.setVisible(false);
		labelChequePurchaseLottoAnotherTransaction.setVisible(false);
		
		labelChequePurchaseLottoSubHeadingPurcaseSuccessfull.setVisible(false);
		labelChequePurchaseLottoYourLottoNumbers.setVisible(false);
		
		buttonChequePurchaseLottoSuccessfullNo.setVisible(false);
		buttonChequePurchaseLottoSuccessfullYes.setVisible(false);
 		//
 		
 		
 	}
 	
 	// Cheque Purchase Lotto Play button 
 	
 	@FXML Button buttonChequePayPurchaseLottoPlay = new Button(); // Play button to generate random numbers
    
 	private EventHandler<ActionEvent> buttonChequePayPurchaseLottoPlayListener = new EventHandler<ActionEvent>() {
    	
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		
			System.out.println("\nLotto QuickPick");
			//
			ArrayList<Integer> lottoNumbers = new ArrayList<Integer>();
			//
			int randomNum1 = (int)(Math.random() * 101);  // 0 to 100
			int randomNum2 = (int)(Math.random() * 101);  // 0 to 100
			int randomNum3 = (int)(Math.random() * 101);  // 0 to 100
			int randomNum4 = (int)(Math.random() * 101);  // 0 to 100
			int randomNum5 = (int)(Math.random() * 101);  // 0 to 100
			//
			lottoNumbers.add(randomNum1);
			lottoNumbers.add(randomNum2);
			lottoNumbers.add(randomNum3);
			lottoNumbers.add(randomNum4);
			lottoNumbers.add(randomNum5);
			//
			shapeLottoNo1.setVisible(true);
	 		shapeLottoNo2.setVisible(true);
	 		shapeLottoNo3.setVisible(true);
	 		shapeLottoNo4.setVisible(true);
	 		shapeLottoNo5.setVisible(true);
	 		//
	 		shapeLottoText1.setVisible(true);
	 		shapeLottoText2.setVisible(true);
	 		shapeLottoText3.setVisible(true);
	 		shapeLottoText4.setVisible(true);
	 		shapeLottoText5.setVisible(true);
			//
	 		shapeLottoText1.setText(""+ randomNum1);
	 		shapeLottoText2.setText(""+ randomNum2);
	 		shapeLottoText3.setText(""+ randomNum3);
	 		shapeLottoText4.setText(""+ randomNum4);
	 		shapeLottoText5.setText(""+ randomNum5);
	 		//
			System.out.println("\nYour Lotto numbers :\n");
			
			String lottoNumbersText = ""; // Initialize an empty string to store the numbers

			for (int i : lottoNumbers) {
			    labelChequePurchaseLottoNumbers.setVisible(false);
			    lottoNumbersText += i + " "; // Append each number to the string
			    System.out.print(i + " ");
			}

			labelChequePurchaseLottoNumbers.setText(lottoNumbersText); // Set the text of LabelPurchaselottoNumbers to the string
			
			System.out.println("\n\nYou have successfully played Lotto QuickPick");
			
    	}
    };
    
    private EventHandler<ActionEvent> buttonChequePayPurchaseLottoProceedListener = new EventHandler<ActionEvent>() {
    	// user proceeds to confirm payment 
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		labelChequePurchaseLottoCuurentbalance.setText("Available Cheque Account Balance :" + moneyFormat.format(cheque.getChequeBalance()));
    		// code below credits the money out of the Cheque Account
    		int amount = 5;
			//
			if (cheque.getChequeBalance() - amount >= 0) {
				//
				labelChequePurchaseLottoNumbers.setVisible(true);
				//
				double chequeAccountBalance = cheque.getChequeBalance() - amount;// chequeBalance is used to new balance after user inputs amount they wish to transfer
				cheque.setChequeBalance(chequeAccountBalance); // sets the new cheque account balance
				//
				labelChequePurchaseLottoCuurentbalance.setText("Available Cheque Account Balance :" + moneyFormat.format(cheque.getChequeBalance()));
				//
				labelChequePurchaseLottoNumbers.setVisible(true);
				labelChequePurchaseLottoAnotherTransaction.setVisible(true);
				
				labelChequePurchaseLottoSubHeadingPurcaseSuccessfull.setVisible(true);
				labelChequePurchaseLottoYourLottoNumbers.setVisible(true);
				
				buttonChequePurchaseLottoSuccessfullNo.setVisible(true);
				buttonChequePurchaseLottoSuccessfullYes.setVisible(true);
				//
				shapeLottoNo1.setVisible(false);
		 		shapeLottoNo2.setVisible(false);
		 		shapeLottoNo3.setVisible(false);
		 		shapeLottoNo4.setVisible(false);
		 		shapeLottoNo5.setVisible(false);
		 		//
		 		shapeLottoText1.setVisible(false);
		 		shapeLottoText2.setVisible(false);
		 		shapeLottoText3.setVisible(false);
		 		shapeLottoText4.setVisible(false);
		 		shapeLottoText5.setVisible(false);
		 		//
		 		labelChequePurchaseLottoCuurentbalance.setVisible(true);
		 		labelChequePurchaseBalanceError.setVisible(false);
		 		
		 		labelChequePurchaseLottoNumbers.setVisible(true);
		 		labelChequePurchasePlayLottoDescription.setVisible(false);
		 	    labelChequePurchasePlayLottoDescription5.setVisible(false);
		 		
		 		buttonChequePayPurchaseLottoBack.setVisible(false);
		 		buttonChequePayPurchaseLottoProceed.setVisible(false);
		 		buttonChequePayPurchaseLottoPlay.setVisible(false);
		 		//
		 		for (int i = 0; i < 1;i++) {
					//
					LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
					String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
					chequeTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
					//
					String transactionDescription = "Lotto " ;// this line of code is the name of the transaction
					chequeTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
					//
					double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
					chequeTransactionsHistory.setTransactionAmount(transactionAmount);;
					//
					String transactionID = "TRA00" + randomID + "CH";
					chequeTransactionsHistory.setTransactionID(transactionID);
					randomID++;
					//
					String transactionType = "Debit";
					chequeTransactionsHistory.setTransactionType(transactionType);
					// line of code below creates a new array
					newChequeTransactions.add(new ChequeTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
					//
				}
				
			} else if (cheque.getChequeAccountNo() - amount < 0 ) {
				//
				System.err.println("Transaction unsucessfull insufficuient funds, Lotto Quick pick costs R5,00  per draw");
				//
				labelChequePurchaseBalanceError.setVisible(true);
				labelChequePurchaseBalanceError.setText("Transaction unsucessfull insufficuient funds, Lotto Quick pick costs R5,00  per draw");
				//
			}
			//
    	}
    };
    
    private EventHandler<ActionEvent> buttonChequePayPurchaseLottoBackListener = new EventHandler<ActionEvent>() {
    	// returns user to cheque purchase menu
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		anchorChequePurchase.setVisible(true);
    		//
    		anchorPaneChequePurchasePlayLotto.setVisible(false);
    	}
    };
    
    /*
     * Cheque purchase pay lotto Transaction successful
     * */
    
    // label cheque purchase lotto transaction sucessfull 
    @FXML Label labelChequePurchaseLottoNumbers = new Label();
    
    @FXML Label labelChequePurchaseLottoAnotherTransaction = new Label();
    
    @FXML Label labelChequePurchaseLottoSubHeadingPurcaseSuccessfull = new Label();
    
    @FXML Label labelChequePurchaseLottoYourLottoNumbers = new Label();
    
    // button cheque purchase lotto transaction sucessfull 
    @FXML Button buttonChequePurchaseLottoSuccessfullNo = new Button();
    
    @FXML Button buttonChequePurchaseLottoSuccessfullYes = new Button();
    
    private EventHandler<ActionEvent> buttonChequePurchaseLottoSuccessfullYesListener = new EventHandler<ActionEvent>() {
    	// returns user to cheque purchase menu
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		chequeAcountMenu();
    		//
    		anchorChequePurchase.setVisible(false);
    		//
    		anchorContainerPurchase.setVisible(false);
    		anchorPaneChequePurchasePlayLotto.setVisible(false);
    		//
    		
    	}
    };
    
    /*
     * Cheque Transaction History 
     * */
    
    TransactionsHistory chequeTransactionsHistory = new ChequeTransactionsHistory(null, null, amount, null, null);
	
    ArrayList<ChequeTransactionsHistory> newChequeTransactions = new ArrayList<>();
    
    // Anchor
    @FXML AnchorPane anchorChequeTransactionHistory = new AnchorPane();
    
	// Table view Cheque Transaction History
    
    @FXML TableView<ChequeTransactionsHistory> tableviewTransactionHistory;
    
    @FXML TableColumn<ChequeTransactionsHistory, String> transactionDate1;

    @FXML TableColumn<ChequeTransactionsHistory, String> transactionDescription;

    @FXML TableColumn<ChequeTransactionsHistory, Double> transactionAmount;
    
    @FXML TableColumn<ChequeTransactionsHistory, String> transactionID;
    
    @FXML TableColumn<ChequeTransactionsHistory, String> transactionType;
    
    // Button Cheque Transaction History 
    @FXML Button buttonChequeTransactionHistoryBack = new Button();
    
    public void chequeTransactionHistory() {
    	//
    	anchorChequeTransactionHistory.setVisible(true);
        // Create the data list
        ObservableList<ChequeTransactionsHistory> data = FXCollections.observableArrayList(newChequeTransactions);
        
        // Set the cell value factories
        transactionID.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
        transactionDate1.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
        transactionDescription.setCellValueFactory(new PropertyValueFactory<>("transactionDescription"));
        transactionAmount.setCellValueFactory(new PropertyValueFactory<>("transactionAmount"));
        transactionType.setCellValueFactory(new PropertyValueFactory<>("transactionType"));

        // Set the data in the TableView
        tableviewTransactionHistory.setItems(data);
        
        // Debugging - print transaction details
        for (ChequeTransactionsHistory transaction : newChequeTransactions) {
            System.out.println("\n\tTransaction ID\t\t: " + transaction.getTransactionID());
            System.out.println("\tTransaction Date\t: " + transaction.getTransactionDate());
            System.out.println("\tTransaction Name\t: " + transaction.getTransactionDescription());
            System.out.println("\tTransaction Amount\t: " + moneyFormat.format(transaction.getTransactionAmount()));
            System.out.println("\tDR or CR\t\t: " + transaction.getTransactionType());
            System.out.println("");
        }
    }
    
    ObservableList<ChequeTransactionsHistory> data = FXCollections.observableArrayList(newChequeTransactions);
    
    
    private EventHandler<ActionEvent> buttonChequeTransactionHistoryBackListener = new EventHandler<ActionEvent>() {
    	// returns user to cheque purchase menu
    	@Override
    	public void handle(ActionEvent actionEvent) {
    		//
    		Button sourceComponent = (Button)actionEvent.getSource();
    		String number = sourceComponent.getText();
    		//
    		System.out.println(number);
    		//
    		chequeAcountMenu();
    		//
    		anchorChequePurchase.setVisible(false);
    		anchorChequeTransactionHistory.setVisible(false);
    		//
    		anchorContainerPurchase.setVisible(false);
    		anchorPaneChequePurchasePlayLotto.setVisible(false);
    		//
    		
    		
    	}
    };
    
    
}

// thank you 
