import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameFrame extends JFrame
{
	private final int X_OFFSET = 50, Y_OFFSET = 50, F_WIDTH = 750, F_HEIGHT = 750, X_INTERVAL, Y_INTERVAL;

	public GameFrame(int rows, int cols)
	{
		//set up basic settings of this frame
		super("PRS Tag");
		X_INTERVAL = F_WIDTH/cols;
		Y_INTERVAL = F_HEIGHT/rows;
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
	}
	
	private boolean exit()
	{
		JOptionPane jop = new JOptionPane();
		return jop.showConfirmDialog(jop.getRootFrame(),
	   			"Are you sure that you want to quit PRS-Tag?", "EXIT?",
	   			JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
	}
}