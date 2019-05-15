package app.controller;

import app.StudentCalc;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import org.apache.poi.ss.formula.functions.FinanceLib;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class LoanCalcViewController implements Initializable   {

	private StudentCalc SC = null;
	
	@FXML
	private TextField LoanAmount;
	
	@FXML
	private TextField InterestRate;
	
	@FXML
	private TextField Term;
	
	@FXML
	private TextField FirstDate;
	
	@FXML
	private TextField AdditionalPayment;
	
	@FXML
	private Label lblTotalPayments;
	
	@FXML
	private Label lblTotalInterest;
	
	@FXML
	private VBox paymentTableHolder;
	
	@FXML
	private TableView<Payment> loanPaymentsTable = new TableView<Payment>();
	
	private ObservableList<Payment> loanPaymentsData;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//this.lblTotalPayments.setText("0.00");
		//this.lblTotalInterest.setText("0.00");
		
	}

	public void setMainApp(StudentCalc sc) {
		this.SC = sc;
	}
	
	/**
	 * btnCalcLoan - Fire this event when the button clicks
	 * 
	 * @version 1.0
	 * @param event
	 */
	@FXML
	private void btnCalcLoan(ActionEvent event) {
		
		// get input data
		double dLoanAmount = Double.parseDouble(LoanAmount.getText());
		double dInterestRate = Double.parseDouble(InterestRate.getText());
		double dTerm = Double.parseDouble(Term.getText());
		double dFirstDate = Double.parseDouble(FirstDate.getText());
		double dAdditionalPayments = Double.parseDouble(AdditionalPayment.getText());
		
		double PMT = Math.abs(
				FinanceLib.pmt(
						dInterestRate / 12, 
						dTerm * 12, 
						dLoanAmount, 
						0,
						false)
				);
		
		System.out.println("Amount: " + LoanAmount.getText());
		System.out.println("Amount: " + dLoanAmount);
		System.out.println("PMT: " + PMT);
		
		// process inputs
		LinkedList<Payment> payments = new LinkedList<Payment>();
		
		double dInterest, dPrincipal, dEndingBalance = dLoanAmount;
		
		for(int i = 1; i < dTerm * 12; i++) {
			if(dEndingBalance != 0.00) {
				dInterest = dEndingBalance * (dInterestRate/12);
				dPrincipal = PMT - dInterest + dAdditionalPayments;
			} else {
				// loan payed off
				dInterest = 0.00;
				dPrincipal = 0.00;
				dAdditionalPayments = 0.00;
				PMT = 0.00;
			}
			
			if(PMT + dAdditionalPayments <= dEndingBalance)
				dEndingBalance -= dPrincipal;
			else
				dEndingBalance = 0.0;
			
			payments.add(new Payment(
					i, // id
					i, // dueDate
					twoDecimals(PMT), // payment 
					twoDecimals(dAdditionalPayments), // additionalPayment
					twoDecimals(dInterest), // interest
					twoDecimals(dPrincipal), // principal
					twoDecimals(dEndingBalance)  // balance (ending)
					));
		}
		
		double dTotalPayments = 0.0, dTotalInterest = 0.0;
		
		for(int i = 0; i < payments.size(); i++) {
			dTotalPayments += payments.get(i).getPayment();
			dTotalInterest += payments.get(i).getInterest();
		}
		
		// update labels
		this.lblTotalPayments.setText(Double.toString(twoDecimals(dTotalPayments)));
		this.lblTotalInterest.setText(Double.toString(twoDecimals(dTotalInterest)));
		
		// update table
		this.loanPaymentsTable.setEditable(true);
		
		this.loanPaymentsTable.getItems().clear();
		this.loanPaymentsTable.getColumns().clear();
		 
        TableColumn paymentNumberCol = new TableColumn("Payment #");
        paymentNumberCol.setCellValueFactory(
                new PropertyValueFactory<Payment, Integer>("id"));
 
        TableColumn dueDateCol = new TableColumn("Due Date");
        dueDateCol.setCellValueFactory(
                new PropertyValueFactory<Payment, String>("dueDate"));
 
        TableColumn paymentCol = new TableColumn("Payment");
        paymentCol.setCellValueFactory(
                new PropertyValueFactory<Payment, String>("payment"));
        
        TableColumn additionalPaymentCol = new TableColumn("Additional Payment");
        additionalPaymentCol.setCellValueFactory(
                new PropertyValueFactory<Payment, String>("additionalPayment"));
        
        TableColumn interestCol = new TableColumn("Interest");
        interestCol.setCellValueFactory(
                new PropertyValueFactory<Payment, String>("interest"));
        
        TableColumn principleCol = new TableColumn("Principle");
        principleCol.setCellValueFactory(
                new PropertyValueFactory<Payment, String>("principal"));
        
        TableColumn balanceCol = new TableColumn("Balance");
        balanceCol.setCellValueFactory(
                new PropertyValueFactory<Payment, String>("balance"));
        
        this.loanPaymentsData = FXCollections.observableArrayList(payments);
        
        this.loanPaymentsTable.setItems(loanPaymentsData);
        this.loanPaymentsTable.getColumns().addAll(
        		paymentNumberCol, 
        		dueDateCol, 
        		paymentCol,
        		additionalPaymentCol,
        		interestCol,
        		principleCol,
        		balanceCol);
		
	}
	
	public static double twoDecimals(double d) {
		return Math.floor(d * 100) / 100;
	}
}
