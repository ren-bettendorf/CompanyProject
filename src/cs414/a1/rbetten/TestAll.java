package cs414.a1.rbetten;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ QualificationTest.class })
public class TestAll {
	public static void main(String[] args) {
	    Result result = JUnitCore.runClasses(QualificationTest.class);
	  }
}