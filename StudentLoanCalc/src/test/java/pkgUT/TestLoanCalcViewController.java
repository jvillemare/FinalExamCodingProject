package pkgUT;

import static org.junit.Assert.*;

import org.junit.Test;

import app.controller.LoanCalcViewController;

public class TestLoanCalcViewController {

	@Test
	public void testTwoDecimals() {
		
		assertEquals(0.00, LoanCalcViewController.twoDecimals(0.00), 0.01);
		assertEquals(1.00, LoanCalcViewController.twoDecimals(1.00), 0.01);
		assertEquals(1.01, LoanCalcViewController.twoDecimals(1.013992), 0.01);
		assertEquals(1.00, LoanCalcViewController.twoDecimals(1.0099999), 0.01);
	}

}
