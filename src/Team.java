import java.lang.Math;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Team
{
	private static int nextTeamID = 1;
	public static final int PLYRS_PER_TEAM = 5;
	private static final String[] OPPOSING_NAMES = {"Squirt", "Hazel", "Evie", "Lewis", "Clark", "Toby",
			"Sage", "Basil", "Polly", "Martin", "Wren", "Julia", "Carmen", "Daniel", "Rafael", "Alex",
			"Rex", "Maggie", "Rick", "Olivia", "Quentin", "Vivian", "Zoe", "Michelle", "Yolanda",
			"Jeremiah", "Dexter", "Fiona", "Guglielmo", "Haley", "Irene", "Jacques", "Kingley",
			"Lydia", "Mozart", "Nubila", "Octavius", "Prince", "Rhett", "Dwayne", "Syrena", "Umbert",
			"Victor", "Briony", "Sylvester", "Cameron", "Bryce", "Latavius", "Rodrick", "Alan", "Elemeno",
			"Natalie", "Nathan", "Chris", "Carlos", "Hector", "Bree", "Julian", "Yvette", "Iggy",
			"Vivalia", "JoAnn", "Lina", "Josie", "Bob", "Colton", "Twila", "Xavier", "Pippa", "Wei", "Jing",
			"Chloe", "Tyrese", "Anthony", "Alina", "Ruth", "Alma", "Rosette", "Ernest", "Felipe", "Carson",
			"Knox"};
	private static final Random RAND = new Random();
	
	private int teamID;
	private boolean isUserTeam;
	private Player[] myPlayers;
	
	public Team(boolean userTeam)
	{
		teamID = nextTeamID++;
		isUserTeam = userTeam;
		myPlayers = new Player[PLYRS_PER_TEAM];
		
		if(userTeam)
		{
			for(int i = 0; i < PLYRS_PER_TEAM; i++)
			{
				int index = ((int) Math.abs(RAND.nextInt())) % OPPOSING_NAMES.length;
				myPlayers[i] = new Player(OPPOSING_NAMES[index], false);
			}
		}
		else
		{
			JFrame f;
			for(int i = 0; i < PLYRS_PER_TEAM; i++)
			{
				f = new JFrame();
				String name = JOptionPane.showInputDialog(f, "Setting up your team. Please enter the name for Player #" + (i+1));
				myPlayers[i] = new Player(name, true);
			}
		}
	}
	
	public int getTeamID()
	{
		return teamID;
	}
	
	public boolean isUserTeam()
	{
		return isUserTeam;
	}
	
	public Player[] getPlayers()
	{
		return myPlayers;
	}
}
