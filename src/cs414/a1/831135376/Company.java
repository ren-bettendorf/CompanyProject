package cs414.a1.831135376;

import java.util.*;
import java.util.Map.Entry;

public class Company {
	
	private String name;
	
	private HashSet<Worker> assignedWorkers = new HashSet<Worker>();
	private HashMap<Worker> availablePool = new HashMap<Worker>();
	private HashSet<Project> projectsPool = new HashSet<Project>();
	
	
	public Company(String name)
	{
		this.name = name;
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
		return assignedWorkers;
	}
	
	public HashSet<Worker> getUnassignedWorkers()
	{
		HashSet<Worker> unassignedWorkers = new HashSet<Worker>();
		
		for (Worker workerPool : availablePool) 
		{
			if( !assignedWorkers.contains(workerPool) )
			{
				unassignedWorkers.add(workerPool);
			}
		}
		
		return unassignedWorkers;
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
		// Returns "Company Name:Number of workers:Number of Projects"
		return name + ":" + availablePool.size() + ":" + projectsPool.size();
	}
	
	public void addToAvailableWorkerPool(Worker worker)
	{
		if(worker == null)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			if( !availablePool.contains(worker) )
			{
				availablePool.add(worker);
			}
		}
	}
	
	public void assign(Project project, Worker worker)
	{
		if (project == null || worker == null)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			
			if ( project.isHelpful(worker) && !worker.willOverload(project) && !worker.getProjectsAssignedTo().contains(project) && availablePool.containsKey(worker) )
			{
				if ( project.getStatus() != ProjectStatus.ACTIVE && project.getStatus() != ProjectStatus.FINISHED )
				{
					assignedWorkers.add(worker);
					worker.assignProject(project);
				}
			}
		}
	}
	
	public void unassign(Project project, Worker worker)
	{
		if (project == null || worker == null)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			// Check to see if worker is on project
			if(assignedWorkers.contains(worker))
			{
				// If so then remove worker from project and project from worker
				workersAssigned.remove(worker);
				worker.unassignProject(project);
				
				// Check to see if project has any missing qualifications by removing worker and is ACTIVE
				if ( project.getStatus() == ProjectStatus.ACTIVE && project.missingQualifications().size() > 0 )
				{
					project.setStatus(ProjectStatus.SUSPENDED);
				}
			}
		}
	}
	
	public void unassignAll(Worker worker)
	{
		if (worker == null)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			// Simply call unassign for every project worker is on
			for ( Project projectWorkerIsAssigned : worker.getProjectsAssignedTo() )
			{
				unassign(projectWorkerIsAssigned, worker);
			}
		}
	}
	
	public void start(Project project)
	{
		if (project == null)
		{
			throw new IllegalArgumentException();
		}
		else
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
		if (project == null)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			if ( project.getStatus() == ProjectStatus.ACTIVE )
			{
				project.setStatus(ProjectStatus.FINISHED);
				
				for ( Worker workersAssignedToProject : project.getWorkers() )
				{
					unassignProject(project, workersAssignedToProject);
				}
			}
		}
	}
	
	public Project createProject(String _name, HashSet<Qualification> qualSet, ProjectSize size)
	{
		Project proj = null;
		
		// Enforce requirement that 1 to 1...* for project to qualifications
		if ( _name == null || qualSet == null || size == null )
		{
			throw new IllegalArgumentException();
		}
		else if ( qualSet.size() == 0 )
		{
			throw new IllegalArgumentException();
		}
		else
		{
			proj = new Project(_name, size, ProjectStatus.PLANNED);
			projectsPool.add(proj);
			addQualificationsToProject(qualSet, proj);
		}

		return proj;
	}
	
	private void addQualificationsToProject(HashSet<Qualification> qualSet, Project proj)
	{
		for (Qualification qual : qualSet)
		{
			proj.addQualification(qual);
		}
	}
}
























