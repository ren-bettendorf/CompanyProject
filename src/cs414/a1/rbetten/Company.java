package cs414.a1.rbetten;
import java.util.*;

public class Company {
	
	private String name;
	private HashSet<Worker> availablePool = new HashSet<Worker>();
	private HashSet<Worker> assignedPool = new HashSet<Worker>();
	private HashSet<Project> workingOn = new HashSet<Project>();
	
	public Company(String _name)
	{
		name = _name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public HashSet<Worker> getAvailableWorkers()
	{
		return availablePool;
	}
	
	public HashSet<Worker> getAssignedWorkers()
	{
		return assignedPool;
	}
	
	public HashSet<Worker> getUnassignedWorkers()
	{
		HashSet<Worker> unassignedPool = new HashSet<Worker>();
		
		for(Worker availableWorker : availablePool )
		{
			if ( !assignedPool.contains(availableWorker))
			{
				unassignedPool.add(availableWorker);
			}
		}
		return unassignedPool;
	}
	
	@Override
	public boolean equals(Object rightHandObject)
	{
		if(rightHandObject instanceof Company)
		{
			return name.equals(((Company)rightHandObject).getName());
		}
		return false;
	}
	
	@Override
	public String toString()
	{
		return name + ":" + availablePool.size() + ":" + workingOn.size();
	}
	
	public void addToAvailableWorkerPool(Worker worker)
	{
		if(worker != null)
		{
			if(!availablePool.contains(worker))
			{
				availablePool.add(worker);
			}
		}
	}
	
	public void assign(Project project, Worker worker)
	{
		if (project != null && worker != null)
		{
			if ( project.isHelpful(worker) && !worker.willOverload(project) && !worker.getAssignedTo().contains(project) && availablePool.contains(worker) )
			{
				if ( project.getStatus() != ProjectStatus.ACTIVE && project.getStatus() != ProjectStatus.FINISHED )
				{
					assignedPool.add(worker);
					project.assignWorker(worker);
					worker.assignProject(project);
				}
			}
		}
	}
	
	public void unassign(Project project, Worker worker)
	{
		if (project != null && worker != null)
		{
			project.unassignWorker(worker);
			worker.unassignProject(project);
			
			// Check to see if worker is assigned to any projects and removes if none are found		
			if ( worker.numberAssignedTo() == 0 )
			{
				assignedPool.remove(worker);
			}
			
			// Check to see if project has any missing qualifications by removing worker
			if ( project.getStatus() == ProjectStatus.ACTIVE && project.missingQualifications().size() > 0 )
			{
				project.setStatus(ProjectStatus.SUSPENDED);
			}
		}
	}
	
	public void unassignAll(Worker worker)
	{
		if (worker != null)
		{
			for ( Project projectsWorkerIsAssigned : worker.getAssignedTo() )
			{
				unassign(projectsWorkerIsAssigned, worker);
			}
		}
	}
	
	public void start(Project project)
	{
		if (project != null)
		{
			if ( project.getStatus() == ProjectStatus.PLANNED || project.getStatus() == ProjectStatus.SUSPENDED )
			{
				if ( project.missingQualifications().size() == 0)
				{
					project.setStatus(ProjectStatus.ACTIVE);
				}
			}
		}
	}
	
	public void finish(Project project)
	{
		if (project != null)
		{
			if ( project.getStatus() == ProjectStatus.ACTIVE )
			{
				project.setStatus(ProjectStatus.FINISHED);
				
				for ( Worker workersAssignedToProject : project.getWorkers() )
				{
					workersAssignedToProject.unassignProject(project);
				}
			}
		}
	}
	
	public Project createProject(String _name, HashSet<Qualification> qualSet, ProjectSize size, ProjectStatus status)
	{
		Project proj = null;
		
		if ( _name != null && qualSet != null && size != null && status != null)
		{
			if ( qualSet.size() > 0 )
			{
				proj = new Project(_name, size, status);
				workingOn.add(proj);
				
				for (Qualification qual : qualSet)
				{
					proj.addQualification(qual);
				}
			}
			
		}

		return proj;
	}
}
























