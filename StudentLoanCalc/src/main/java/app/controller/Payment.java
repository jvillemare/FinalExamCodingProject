package app.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Payment {
	
	private SimpleIntegerProperty id;
	
	private SimpleIntegerProperty dueDate;
	
	private SimpleDoubleProperty payment;
	
	private SimpleDoubleProperty additionalPayment;
	
	private SimpleDoubleProperty interest;
	
	private SimpleDoubleProperty principal;
	
	private SimpleDoubleProperty balance;
	
	public Payment(int id, int dueDate, double payment, 
			double additionalPayment, double interest, double principal,
			double balance) {
		this.id = new SimpleIntegerProperty(id);
		this.dueDate = new SimpleIntegerProperty(dueDate);
		this.payment = new SimpleDoubleProperty(payment);
		this.additionalPayment = new SimpleDoubleProperty(additionalPayment);
		this.interest = new SimpleDoubleProperty(interest);
		this.principal = new SimpleDoubleProperty(principal);
		this.balance = new SimpleDoubleProperty(balance);
	}

	public int getId() {
		return id.get();
	}

	public int getDueDate() {
		return dueDate.get();
	}

	public double getPayment() {
		return payment.get();
	}
	
	public double getAdditionalPayment() {
		return additionalPayment.get();
	}

	public double getInterest() {
		return interest.get();
	}

	public double getPrincipal() {
		return principal.get();
	}

	public double getBalance() {
		return balance.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}

	public void setDueDate(int dueDate) {
		this.dueDate.set(dueDate);
	}

	public void setPayment(double payment) {
		this.payment.set(payment);
	}

	public void setAdditionalPayment(double additionalPayment) {
		this.additionalPayment.set(additionalPayment);
	}

	public void setInterest(double interest) {
		this.interest.set(interest);
	}

	public void setPrincipal(double principal) {
		this.principal.set(principal);
	}

	public void setBalance(double balance) {
		this.balance.set(balance);
	}

}
