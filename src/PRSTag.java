import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PRSTag
{	
	private static final int DEF_ROWS = 30, DEF_COLS = 30;
	
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
		GameGrid gg = new GameGrid(DEF_ROWS, DEF_COLS, t1, t2);
		JFrame jf = new GameFrame(DEF_ROWS, DEF_COLS, gg);
	}
}
