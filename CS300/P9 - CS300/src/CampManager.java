import java.util.NoSuchElementException;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           CampManager
// Files:           Camper, CamperBST, CamperTreeNode, CampEnrollmentApp, CamperManager, 
// Course:          300, Fall, and 2019
//
// Author:          (Ariel Fu)
// Email:           afu5@wisc.edu
// Lecturer's Name: Mouna Ayari Ben Hadj Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    None
// Partner Email:   None
// Partner Lecturer's Name: None
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         NONE
// Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * This class will have an instance of CamperBST and call various operations
 * along with some basic operations in regards to managing Camp Badger.
 * 
 * @author Ariel
 *
 */
public class CampManager {

  private CamperBST campers; // BST of Campers
  private final static String[] CABIN_NAMES = new String[] { "Otter Overpass", "Wolverine Woodland",
      "Badger Bunkhouse" }; // String array of cabin names

  /**
   * Constructor for the CampManager by initializing the campers field
   */
  public CampManager() {
    campers = null;
  }

  /**
   * "Enrolls" a camper by determining their cabin and adding them to the tree
   * 
   * @param newCamper - the camper to be enrolled into the camp
   */
  public void enrollCamper(Camper newCamper) {
    // determine the cabin
    if(newCamper.getAge() <= 9) {
      newCamper.assignCabin(CABIN_NAMES[0]);
    } else if(newCamper.getAge() <= 12) {
      newCamper.assignCabin(CABIN_NAMES[1]);
    } else if(newCamper.getAge() <= 14) {
      newCamper.assignCabin(CABIN_NAMES[2]);
    }
    // add them to the tree.
    campers.insert(newCamper);
  }

  /**
   * Prints statistics based on the current "state" of the camp. The statistics to
   * be printed is the total number of campers.
   */
  public void printStatistics() {

    System.out.println("--- Camp Statistics ---");
    System.out.println("Number of campers: " + campers.size());
    System.out.println("------------------------");

  }

  /**
   * Traverses the tree in the designated order by calling it through the
   * CamperBST class
   * 
   * @param order - the type of traversal for the tree to perform
   * @return the Iterator of Campers from CampBST.traverse()
   */
  public Iterator<Camper> traverse(String order) {

  }

  /**
   * "Unenrolls" a camper by removing them from the tree.
   * 
   * @param delCamper - the camper to be unenrolled the camp
   * @throws NoSuchElementException - if CamperBST.delete throws the exception
   */
  public void unenrollCamper(Camper delCamper) throws NoSuchElementException {
    
      campers.delete(delCamper);
  }

}
