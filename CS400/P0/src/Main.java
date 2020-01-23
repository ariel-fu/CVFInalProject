import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
// Name: Ariel Fu
// Email: afu5@wisc.edu
// Lecture: Lecture 001

/**
 * Main class runs my gymnastic beam routine builder
 * 
 * @author Ariel This class asks the user for an input of a variety of skills to
 *         add into their routine.
 * 
 * 
 */

public class Main {

  public static void main(String[] args) throws FileNotFoundException {
    Scanner fileReader = new Scanner(System.in);
    System.out.println("Welcome to the gymnastics beam routine builder.");
    System.out.println("Please input your list of requirements / file path name: ");
    // get the file path name, convert to a file, then create a new scanner that
    // takes in input from the file.
    String filePathName = fileReader.nextLine();
    String input = "";
    String currentRoutine = "";

    Requirements req1 = new Requirements(filePathName); // new Requirement object from the file
    ArrayList<String> allRequirement = req1.getRequirement(fileReader); // list of requirements
    ArrayList<Skill> skills = req1.getListOfSkills(); // list of skills

    int numRequirements = 0; // keep track of how many requirements are in the routine already.
    do {
      /*
       * arraylist of skills has a requirement. TO find a new requirement and
       * (consequently) the list of skills that follow, run through skills until the
       * name matches one of the string from the list of requirements.
       * 
       * after the 3 println statements, print out the skills arraylist until hit a
       * empty space. then set skillsIndex (make a new variable) to the current index.
       * Index will stand for the current index in the skills arraylist
       * 
       */
      System.out.println(
              "Choose one beam " + skills.get(numRequirements) + " by typing any number from 1-4.");
      System.out.println("Type exit at any time to quit.");
      System.out.println("To see current routine, press p");

      // get the input from the user (should be any number from 1-4)
      Scanner userInput = new Scanner(System.in);
      input = userInput.nextLine();

      // if the input is incorrect, prompt user to try again until correct or they
      // exit.
      while (!validInput(input)) {
        System.out.println("Not a valid input, please try again. Or enter exit to quit.");
        input = userInput.nextLine();
      }

      // TODO: add the skill picked to the current routine. (A String)
    } while (!input.equalsIgnoreCase("exit") || numRequirements < allRequirement.size());
  }

  /**
   * Retrieves the skills from the file
   * 
   * @param skillsList - a list of skills under that specific requirement
   * @return a String array of skills under that requirement
   */
  private static String[] getSkills(Scanner skillsList) {
    String[] listOfSkills = new String[10];
    int index = 0;
    String nextSkill = skillsList.nextLine();
    while (!nextSkill.equals("---")) {
      // add the current skill to the String array
      listOfSkills[index] = nextSkill;
      index++;
      nextSkill = skillsList.nextLine();
    }
    return listOfSkills;
  }

  /**
   * Tests if there is a valid input for getting a skill
   * 
   * @param input - input given by the user
   * 
   * @return true if the requirement matches, and the input was the correct type.
   */
  private static boolean validInput(String input) {
    // Requirement have an input of only numbers from 1-4
    if(input.equals("1")) {
      return true;
    } else if(input.equals("2")) {
      return true;
    } else if(input.equals("3")) {
      return true;
    } else if(input.equals("4")) {
      return true;
    }

    return false;
  }

  /**
   * Prints out a String array
   * 
   * @param skillList - a String array that is going to be printed out
   */
  private static void printSkillList(String[] skillList) {
    for(int i = 0; i < skillList.length; i++) {
      if(skillList[i] != null) {
        System.out.println(skillList[i]);
      }
    }
  }

  private static int getInt(String userInput) {
    char character = userInput.charAt(0);
    int number = Integer.parseInt(String.valueOf(character));
    return number;
  }

}
