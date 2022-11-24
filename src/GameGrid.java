import java.util.*;
import java.lang.Math;

public class GameGrid
{
	private int numRows, numCols;
	private Team[] participants;
	private HashMap<Location, Locatable> spcToOccupants;
	private GameFrame gf;
	
	//this could be implemented as a map of Location to Locatables; if done this way, isOccupied could just return the object
	//at that Location or null if empty; major con of this method is storage space. You have 10 players, but you'd be storing
	//the contents of rows*cols spaces, most of which would be null; another idea would be to make the map of Location to Locatables
	//only be for the locations that contain items
	public GameGrid(int rows, int cols, Team one, Team two)
	{
		numRows = rows;
		numCols = cols;
		participants = new Team[2];
		participants[0] = one;
		participants[1] = two;
		spcToOccupants = new HashMap<Location, Locatable>();
		assignStartLocs();
		
		Player[] a = participants[0].getPlayers(), b = participants[1].getPlayers();
		for(Player each : a)
		{
			System.out.println(each.getName() + " @ " + each.getLoc());
		}
		for(Player each : b)
		{
			System.out.println(each.getName() + " @ " + each.getLoc());
		}
		
		//gf = new GameFrame(rows, cols, participants[0], participants[1]);
		
		//play turns
		//redraw
	}
	
	//determines whether the spot at loc is occupied or not
	public boolean isOccupied(Location loc)
	{
		return spcToOccupants.containsKey(loc);
	}
	
	//returns whether or not this Location is within the GameGrid
	public boolean isInBounds(Location loc)
	{
		return (loc.getRow() >= 0) && (loc.getRow() < numRows) && (loc.getCol() >= 0) && (loc.getCol() < numCols);
	}
	
	public boolean gameOver()
	{
		return false;
	}
	
	public int playTurn()
	{
		//before allowing each team to play, check to ensure that the game has not ended
		//if it has ended, declare a winner
		if(!participants[0].isEliminated() && !participants[1].isEliminated())
		{
			participants[0].playTurn();
			if(!participants[0].isEliminated() && !participants[1].isEliminated())
			{
				participants[1].playTurn();
			}
			else
				declareWinner();
		}
		else
			declareWinner();
		
		return -1;
	}
	
	public int declareWinner()
	{
		System.out.println("We've got a winner!!!");
		return -1;
	}
	
	public Team[] getTeams()
	{
		return participants;
	}
	
	//returns a random empty location from the GameGrid
	public Location randomEmptyLoc()
	{
		Random r = new Random();
		Location loc;
		int x, y;
		
		do
		{
			x = r.nextInt(numRows);
			y = r.nextInt(numCols);
			loc = new Location(x, y);
		}
		while(isOccupied(loc));
		
		return loc;
	}
	
	private boolean assignStartLocs()
	{
		Player[] t1 = participants[0].getPlayers(), t2 = participants[1].getPlayers();
		//Location[] startLocs1 = new Location[Team.PLYRS_PER_TEAM], startLocs2 = new Location[Team.PLYRS_PER_TEAM];
		for(int i = 0; i < Team.PLYRS_PER_TEAM; i++)
		{
			Location loc1, loc2;
			loc1 = randomEmptyLoc();
			t1[i].setLoc(loc1);
			spcToOccupants.put(loc1, t1[i]);
			
			loc2 = randomEmptyLoc();
			t2[i].setLoc(loc2);
			spcToOccupants.put(loc2, t2[i]);
		}
		
		return true;
	}
}
