//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           DS_My.java
// Files:           DataStructureADT.java, DataStructureADTTest.java, DS_My.java, TestDS_My.java
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

// TODO: Add class header here (DONE)
public class DS_My implements DataStructureADT<String, String> {

  // TODO - may wish to define an inner class
  // for storing key and value as a pair
  // such a class and its members should be "private"
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

  }

  // Private Fields of the class
  // TODO create field(s) here to store data pairs
  private int currNumPairs; // current number of elements in the Pair[]
  private Pair[] pairArray; // array to hold the Pair(s)

  /**
   * Constructor for DS_My class. Sets the currNumPairs to 0, and initialize the
   * pairArray to hold 20 elements.
   */
  public DS_My() {
    currNumPairs = 0; // set the current number of elements to 0 (duh)
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
   * @throws RunTimeException         - if there the key input is a duplicate of a
   *                                  key already in the Pair array.
   */
  @Override
  public void insert(String key, String value) {
    // TODO Auto-generated method stub
    // check if the key is null, if so throw an IllegalArgumentException
    if (key == null) {
      throw new IllegalArgumentException("null key");
    }
    // then check if the array is maxed out. (length = currNumPairs)
    if (pairArray.length == currNumPairs) {
      // if it is, expand the array
      pairArray = expandArray(pairArray);
    }

    // TODO - check dup before expand
    // run a for loop through the array to check if this key is a duplicate key.
    if (this.contains(key)) {
      throw new RuntimeException("duplicate key.");
    }
    // if no duplicate keys are found, add the new Pair to the end of the array
    pairArray[currNumPairs] = new Pair(key, value);
    currNumPairs++; // increase the number of pairs inside the Pair[]

  }

  /**
   * This method removes a Pair, given the key.
   * 
   * @param key - key of the Pair to be removed
   * @throws IllegalArgumentException - if the key is null
   */
  @Override
  public boolean remove(String key) {
    // TODO Auto-generated method stub
    // check if the key is null, if so throw an IllegalArgumentException
    if (key == null) {
      throw new IllegalArgumentException("null key");
    }

    for (int i = 0; i < currNumPairs; i++) {
      if (pairArray[i].getKey().compareTo(key) == 0) {
        // take the element at the end and add it to the curr index
        pairArray[i] = pairArray[currNumPairs - 1];
        // decrease the number of elements in the array
        currNumPairs--;
        return true; // found the key in the array
      }
    }
    return false;
  }

  /**
   * Getter method for an element with a certain key. If there isn't an element
   * with the same key, return null.
   * 
   * @param key - key of element to get
   * @throws IllegalArgumentException - if the key is null
   * @return the element with the same key. If there isn't an element with the
   *         same key, return null.
   */
  @Override
  public String get(String key) {
    // TODO Auto-generated method stub
    // if the key is null, throw an IllegalArgumentException
    if (key == null) {
      throw new IllegalArgumentException("null key");
    }
    // run through the array to find the element that matches the key
    for (int i = 0; i < currNumPairs; i++) {
      String currKey = pairArray[i].getKey();
      if (currKey.equals(key)) {
        return currKey;
      }
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
  @Override
  public boolean contains(String key) {
    // TODO Auto-generated method stub
    for (int i = 0; i < currNumPairs; i++) {
      // if there is an element with the same key, return true
      if (pairArray[i].getKey().equals(key)) {
        return true;
      }
    }
    // else return false.
    return false;
  }

  /**
   * This method returns the size of the array, and by size, I mean the number of
   * elements in the array.
   * 
   * @return the current number of elements in the Pair[]
   */
  @Override
  public int size() {
    // TODO Auto-generated method stub
    return currNumPairs;
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
    for (int i = 0; i < array.length; i++) {
      biggerArray[i] = array[i];
    }

    // return the new array with all the same values as the old array.
    return biggerArray;
  }

  // TODO - remove if not used
  /**
   * Helper method to remove an element from an array
   * 
   * @param array - array that needs to remove an element
   * @param key   - the element to be removed
   * @return a Pair[] with the element with key removed.
   */
  private Pair[] removeElement(Pair[] array, String key) {
    Pair[] newArray = new Pair[array.length]; // start with the same length
    int newArrayIndex = 0; // index of the newArray
    for (int i = 0; i < array.length; i++) {
      // check if the current element == null
      if (array[i] != null) {
        // if it not, check if it is equal to the value
        if (!array[i].getKey().equals(key)) {
          // if it is not equal to the value, add the current element to newArray.
          newArray[newArrayIndex] = array[i];
          newArrayIndex++;
        }
        // if it is equal to the value, skip one.
      }

    }
    return newArray; // return the newArray
  }

}
