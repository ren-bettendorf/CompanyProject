package cs414.a1.rbetten;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.textui.TestRunner;


@RunWith(Suite.class)
@SuiteClasses({ CompanyTest.class, ProjectTest.class, QualificationTest.class, WorkerTest.class })
public class TestAll {
	public static void main(String[] args) {
	    Result result = JUnitCore.runClasses(CompanyTest.class, ProjectTest.class, QualificationTest.class, WorkerTest.class);
		
    }
	/*
	public static void main(String[] args)
	{
		junit.textui.TestRunner.run(suite());
	}
	
	public static junit.framework.Test suite()
	{
			return new JUnit4TestAdapter(AllTests.class);
	}
}
