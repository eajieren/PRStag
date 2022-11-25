import java.util.*;
import java.lang.Math;
import javax.swing.JOptionPane;

public class GameGrid
{
	private static Random rand;
	private static final int STARTING_BONUSES = 10;
	
	private int numRows, numCols;
	private boolean gameOver;
	private Team[] participants;
	private HashMap<Location, Locatable> spcToOccupants;
	private ArrayList<BonusItem> myBonuses;
	private GameFrame gf;
	
	//this could be implemented as a map of Location to Locatables; if done this way, isOccupied could just return the object
	//at that Location or null if empty; major con of this method is storage space. You have 10 players, but you'd be storing
	//the contents of rows*cols spaces, most of which would be null; another idea would be to make the map of Location to Locatables
	//only be for the locations that contain items
	public GameGrid(int rows, int cols, Team one, Team two)
	{
		numRows = rows;
		numCols = cols;
		gameOver = false;
		participants = new Team[2];
		participants[0] = one;
		participants[1] = two;
		spcToOccupants = new HashMap<Location, Locatable>();
		myBonuses = new ArrayList<BonusItem>();
		assignStartLocs();
		distributeBonus(false, STARTING_BONUSES);
		
		Player[] a = participants[0].getPlayers(), b = participants[1].getPlayers();
		for(Player each : a)
		{
			System.out.println(each.getName() + " @ " + each.getLoc());
		}
		for(Player each : b)
		{
			System.out.println(each.getName() + " @ " + each.getLoc());
		}
		
		//play turns
		//redraw
	}
	
	//determines whether the spot at loc is occupied or not
	public boolean isOccupied(Location loc)
	{
		return spcToOccupants.containsKey(loc);
	}
	
	public boolean isFreeOfPlayers(Location loc)
	{
		if(isOccupied(loc))
			return !(spcToOccupants.get(loc) instanceof Player);
		return true;
	}
	
	//returns whether or not this Location is within the GameGrid
	public boolean isInBounds(Location loc)
	{
		return (loc.getRow() >= 0) && (loc.getRow() < numRows) && (loc.getCol() >= 0) && (loc.getCol() < numCols);
	}
	
	public boolean gameOver()
	{
		return gameOver;
	}
	
