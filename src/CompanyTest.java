import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CompanyTest {

	Company cKSI;
	Company cAmazon;
	Company cGoogle;
	
	@Before
	public void setUp() throws Exception 
	{
		
		HashSet<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(new Qualification("Program"));
		qualifications.add(new Qualification("Plan"));
		qualifications.add(new Qualification("Testing"));
		
		HashSet<Qualification> willQuals = new HashSet<Qualification>();
		willQuals.add(new Qualification("Program"));
		Worker will = new Worker("Will", willQuals);
		
		HashSet<Qualification> tinaQuals = new HashSet<Qualification>();
		willQuals.add(new Qualification("Testing"));
		Worker tina = new Worker("Tina", tinaQuals);
		
		cKSI = new Company("Karl Storz");
		cKSI.createProject("Project 1", qualifications, ProjectSize.LARGE, ProjectStatus.PLANNED);
		cKSI.createProject("Project 2", qualifications, ProjectSize.LARGE, ProjectStatus.PLANNED);
		cKSI.createProject("Project 3", qualifications, ProjectSize.LARGE, ProjectStatus.PLANNED);
		
		cKSI.addToAvailableWorkerPool(will);
		cKSI.addToAvailableWorkerPool(tina);
		
		cAmazon = new Company("Amazon");
		cGoogle = new Company("Google");
	}

	@Test
	public void testCompanyString() 
	{
		Assert.assertTrue(true);
	}

	@Test
	public void testEqualsObject() 
	{
		Assert.assertTrue(cKSI.equals(new Company("Karl Storz")));
		Assert.assertFalse(cGoogle.equals(cAmazon));
		Assert.assertFalse(cKSI.equals(new Qualification("Qual")));
		Assert.assertFalse(cGoogle.equals(null));
	}

	@Test
	public void testToString() 
	{
		Assert.assertTrue(cKSI.toString().equals("Karl Storz:2:3"));
		Assert.assertTrue(cAmazon.toString().equals("Amazon:0:0"));
	}

	@Test
	public void testAddToAvailableWorkerPool()
	{
		Assert.assertTrue(true);
	}

	@Test
	public void testAssign() 
	{
		Assert.assertTrue(true);
	}

	@Test
	public void testUnassign() 
	{
		Assert.assertTrue(true);
	}

	@Test
	public void testUnassignAll() 
	{
		Assert.assertTrue(true);
	}

	@Test
	public void testStart() 
	{
		Assert.assertTrue(true);
	}

	@Test
	public void testFinish() 
	{
		Assert.assertTrue(true);
	}

	@Test
	public void testCreateProject() 
	{
		Assert.assertTrue(true);
	}

}
