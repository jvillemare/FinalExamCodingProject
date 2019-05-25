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
import java.time.LocalDate;
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
	@SuppressWarnings("unchecked")
	@FXML
	private void btnCalcLoan(ActionEvent event) {
		
		// get input data
		double dLoanAmount = Double.parseDouble(LoanAmount.getText());
		double dInterestRate = Double.parseDouble(InterestRate.getText());
		double dTerm = Double.parseDouble(Term.getText());
		String dFirstDate = FirstDate.getText();
		double dAdditionalPayments = Double.parseDouble(AdditionalPayment.getText());
		
		LocalDate ld = LocalDate.parse(dFirstDate);
		
		// process inputs
		StudentLoan sl = new StudentLoan(dLoanAmount, dInterestRate, dTerm,
				ld, dAdditionalPayments);
		
		// update labels
		this.lblTotalPayments.setText(Double.toString(sl.getdTotalPayments()));
		this.lblTotalInterest.setText(Double.toString(sl.getdTotalInterest()));
		
		// update table
		this.loanPaymentsTable.setEditable(true);
		
		this.loanPaymentsTable.getItems().clear();
		this.loanPaymentsTable.getColumns().clear();
		 
		@SuppressWarnings("rawtypes")
        TableColumn paymentNumberCol = new TableColumn("Payment #");
        paymentNumberCol.setCellValueFactory(
                new PropertyValueFactory<Payment, Integer>("id"));
 
        @SuppressWarnings("rawtypes")
        TableColumn dueDateCol = new TableColumn("Due Date");
        dueDateCol.setCellValueFactory(
                new PropertyValueFactory<Payment, String>("dueDate"));
 
        @SuppressWarnings("rawtypes")
        TableColumn paymentCol = new TableColumn("Payment");
        paymentCol.setCellValueFactory(
                new PropertyValueFactory<Payment, String>("payment"));
        
        @SuppressWarnings("rawtypes")
        TableColumn additionalPaymentCol = new TableColumn("Additional Payment");
        additionalPaymentCol.setCellValueFactory(
                new PropertyValueFactory<Payment, String>("additionalPayment"));
        
        @SuppressWarnings("rawtypes")
		TableColumn interestCol = new TableColumn("Interest");
        interestCol.setCellValueFactory(
                new PropertyValueFactory<Payment, String>("interest"));
        
        @SuppressWarnings("rawtypes")
        TableColumn principleCol = new TableColumn("Principle");
        principleCol.setCellValueFactory(
                new PropertyValueFactory<Payment, String>("principal"));
        
        @SuppressWarnings("rawtypes")
        TableColumn balanceCol = new TableColumn("Balance");
        balanceCol.setCellValueFactory(
                new PropertyValueFactory<Payment, String>("balance"));
        
        this.loanPaymentsData = FXCollections.observableArrayList(sl.getPayments());
        
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

}
