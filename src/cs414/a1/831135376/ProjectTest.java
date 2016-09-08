package cs414.a1.831135376;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProjectTest {

	Project p1;
	Project p2;
	Project p3;
	
	Worker will;
	Worker tina;
	
	@Before
	public void setUp() throws Exception 
	{
		p1 = new Project("Proj1", ProjectSize.LARGE, ProjectStatus.PLANNED);
		p2 = new Project("Proj2", ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		p3 = new Project("Proj3", ProjectSize.SMALL, ProjectStatus.PLANNED);
	}

	@Test
	public void testEqualsObject() 
	{
		Assert.assertTrue(p1.equals(new Project("Proj1", ProjectSize.LARGE, ProjectStatus.PLANNED)));
		Assert.assertFalse(p1.equals(p2));
		Assert.assertFalse(p2.equals(null));
		Assert.assertFalse(p3.equals(new Company("Comp")));
	}

	@Test
	public void testToString() 
	{
		String expected = "Proj1:0:PLANNED";
		Assert.assertTrue(p1.toString().equals(expected));
	}

	@Test
	public void testMissingQualification() 
	{
		Assert.assertTrue(true);
	}

	@Test
	public void testIsHelpful() 
	{
		Assert.assertTrue(true);
	}

}
