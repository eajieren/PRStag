import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PRSTag
{
	final static int PLYRS_PER_TEAM = 5;
	
	public static void main(String[] args)
	{
		setupGame();
	}
	
	private static void setupGame()
	{
		//display starting message
		JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f, "Setting up this game.... Press OK to continue.");
				
		//set up one team of players to play the game
		ArrayList<Player> team = new ArrayList<Player>();
		for(int i = 0; i < PLYRS_PER_TEAM; i++)
		{
			f = new JFrame();
			String name = JOptionPane.showInputDialog(f, "Please enter the name for Player #" + (i+1));
			team.add(new Player(name));
		}
	}
}
