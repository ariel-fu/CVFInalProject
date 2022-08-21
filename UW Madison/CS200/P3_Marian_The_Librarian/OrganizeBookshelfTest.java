package P3_Marian_The_Librarian;

import static org.junit.Assert.*;

import org.junit.Test;

public class OrganizeBookshelfTest {

	@Test
	public void testGetRightmostBookID() {
		assertTrue("-1,-3", OrganizeBookshelf.getRightmostBookID(-1 ,-3)==-1);
		assertTrue("-3,-1", OrganizeBookshelf.getRightmostBookID(-3 ,-1)==-1);
		assertTrue("-1,-1", OrganizeBookshelf.getRightmostBookID(-1 ,-1)==-1);
		
		assertTrue("-1,0", OrganizeBookshelf.getRightmostBookID(-1 ,0)==0);
		assertTrue("0,-3", OrganizeBookshelf.getRightmostBookID(0 ,-3)==0);
		
		assertTrue("0,3", OrganizeBookshelf.getRightmostBookID(0 ,3)==3);
		assertTrue("3,0", OrganizeBookshelf.getRightmostBookID(3 ,0)==3);
		
		assertTrue("1,3", OrganizeBookshelf.getRightmostBookID(1 ,3)==3);
		assertTrue("3,1", OrganizeBookshelf.getRightmostBookID(3 ,1)==3);
		
		assertTrue("-1,3", OrganizeBookshelf.getRightmostBookID(-1 ,3)==3);
		assertTrue("3,-1", OrganizeBookshelf.getRightmostBookID(3 ,-1)==3);

	}

	@Test
	public void testSpaceToLeave() {
		assertTrue("-1,-3", OrganizeBookshelf.spaceToLeave(-1 ,-3)==1);
		assertTrue("-3,-1", OrganizeBookshelf.spaceToLeave(-3 ,-1)==1);
		assertTrue("-1,-1", OrganizeBookshelf.spaceToLeave(-1 ,-1)==0);
		
		assertTrue("-1,0", OrganizeBookshelf.spaceToLeave(-1 ,0)==0);
		assertTrue("0,-3", OrganizeBookshelf.spaceToLeave(0 ,-3)==2);
		
		assertTrue("0,3", OrganizeBookshelf.spaceToLeave(0 ,3)==2);
		assertTrue("3,0", OrganizeBookshelf.spaceToLeave(3 ,0)==2);
		
		assertTrue("1,3", OrganizeBookshelf.spaceToLeave(1 ,3)==1);
		assertTrue("3,1", OrganizeBookshelf.spaceToLeave(3 ,1)==1);
		
		assertTrue("-1,3", OrganizeBookshelf.spaceToLeave(-1 ,3)==3);
		assertTrue("3,-1", OrganizeBookshelf.spaceToLeave(3 ,-1)==3);
	}

}
