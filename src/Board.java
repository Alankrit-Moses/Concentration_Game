/**
 * Name: Alankrit Jacinth Moses
 * Class: CSC 335
 * Description: This class is the main backend class 
 * 				which instantiates all other classes and helps them 
 * 				communicate with each other and the frontend/GUI class. 
 */
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.GridLayout;
import java.util.List;
import java.util.*;

public class Board
{
	private Users us; 
	private Cards cd;
	private ArrayList<Integer[]> flipped,found;
	private boolean oneFlip;
	
	/**
	 * Method responsible for initializing the game for the backend programs
	 * @param noOfUsers
	 * @param selection
	 * @param oneFlip
	 */
	public void game(int noOfUsers, String selection, boolean oneFlip) {
		us= new Users(noOfUsers);
		cd = new Cards(selection);
		cd.fillDeck();
		flipped = new ArrayList<Integer[]>();
		found = new ArrayList<Integer[]>();
		this.oneFlip = oneFlip;
	}
	
	/**
	 * This method returns an array of 
	 * arrays representing the image grid
	 * @return String[][]
	 */
	public String[][] getImages() {
		String img[][] = new String[5][6];	
		for(int x=0;x<5;x++)
		{
			for(int y=0;y<6;y++)
			{
				img[x][y] = "blank";
			}
		}
		for(int x=0;x<flipped.size();x++)
		{
			Integer[] elem = flipped.get(x);
			img[elem[0]][elem[1]] = cd.getCard(elem[0],elem[1]);
		}
		for(int x=0;x<found.size();x++)
		{
			Integer[] elem = found.get(x);
			img[elem[0]][elem[1]] = "cancel";
		}
		return img;
	}
	
	/**
	 * This method is responsible for flipping the cards
	 * @param x
	 * @param y
	 */
	public void flip(int x, int y)
	{
		Integer[] tile = {x,y};
		if(flipped.size()>0)
		{
			boolean skip = false;
			for(int z=0;z<flipped.size();z++)
			{
				if(flipped.get(z)[0]==x && flipped.get(z)[1]==y){
					skip = true;
					break;
				}
			}
			for(int z=0;z<found.size();z++)
			{
				if(found.get(z)[0]==x && found.get(z)[1]==y){
					skip = true;
					break;
				}
			}
			
			if(!skip && flipped.size()<2)
			{
				flipped.add(tile);
			}
		}
		else{
			flipped.add(tile);
		}
	}
	
	/**
	 * This method checks if a pair is 
	 * found when two cards are selected and calls
	 * respective functions.
	 */
	public void check()
	{
		boolean pair = false;
		if(flipped.size()==2)
		{
			if(flipped.get(0)[0]!=flipped.get(1)[0] || flipped.get(0)[1]!=flipped.get(1)[1])
			{
				if(cd.getCard(flipped.get(0)[0], flipped.get(0)[1]).equals(cd.getCard(flipped.get(1)[0], flipped.get(1)[1])))
				{
					found.add(flipped.get(0));
					found.add(flipped.get(1));
					pair = true;
				}
				flipped.clear();
			}
			if(oneFlip && pair)
			{
				us.updateScore();
				us.nextUser();
			}
			else if(pair && !oneFlip)
				us.updateScore();
			else
			{
				us.nextUser();
			}
			
		}
	}
	
	/**
	 * This function returns the current user as a string.
	 * @return
	 */
	public String getCurrentPlayer()
	{
		return "Player "+us.currentPlayer();
	}
	
	/**
	 * This function returns a 
	 * leaderboard of top 3 players as a string
	 * @return
	 */
	public String leaderBoard()
	{
		int[] scores = us.getScore();
		int size = Math.min(scores.length, 3);
		String lb = "LEADERBOARD\n\n";
		ArrayList<Integer> traversed = new ArrayList<Integer>();
		for(int x=0;x<size;x++)
		{
			int recentScore = -1;
			int recentInd = -1;
			for(int y=0;y<scores.length;y++)
			{
				if(traversed.contains(y))
					continue;
				if(scores[y]>recentScore)
				{
					recentScore = scores[y];
					recentInd = y;
				}
			}
			if(recentInd!=-1)
			{
				traversed.add(recentInd);
				lb+=("Player "+(recentInd+1)+" - "+recentScore+"\n\n");
			}
		}
		return lb;
	}
	
	/**
	 * This function is responsible for 
	 * returning how many pairs have been found.
	 * @return
	 */
	public int totalFound() {
		return found.size();
	}
	
	
}