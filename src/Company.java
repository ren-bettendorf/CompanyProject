import java.util.*;

public class Company {
	
	private String name;
	private HashSet<Worker> availablePool;
	private HashSet<Worker> assignedPool;
	private HashSet<Project> workingOn;
	
	public Company()
	{
		name = "";
	}
	
	public Company(String _name)
	{
		name = _name;
		availablePool = new HashSet<Worker>();
		assignedPool = new HashSet<Worker>();
		workingOn = new HashSet<Project>();
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
		for(Worker w : availablePool )
		{
			if ( !assignedPool.contains(w))
			{
				unassignedPool.add(w);
			}
		}
		return unassignedPool;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof Company)
		{
			return name.equals(((Company)obj).getName());
		}
		return false;
	}
	
	@Override
	public String toString()
	{
		return name + ":" + availablePool.size() + ":" + workingOn.size();
	}
	
	public void addToAvailableWorkerPool(Worker w)
	{
		if(!availablePool.contains(w))
		{
			availablePool.add(w);
		}
	}
	
	public void assign(Project p, Worker w)
	{
		if ( p.isHelpful(w) && !w.willOverload(p) && !w.getAssignedTo().contains(p) && availablePool.contains(w) )
		{
			if ( p.getStatus() != ProjectStatus.ACTIVE && p.getStatus() != ProjectStatus.FINISHED )
			{
				assignedPool.add(w);
				p.addWorker(w);
				w.addProject(p);
			}
		}
	}
	
	public void unassign(Project p, Worker w)
	{
		p.removeWorker(w);
		w.removeProject(p);
		
		if ( w.numberAssignedTo() == 0 )
		{
			assignedPool.remove(w);
		}
		
		if ( p.getStatus() == ProjectStatus.ACTIVE && p.missingQualifications().size() > 0 )
		{
			p.setStatus(ProjectStatus.SUSPENDED);
		}
		
	}
	
	public void unassignAll(Worker w)
	{
		for ( Project p : w.getAssignedTo() )
		{
			unassign(p, w);
		}
	}
	
	public void start(Project p)
	{
		if ( p.getStatus() == ProjectStatus.PLANNED || p.getStatus() == ProjectStatus.SUSPENDED )
		{
			if ( p.missingQualifications().size() == 0)
			{
				p.setStatus(ProjectStatus.ACTIVE);
			}
		}
	}
	
	public void finish(Project p)
	{
		if ( p.getStatus() == ProjectStatus.ACTIVE )
		{
			p.setStatus(ProjectStatus.FINISHED);
			
			for ( Worker w : p.getWorkers() )
			{
				w.toString();
			}
		}
	}
	
	public Project createProject(String _name, HashSet<Qualification> qualSet, ProjectSize size, ProjectStatus status)
	{
		Project proj = new Project(_name, size, status);
		workingOn.add(proj);
		
		for (Qualification qual : qualSet)
		{
			proj.addQualification(qual);
		}
		
		return proj;
	}
}
























