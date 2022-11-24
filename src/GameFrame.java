import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameFrame extends JFrame
{
	private final int X_OFFSET = 50, Y_OFFSET = 50, F_WIDTH = 750, F_HEIGHT = 750, X_INTERVAL, Y_INTERVAL;

	private Team first, second;
	private GameGrid myGrid;
	
	public GameFrame(int rows, int cols, GameGrid gGrid)
	{
		super("PRS Tag");
		X_INTERVAL = F_WIDTH/cols;
		Y_INTERVAL = F_HEIGHT/rows;
		myGrid = gGrid;
		Team[] teams = myGrid.getTeams();
		first = teams[0];
		second = teams[1];
		
		//set up basic settings of this frame
		setPreferredSize(new Dimension(850, 850));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				if(exit())
				{
					System.exit(0);
				}
         	}
        });
		pack();
		setVisible(true);
	}
	
	public void paint(Graphics phics)
	{
		System.out.println("I'm in paint");
		//draw background
		phics.setColor(Color.gray);
		phics.fillRect(X_OFFSET, Y_OFFSET, F_WIDTH, F_HEIGHT);

		//draw gridlines
		phics.setColor(Color.black);
		
		for(int x = 0; x <= F_WIDTH; x += X_INTERVAL)
		{
			phics.drawLine(X_OFFSET + x, Y_OFFSET, X_OFFSET + x, Y_OFFSET + F_HEIGHT);
		}

		for(int y = 0; y <= F_HEIGHT; y += Y_INTERVAL)
		{
			phics.drawLine(X_OFFSET, Y_OFFSET + y, X_OFFSET + F_WIDTH, Y_OFFSET + y);
		}
		
		//draw both teams
		draw(first, phics);
		draw(second, phics);
		if(!myGrid.gameOver())
			update(phics);
	}
	
    public void update(Graphics g)    
    {     
    	myGrid.playTurn();
        repaint();
    }  
	
	private boolean draw(Team t, Graphics gfx)
	{
		Graphics2D g2 = (Graphics2D) gfx;
		if(t.isUserTeam())
			g2.setColor(Color.green);
		else
			g2.setColor(Color.red);
		
		Player[] plyrs = t.getPlayers();
		for(Player p : plyrs)
		{
			if(p.getLivesRemaining() > 0)
			{
				int[] drawLocs = convertToDraw(p.getLoc());
				g2.fillOval(drawLocs[0], drawLocs[1], X_INTERVAL, Y_INTERVAL);
			}
		}
		
		return true;
	}
	
	//converts the game location into an int array of the pixel location for drawing
	private int[] convertToDraw(Location loc)
	{
		int[] drawCoords = new int[2];
		drawCoords[0] = loc.getCol() * X_INTERVAL + X_OFFSET;
		drawCoords[1] = loc.getRow() * Y_INTERVAL + Y_OFFSET;

		return drawCoords;
	}
	
	private boolean exit()
	{
		JOptionPane jop = new JOptionPane();
		return jop.showConfirmDialog(jop.getRootFrame(),
	   			"Are you sure that you want to quit PRS-Tag?", "EXIT?",
	   			JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
	}
}