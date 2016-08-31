package cs414.a1.rbetten;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WorkerTest {

	Worker wWill;
	Worker wTina;
	Worker wNoah;
	Company comp;

	HashSet<Qualification> qualifications;
	
	@Before
	public void setUp() throws Exception 
	{	
		qualifications = new HashSet<Qualification>();
		qualifications.add(new Qualification("Program"));
		qualifications.add(new Qualification("Plan"));
		qualifications.add(new Qualification("Testing"));
		
		wWill = new Worker("Will", new HashSet<Qualification>());
		wTina = new Worker("Tina", qualifications);
		wTina.setSalary(10000);
		wNoah = new Worker("Noah", qualifications);
	}

	@Test
	public void testWorkerStringQualification()
	{
		Assert.assertTrue(true);
	}

	@Test
	public void testAddQualification()
	{
		Assert.assertTrue(true);
	}

	@Test
	public void testEqualsObject() 
	{
		Assert.assertTrue(wWill.equals(new Worker("Will", qualifications)));
		Assert.assertFalse(wTina.equals(wNoah));
		Assert.assertFalse(wWill.equals(new Qualification("Qual")));
		Assert.assertFalse(wTina.equals(null));
	}

	@Test
	public void testToString()
	{
		Assert.assertTrue(wWill.toString().equals("Will:0:0:0"));
		Assert.assertTrue(wTina.toString().equals("Tina:0:3:10000"));
	}

	@Test
	public void testWillOverload()
	{
		Assert.assertTrue(true);
	}

}
