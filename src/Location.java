
public class Location
{
	private static final int PRIME1 = 89, PRIME2 = 97;
	private int myRow, myCol;
	
	public Location(int row, int col)
	{
		myRow = row;
		myCol = col;
	}
	
	public int getRow()
	{
		return myRow;
	}
	
	public int getCol()
	{
		return myCol;
	}
	
	//override the equals method (from the Object class) in order to adequately compare Locations
	public boolean equals(Object o)
	{
		//if this is the same object, return true
		if(o == this)
			return true;
		
		//if o is not an instance of Location, it cannot be adequately compared; so, return false
		if(!(o instanceof Location))
			return false;
		
		//cast it to Location and compare row and loc
		Location other = (Location) o;
		
		return Integer.compare(myRow, other.getRow()) == 0 && Integer.compare(myCol,  other.getCol()) == 0;
		
	}
	
	//override hashCode method (from the Object class) in order for hash-based containers to work with Location objs
	public int hashCode()
	{
		return PRIME1*myRow + PRIME2*myCol;
	}
	
	public String toString()
	{
		return new String("(" + myRow + "," + myCol + ")");
	}
}
