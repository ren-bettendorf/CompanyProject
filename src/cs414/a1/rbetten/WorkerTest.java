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
		
		wWill = new Worker("Will", qualifications);
		wTina = new Worker("Tina", qualifications);
		wTina.setSalary(10000);
		wNoah = new Worker("Noah", qualifications);
	}
	
	@Test
	public void testSetSalary()
	{
		double negative = -1.00;
		double zero = 0.00;
		
		// Testing default salary
		Assert.assertTrue(wWill.getSalary() == zero);
		// Testing set salary correct
		Assert.assertTrue(wTina.getSalary() == 10000);
		
		// Testing negative setSalary();
		wNoah.setSalary(negative);
		Assert.assertFalse(wNoah.getSalary() == negative);
		
		// Testing too many decimal places.
		wWill.setSalary(100.0005);
		Assert.assertTrue(wWill.getSalary() == 100.00);
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
		Assert.assertTrue(wWill.toString().equals("Will:0:3:0.00"));
		Assert.assertTrue(wTina.toString().equals("Tina:0:3:10000.00"));
	}

	@Test
	public void testWillOverload()
	{
		Assert.assertTrue(true);
	}

}
