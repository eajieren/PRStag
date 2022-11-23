import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PRSTag
{
	final static int PLYRS_PER_TEAM = 5;
	final static String[] opposingNames = {"Squirt", "Hazel", "Evie", "Lewis", "Clark", "Toby", "Sage", "Basil", "Polly", "Martin", "Wren", "Julia", "Carmen", "Daniel", "Rafael", "Alex", "Rex", "Maggie", "Rick", "Olivia", "Quentin", "Vivian", "Zoe", "Michelle", "Yolanda", "Jeremiah", "Dexter", "Fiona", "Guglielmo", "Haley", "Irene", "Jacques", "Kingley", "Lydia", "Mozart", "Nubila", "Octavius", "Prince", "Rhett", "Dwayne", "Syrena", "Umbert", "Victor"};
	final static Random rand = new Random();
	
	public static void main(String[] args)
	{
		setupGame();
	}
	
	private static void setupGame()
	{
		//display starting message
		JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f, "Setting up this game.... Press OK to continue.");

		//a 2-D array of players; each of the 2 rows holds a team of players
		Player[][] teams = new Player[2][PLYRS_PER_TEAM];
		
		//for the user team, get names from user input; for the opposing team, randomly assign names
		for(int i = 0; i < PLYRS_PER_TEAM; i++)
		{
			f = new JFrame();
			String name = JOptionPane.showInputDialog(f, "Setting up your team. Please enter the name for Player #" + (i+1));
			teams[0][i] = new Player(name, true);
			
			int index = ((int) Math.abs(rand.nextInt())) % opposingNames.length;
			teams[1][i] = new Player(opposingNames[index], false);
			
			JOptionPane.showMessageDialog(f, "Your team adds: " + name + "\nOther team adds: " + opposingNames[index]);
		}
	}
}
