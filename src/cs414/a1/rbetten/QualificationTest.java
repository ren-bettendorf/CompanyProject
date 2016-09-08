package cs414.a1.rbetten;

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
	public void testEqualsObjectTrue()
	{
		Assert.assertTrue(qProgram.equals(new Qualification("Program")));
	}
	@Test
	public void testEqualsObjectFalse()
	{
		Assert.assertFalse(qPlan.equals(qTest));
	}
	@Test
	public void testEqualsObjectDifferent()
	{
		Assert.assertFalse(qPlan.equals(new Company("Comp")));
	}
	@Test
	public void testEqualsObjectNull()
	{
		Assert.assertFalse(qTest.equals(null));
	}

	@Test
	public void testToStringTrue()
	{
		Assert.assertTrue(qProgram.toString().equals("Program"));
	}
	@Test
	public void testToStringFalse()
	{
		Assert.assertFalse(qPlan.toString().equals(qTest.toString()));
	}

}
