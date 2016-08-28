import java.util.*;

public class Worker {

	private String name;
	private double salary;
	
	private HashSet<Qualification> isQualifiedFor;
	private HashSet<Project> assignedTo;
	
	public Worker()
	{
		name = "";
		salary = 0;
	}
	
	public Worker(String _name, HashSet<Qualification> qualifications)
	{
		name = _name;
		salary = 0;
		assignedTo = new HashSet<Project>();
		isQualifiedFor = new HashSet<Qualification>();
		for (Qualification qual : qualifications)
		{
			isQualifiedFor.add(qual);
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getSalary()
	{
		return salary;
	}
	
	public HashSet<Project> getAssignedTo()
	{
		return assignedTo;
	}
	
	public void setSalary(double s)
	{
		salary = s;
	}
	
	public HashSet<Qualification> getQualifications()
	{
		return isQualifiedFor;
	}
	
	public void addQualification(Qualification q)
	{
		isQualifiedFor.add(q);
		
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if ( obj instanceof Worker )
		{
			return name.equals(((Worker)obj).getName());
		}
		return false;
	}
	
	@Override
	public String toString()
	{
		return name + ":" + assignedTo.size() + ":" + isQualifiedFor.size() + ":" + (int)salary;
	}
	
	public boolean willOverload(Project p)
	{
		int sum = 0;
		for (Project projectOn : assignedTo )
		{
			if(projectOn.getStatus() == ProjectStatus.ACTIVE)
			{
				sum += projectSizeMultiple(projectOn);
			}
		}
		return sum > 12;
	}
	
	public void addProject(Project p)
	{
		if( !willOverload(p) )
		{
			assignedTo.add(p);
		}
	}
	
	public void removeProject(Project p)
	{
		if( assignedTo.contains(p) )
		{
			assignedTo.remove(p);
		}
	}
	
	public int numberAssignedTo()
	{
		return assignedTo.size();
	}
	
	private int projectSizeMultiple(Project p)
	{
		int mult = 0;
		switch(p.getSize())
		{
			case LARGE:
				mult = 3;
				break;
			case MEDIUM:
				mult = 2;
				break;
			case SMALL:
				mult = 1;
				break;
			default:
				mult = 0;
		}
		
		return mult;
	}
}
