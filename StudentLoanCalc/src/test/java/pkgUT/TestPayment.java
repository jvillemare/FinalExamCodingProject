package pkgUT;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import app.controller.Payment;

public class TestPayment {

	@Test
	public void testGetters() {
		
		/* int id, int dueDate, double payment, 
			double additionalPayment, double interest, double principal,
			double balance*/
		Payment p = new Payment(1, LocalDate.parse("2007-09-20"), 3.00, 4.00, 
				5.00, 6.00, 7.00);
		
		assertEquals(p.getId(), 1);
		assertEquals(p.getDueDate(), "2007-09-20");
		assertEquals(p.getPayment(), 3.00, 0.01);
		assertEquals(p.getAdditionalPayment(), 4.00, 0.01);
		assertEquals(p.getInterest(), 5.00, 0.01);
		assertEquals(p.getPrincipal(), 6.00, 0.01);
		assertEquals(p.getBalance(), 7.00, 0.01);
		
	}
	
	@Test
	public void testSetters() {
		
		/* int id, int dueDate, double payment, 
		double additionalPayment, double interest, double principal,
		double balance*/
		Payment p = new Payment(0, LocalDate.parse("2007-09-20"), 2.00, 3.00, 
				4.00, 5.00, 6.00);
		
		p.setId(1);
		p.setDueDate(LocalDate.parse("2007-09-21"));
		p.setPayment(3.00);
		p.setAdditionalPayment(4.00);
		p.setInterest(5.00);
		p.setPrincipal(6.00);
		p.setBalance(7.00);
	
		assertEquals(p.getId(), 1);
		assertEquals(p.getDueDate(), "2007-09-21");
		assertEquals(p.getPayment(), 3.00, 0.01);
		assertEquals(p.getAdditionalPayment(), 4.00, 0.01);
		assertEquals(p.getInterest(), 5.00, 0.01);
		assertEquals(p.getPrincipal(), 6.00, 0.01);
		assertEquals(p.getBalance(), 7.00, 0.01);
		
	}

}
