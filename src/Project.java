import java.util.*;

public class Project {
	
	private String name;
	private static ProjectSize size;
	private static ProjectStatus status;
	
	private HashSet<Qualification> requires;
	private HashSet<Worker> workersOnProject;
	
	
	public Project()
	{
		name = "";
		size = ProjectSize.LARGE;
		status = ProjectStatus.SUSPENDED;
	}
	
	public Project(String _name, ProjectSize _size, ProjectStatus _status)
	{
		name = _name;
		size = _size;
		status = _status;
		requires = new HashSet<Qualification>();
		workersOnProject = new HashSet<Worker>();
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
		for( Qualification q : w.getQualifications() )
		{
			if ( missingQualifications().contains(q) )
			{
				return true;
			}
		}
		return false;
	}
	
	public void addQualification(Qualification q)
	{
		requires.add(q);
	}
	
	public void addWorker(Worker w)
	{
		if( isHelpful(w) )
		{
			workersOnProject.add(w);
		}
	}
	
	public void removeWorker(Worker w)
	{
		if( workersOnProject.contains(w) )
		{
			workersOnProject.remove(w);
		}
	}
}
