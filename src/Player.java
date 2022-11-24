import java.util.HashMap;

public class Player extends Locatable
{
	private static int nextPID = 1;
	private static final int NUM_LIVES = 2;
	
	private String myName;
	private int myPID, livesRemaining, turnsPlayed;
	private boolean isUserPlayer;
	private HashMap<Player, Integer> recentOpponents;
	
	public Player(String name, boolean userPlayer)
	{
		super();
		myName = name;
		myPID = nextPID++;
		livesRemaining = NUM_LIVES;
		isUserPlayer = userPlayer;
		turnsPlayed = 0;
		recentOpponents = new HashMap<Player, Integer>();
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
	
	public int getLivesRemaining()
	{
		return livesRemaining;
	}
	
	public int playTurn()
	{
		//move
		//check if you're bordering any opposing players whom you haven't seen within 5 turns (COUNT TURNS)
		//
		
		//reduceOpponents(); //decreases the turn-count for each of these opponents
		//addPRSOpponent();	//adds any recent opponents
		turnsPlayed++;
		return -1;
	}
	
	public int addPRSOpponent(Player p)
	{
		recentOpponents.put(p, 5);
		return -1;
	}
}
