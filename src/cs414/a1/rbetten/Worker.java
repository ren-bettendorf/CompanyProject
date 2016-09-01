package cs414.a1.rbetten;

import java.text.DecimalFormat;
import java.util.*;

public class Worker {

	private String name;
	private String salary;
	
	private HashSet<Qualification> isQualifiedFor = new HashSet<Qualification>();
	private HashSet<Project> assignedTo = new HashSet<Project>();
		
	public Worker(String name, HashSet<Qualification> qualifications)
	{
		this.name = name;
		this.salary = "0.00";
		
		if(qualifications.size() > 0)
		{
			for (Qualification qual : qualifications)
			{
				isQualifiedFor.add(qual);
			}
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getSalary()
	{
		return Double.valueOf(salary);
	}
	
	public HashSet<Project> getAssignedTo()
	{
		return assignedTo;
	}
	
	public void setSalary(double salary)
	{
		// Ensures worker can never have a negative salary
		if(salary >= 0.00)
		{
			this.salary = formatDouble(salary);
		}
	}
	
	public HashSet<Qualification> getQualifications()
	{
		return isQualifiedFor;
	}
	
	public void addQualification(Qualification q)
	{
		// Ensures a null qualification is not added
		if (q != null && !isQualifiedFor.contains(q))
		{
			isQualifiedFor.add(q);
		}
	}

	public int numberAssignedTo()
	{
		return assignedTo.size();
	}
	
	/** Overrides the equals method of Worker
	 * 
	 * @return		Returns true is names are equal, otherwise false
	 */
	@Override
	public boolean equals(Object obj)
	{
		if ( obj instanceof Worker )
		{
			return name.equals(((Worker)obj).getName());
		}
		return false;
	}
	
	/** Overrides the toString method of Worker
	 * 
	 * @return		Returns the worker class as a string as "Name:#Projects:#Qualifications:Salary"
	 */
	@Override
	public String toString()
	{
		return name + ":" + assignedTo.size() + ":" + isQualifiedFor.size() + ":" + salary;
	}
	
	/** Checks to see if adding a Project to the workers will overload considering only ACTIVE projects
	 * 
	 * @param p			Project that will be checked if it overloads
	 * @return 			Returns true if the sum > 12, otherwise false
	 */
	public boolean willOverload(Project p)
	{
		int sum = 0;
		if(p != null)
		{
			sum = projectSizeMultiple(p);
			for (Project projectOn : assignedTo )
			{
				if(projectOn.getStatus() == ProjectStatus.ACTIVE)
				{
					sum += projectSizeMultiple(projectOn);
				}
			}
		}
		return sum > 12;
	}
	
	/** Attempts to add Project p to worker but checks if the worker will be overloaded
	 * 
	 * @param p			Project that will be attempted to add to project
	 * @return 			
	 */
	public void assignProject(Project p)
	{
		// Ensures a null project is not added
		if (p != null)
		{
			ProjectStatus status = p.getStatus();
			if( !willOverload(p) && !(status == ProjectStatus.ACTIVE || status == ProjectStatus.FINISHED ) );
			{
				assignedTo.add(p);
			}
		}
	}
	
	public void unassignProject(Project p)
	{
		// Ensures a null project is not added
		if (p != null)
		{
			if( assignedTo.contains(p) )
			{
				assignedTo.remove(p);
			}
		}
	}
	
	/** Returns the projectSizeMultiple(Project p)
	 * 
	 * @param p			Project size to be checked
	 * @return mult		Returns the project size multiple
	 */
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
				break;
		}
		
		return mult;
	}
	
	// Formats string to ensure 2 decimal places
	private String formatDouble(double d)
	{
		DecimalFormat decimalFormatter = new DecimalFormat("#.00");
		return decimalFormatter.format(d);
	}
}
