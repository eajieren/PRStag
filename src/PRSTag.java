import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PRSTag
{	
	public static void main(String[] args)
	{
		setupGame();
	}
	
	private static void setupGame()
	{
		//display starting message
		JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f, "Setting up this game.... Press OK to continue.");
		
		Team t1 = new Team(true), t2 = new Team(false);
		GameGrid gg = new GameGrid(30, 30, t1, t2);
	}
}
