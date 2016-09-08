package cs414.a1.rbetten;

import java.util.*;

public class Project {
	
	private String name;
	private ProjectSize projectSize;
	private ProjectStatus status;
	
	private HashSet<Qualification> requires = new HashSet<Qualification>();
	private HashSet<Worker> workersAssignedToProject = new HashSet<Worker>();
		
	public Project(String name, ProjectSize projectSize, ProjectStatus status)
	{
		this.name = name;
		this.projectSize = projectSize;
		this.status = status;
	}
	
	public String getName()
	{
		return name;
	}
	
	public ProjectSize getSize()
	{
		return projectSize;
	}
	
	public ProjectStatus getStatus()
	{
		return status;
	}
	
	public HashSet<Worker> getWorkers()
	{
		return workersAssignedToProject;
	}
	
	public void setStatus(ProjectStatus ps)
	{
		status = ps;
	}
	
	public void assignWorker(Worker w)
	{
		workersAssignedToProject.add(w);
	}
	
	public void unassignWorker(Worker w)
	{
		workersAssignedToProject.remove(w);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if ( obj instanceof Project )
		{
			return name.equals(((Project)obj).getName());
		}
		return false;
	}
	
	public HashSet<Qualification> missingQualifications()
	{
		HashSet<Qualification> missingQualifications = new HashSet<Qualification>();
		HashSet<Qualification> allWorkerQualifications  = new HashSet<Qualification>();
		
		for ( Worker w : workersAssignedToProject )
		{
			allWorkerQualifications.addAll(w.getQualifications());
		}
		
		for ( Qualification q : requires )
		{
			if ( !allWorkerQualifications.contains(q) )
			{
				missingQualifications.add(q);
			}
		}
				
		return missingQualifications;
	}
	
	public boolean isHelpful(Worker w)
	{
		if(w == null)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			for( Qualification workerQualification : w.getQualifications() )
			{
				if ( missingQualifications().contains(workerQualification) )
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public void addQualification(Qualification q)
	{
		// Check for null pointer in q and if not null adds the requirement
		if (q == null)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			requires.add(q);
		}
	}
	
	@Override
	public String toString()
	{
		// Returns "Company Name:#Workers On Project:Project Status"
		return name + ":" + workersAssignedToProject.size() + ":" + status.toString();
	}
	
}
