import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameTest {

	@Test
	/**
	 * This test checks if the board class is properly working for all values or not.
	 */
	void test() {
		Board b = new Board();
		b.game(2,"Fruits",false);
		String images[][] = b.getImages();
		b.game(3, "Animals", true);
		String images2[][] = b.getImages();
		boolean ch = images.length==5;
		boolean ch2 = images.length==6;
		b.flip(4, 4);
		assertEquals(true,ch);
		assertEquals(true,ch2);
	}

}
