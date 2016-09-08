package cs414.a1.rbetten;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.textui.TestRunner;


@RunWith(Suite.class)
@SuiteClasses({ CompanyTest.class, ProjectTest.class, QualificationTest.class, WorkerTest.class })
public class TestAll {
	
	public static void main(String arg[])
    {
        TestRunner.run(Suite.class);
 
    }
	
	
}
