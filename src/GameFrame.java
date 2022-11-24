import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameFrame extends JFrame
{
	public GameFrame()
	{
		super("PRS Tag");
		setPreferredSize(new Dimension(300, 300));
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
	
	private boolean exit()
	{
		JOptionPane jop = new JOptionPane();
		return jop.showConfirmDialog(jop.getRootFrame(),
	   			"Are you sure that you want to quit PRS-Tag?", "EXIT?",
	   			JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
	}
}