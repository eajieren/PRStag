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
		
		//reduceOpponents(); //decreases the turn-count for each of these opponents
		//addPRSOpponent();	//adds any recent opponents
		
		//TEST
		livesRemaining--;
		
		turnsPlayed++;
		return -1;
	}
	
	public int addPRSOpponent(Player p)
	{
		recentOpponents.put(p, 5);
		return -1;
	}
}