	public int playTurn()
	{
		System.out.println("playTurn()");
		//before allowing each team to play, check to ensure that the game has not ended;
		//if it has ended, declare a winner
		if(!participants[0].isEliminated() && !participants[1].isEliminated())
		{
			scanForRPSMatches();
			
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
		gameOver = true;
		System.out.println("We've got a winner!!!");
		return -1;
	}
	
	public Team[] getTeams()
	{
		return participants;
	}
	
	public ArrayList<BonusItem> getBonuses()
	{
		return myBonuses;
	}
	
	//returns an ArrayList of Locations surrounding loc that are in-bounds and don't have any Players occupying them
	public ArrayList<Location> getValidEmptySurroundingSpaces(Location loc)
	{
		ArrayList<Location> goodLocs = new ArrayList<Location>();
		for(int a = -1; a <= 1; a++)
		{
			for(int b = -1; b <= 1; b++)
			{
				if(a == b && a == 0)
					continue;
				Location nearby = new Location(loc.getRow() + a, loc.getCol() + b);
				if(isInBounds(nearby) && isFreeOfPlayers(nearby))
					goodLocs.add(nearby);
			}
		}
		
		return goodLocs;
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
	
	//given a Location and a team ID, return any Players in adjacent spaces with a different team ID
	private ArrayList<Player> neighboringOpponents(Location myLoc, int myTeamID)
	{
		ArrayList<Player> adjacentFoes = new ArrayList<Player>();
		
		//loop through all surrounding locations
		for(int c = -1; c <= 1; c++)
		{
			for(int d = -1; d <= 1; d++)
			{
				if(c == d && c == 0)
					continue;
				Location nearby = new Location(myLoc.getRow() + c, myLoc.getCol() + d);
				
				//if the Location is in-bounds and if there is a Locatable at that Location...
				if(isInBounds(nearby) && spcToOccupants.containsKey(nearby))
				{
					//if that Locatable is a Player object, add it to the ArrayList if it has a different teamID
					if(spcToOccupants.get(nearby) instanceof Player)
					{
						Player p = (Player) spcToOccupants.get(nearby);
						if(p.getTeamID() != myTeamID)
						{
							adjacentFoes.add(p);
						}
					}
				}
			}
		}
		
		return adjacentFoes;
	}
	
	private boolean assignStartLocs()
	{
		Player[] t1 = participants[0].getPlayers(), t2 = participants[1].getPlayers();
		
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
	
	private int scanForRPSMatches()
	{
		//if you have n teams, you only need to scan through n-1 teams to get all of the possible matches
		for(Player p : participants[0].getPlayers())
		{
			ArrayList<Player> opps = neighboringOpponents(p.getLoc(), p.getTeamID());
			HashMap<Player, Integer> recents = p.getRecentOpponents();
			
			//iterate through all of the neighboring opponents of p
			for(Player nearOne : opps)
			{
				//if they aren't in p's recent list OR if they are in p's recents but it's been more than 5 turns, let them engage in a match
				if(!(recents.containsKey(nearOne) && recents.get(nearOne) > 0))
				{
					int result = RPS_match(p, nearOne);
					displayRoundResults(p, nearOne, result);
					
					//winner can choose to decrease lives of loser or take one of his/her items
					
					p.addPRSOpponent(nearOne);
					nearOne.addPRSOpponent(p);
				}
			}
		}
		
		return -1;
	}

	private int postMatchImpacts(Player victor, Player vanquished)
	{
		int numBalls = vanquished.getBallCount();
		if(numBalls > 0)
			distributeBonus(true, numBalls);
		vanquished.loseLife();
		
		return -1;
	}

	private int distributeBonus(boolean ballsOnly, int numToDistribute)
	{
		rand = new Random();
		
		for(int i = 0; i < numToDistribute; i++)
		{
			Location loc = randomEmptyLoc();
			BonusItem bi;
			BonusType bt;
			if(ballsOnly)
				bt = BonusType.BALL;
			else
			{
				if(rand.nextInt() % 2 == 0)
					bt = BonusType.BALL;
				else
					bt = BonusType.LIFE;
			}
			bi = new BonusItem(loc, bt);
			
			spcToOccupants.put(loc, bi);
			myBonuses.add(bi);
		}
		
		return -1;
	}
	
	//runs a rock-paper-scissors match between Players p1 and p2
	public static int RPS_match(Player p1, Player p2)
	{
		JOptionPane.showMessageDialog(null, "Here we go! A rock-paper-scissors match between " + p1.getName() + " and " + p2.getName() + ".");
		System.out.println("Paper-Rock-Scissors match between " + p1.getName() + " and " + p2.getName());
		
		rand = new Random();
		int scoreP1 = 0, scoreP2 = 0;
		boolean userPresent = p1.isUserPlayer() || p2.isUserPlayer();
		
		//from 1 to 10 rounds
		int numRounds = rand.nextInt(10) + 1;
		do
		{
			RPS play1 = p1.getRPSMove();
			RPS play2 = p2.getRPSMove();
			
			if(play1 != play2)
			{
				switch(play1)
				{
					case ROCK:
					{
						if(play2 == RPS.PAPER)
						{
							if(userPresent)
								JOptionPane.showMessageDialog(null, p2.getName() + " throws PAPER and wins!");
							scoreP2++;
						}	
						else
						{
							if(userPresent)
								JOptionPane.showMessageDialog(null, p1.getName() + " throws ROCK and wins!");
							scoreP1++;
						}
						break;
					}
					case PAPER:
					{
						if(play2 == RPS.SCISSORS)
						{
							if(userPresent)
								JOptionPane.showMessageDialog(null, p2.getName() + " throws SCISSORS and wins!");
							scoreP2++;
						}	
						else
						{
							if(userPresent)
								JOptionPane.showMessageDialog(null, p1.getName() + " throws PAPER and wins!");
							scoreP1++;
						}
						break;
					}
					case SCISSORS:
					{
						if(play2 == RPS.ROCK)
						{
							if(userPresent)
								JOptionPane.showMessageDialog(null, p2.getName() + " throws ROCK and wins!");
							scoreP2++;
						}	
						else
						{
							if(userPresent)
								JOptionPane.showMessageDialog(null, p1.getName() + " throws SCISSORS and wins!");
							scoreP1++;
						}
						break;
					}
					default:
						break;
				}
			}
			else
			{
				if(userPresent)
					JOptionPane.showMessageDialog(null, "That round was a draw!");
			}
			
			if(userPresent)
				JOptionPane.showMessageDialog(null, p1.getName() + ": " + scoreP1 + "\n" + p2.getName() + ": " + scoreP2);
			
			numRounds--;
		}
		while(numRounds > 0);
		
		return scoreP1 - scoreP2;
	}
	
	//displays a JOptionPane message regarding the results of the RPS match
	private int displayRoundResults(Player p1, Player p2, int result)
	{
		if(result > 0)
		{
			JOptionPane.showMessageDialog(null, "The match is over! The winner is " + p1.getName() + "!");
			postMatchImpacts(p1, p2);
		}
		else
			if(result == 0)
				JOptionPane.showMessageDialog(null, "The match is over, and it was a tie!!!");
			else
			{
				JOptionPane.showMessageDialog(null, "The match is over! The winner is " + p2.getName() + "!");
				postMatchImpacts(p2, p1);
			}
				
		
		return -1;
	}

}
