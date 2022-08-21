package P3_Marian_The_Librarian;

import static org.junit.Assert.*;

import org.junit.Test;

public class CombinationsTest {

	private char plus='+';
	private char mult='*';
	private char none='N';
	
	private char sub='-';
	private char div='/';
	
	@Test
	public void testFindCombination() {
		assertTrue("1,2,3",Combinations.findCombination(1, 2, 3)==plus );
		assertTrue("1,3,2",Combinations.findCombination(1, 3, 2)==plus );
		assertTrue("2,1,3",Combinations.findCombination(2, 1, 3)==plus );
		assertTrue("2,3,1",Combinations.findCombination(2, 3, 1)==plus );
		assertTrue("3,1,2",Combinations.findCombination(3, 1, 2)==plus );
		assertTrue("3,2,1",Combinations.findCombination(3, 2, 1)==plus );

		assertTrue("1,2,2",Combinations.findCombination(1, 2, 2)==mult );
		assertTrue("2,1,2",Combinations.findCombination(2, 1, 2)==mult );
		assertTrue("2,2,1",Combinations.findCombination(2, 2, 1)==mult );

		assertTrue("4,2,2",Combinations.findCombination(4, 2, 2)==mult );
		assertTrue("2,4,2",Combinations.findCombination(2, 4, 2)==mult );
		assertTrue("2,2,4",Combinations.findCombination(2, 2, 4)==mult );

		assertTrue("6,2,3",Combinations.findCombination(6, 2, 3)==mult );
		assertTrue("6,3,2",Combinations.findCombination(6, 3, 2)==mult );
		assertTrue("2,6,3",Combinations.findCombination(2, 6, 3)==mult );
		assertTrue("2,3,6",Combinations.findCombination(2, 3, 6)==mult );
		assertTrue("3,6,2",Combinations.findCombination(3, 6, 2)==mult );
		assertTrue("3,2,6",Combinations.findCombination(3, 2, 6)==mult );

		assertTrue("0,0,0",Combinations.findCombination(0, 0, 0)==mult );
		assertTrue("1,1,1",Combinations.findCombination(1, 1, 1)==mult );
	
		assertTrue("7,2,3",Combinations.findCombination(7, 2, 3)==none );
		assertTrue("7,3,2",Combinations.findCombination(7, 3, 2)==none );
		assertTrue("2,7,3",Combinations.findCombination(2, 7, 3)==none );
		assertTrue("2,3,7",Combinations.findCombination(2, 3, 7)==none );
		assertTrue("3,7,2",Combinations.findCombination(3, 7, 2)==none );
		assertTrue("3,2,7",Combinations.findCombination(3, 2, 7)==none );

	}

	@Test
	public void testFindOppositeCombination() {
		assertTrue("1,2,3",Combinations.findOppositeCombination(1, 2, 3)==sub );
		assertTrue("1,3,2",Combinations.findOppositeCombination(1, 3, 2)==sub );
		assertTrue("2,1,3",Combinations.findOppositeCombination(2, 1, 3)==sub );
		assertTrue("2,3,1",Combinations.findOppositeCombination(2, 3, 1)==sub );
		assertTrue("3,1,2",Combinations.findOppositeCombination(3, 1, 2)==sub );
		assertTrue("3,2,1",Combinations.findOppositeCombination(3, 2, 1)==sub );

		assertTrue("1,2,2",Combinations.findOppositeCombination(1, 2, 2)==div );
		assertTrue("2,1,2",Combinations.findOppositeCombination(2, 1, 2)==div );
		assertTrue("2,2,1",Combinations.findOppositeCombination(2, 2, 1)==div );

		assertTrue("4,2,2",Combinations.findOppositeCombination(4, 2, 2)==div );
		assertTrue("2,4,2",Combinations.findOppositeCombination(2, 4, 2)==div );
		assertTrue("2,2,4",Combinations.findOppositeCombination(2, 2, 4)==div );

		assertTrue("6,2,3",Combinations.findOppositeCombination(6, 2, 3)==div );
		assertTrue("6,3,2",Combinations.findOppositeCombination(6, 3, 2)==div );
		assertTrue("2,6,3",Combinations.findOppositeCombination(2, 6, 3)==div );
		assertTrue("2,3,6",Combinations.findOppositeCombination(2, 3, 6)==div );
		assertTrue("3,6,2",Combinations.findOppositeCombination(3, 6, 2)==div );
		assertTrue("3,2,6",Combinations.findOppositeCombination(3, 2, 6)==div );

//		assertTrue("0,0,0",Combinations.findOppositeCombination(0, 0, 0)==div );
		assertTrue("1,1,1",Combinations.findOppositeCombination(1, 1, 1)==div );
	
		assertTrue("7,2,3",Combinations.findOppositeCombination(7, 2, 3)==none );
		assertTrue("7,3,2",Combinations.findOppositeCombination(7, 3, 2)==none );
		assertTrue("2,7,3",Combinations.findOppositeCombination(2, 7, 3)==none );
		assertTrue("2,3,7",Combinations.findOppositeCombination(2, 3, 7)==none );
		assertTrue("3,7,2",Combinations.findOppositeCombination(3, 7, 2)==none );
		assertTrue("3,2,7",Combinations.findOppositeCombination(3, 2, 7)==none );
	}
	
	@Test
	public void testFindColorCombination() {
		assertTrue("None",Combinations.findColorCombination(1, 2, 3,false,false)==plus );
		assertTrue("isRed",Combinations.findColorCombination(1, 12, 3,true,false)==plus );
		assertTrue("isBlue",Combinations.findColorCombination(1, 2, 5,false,true)==plus );
		assertTrue("Both",Combinations.findColorCombination(1, 6, 3,true,true)==plus );
		
		assertTrue("None",Combinations.findColorCombination(6, 2, 3,false,false)==mult );
		assertTrue("isRed",Combinations.findColorCombination(1, 27, 3,true,false)==mult );
		assertTrue("isBlue",Combinations.findColorCombination(1, 2, 4,false,true)==mult );
		assertTrue("Both",Combinations.findColorCombination(1, 2, 4,true,true)==mult );

		for (int i=0;i<2;i++) {
			for (int j=0;j<2;j++) {
				assertTrue("0,0,0",Combinations.findColorCombination(0, 0, 0,i==0,j==0)==mult );
			}			
		}

		assertTrue("None",Combinations.findColorCombination(1, 5, 3,false,false)==none );
		assertTrue("isRed",Combinations.findColorCombination(1, 102, 3,true,false)==none );
		assertTrue("isBlue",Combinations.findColorCombination(1, 25, 5,false,true)==none );
		assertTrue("Both",Combinations.findColorCombination(1, 61, 3,true,true)==none );
	}

}
