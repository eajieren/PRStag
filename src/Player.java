import java.util.HashMap;
import java.util.Random;
import javax.swing.JOptionPane;

public class Player extends Locatable
{
	private static int nextPID = 1;
	private static final int NUM_LIVES = 2;
	
	private final double PROB_ROCK, PROB_PAPER;
	private String myName;
	private Random rand;
	private int myPID, myTeamID, livesRemaining, turnsPlayed, numBalls;
	private boolean isUserPlayer;
	private HashMap<Player, Integer> recentOpponents;
	
	public Player(String name, int teamID, boolean userPlayer)
	{
		super();
		myName = name;
		myPID = nextPID++;
		myTeamID = teamID;
		livesRemaining = NUM_LIVES;
		numBalls = 0;
		isUserPlayer = userPlayer;
		turnsPlayed = 0;
		recentOpponents = new HashMap<Player, Integer>();
		
		if(!userPlayer)
		{
			rand = new Random();
			double d1 = rand.nextDouble(), d2 = rand.nextDouble();
			if(d1 < d2)
			{
				PROB_ROCK = d1;
				PROB_PAPER = d2;
			}
			else
			{
				PROB_ROCK = d2;
				PROB_PAPER = d1;
			}
		}
		else
		{
			PROB_ROCK = PROB_PAPER = 0;
		}
		
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
	
	public int getTeamID()
	{
		return myTeamID;
	}
	
	public int getLivesRemaining()
	{
		return livesRemaining;
	}
	
	public HashMap<Player, Integer> getRecentOpponents()
	{
		return recentOpponents;
	}
	
	public RPS getRPSMove()
	{
		if(!isUserPlayer)
		{
			rand = new Random();
			double prob = rand.nextDouble();
			if(prob < PROB_ROCK)
				return RPS.ROCK;
			if(prob < PROB_PAPER)
				return RPS.PAPER;
			return RPS.SCISSORS;
		}
		else
		{
			String[] choices = {"Rock", "Paper", "Scissors"};
			int s = JOptionPane.showOptionDialog(null,
					myName + ", choose your play for this round.", "Rock-Paper-Scissors",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
					choices, choices[0]);
			String selected = choices[s];
			if(selected == "Rock")
				return RPS.ROCK;
			if(selected == "Paper")
				return RPS.PAPER;
			return RPS.SCISSORS;
		}
	}
	
	public int playTurn()
	{
		//move
		//check if you're bordering any opposing players whom you haven't seen within 5 turns (COUNT TURNS)
		//
		
		reduceOpponents(); //decreases the turn-count for each of these opponents
		
		//TEST
		//livesRemaining--;
		
		turnsPlayed++;
		return -1;
	}
	
	public int addPRSOpponent(Player p)
	{
		recentOpponents.put(p, 5);
		return -1;
	}
	
	public int loseLife()
	{
		livesRemaining--;
		return -1;
	}
	
	public int getBallCount()
	{
		return numBalls;
	}
	
	private int reduceOpponents()
	{
		//iterate through all recent opponents in HashMap;
		//if the associated integer is greater than 1, decrement it by 1;
		//if the associated integer = 1, remove the Player from the map;
		
		for (HashMap.Entry<Player,Integer> mapElement : recentOpponents.entrySet())
		{
			int val = mapElement.getValue();
			if(val > 1)
				mapElement.setValue(val - 1);
			else
				recentOpponents.remove(mapElement.getKey());
		}
		
		return -1;
	}
}
