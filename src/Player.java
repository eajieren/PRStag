
public class Player extends Locatable
{
	private String myName;
	private boolean isUserPlayer;
	
	public Player(String name, boolean userPlayer)
	{
		super();
		myName = name;
		isUserPlayer = userPlayer;
	}
	
	public String getName()
	{
		return myName;
	}
	
	public boolean isUserPlayer()
	{
		return isUserPlayer;
	}
}
