package cs414.a1.rbetten;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CompanyTest {

	Company cKSI;
	Company cAmazon;
	Company cGoogle;
	
	HashSet<Qualification> willQuals;
	
	Worker will;
	
	@Before
	public void setUp() throws Exception 
	{
		HashSet<Qualification> qualifications = new HashSet<Qualification>();
		qualifications.add(new Qualification("Program"));
		qualifications.add(new Qualification("Plan"));
		qualifications.add(new Qualification("Testing"));
		
		willQuals = new HashSet<Qualification>();
		willQuals.add(new Qualification("Program"));
		will = new Worker("Will", willQuals);
		
		//HashSet<Qualification> tinaQuals = new HashSet<Qualification>();
		willQuals.add(new Qualification("Testing"));
		Worker tina = new Worker("Tina", willQuals);
		
		cKSI = new Company("Karl Storz");
		cKSI.createProject("Project 1", qualifications, ProjectSize.LARGE);
		cKSI.createProject("Project 2", qualifications, ProjectSize.LARGE);
		cKSI.createProject("Project 3", qualifications, ProjectSize.LARGE);
		
		cKSI.addToAvailableWorkerPool(will);
		cKSI.addToAvailableWorkerPool(tina);
		
		cAmazon = new Company("Amazon");
		cGoogle = new Company("Google");
	}

	// Testing equals()
	@Test
	public void testEqualsObject() 
	{
		Assert.assertTrue(cKSI.equals(new Company("Karl Storz")));
	}
	@Test
	public void testEqualsDiffObjects()
	{
		Assert.assertFalse(cKSI.equals(new Qualification("Qual")));
	}	
	@Test
	public void testEqualsDiffCompanies()
	{
		Assert.assertFalse(cGoogle.equals(cAmazon));
	}
	@Test
	public void testEqualsNullCompanies()
	{
		Assert.assertFalse(cGoogle.equals(null));
	}	
	
	// Testing toString()
	@Test
	public void testToStringCreated() 
	{
		Assert.assertTrue(cAmazon.toString().equals("Amazon:0:0"));
	}
	@Test
	public void testToStringChanged() 
	{
		Assert.assertTrue(cKSI.toString().equals("Karl Storz:2:3"));
	}

	// Testing addToAvailableWorkerPool(Worker)
	@Test
	public void testAddToAvailableWorkerPoolAddingWillFirstTime()
	{
		HashSet<Worker> expected = new HashSet<Worker>();
		expected.add(will);
		cAmazon.addToAvailableWorkerPool(will);
		Assert.assertTrue(cAmazon.getAvailableWorkers().equals(expected));
	}
	@Test
	public void testAddToAvailableWorkerPoolAddingSamePersonTwice()
	{
		HashSet<Worker> expected = new HashSet<Worker>();
		expected.add(will);
		cAmazon.addToAvailableWorkerPool(will);
		Assert.assertTrue(cAmazon.getAvailableWorkers().equals(expected));
	}
	@Test(expected = IllegalArgumentException.class)
	public void testAddToAvailableWorkerPoolWorkerNull() 
	{
		cKSI.addToAvailableWorkerPool(null);
	}

	// Testing assign(Project, Worker)
	@Test
	public void testAssignProjectTrue() 
	{
		Qualification qA = new Qualification("A");
		Qualification qB = new Qualification("B");
		Qualification qC = new Qualification("C");
		HashSet<Qualification> projectQuals = new HashSet<Qualification>();
		projectQuals.add(qA);
		projectQuals.add(qB);
		projectQuals.add(qC);
		
		Project project = cKSI.createProject("Project", projectQuals, ProjectSize.SMALL);
		

		HashSet<Qualification> workerQuals = new HashSet<Qualification>();
		workerQuals.add(qA);
		workerQuals.add(qB);
		workerQuals.add(qC);
		
		Worker worker = new Worker("Worker", workerQuals);
		cKSI.addToAvailableWorkerPool(worker);
		
		cKSI.assign(project, worker);
		Assert.assertTrue(project.getWorkers().contains(worker) && project.getWorkers().size() == 1);
	}
	@Test
	public void testAssignWorkerTrue() 
	{
		Qualification qA = new Qualification("A");
		Qualification qB = new Qualification("B");
		Qualification qC = new Qualification("C");
		HashSet<Qualification> projectQuals = new HashSet<Qualification>();
		projectQuals.add(qA);
		projectQuals.add(qB);
		projectQuals.add(qC);
		
		Project project = cKSI.createProject("Project", projectQuals, ProjectSize.SMALL);
		

		HashSet<Qualification> workerQuals = new HashSet<Qualification>();
		workerQuals.add(qA);
		workerQuals.add(qB);
		workerQuals.add(qC);
		
		Worker worker = new Worker("Worker", workerQuals);
		cKSI.addToAvailableWorkerPool(worker);
		
		cKSI.assign(project, worker);
		Assert.assertTrue(worker.getProjectsAssignedTo().contains(project) && worker.getProjectsAssignedTo().size() == 1);
	}
	@Test
	public void testAssignCompanyTrue() 
	{
		Qualification qA = new Qualification("A");
		Qualification qB = new Qualification("B");
		Qualification qC = new Qualification("C");
		HashSet<Qualification> projectQuals = new HashSet<Qualification>();
		projectQuals.add(qA);
		projectQuals.add(qB);
		projectQuals.add(qC);
		
		Project project = cKSI.createProject("Project", projectQuals, ProjectSize.SMALL);
		

		HashSet<Qualification> workerQuals = new HashSet<Qualification>();
		workerQuals.add(qA);
		workerQuals.add(qB);
		workerQuals.add(qC);
		
		Worker worker = new Worker("Worker", workerQuals);
		cKSI.addToAvailableWorkerPool(worker);
		
		cKSI.assign(project, worker);
		Assert.assertTrue(cKSI.getAssignedWorkers().contains(worker));
	}
	@Test
	public void testAssignWorkerFalse() 
	{
		Qualification qA = new Qualification("A");
		Qualification qB = new Qualification("B");
		Qualification qC = new Qualification("C");
		HashSet<Qualification> projectQuals = new HashSet<Qualification>();
		projectQuals.add(qA);
		projectQuals.add(qB);
		projectQuals.add(qC);
		
		Project project = cKSI.createProject("Project", projectQuals, ProjectSize.SMALL);
		

		HashSet<Qualification> workerQuals = new HashSet<Qualification>();
		Qualification qFalse = new Qualification("False");
		workerQuals.add(qFalse);
		
		Worker worker = new Worker("Worker", workerQuals);
		cKSI.addToAvailableWorkerPool(worker);
		
		cKSI.assign(project, worker);
		Assert.assertFalse(worker.getProjectsAssignedTo().contains(project) && worker.getProjectsAssignedTo().size() == 1);
	}
	@Test
	public void testAssignProjectFalse() 
	{
		// Setting up Test
		Qualification qA = new Qualification("A");
		Qualification qB = new Qualification("B");
		Qualification qC = new Qualification("C");
		HashSet<Qualification> projectQuals = new HashSet<Qualification>();
		projectQuals.add(qA);
		projectQuals.add(qB);
		projectQuals.add(qC);
		
		Project project = cKSI.createProject("Project", projectQuals, ProjectSize.SMALL);
		
		HashSet<Qualification> workerQuals = new HashSet<Qualification>();
		Qualification qFalse = new Qualification("False");
		workerQuals.add(qFalse);
		
		Worker worker = new Worker("Worker", workerQuals);
		cKSI.addToAvailableWorkerPool(worker);
		
		// Method being tested
		cKSI.assign(project, worker);
		Assert.assertFalse(project.getWorkers().contains(worker) && project.getWorkers().size() == 1);
	}
	@Test
	public void testAssignCompanyFalse() 
	{
		Qualification qA = new Qualification("A");
		Qualification qB = new Qualification("B");
		Qualification qC = new Qualification("C");
		HashSet<Qualification> projectQuals = new HashSet<Qualification>();
		projectQuals.add(qA);
		projectQuals.add(qB);
		projectQuals.add(qC);
		
		Project project = cKSI.createProject("Project", projectQuals, ProjectSize.SMALL);
		

		HashSet<Qualification> workerQuals = new HashSet<Qualification>();
		Qualification qFalse = new Qualification("False");
		workerQuals.add(qFalse);
		
		Worker worker = new Worker("Worker", workerQuals);
		cKSI.addToAvailableWorkerPool(worker);
		
		cKSI.assign(project, worker);
		Assert.assertFalse(cKSI.getAssignedWorkers().contains(worker));
	}
	@Test(expected = IllegalArgumentException.class)
	public void testAssignWorkerNull() 
	{
		Project proj = new Project("P", ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		cKSI.assign(proj, null);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testAssignProjectNull() 
	{
		cKSI.assign(null, will);
	}
	
	// Testing unassign(Project, Worker)
	@Test
	public void testUnassignCompanyTrue() 
	{
		Qualification qA = new Qualification("A");
		Qualification qB = new Qualification("B");
		Qualification qC = new Qualification("C");
		HashSet<Qualification> projectQuals = new HashSet<Qualification>();
		projectQuals.add(qA);
		projectQuals.add(qB);
		projectQuals.add(qC);
		
		Project project = cKSI.createProject("Project", projectQuals, ProjectSize.SMALL);
		

		HashSet<Qualification> workerQuals = new HashSet<Qualification>();
		workerQuals.add(qA);
		workerQuals.add(qB);
		workerQuals.add(qC);
		
		Worker worker = new Worker("Worker", workerQuals);
		cKSI.addToAvailableWorkerPool(worker);
		
		cKSI.assign(project, worker);
		cKSI.unassign(project, worker);
		Assert.assertTrue(!cKSI.getAssignedWorkers().contains(worker) && cKSI.getAvailableWorkers().contains(worker));
	}
	@Test
	public void testUnassignProjectTrue() 
	{
		Qualification qA = new Qualification("A");
		Qualification qB = new Qualification("B");
		Qualification qC = new Qualification("C");
		HashSet<Qualification> projectQuals = new HashSet<Qualification>();
		projectQuals.add(qA);
		projectQuals.add(qB);
		projectQuals.add(qC);
		
		Project project = cKSI.createProject("Project", projectQuals, ProjectSize.SMALL);
		

		HashSet<Qualification> workerQuals = new HashSet<Qualification>();
		workerQuals.add(qA);
		workerQuals.add(qB);
		workerQuals.add(qC);
		
		Worker worker = new Worker("Worker", workerQuals);
		cKSI.addToAvailableWorkerPool(worker);
		
		cKSI.assign(project, worker);
		cKSI.unassign(project, worker);
		Assert.assertTrue(!project.getWorkers().contains(worker));
	}
	@Test
	public void testUnassignWorkerTrue() 
	{
		Qualification qA = new Qualification("A");
		Qualification qB = new Qualification("B");
		Qualification qC = new Qualification("C");
		HashSet<Qualification> projectQuals = new HashSet<Qualification>();
		projectQuals.add(qA);
		projectQuals.add(qB);
		projectQuals.add(qC);
		
		Project project = cKSI.createProject("Project", projectQuals, ProjectSize.SMALL);
		

		HashSet<Qualification> workerQuals = new HashSet<Qualification>();
		workerQuals.add(qA);
		workerQuals.add(qB);
		workerQuals.add(qC);
		
		Worker worker = new Worker("Worker", workerQuals);
		cKSI.addToAvailableWorkerPool(worker);
		
		cKSI.assign(project, worker);
		cKSI.unassign(project, worker);
		Assert.assertTrue(!worker.getProjectsAssignedTo().contains(project));
	}
	@Test
	public void testUnassignProjectSuspended() 
	{
		Qualification qA = new Qualification("A");
		Qualification qB = new Qualification("B");
		Qualification qC = new Qualification("C");
		HashSet<Qualification> projectQuals = new HashSet<Qualification>();
		projectQuals.add(qA);
		projectQuals.add(qB);
		projectQuals.add(qC);
		
		Project project = cKSI.createProject("Project", projectQuals, ProjectSize.SMALL);
		

		HashSet<Qualification> workerQuals = new HashSet<Qualification>();
		workerQuals.add(qA);
		workerQuals.add(qB);
		workerQuals.add(qC);
		
		Worker worker = new Worker("Worker", workerQuals);
		cKSI.addToAvailableWorkerPool(worker);
		
		cKSI.assign(project, worker);
		cKSI.start(project);
		cKSI.unassign(project, worker);
		Assert.assertTrue(project.getStatus().equals(ProjectStatus.SUSPENDED));
	}
	@Test
	public void testUnassignMultipleProjects()
	{
		Qualification qA = new Qualification("A");
		Qualification qB = new Qualification("B");
		Qualification qC = new Qualification("C");
		HashSet<Qualification> projectQuals = new HashSet<Qualification>();
		projectQuals.add(qA);
		projectQuals.add(qB);
		projectQuals.add(qC);
		
		Project project = cKSI.createProject("Project", projectQuals, ProjectSize.SMALL);
		Project project2 = cKSI.createProject("Project2", projectQuals, ProjectSize.MEDIUM);
		
	
		HashSet<Qualification> workerQuals = new HashSet<Qualification>();
		workerQuals.add(qA);
		workerQuals.add(qB);
		workerQuals.add(qC);
		
		Worker worker = new Worker("Worker", workerQuals);
		cKSI.addToAvailableWorkerPool(worker);
		
		cKSI.assign(project, worker);
		cKSI.assign(project2, worker);
		
		cKSI.unassign(project, worker);
		Assert.assertTrue(!project.getWorkers().contains(worker) && project2.getWorkers().contains(worker));
	}
	@Test(expected = IllegalArgumentException.class)
	public void testUnassignWorkerNull() 
	{
		Project proj = new Project("P", ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		cKSI.unassign(proj, null);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testUnassignProjectNull() 
	{
		cKSI.unassign(null, will);
	}
	
	// Testing unassignAll(Worker)
	@Test
	public void testUnassignAllCompany() 
	{
		Qualification qA = new Qualification("A");
		Qualification qB = new Qualification("B");
		Qualification qC = new Qualification("C");
		HashSet<Qualification> projectQuals = new HashSet<Qualification>();
		projectQuals.add(qA);
		projectQuals.add(qB);
		projectQuals.add(qC);
		
		Project project = cKSI.createProject("Project", projectQuals, ProjectSize.SMALL);
		Project project2 = cKSI.createProject("Project2", projectQuals, ProjectSize.MEDIUM);
		

		HashSet<Qualification> workerQuals = new HashSet<Qualification>();
		workerQuals.add(qA);
		workerQuals.add(qB);
		workerQuals.add(qC);
		
		Worker worker = new Worker("Worker", workerQuals);
		cKSI.addToAvailableWorkerPool(worker);
		
		cKSI.assign(project, worker);
		cKSI.assign(project2, worker);
		
		cKSI.unassignAll(worker);
		Assert.assertTrue(!cKSI.getAssignedWorkers().contains(worker) && cKSI.getAvailableWorkers().contains(worker));
	}
	@Test
	public void testUnassignAllProject() 
	{
		Qualification qA = new Qualification("A");
		Qualification qB = new Qualification("B");
		Qualification qC = new Qualification("C");
		HashSet<Qualification> projectQuals = new HashSet<Qualification>();
		projectQuals.add(qA);
		projectQuals.add(qB);
		projectQuals.add(qC);
		
		Project project = cKSI.createProject("Project", projectQuals, ProjectSize.SMALL);
		Project project2 = cKSI.createProject("Project2", projectQuals, ProjectSize.MEDIUM);
		

		HashSet<Qualification> workerQuals = new HashSet<Qualification>();
		workerQuals.add(qA);
		workerQuals.add(qB);
		workerQuals.add(qC);
		
		Worker worker = new Worker("Worker", workerQuals);
		cKSI.addToAvailableWorkerPool(worker);
		
		cKSI.assign(project, worker);
		cKSI.assign(project2, worker);
		
		cKSI.unassignAll(worker);
		Assert.assertTrue(!project.getWorkers().contains(worker) && !project2.getWorkers().contains(worker));
	}
	@Test
	public void testUnassignAllWorker() 
	{
		Qualification qA = new Qualification("A");
		Qualification qB = new Qualification("B");
		Qualification qC = new Qualification("C");
		HashSet<Qualification> projectQuals = new HashSet<Qualification>();
		projectQuals.add(qA);
		projectQuals.add(qB);
		projectQuals.add(qC);
		
		Project project = cKSI.createProject("Project", projectQuals, ProjectSize.SMALL);
		Project project2 = cKSI.createProject("Project2", projectQuals, ProjectSize.MEDIUM);
		

		HashSet<Qualification> workerQuals = new HashSet<Qualification>();
		workerQuals.add(qA);
		workerQuals.add(qB);
		workerQuals.add(qC);
		
		Worker worker = new Worker("Worker", workerQuals);
		cKSI.addToAvailableWorkerPool(worker);
		
		cKSI.assign(project, worker);
		cKSI.assign(project2, worker);
		
		cKSI.unassignAll(worker);
		HashSet<Project> projAssigned = worker.getProjectsAssignedTo();
		Assert.assertTrue(!projAssigned.contains(project) && !projAssigned.contains(project2) && projAssigned.size() == 0);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testUnassignAllWorkerNull() 
	{
		cKSI.unassignAll(null);
	}
	
	// Testing start(Project)
	@Test
	public void testStartPlannedTrue() 
	{
		Project project = new Project("True Project", ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		cKSI.start(project);
		Assert.assertTrue(project.getStatus().equals(ProjectStatus.ACTIVE));
	}
	@Test
	public void testStartSuspendedTrue() 
	{
		Project project = new Project("True Project", ProjectSize.MEDIUM, ProjectStatus.SUSPENDED);
		cKSI.start(project);
		Assert.assertTrue(project.getStatus().equals(ProjectStatus.ACTIVE));
	}
	@Test
	public void testStartActiveTrue() 
	{
		Project project = new Project("True Project", ProjectSize.MEDIUM, ProjectStatus.ACTIVE);
		cKSI.start(project);
		Assert.assertTrue(project.getStatus().equals(ProjectStatus.ACTIVE));
	}
	@Test
	public void testStartFinishedTrue() 
	{
		Project project = new Project("True Project", ProjectSize.MEDIUM, ProjectStatus.FINISHED);
		cKSI.start(project);
		Assert.assertTrue(project.getStatus().equals(ProjectStatus.FINISHED));
	}
	@Test
	public void testStartPlannedFalse() 
	{
		Project project = new Project("True Project", ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		Qualification q = new Qualification("Q");
		project.addQualification(q);
		cKSI.start(project);
		Assert.assertFalse(project.getStatus().equals(ProjectStatus.ACTIVE));
	}
	@Test
	public void testStartSuspendedFalse() 
	{
		Project project = new Project("True Project", ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		Qualification q = new Qualification("Q");
		project.addQualification(q);
		cKSI.start(project);
		Assert.assertFalse(project.getStatus().equals(ProjectStatus.ACTIVE));
	}
	@Test(expected = IllegalArgumentException.class)
	public void testStartProjectNull() 
	{
		cKSI.start(null);
	}
	
	// Testing finish(Project)
	@Test
	public void testFinishWorkerRemoved() 
	{
		Qualification qA = new Qualification("A");
		Qualification qB = new Qualification("B");
		Qualification qC = new Qualification("C");
		HashSet<Qualification> projectQuals = new HashSet<Qualification>();
		projectQuals.add(qA);
		projectQuals.add(qB);
		projectQuals.add(qC);
		
		Project project = cKSI.createProject("Project", projectQuals, ProjectSize.SMALL);
		

		HashSet<Qualification> workerQuals = new HashSet<Qualification>();
		workerQuals.add(qA);
		workerQuals.add(qB);
		workerQuals.add(qC);
		
		Worker worker = new Worker("Worker", workerQuals);
		cKSI.addToAvailableWorkerPool(worker);
		
		cKSI.assign(project, worker);
		cKSI.start(project);
		cKSI.finish(project);
		
		Assert.assertTrue(worker.getProjectsAssignedTo().size() == 0);
	}
	
	// Testing createProject(String, HashSet<Qualification>, ProjectSize)
	@Test(expected = IllegalArgumentException.class)
	public void testCreateProjectStringNull() 
	{
		cKSI.createProject(null, willQuals, ProjectSize.SMALL);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testCreateProjectQualsNull() 
	{
		cKSI.createProject("Project", null, ProjectSize.SMALL);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testCreateProjectProjectSizeNull() 
	{
		cKSI.createProject("Project", willQuals, null);
	}

}
