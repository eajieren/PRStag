
public abstract class Locatable
{
	private Location myLoc;
	
	public Locatable()
	{
		myLoc = null;
	}
	
	public Location getLoc()
	{
		return myLoc;
	}
	
	public void setLoc(Location newLoc)
	{
		myLoc = newLoc;
	}
	
	abstract void draw();
}
