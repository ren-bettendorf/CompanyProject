package cs414.a1.rbetten;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import junit.framework.JUnit4TestAdapter;

@RunWith(Suite.class)
@Suite.SuiteClasses({ CompanyTest.class, ProjectTest.class, QualificationTest.class, WorkerTest.class })
public class TestAll {
	public static void main(String[] args) {
		junit.textui.TestRunner.run(new JUnit4TestAdapter(TestAll.class));
	}
}
