package app.controller;

import java.time.LocalDate;
import java.util.LinkedList;

import org.apache.poi.ss.formula.functions.FinanceLib;

/**
 * Holds Payment objects.
 * 
 * @author jvillemarette
 */
public class StudentLoan {
	
	private double PMT;
	
	private LinkedList<Payment> payments = new LinkedList<Payment>();
	
	private double dTotalPayments = 0.0, dTotalInterest = 0.0;
	
	/**
	 * Creates a standard Student Loan.
	 * 
	 * @param dLoanAmount
	 * @param dInterestRate
	 * @param dTerm
	 * @param dFirstDate
	 * @param dAdditionalPayments
	 */
	public StudentLoan(double dLoanAmount, double dInterestRate, double dTerm, 
			LocalDate ld, double dAdditionalPayments) {
		
		this.PMT = Math.abs(
				FinanceLib.pmt(
						dInterestRate / 12, 
						dTerm * 12, 
						dLoanAmount, 
						0,
						false)
				);
		
		// process inputs
		double dInterest = 0.0, dPrincipal, dEndingBalance = dLoanAmount, 
				dPreviousEndingBalance;
		
		boolean bTerminate = false;
				
		for(int i = 1; i < dTerm * 12; i++) {
			dPreviousEndingBalance = dEndingBalance;
			
			if(bTerminate) {
				break;
			}
			
			if(dEndingBalance == dInterest) {
				bTerminate = true;
			}
			
			if(dEndingBalance != 0.00) {
				dInterest = dEndingBalance * (dInterestRate/12);
				dPrincipal = PMT - dInterest + dAdditionalPayments;
			} else {
				// loan payed off
				dInterest = 0.00;
				dPrincipal = 0.00;
				dAdditionalPayments = 0.00;
				PMT = 0.00;
				
				break;
			}
					
			if(PMT + dAdditionalPayments <= dEndingBalance)
				dEndingBalance -= dPrincipal;
			else
				dEndingBalance = 0.0;
			
			ld = ld.plusMonths(1L);
			
			if(dEndingBalance <= 0.00 && dAdditionalPayments > 0.0) {
				// tidy up principal on last payment
				dPrincipal = dPreviousEndingBalance - dInterest;
				dEndingBalance = dInterest;
			}
			
			this.payments.add(new Payment(
				i, // id
				ld, // dueDate
				twoDecimals(PMT), // payment 
				twoDecimals(dAdditionalPayments), // additionalPayment
				twoDecimals(dInterest), // interest
				twoDecimals(dPrincipal), // principal
				twoDecimals(dEndingBalance)  // balance (ending)
					));
			
		}
				
		for(int i = 0; i < payments.size(); i++) {
			this.dTotalPayments += payments.get(i).getPayment();
			this.dTotalInterest += payments.get(i).getInterest();
		}
	}
	
	public static double twoDecimals(double d) {
		/*
		 * Math.round was having a few problems... apparently, I needed to 
		 * spell this out, below:
		 */
		if((d * 100) % 1 <= 0.5)
			return Math.floor(d * 100) / 100;
		
		return Math.ceil(d * 100) / 100;
	}

	public double getPMT() {
		return PMT;
	}

	public LinkedList<Payment> getPayments() {
		return payments;
	}

	public double getdTotalPayments() {
		return StudentLoan.twoDecimals(dTotalPayments);
	}

	public double getdTotalInterest() {
		return StudentLoan.twoDecimals(dTotalInterest);
	}

}
