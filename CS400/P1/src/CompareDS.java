//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           CompareDS.java
// Files:           DataStructureADT.java, DataStructureADTTest.java, DS_My.java, TestDS_My.java, CompareDS.java
// Course:          (CS400, Spring, 2020)
//
// Author:          (Ariel Fu)
// Email:           (afu5@wisc.edu)
// Lecture Number: 001
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////

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
// Persons:         (NONE)
// Online Sources:  (NONE)
//               
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class compares DS_My against one of the working TA implementation
 * 
 * @author Ariel
 *
 */
//TODO: figure out how to improve time complexity for insert 
// TODO: add in the TAs that work
// TODO: what is do work? all of the implementations or insert or remove??
public class CompareDS {
  public static void main(String[] args) {
    // calculate the time it takes to do the work for 10000 inserts on DS_My
    System.out.println("DS_My is doing work for 10000");
    DS_My ds = new DS_My();
    long startTime = System.nanoTime();
    for (int i = 0; i < 10000; i++) {
      ds.insert(Integer.toString(i), "#" + i);
    }
    long endTime = System.nanoTime();
    long myTime = (endTime - startTime);
    System.out.println("DS_My took: " + myTime + " nanoseconds to insert 10000 items.");
    // calculate the time it takes to do the work for 10000 inserts on DS_Andy (or
    // brian?)
    DS_Brian brian = new DS_Brian();
    startTime = System.nanoTime();
    for (int i = 0; i < 10000; i++) {
      brian.insert(Integer.toString(i), "#" + i);
    }
    endTime = System.nanoTime();
    long debTime = (endTime - startTime);
    System.out.println("DS_Andy " + " took: " + debTime + " nanoseconds to insert 10000 items.");

    System.out.println("The difference was " + (myTime - debTime) + " nanoseconds.");
  }

}
