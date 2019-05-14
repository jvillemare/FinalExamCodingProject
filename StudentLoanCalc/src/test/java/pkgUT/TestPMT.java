package pkgUT;

import static org.junit.Assert.*;

import org.apache.poi.ss.formula.functions.FinanceLib;
import org.junit.Test;

public class TestPMT {

	@Test
	public void test() {
		
		double PMT;
		double r = 0.07 / 12;
		double n = 20 * 12;
		double p = 150000;
		double f = 0;
		boolean t = false;
		
		PMT = Math.abs(FinanceLib.pmt(r, n, p, f, t));
		
		double PMTExpect = 1162.95;
		
		assertEquals(PMTExpect, PMT, 0.01);
		
	}

}
