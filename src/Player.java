
public class Player extends Locatable
{
	private static int nextPID = 1;
	
	private String myName;
	private int myPID;
	private boolean isUserPlayer;
	
	public Player(String name, boolean userPlayer)
	{
		super();
		myName = name;
		myPID = nextPID++;
		isUserPlayer = userPlayer;
	}
	
	public String getName()
	{
		return myName;
	}
	
	public boolean isUserPlayer()
	{
		return isUserPlayer;
	}
	
	public int getPID()
	{
		return myPID;
	}
}
