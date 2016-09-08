package cs414.a1.rbetten;

import java.util.*;

public class Project {
	
	private String name;
	private ProjectSize size;
	private ProjectStatus status;
	
	private HashSet<Qualification> requires = new HashSet<Qualification>();
	private HashSet<Worker> workersOnProject = new HashSet<Worker>();
		
	public Project(String name, ProjectSize size, ProjectStatus status)
	{
		this.name = name;
		this.size = size;
		this.status = status;
	}
	
	public String getName()
	{
		return name;
	}
	
	public ProjectSize getSize()
	{
		return size;
	}
	
	public ProjectStatus getStatus()
	{
		return status;
	}
	
	public HashSet<Worker> getWorkers()
	{
		return workersOnProject;
	}
	
	public void setStatus(ProjectStatus ps)
	{
		status = ps;
	}
	
	public void assignWorker(Worker w)
	{
		workersOnProject.add(w);
	}
	
	public void unassignWorker(Worker w)
	{
		workersOnProject.remove(w);
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
	
	@Override
	public String toString()
	{
		return name + ":" + workersOnProject.size() + ":" + status.toString();
	}
	
	public HashSet<Qualification> missingQualifications()
	{
		HashSet<Qualification> missingQ = new HashSet<Qualification>();
		HashSet<Qualification> allWorkerQ  = new HashSet<Qualification>();
		
		for ( Worker w : workersOnProject )
		{
			allWorkerQ.addAll(w.getQualifications());
		}
		
		for ( Qualification q : requires )
		{
			if ( !allWorkerQ.contains(q) )
			{
				missingQ.add(q);
			}
		}
				
		return missingQ;
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
		if (q == null)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			requires.add(q);
		}
	}
}
