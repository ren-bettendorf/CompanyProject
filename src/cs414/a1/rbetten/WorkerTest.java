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

	HashSet<Qualification> originalQuals;
	HashSet<Qualification> qualifications;
	
	Project lProject1;
	Project lProject2;
	Project lProject3;	
	Project mProject1;
	
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

		lProject1 = new Project("LargeProject 1", ProjectSize.LARGE, ProjectStatus.PLANNED);
		lProject2 = new Project("LargeProject 2", ProjectSize.LARGE, ProjectStatus.PLANNED);
		lProject3 = new Project("LargeProject 3", ProjectSize.LARGE, ProjectStatus.PLANNED);	
		mProject1 = new Project("MediumProject 1", ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		
		// Ensure almost maxed out projects assigned
		wWill.assignProject(lProject1);
		wWill.assignProject(lProject2);
		wWill.assignProject(lProject3);
		wWill.assignProject(mProject1);
		
		lProject1.setStatus(ProjectStatus.ACTIVE);
		lProject2.setStatus(ProjectStatus.ACTIVE);
		lProject3.setStatus(ProjectStatus.ACTIVE);
		mProject1.setStatus(ProjectStatus.ACTIVE);
		
		originalQuals = new HashSet<Qualification>();
		for(Qualification q : wWill.getQualifications())
		{
			originalQuals.add(q);
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalSalary()
	{
		wTina.setSalary(-1);
		wTina.setSalary(-0.0000000001);
	}
	
	// Testing setSalary(double)
	@Test
	public void testSetSalaryInitial()
	{
		double zero = 0.00;
		
		// Testing default salary
		Assert.assertTrue(wWill.getSalary() == zero);
	}
	@Test
	public void testSetSalaryTrue()
	{
		// Testing set salary correct
		Assert.assertTrue(wTina.getSalary() == 10000);
	}
	@Test
	public void testSetSalaryTooManyDecimalPlaces()
	{
		// Testing too many decimal places.
		wWill.setSalary(100.0005);
		Assert.assertTrue(wWill.getSalary() == 100.00);
	}
	
	// Testing addQualification(Qualification)
	@Test
	public void testAddQualificationTrue()
	{
		Assert.assertTrue(originalQuals.equals(wWill.getQualifications()));
	}
	@Test
	public void testAddQualificationFalse()
	{
		Qualification qual = new Qualification("Qualification");
		wWill.addQualification(qual);
		
		Assert.assertFalse(originalQuals.equals(wWill.getQualifications()));
	}
	@Test(expected = IllegalArgumentException.class)
	public void testAddQualification()
	{
		wWill.addQualification(null);
	}

	// Testing equals()
	@Test
	public void testEqualsObjectTrue() 
	{
		Assert.assertTrue(wWill.equals(new Worker("Will", qualifications)));
	}
	@Test
	public void testEqualsObjectFalse() 
	{
		Assert.assertFalse(wTina.equals(wNoah));
	}
	@Test
	public void testEqualsObjectDifferent() 
	{
		Assert.assertFalse(wWill.equals(new Qualification("Qual")));
	}
	@Test
	public void testEqualsObjectNull() 
	{
		Assert.assertFalse(wTina.equals(null));
	}
	
	// Testing toString()
	@Test
	public void testToStringBase()
	{
		Assert.assertTrue(wNoah.toString().equals("Noah:0:3:0.00"));
	}
	@Test
	public void testToStringChangedSalary()
	{
		Assert.assertTrue(wTina.toString().equals("Tina:0:3:10000.00"));
	}
	
	// Testing willOverload(Project)
	@Test
	public void testWillOverloadFalse()
	{
		Project sProject1 = new Project("SmallProject 1", ProjectSize.SMALL, ProjectStatus.PLANNED);
		sProject1.setStatus(ProjectStatus.ACTIVE);

		Assert.assertFalse(wWill.willOverload(sProject1));
		
	}
	@Test
	public void testWillOverloadTrue()
	{
		Project mProject2 = new Project("SmallProject 2", ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		mProject2.setStatus(ProjectStatus.ACTIVE);
		
		Assert.assertTrue(wWill.willOverload(mProject2));
	}
	@Test(expected = IllegalArgumentException.class)
	public void testWillOverloadNull()
	{
		wWill.willOverload(null);
	}
	
}
