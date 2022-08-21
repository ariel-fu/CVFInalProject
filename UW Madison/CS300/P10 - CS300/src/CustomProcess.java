//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           CustomProcess
// Files:           CustomProcess, ProcessScheduler, WaitingProcessQueue, WaitingQueueADT 
// Course:          300, Fall, and 2019
//
// Author:          (Ariel Fu)
// Email:           afu5@wisc.edu
// Lecturer's Name: Mouna Ayari Ben Hadj Kacem
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
 * This class represents the data type for the processes used in the application
 * 
 * @author Ariel
 *
 */
public class CustomProcess implements Comparable<CustomProcess> {
  private static int nextProcessId = 1; // stores the id to be assigned to the next process to be
                                        // created
  private final int PROCESS_ID; // unique identifier for this process
  private int burstTime; // time required by this process for CPU execution

  /**
   * Constructor for CustomProcess class
   * 
   * @param burstTime - time it takes to complete process
   * @throws IllegalArgumentException - burstTime is less than or equal to 0
   */
  public CustomProcess(int burstTime) {
    if(burstTime <= 0) {
      throw new IllegalArgumentException("The time of a process cannot be 0 or less.");
    }
    this.burstTime = burstTime; // set the burstTime
    PROCESS_ID = nextProcessId; // set the unique ID
    nextProcessId++; // increment the ID for the next process
  }

  /**
   * Returns a String representation of this CustomProcess
   * 
   * @return a string representation of this CustomProcess
   */
  public String toString() {
    return "p" + this.PROCESS_ID + "(" + this.burstTime + ")";
  }

  /**
   * CompareTo method overriden from Comparable interface
   * 
   * @return negative integer if this is less than the argument, a positive
   *         integer if this is greater than the argument, 0 if both are equal.
   */
  @Override
  public int compareTo(CustomProcess other) {
    // check burstTime first
    if(this.getBurstTime() < other.getBurstTime()) {
      return -10;
    } else if(this.getBurstTime() > other.getBurstTime()) {
      return 10;
    } else if(this.getBurstTime() == other.getBurstTime()) {
      // if they have the same burstTime, check their processID
      // smaller ID == smaller CustomProcess
      if(this.getProcessId() < other.getProcessId()) {
        return -10;
      } else if(this.getProcessId() > other.getProcessId()) {
        return 10;
      }
    }
    return 0; // if they have the same burstTime and the same ID, they are the equal to each
              // other.
  }

  /**
   * Accessor for ID field
   * 
   * @return PROCESS_ID --> unique ID for this process.
   */
  public int getProcessId() {
    return PROCESS_ID;
  }

  /**
   * Accessor for burstTime
   * 
   * @return burstTime --> time required to be processed
   */
  public int getBurstTime() {
    return burstTime;
  }

}
