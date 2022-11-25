
import java.util.Random;

public class BonusItem extends Locatable
{
	private final BonusType myType;
	private final boolean isHidden;
	
	public BonusItem(Location loc, BonusType type)
	{
		super();
		setLoc(loc);
		myType = type;
		Random rand = new Random();
		isHidden = rand.nextBoolean();
	}
	
	public BonusType getType()
	{
		return myType;
	}
	
	public boolean isHidden()
	{
		return isHidden;
	}
}
