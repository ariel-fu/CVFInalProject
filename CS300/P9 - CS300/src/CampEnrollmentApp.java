import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           CampEnrollmentApp
// Files:           CampEnrollmentApp, Camper, CamperBST, CampManager, CampTreeNode 
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
 * This class will have an instance of CampManager to execute certain commands
 * as read from a text file.
 * 
 * @author Ariel
 *
 */
public class CampEnrollmentApp {

  private CampManager manager = new CampManager(); // executes certain commands

  /**
   * Prints out the statistics of the camp
   */
  public void statistics() {
    manager.printStatistics();
  }

  /**
   * Enrolls a camper
   * 
   * @param info - String of last name and first name and age
   */
  public void enroll(String info) {
    // each command is seperated by a space
    String[] split = info.split(" ");
    Integer age = Integer.parseInt(split[3]);
    Camper camper = null; // set to a default value that will later be set to the camper being
                          // enrolled's info.
    try {
      // split at index 2 is the first name, index 1 is the last name.
      camper = new Camper(split[2], split[1], age);
    } catch (IllegalArgumentException e) {
      // if the age is out of range, an exception will be thrown and the enrollment of
      // that child will be unsuccessful.
      System.out.println("This person is either too old or too young to be in Camp Badger");
      return;
    }
    manager.enrollCamper(camper);
    System.out.println("Enrollment of " + split[2] + " " + split[1] + " Successful!");
  }

  /**
   * Unenrolls a camper, if the NoSuchElementException is thrown, the camper is
   * not enrolled in Camp Badger yet.
   * 
   * @param info - String of last name and first name
   */
  public void unenroll(String info) {
    String[] split = info.split(" ");
    Iterator<Camper> iterate = manager.traverse("INORDER");
    Camper current = null;
    while (iterate.hasNext()) {
      Camper next = iterate.next();
      if(next.getFirstName().equals(split[2])) {
        if(next.getLastName().equals(split[1])) {
          current = next;
        }
      }
    }
    try {
      manager.unenrollCamper(current);
      System.out.println("--------------------------");
      System.out.println("Unenrollment of " + split[2] + " " + split[1] + " Successful!");
    } catch (Exception e) {
      System.out.println("That camper is not enrolled.");
    }

  }

  /**
   * Traverses the BST depending on the order
   * 
   * @param order - type of traversal
   */
  public void traverse(String order) {
    String[] command = order.split(" ");
    System.out.println("--- " + command[1] + " Traversal ---");
    if(manager.getCampers().isEmpty()) {
      System.out.println("The camper list is empty.");
    } else {
      Iterator<Camper> iterate = manager.traverse(command[1]);
      while (iterate.hasNext()) {
        System.out.println(iterate.next());
      }
    }
  }

  /**
   * Executes the commands read by text file
   * 
   * @param args
   * @throws IOException - if the text file is invalid
   */
  public static void main(String[] args) throws IOException {
    CampEnrollmentApp app = new CampEnrollmentApp();
    List<String> fileLines = Files.readAllLines(Paths.get("sim.txt"));

    Iterator<String> iterate = fileLines.iterator();
    while (iterate.hasNext()) {
      String info = iterate.next();
      String[] command = info.split(" ");
      if(command[0].equals("E")) {
        // E means enroll
        app.enroll(info);
      } else if(command[0].equals("R")) {
        // R means unenroll
        app.unenroll(info);
      } else if(command[0].equals("T")) {
        // T means traverse the tree
        app.traverse(info);
      } else if(command[0].equals("S")) {
        // S means print out statistics
        app.statistics();
      }

    }
  }
}
