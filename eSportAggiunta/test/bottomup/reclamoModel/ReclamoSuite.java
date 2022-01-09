package bottomup.reclamoModel;

import bottomup.reclamoModel.ReclamoModelTestCase;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class ReclamoSuite {
	public static Test suite() {
		TestSuite suite=new TestSuite();
		suite.addTest(new ReclamoModelTestCase("doSave"));
		suite.addTest(new ReclamoModelTestCase("doRetrieveAll"));
		suite.addTest(new ReclamoModelTestCase("doRetrieveIfAttivi"));
		suite.addTest(new ReclamoModelTestCase("doUpdate"));

		return suite;
	}
	
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

}