/**
 * Name: Alankrit Jacinth Moses
 * Class: CSC 335
 * Description: This class is responsible for holding and 
 * 				handling data related to all the players.
 */

public class Users {
	
	private int noOfUsers,userScore[],currentUser;
	/**
	 * The constructor for instantiating 
	 * Users class and initializing variables 
	 * @param number
	 */
	public Users(int number)
	{
		noOfUsers = number;
		userScore = new int[number];
		for(int x=0;x<number;x++)
			userScore[x] = 0;
		currentUser = 0;
	}
	
	/**
	 * This method changes the user to the next one.
	 */
	public void nextUser()
	{
		currentUser+=1;
		if(currentUser==noOfUsers)
			currentUser = 0;
	}
	
	/**
	 * This method updates the score 
	 * of the current player by 1.
	 */
	public void updateScore()
	{
		userScore[currentUser]+=1;
	}
	
	/**
	 * This method returns a list of 
	 * the scores of all the players.
	 * @return
	 */
	public int[] getScore()
	{
		return userScore;
	}
	
	/**
	 * This method returns the current player whose turn it is.
	 * @return
	 */
	public int currentPlayer()
	{
		return currentUser+1;
	}
}
