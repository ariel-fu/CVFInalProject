import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// Name: Ariel Fu
// Email:afu5@wisc.edu
// Lecture: Lecture 001

/**
 * This class models my requirements in a routine.
 * 
 * @author Ariel
 *
 */
public class Requirements {
  private Scanner scnr; // scanner to read the file
  private static String nextRequirement; // previous requirement added to the ArrayList
  private ArrayList<Skill> requirement; // an ArrayList of skill based on the requirement

  /**
   * Constructs a Requirement from a String path name
   * 
   * @param fileName - path name to a file
   * @throws FileNotFoundException - if a file is not found
   */
  public Requirements(String fileName) throws FileNotFoundException {
    this(new File(fileName));
  }

  /**
   * Constructs a Requirement from a file
   * 
   * @param file - a file input
   * @throws FileNotFoundException - if a file is not found
   */
  public Requirements(File file) throws FileNotFoundException {
    scnr = new Scanner(file);
    addListOfSkills(scnr);
  }

  /**
   * Helper method that adds the skills under a specific requirement to the
   * ArrayList
   * 
   * @param file - file with requirements and skills
   */
  private void addListOfSkills(Scanner file) {
    String currentSkill;
    if(nextRequirement != null) {
      currentSkill = nextRequirement; // start at the next requirement if it is not null
    } else {
      currentSkill = file.next();
    }
    int index = 0;
    // add all the skills into the ArrayList along with its requirement name.
    while (file.hasNext()) {
      requirement.set(index, new Skill(currentSkill)); // place the current skill into the ArrayList
      index++;
      currentSkill = file.nextLine(); // set the currentSkill to the next skill
    }
    nextRequirement = file.nextLine();
  }

  /**
   * Getter method for the requirements in a file,
   * 
   * @param file - file that has requirements and skills
   * @return an ArrayList of Strings that represent a requirement
   */
  public ArrayList<String> getRequirement(Scanner file) {
    ArrayList<String> reqs = new ArrayList<>();
    String nextLine = file.nextLine();
    int index = 1;

    reqs.add(nextLine); // add the first line/requirement to the List

    // while the file has lines, add in requirements.
    while (file.hasNext()) {
      if(nextLine.equals("")) {
        reqs.set(index, file.skip("\r").nextLine());
      }
    }
    return reqs;
  }

  /**
   * Getter method for the ArrayList of skills
   * 
   * @return requirement - list of skills
   */
  public ArrayList<Skill> getListOfSkills() {
    return requirement;
  }
}
