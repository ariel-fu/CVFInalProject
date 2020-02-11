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
// TODO: figure out how to improve time complexity for insert 
// TODO: add in the TAs that work
// TODO: what is do work? all of the implementations or insert or remove??
public class CompareDS {
  public static void main(String[] args) {
    long fastestTime = 0;
    String nameOfFastestImplementation = "";
    String thingsToDo = "1. Insert 10,000 items " + "\r\n"
        + "2. Check for an item that isn't in the data structure " + "\r\n"
        + "3. Get an item that isn't in the data structure" + "\r\n"
        + "3. Remove all items" + "\r\n" + "4. Check that size = 0";

    System.out.println(
        "CompareDS.main compares work time for DS_: Brian, My, and Mark");
    // print out the description of what each trial does.
    System.out.println("Each implementation will " + "\r\n" + thingsToDo);
    System.out.println("********************************");
    System.out.println();

    // put Brian to the trial.
    long brian = brianImplementation();
    System.out.println("Brian's implementation took " + brian
        + " nanoseconds to do " + "\r\n" + thingsToDo);

    // set the current fastest implementation to Brian.
    fastestTime = brian;
    nameOfFastestImplementation = "Brian";
    System.out.println("********************************");

    // put Mark to the trial.
    long mark = markImplementation();
    System.out.println("Mark's implementation took " + mark
        + " nanoseconds to do " + "\r\n" + thingsToDo);
    // check if Mark has a faster implementation than Brian
    if (mark < fastestTime) {
      fastestTime = mark;
      nameOfFastestImplementation = "Mark";
    }
    System.out.println("********************************");

    // put me to the trial.
    long me = myImplementation();
    System.out.println("My implementation took " + me + " nanoseconds to do "
        + "\r\n" + thingsToDo);
    // check if I have a faster implementation compared to the current fastest
    // implementation
    if (me < fastestTime) {
      fastestTime = me;
      nameOfFastestImplementation = "Ariel (me)";
    }
    System.out.println("********************************");

    System.out.println("Now, the results of our trial...");
    System.out.println(
        nameOfFastestImplementation + " had our fastest implementation at "
            + fastestTime + " nanoseconds.");
  }

  /**
   * This method calculates how long it takes Brian's implementation to insert
   * 10,000 items, check if it contains an item that isn't in the data
   * structure, remove all 10,000 items, and check that size = 0.
   * 
   * @return time it takes to do all that (in nanoseconds)
   */
  private static long brianImplementation() {
    // calculate the time it takes to do the work for 10000 inserts on DS_Brian
    DS_Brian brian = new DS_Brian();
    long startTime = System.nanoTime();
    for (int i = 0; i < 10000; i++) {
      brian.insert(Integer.toString(i), "#" + i);
    }
    long endTime = System.nanoTime();
    long totalTime = (endTime - startTime);

    // calculate the time it takes to find a key that isn't in the data
    // structure.
    startTime = System.nanoTime();
    brian.contains("random key");
    endTime = System.nanoTime();
    totalTime += (endTime - startTime); // add it to the total time

    // calculate the time it takes to get something that is not in the list.
    startTime = System.nanoTime();
    brian.get("not in the list (hopefully)");
    endTime = System.nanoTime();
    totalTime += (endTime - startTime);
    // calculate the time it takes to remove the whole list
    startTime = System.nanoTime();
    for (int i = 0; i < 10000; i++) {
      brian.remove(Integer.toString(i));
    }
    endTime = System.nanoTime();
    totalTime += (endTime - startTime); // add it to the total time

    // calculate the time it takes to get the size of the Data structure
    startTime = System.nanoTime();
    if (brian.size() == 0) {
    }
    endTime = System.nanoTime();
    totalTime += (endTime - startTime); // add it to the total time

    // calculate the time it takes to find the element that doesn't exist.
    return totalTime;
  }

  /**
   * This method calculates how long it takes Mark's implementation to insert
   * 10,000 items, check if it contains an item that isn't in the array, remove
   * all 10,000 items, and check that size = 0.
   * 
   * @return time it takes to do all that (in nanoseconds)
   */
  private static long markImplementation() {
    // calculate the time it takes to do the work for 10000 inserts on DS_Mark
    DS_Mark mark = new DS_Mark();
    long startTime = System.nanoTime();
    for (int i = 0; i < 10000; i++) {
      mark.insert(Integer.toString(i), "#" + i);
    }
    long endTime = System.nanoTime();
    long totalTime = (endTime - startTime);
    // calculate the time it takes to find a key that isn't in the data
    // structure.
    startTime = System.nanoTime();
    mark.contains("random key");
    endTime = System.nanoTime();
    totalTime += (endTime - startTime); // add it to the total time

    // calculate the time it takes to get an item that is not in the list.
    startTime = System.nanoTime();
    mark.get("not in the list (hopefully)");
    endTime = System.nanoTime();
    totalTime += (endTime - startTime);

    // calculate the time it takes to remove the whole list
    startTime = System.nanoTime();
    for (int i = 0; i < 10000; i++) {
      mark.remove(Integer.toString(i));
    }
    endTime = System.nanoTime();
    totalTime += (endTime - startTime); // add it to the total time

    // calculate the time it takes to get the size of the Data structure
    startTime = System.nanoTime();
    if (mark.size() == 0) {
    }
    endTime = System.nanoTime();
    totalTime += (endTime - startTime); // add it to the total time

    return totalTime;
  }

  /**
   * This method calculates how long it takes my implementation to insert 10,000
   * items, check if it contains an item that isn't in the array, remove all
   * 10,000 items, and check the size of the array is equal to 0
   * 
   * @return time it takes to do all that (in nanoseconds)
   */
  private static long myImplementation() {
    // calculate the time it takes to do the work for 10000 inserts on DS_My
    DS_My ds = new DS_My();
    long startTime = System.nanoTime();
    for (int i = 0; i < 10000; i++) {
      ds.insert(Integer.toString(i), "#" + i);
    }
    long endTime = System.nanoTime();
    long totalTime = (endTime - startTime);
    // calculate the time it takes to find a key that isn't in the data
    // structure.
    startTime = System.nanoTime();
    ds.contains("random key");
    endTime = System.nanoTime();
    totalTime += (endTime - startTime); // add it to the total time

    // calculate the time it takes to get something that is not in the list.
    startTime = System.nanoTime();
    ds.get("not in the list (hopefully)");
    endTime = System.nanoTime();
    totalTime += (endTime - startTime);

    // calculate the time it takes to remove the whole list
    startTime = System.nanoTime();
    for (int i = 0; i < 10000; i++) {
      ds.remove(Integer.toString(i));
    }
    endTime = System.nanoTime();
    totalTime += (endTime - startTime); // add it to the total time

    // calculate the time it takes to check the size of the Data structure.
    startTime = System.nanoTime();
    if (ds.size() == 0) {
    }
    endTime = System.nanoTime();
    totalTime += (endTime - startTime); // add it to the total time

    return totalTime;
  }
}
