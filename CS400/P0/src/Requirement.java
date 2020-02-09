import java.util.ArrayList;

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           Requirement.java
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
 * This class models a single requirement in a routine.
 * 
 * @author Ariel
 *
 */
public class Requirement {
  private ArrayList<Skill> skills; // an ArrayList of skill for the
                                   // requirement
  private String name;

  /**
   * Constructor that sets the name of the requirement and fills the ArrayList
   * of Skill with skills
   * 
   * @param data - ArrayList that contains the name of the requirement and
   *             skills under that requirement. First element in the ArrayList
   *             is always the name of the requirement.
   */
  public Requirement(ArrayList<String> data) {
    this.name = data.get(0);
    skills = new ArrayList<Skill>();
    for(int i = 1; i < data.size(); i++) {
      skills.add(new Skill(data.get(i)));
    }
  }

  /**
   * Getter method for the name of the requirement
   * 
   * @return the name of the requirement
   */
  public String getName() {
    return name;
  }

  /**
   * Getter method for the ArrayList of skills
   * 
   * @return requirement - list of skills
   */
  public ArrayList<Skill> getListOfSkills() {
    return skills;
  }
}
