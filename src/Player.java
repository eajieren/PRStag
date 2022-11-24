
public class Player extends Locatable
{
	private static int nextPID = 1;
	private static final int NUM_LIVES = 2;
	
	private String myName;
	private int myPID, livesRemaining;
	private boolean isUserPlayer;
	
	public Player(String name, boolean userPlayer)
	{
		super();
		myName = name;
		myPID = nextPID++;
		livesRemaining = NUM_LIVES;
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
	
	public void draw()
	{
	}
}
