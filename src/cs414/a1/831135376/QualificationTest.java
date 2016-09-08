package cs414.a1.831135376;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QualificationTest {
	
	Qualification qProgram;
	Qualification qPlan;
	Qualification qTest;
	
	@Before
	public void setUp() throws Exception 
	{
		qProgram = new Qualification("Program");
		qPlan = new Qualification("Plan");
		qTest = new Qualification("Test");
	}
	
	@Test
	public void testEqualsObject()
	{
		Assert.assertTrue(qProgram.equals(new Qualification("Program")));
		Assert.assertFalse(qPlan.equals(qTest));
		Assert.assertFalse(qPlan.equals(new Company("Comp")));
		Assert.assertFalse(qTest.equals(null));
	}

	@Test
	public void testToString()
	{
		Assert.assertTrue(qProgram.toString().equals("Program"));
		Assert.assertFalse(qPlan.toString().equals(qTest.toString()));
	}

}
