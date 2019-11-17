import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;

import com.sun.corba.se.spi.monitoring.StatisticsAccumulator;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           CampEnrollmentApp
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
      camper = new Camper(split[1], split[2], age);
    } catch (IllegalArgumentException e) {
      // if the age is out of range, an exception will be thrown and the enrollment of
      // that child will be unsuccessful.
      System.out.println("This person is either too old or too young to be in Camp Badger");
    }
    manager.enrollCamper(camper);
    System.out.println("Enrollment of " + split[1] + " " + split[2] + " Successful!");
  }

  /**
   * Unenrolls a camper, if the NoSuchElementException is thrown, the camper is
   * not enrolled in Camp Badger yet.
   * 
   * @param info - String of last name and first name
   */
  public void unenroll(String info) {
    String[] split = info.split(" ");
    Camper camper = null;
    try {
      manager.unenrollCamper(camper);
    } catch (NoSuchElementException e) {
      System.out.println("That camper is not enrolled.");
    }
    System.out.println("Unenrollment of " + split[1] + " " + split[2] + " Successful!");

  }
  
  public void traverse(String order) {
    Camper camper = null;
    if(order.toUpperCase().equals("PREORDER")) {
      
    } else if(order.equals("POSTORDER")) {
      
    } else if(order.equals("INORDER")) {
      
    }
  }

  public static void main(String[] args) throws IOException {
    CampEnrollmentApp app = new CampEnrollmentApp();
    List<String> fileLines = Files.readAllLines(Paths.get("sim.txt"));
    for(int i = 0; i < fileLines.size(); i++) {
      String info = fileLines.get(i);
      if(info.contains("E")) {
        app.enroll(info);
      } else if(info.contains("R")) {
        app.unenroll(info);
      } else if(info.contains("T")) {
      
      } else if(info.contains("S")) {
        app.statistics();
      }
    }
  }
}
