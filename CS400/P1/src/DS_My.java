//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           DS_My.java
// Files:           DataStructureADT.java, DataStructureADTTest.java, DS_My.java, TestDS_My.java, CompareDS.java.
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
// IMPORTANT NOTES WORTH READING
/*
 *  Please note that the insert algorithm takes a long time when inserting a very large number - 100,000 or more. 
 *  The largest it has inserted without taking a time that makes you wonder if it is actually working is around 25,000.
 *  Be warned of the following as well:
 *  1. Items are inserted at the end of the array to increase the insert time-complexity
 *  2. Since items are inserted in at random order, contains and remove has longer time complexity of O(n).
 */
// 

// TODO: Add class header here (DONE)
public class DS_My implements DataStructureADT<String, String> {

  // TODO - may wish to define an inner class
  // for storing key and value as a pair
  // such a class and its members should be "private" (DONE)
  private class Pair {
    private String value; // stores the value of the pair
    private String key; // stores the key String of the pair

    /**
     * Constructor that stores the key and value
     * 
     * @param key   - key String of the pair
     * @param value - value associated with the key
     */
    private Pair(String key, String value) {
      this.key = key;
      this.value = value;
    }

    /**
     * Helper method that returns the key String
     * 
     * @return the String key
     */
    private String getKey() {
      return key;
    }

    /**
     * Helper method that returns the value String
     * 
     * @return the value of the key
     */
    private String getValue() {
      return value;
    }

  }

  // Private Fields of the class
  // TODO create field(s) here to store data pairs (DONE)
  private int numPairs; // current number of elements in the Pair[]
  private Pair[] pairArray; // array to hold the Pair(s)

  /**
   * Constructor for DS_My class. Sets the currNumPairs to 0, and initialize the
   * pairArray to hold 20 elements.
   */
  public DS_My() {
    numPairs = 0; // set the current number of elements to 0
    pairArray = new Pair[20]; // set the array to hold 20 elements (to start)
  }

  // TODO: add unimplemented methods
  // ProTip: Eclipse can do this for you

  /**
   * This method inserts a new Pair that consists of a key and a value
   * 
   * @param key   - key of the Pair
   * @param value - value associated with the key
   * @throws IllegalArgumentException - if the key is null
   * @throws RunTimeException         - if there the key input is a duplicate of
   *                                  a key already in the Pair array.
   */

  @Override
  public void insert(String key, String value) {
    // check if the key is null, if so throw an IllegalArgumentException
    if(key == null) {
      throw new IllegalArgumentException("null key");
    }
    // Check if this key is a duplicate key. If it is, throw a RuntimeException
    // if the key is already associated with an index, it exists in the array.
    int keyIndex = getIndex(key);
    if(!validIndex(keyIndex)) {
      throw new RuntimeException("duplicate key.");
    }

    // Now that we know the key is a valid key, check if the array is maxed out.
    if(pairArray.length == numPairs) {
      // if it is, expand the array
      pairArray = expandArray(pairArray);
    }
    // if no duplicate keys are found, add the new Pair to the end of the array
    pairArray[numPairs] = new Pair(key, value);
    numPairs++; // increase the number of pairs inside the Pair[]

  }

  /**
   * This method removes a Pair, given the key.
   * 
   * @param key - key of the Pair to be removed
   * @throws IllegalArgumentException - if the key is null
   * @return true if the key is found and removed, false if the key is not in
   *         the array.
   */
  @Override
  public boolean remove(String key) {
    // check if the key is null, if so throw an IllegalArgumentException
    if(key == null) {
      throw new IllegalArgumentException("null key");
    }
    int keyIndex = getIndex(key);
    if(validIndex(keyIndex)) {
      // set the key at the index to the last key in the array
      pairArray[keyIndex] = pairArray[numPairs - 1];
      // set the last key index to null
      pairArray[numPairs - 1] = null;
      // decrease the number of elements in the array
      numPairs--;
      return true;
    }
    return false; // key is not in array
  }

  /**
   * Getter method for an element with a certain key. If there isn't an element
   * with the same key, return null.
   * 
   * @param key - key of element to get
   * @throws IllegalArgumentException - if the key is null
   * @return the value associated with the key
   */
  @Override
  public String get(String key) {

    // if the key is null, throw an IllegalArgumentException
    if(key == null) {
      throw new IllegalArgumentException("null key");
    }
    int keyIndex = getIndex(key);
    if(validIndex(keyIndex)) {
      return pairArray[keyIndex].getValue();
    }
    // if there isn't an element that matches the key, return null
    return null;

  }

  /**
   * This method searches the array to find if the key inputed exists in the
   * array.
   * 
   * @param key - the key of the element to be found
   * @return true if the array contains an element with the same key
   */

  // TODO: figure out how to use getIndex(STring) in this method
  @Override
  public boolean contains(String key) {
    int keyIndex = getIndex(key);

    // if the key is found, return true.
    if(validIndex(keyIndex)) {
      return true;
    }
    // else return false.
    return false;
  }

  /**
   * This method returns the size of the array, and by size, I mean the number
   * of elements in the array.
   * 
   * @return the current number of elements in the Pair[]
   */
  @Override
  public int size() {
    return numPairs;
  }

  /**
   * Helper method that doubles the size of the current array
   * 
   * @param array - current array
   * @return an array that has double the size of the current array.
   */
  private Pair[] expandArray(Pair[] array) {
    Pair[] biggerArray = new Pair[array.length * 2];
    // transfer all the Pairs in the old array to the new array.
    for(int i = 0; i < array.length; i++) {
      biggerArray[i] = array[i];
    }

    // return the new array with all the same values as the old array.
    return biggerArray;
  }

  /**
   * Helper method that gets the index of the key, works under the assumption
   * that the key is valid (not null, and not a duplicate.)
   * 
   * @param key - get the index of this key
   * @return the index of the key, or -1 if the key is not in the array.
   */
  private int getIndex(String key) {
    for(int i = 0; i < numPairs; i++) {
      String currKey = pairArray[i].getKey();
      if(currKey.equals(key)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Helper method that determines if the index is a valid index or not
   * 
   * @param keyIndex - index of the key
   * @return true if the index is between 0 and numPairs, false if it isn't
   */
  private boolean validIndex(int keyIndex) {
    if(keyIndex < numPairs && keyIndex >= 0) {
      return true;
    }
    return false;
  }
}
