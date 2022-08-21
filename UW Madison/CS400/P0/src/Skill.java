////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           Skill.java
//Files:           Main.java, Requirement.java, Skill.java, input.txt, output.txt, log.txt
//Course:          (CS400, Spring, 2020)
//
//Author:          (Ariel Fu)
//Email:           (afu5@wisc.edu)
//Lecture Number: 001
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////

//
//VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//___ Write-up states that pair programming is allowed for this assignment.
//___ We have both read and understand the course Pair Programming Policy.
//___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully 
//acknowledge and credit those sources of help here.  Instructors and TAs do 
//not need to be credited here, but tutors, friends, relatives, room mates, 
//strangers, and others do.  If you received no outside help from either type
//of source, then please explicitly indicate NONE.
//
//Persons:         (NONE)
//Online Sources:  (NONE)
//
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class models a skill that is part of a Requirement.
 * 
 * @author Ariel
 *
 */
public class Skill {
  private String name;

  /**
   * Constructor to set the name
   * 
   * @param name - name of the skill
   */
  public Skill(String name) {
    this.name = name;
  }

  /**
   * Overrides Object's toString method
   * 
   * @return the name
   */
  public String toString() {
    return name;
  }
}
