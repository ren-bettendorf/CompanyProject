package cs414.a1.831135376;

public class Qualification {

	public String description;
	
	public Qualification(String description)
	{
		this.description = description;
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
