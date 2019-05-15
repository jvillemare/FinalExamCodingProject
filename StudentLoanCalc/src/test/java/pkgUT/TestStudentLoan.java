package pkgUT;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.LinkedList;

import org.junit.Test;

import app.controller.Payment;
import app.controller.StudentLoan;

public class TestStudentLoan {

	@Test
	public void testTwoDecimals() {
		
		assertEquals(0.00, StudentLoan.twoDecimals(0.00), 0.01);
		assertEquals(1.00, StudentLoan.twoDecimals(1.00), 0.01);
		assertEquals(1.01, StudentLoan.twoDecimals(1.013992), 0.01);
		assertEquals(1.00, StudentLoan.twoDecimals(1.0099999), 0.01);
		
	}
	
	@Test
	public void testStudentLoan() {
		
		StudentLoan sl = new StudentLoan(150000, 0.07, 20, LocalDate.parse("2007-09-20"), 100);
		
		assertEquals(106292.13, sl.getdTotalInterest(), 0.01);
		assertEquals(236076.82, sl.getdTotalPayments(), 0.01);
		assertEquals(0.0, sl.getPMT(), 0.01);
		/* pmt should be set to zero at the end as the loan has been paid off
		 * before the term ended.
		 */
		
		LinkedList<Payment> payments = sl.getPayments();
		
		assertEquals(239, payments.size());
		
	}

}
