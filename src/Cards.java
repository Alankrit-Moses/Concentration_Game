/**
 * Name: Alankrit Jacinth Moses
 * Class: CSC 335
 * Description: This class is responsible for holding 
 * 				and handling all the data related to cards.
 */
import java.util.ArrayList;

public class Cards {
	
	private String cardHolder[][], category[];
	
	/**
	 * This is the constructor for the Cards class
	 * @param category
	 */
	public Cards(String category)
	{
		if(category.equalsIgnoreCase("Fruits"))
		{
			this.category = new String[] {"apple","pear","avocado","greenapple","peach","pineapple"};
		}
		else
		{
			this.category = new String[] {"duck","chicken","cow","dog"};
		}
		cardHolder = new String[5][6];
	}
	
	/**
	 * This method fills up the deck 
	 * with pairs of cards in random places
	 */
	public void fillDeck()
	{
		int currentCard = 0;
		ArrayList<Integer> available = new ArrayList<Integer>();
		for(int y=0;y<30;y++)
		{
			available.add(y);
		}
		while(available.size()!=0)
		{
			int index1 = (int)(Math.random()*available.size());
			int number = available.get(index1);
			available.remove(index1);
			int index2 = (int)(Math.random()*available.size());
			int number2 = available.get(index2);
			available.remove(index2);
			cardHolder[(int)(number/6)][number%6] = category[currentCard%category.length];
			cardHolder[(int)(number2/6)][number2%6] = category[currentCard%category.length];
			currentCard++;
		}
	}
	
	/**
	 * This method returns the card at a specific point in a grid.
	 * @param x
	 * @param y
	 * @return
	 */
	public String getCard(int x,int y)
	{
		return cardHolder[x][y];
	}
}
