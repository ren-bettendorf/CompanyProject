package cs414.a1.rbetten;
import java.util.*;
import java.util.Map.Entry;

public class Company {
	
	private String name;
	private HashMap<Project, HashSet<Worker>> workersAssignedToProjects = new HashMap<Project, HashSet<Worker>>();
	private HashMap<Worker, HashSet<Project>> availablePool = new HashMap<Worker, HashSet<Project>>();
	private HashMap<Project, HashSet<Qualification>> projectsQualifications = new HashMap<Project, HashSet<Qualification>>();
	private HashSet<Project> projectsCurrentlyActive = new HashSet<Project>();
	
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
		return (HashSet<Worker>)availablePool.keySet();
	}
	
	public HashSet<Worker> getAssignedWorkers()
	{
		HashSet<Worker> assignedWorkers = new HashSet<Worker>();
		for (Entry<Worker, HashSet<Project>> entry : availablePool.entrySet()) 
		{
			Worker w = entry.getKey();
			HashSet<Project> p = entry.getValue();
			if(p.size() > 0)
			{
				assignedWorkers.add(w);
			}
		}
		return assignedWorkers;
	}
	
	public HashSet<Worker> getUnassignedWorkers()
	{
		HashSet<Worker> assignedWorkers = new HashSet<Worker>();
		for (Entry<Worker, HashSet<Project>> entry : availablePool.entrySet()) 
		{
			Worker w = entry.getKey();
			HashSet<Project> p = entry.getValue();
			if(p.size() == 0)
			{
				assignedWorkers.add(w);
			}
		}
		return assignedWorkers;
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
		return name + ":" + availablePool.keySet().size() + ":" + workersAssignedToProjects.keySet().size();
	}
	
	public void addToAvailableWorkerPool(Worker worker)
	{
		if(worker == null)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			if(!availablePool.containsKey(worker))
			{
				availablePool.put(worker, new HashSet<Project>());
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
			
			if ( project.isHelpful(worker) && !worker.willOverload(project) && !worker.getAssignedTo().contains(project) && availablePool.containsKey(worker) )
			{
				if ( project.getStatus() != ProjectStatus.ACTIVE && project.getStatus() != ProjectStatus.FINISHED )
				{
					workersAssignedToProjects.get(project).add(worker);
					worker.assignProject(project);
					// Add project to users general assigned projects
					availablePool.get(worker).add(project);
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
			
			// If so then remove worker from project and project from worker
			
			// Check to see if project has any missing qualifications by removing worker
			if ( project.getStatus() == ProjectStatus.ACTIVE && project.missingQualifications().size() > 0 )
			{
				project.setStatus(ProjectStatus.SUSPENDED);
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
			for ( Project projectsWorkerIsAssigned : worker.getAssignedTo() )
			{
				unassign(projectsWorkerIsAssigned, worker);
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
					projectsCurrentlyActive.add(project);
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
				projectsCurrentlyActive.remove(project);
				
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
		
		// Enforce requirement that 1 to 1...* for project to qualifications
		if ( _name == null || qualSet == null || size == null || status == null || qualSet.size() == 0)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			proj = new Project(_name, size, status);
			workersAssignedToProjects.put(proj, new HashSet<Worker>());
			if(status == ProjectStatus.ACTIVE)
			{
				projectsCurrentlyActive.add(proj);
			}
			
			for (Qualification qual : qualSet)
			{
				proj.addQualification(qual);
			}

			projectsQualifications.put(proj, qualSet);
		}

		return proj;
	}
}
























