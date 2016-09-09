package cs414.a1.rbetten;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProjectTest {

	Project p1;
	Project p2;
	Project p3;
	Project projectActive;
	
	
	@Before
	public void setUp() throws Exception 
	{
		p1 = new Project("Proj1", ProjectSize.LARGE, ProjectStatus.PLANNED);
		p2 = new Project("Proj2", ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		p3 = new Project("Proj3", ProjectSize.SMALL, ProjectStatus.PLANNED);
		
		projectActive = new Project("ProjectActive", ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		projectActive.setStatus(ProjectStatus.ACTIVE);
		
		HashSet<Qualification> workerQualifications = new HashSet<Qualification>();
		Qualification q1 = new Qualification("Planning");
		Qualification q2 = new Qualification("Learning");
		Qualification q3 = new Qualification("Coding");
		Qualification q4 = new Qualification("Another");
		workerQualifications.add(q1);
		workerQualifications.add(q2);
		workerQualifications.add(q3);
		
		Worker will = new Worker("Will", workerQualifications);
		Worker tina = new Worker("Tina", workerQualifications);
		
		p2.assignWorker(will);
		p2.assignWorker(tina);
		
		p2.addQualification(q1);
		p2.addQualification(q2);
		p2.addQualification(q3);
		p2.addQualification(q4);		
	}

	// Testing Equals()
	@Test
	public void testEqualsObject() 
	{
		Assert.assertTrue(p1.equals(new Project("Proj1", ProjectSize.LARGE, ProjectStatus.PLANNED)));
	}
	@Test
	public void testEqualsObjectFails() 
	{
		Assert.assertFalse(p1.equals(p2));	
	}
	@Test
	public void testEqualsObjectNull() 
	{
		Assert.assertFalse(p2.equals(null));		
	}
	@Test
	public void testEqualsObjectDifferent() 
	{
		Assert.assertFalse(p3.equals(new Company("Comp")));	
	}
	
	// Testing toString()
	@Test
	public void testToStringBaseCreation() 
	{
		String expected = "Proj1:0:PLANNED";
		Assert.assertTrue(p1.toString().equals(expected));
	}
	@Test
	public void testToStringActiveStatus() 
	{
		String expected = "ProjectActive:0:ACTIVE";
		Assert.assertTrue(projectActive.toString().equals(expected));
	}
	@Test
	public void testToStringWorkers() 
	{
		String expected = "Proj2:2:PLANNED";
		Assert.assertTrue(p2.toString().equals(expected));
	}
	
	// Testing missingQualification()
	@Test
	public void testMissingQualificationMissingNone() 
	{
		Assert.assertTrue(p1.missingQualifications().equals(new HashSet<Qualification>()));
	}
	@Test
	public void testMissingQualificationMissingOne() 
	{
		HashSet<Qualification> expected = new HashSet<Qualification>();
		Qualification q4 = new Qualification("Another");
		expected.add(q4);
		Project project = new Project("Project", ProjectSize.MEDIUM, ProjectStatus.ACTIVE);
		project.addQualification(q4);
		Assert.assertTrue(project.missingQualifications().equals(expected));
	}
	@Test
	public void testMissingQualificationIgnoreOrder() 
	{
		HashSet<Qualification> expected = new HashSet<Qualification>();
		Qualification qA = new Qualification("A");
		Qualification qB = new Qualification("B");
		expected.add(qA);
		expected.add(qB);
		
		Project projectOrderIgnored = new Project("ProjectOrderIgnored", ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		projectOrderIgnored.addQualification(qB);
		projectOrderIgnored.addQualification(qA);
		
		Assert.assertTrue(projectOrderIgnored.missingQualifications().equals(expected));
	}
	
	// Testing isHelpful()
	@Test
	public void testIsHelpfulTrue() 
	{
		HashSet<Qualification> helpfulSet = new HashSet<Qualification>();
		Qualification qAnother = new Qualification("Another");
		helpfulSet.add(qAnother);
		Project project = new Project("Project", ProjectSize.SMALL, ProjectStatus.ACTIVE);
		project.addQualification(qAnother);
		Worker helpfulWorker = new Worker("helpful worker", helpfulSet);
		Assert.assertTrue(project.isHelpful(helpfulWorker));
	}
	@Test
	public void testIsHelpfulFalse() 
	{
		HashSet<Qualification> notHelpfulSet = new HashSet<Qualification>();
		Qualification qFalse = new Qualification("False");
		notHelpfulSet.add(qFalse);
		Worker notHelpfulWorker = new Worker("not helpful worker", notHelpfulSet);
		Assert.assertFalse(p2.isHelpful(notHelpfulWorker));
	}
	@Test(expected = IllegalArgumentException.class)
	public void testIsHelpfulNull() 
	{
		Worker nullWorker = null;
		p2.isHelpful(nullWorker);
	}

}
