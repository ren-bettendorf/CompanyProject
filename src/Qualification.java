
public class Qualification {

	public String description;
	public Qualification()
	{}
	
	public Qualification(String q)
	{
		description = q;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Qualification)
		{
			return description.equals(((Qualification)obj).toString());
		}
		return false;
	}
	
	@Override
	public String toString()
	{
		return description;
	}
}
