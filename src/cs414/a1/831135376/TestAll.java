package cs414.a1.831135376;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.JUnit4TestAdapter;

@RunWith(Suite.class)
@SuiteClasses({ CompanyTest.class, ProjectTest.class, QualificationTest.class, WorkerTest.class })
public class TestAll {
	
	// Execution begins at main(). In this test class, we will execute
	// a text test runner that will tell you if any of your tests fail.
	public static void main (String[] args)
	{
		junit.textui.TestRunner.run (suite());
	}
	
}
