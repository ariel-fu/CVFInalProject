import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Paths;

/**
 * Main class runs my gymnastic beam routine builder
 * 
 * @author Ariel This class asks the user for an input of a variety of skills to
 *         add into their routine.
 * @name Ariel Fu Email: afu5@wisc.edu Lecture: Lecture 001
 * 
 */

public class Main {
  public static final String title = "Name: Ariel Fu, email: afu5@wisc.edu, Lecture: Lecture 001";
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    System.out.println(title);
    System.out.println("Welcome to the gymnastics beam routine builder.");
    System.out.println("Please input your list of requirements / file path name: ");
    // get the file path name, convert to a file, then create a new scanner that
    // takes in input from the file.
    String filePathName = "";
    try {
      filePathName = scanner.nextLine();

      List<String> fileLines = Files.readAllLines(Paths.get(filePathName));
      String command = "";
      ArrayList<String> reqs = getReqs(fileLines);
      int currReq = 0; // helps keep track of curr index in the requirement list.
      ArrayList<String> routine = new ArrayList<String>(); // a List of the routine, all skills
                                                           // picked will be added to this list.

      // should only run as long as there are still requirements left in the routine.
      for(int i = 0; i < reqs.size(); i++) {
        System.out.println("Please choose a " + reqs.get(currReq)
                + " requirement. Enter exit at any time to quit.");
        // increment after a requirement is chosen.

        System.out.println("Skills: ");
        printList(getSkills(fileLines, reqs.get(currReq)));
        System.out.println();

        // String array of skills under current requirement
        ArrayList<String> currSkillList = getSkills(fileLines, reqs.get(currReq));
        // prompt user to choose a skill
        System.out.println("Choose a skill using the numbers 1-" + currSkillList.size());

        command = scanner.nextLine();

        // break if the user entered exit.
        if(command.equalsIgnoreCase("exit")) {
          break;
        } else if(!command.equals("")) {
          //
          while (!isValidInput(command, currSkillList)) {
            if(command.equalsIgnoreCase("exit")) {
              break;
            }
            System.out.println("Whoops, you didn't choose a number within range of 1-"
                    + currSkillList.size() + " Please try again.");
            command = scanner.nextLine();
          }
        } else if(command.equals("")) {
          // first print out an error message to try again.

          System.out.println("Whoops, you didn't choose a number within range of 1-"
                  + currSkillList.size() + " Please try again.");
          command = scanner.nextLine();
          while (command.equals("") || !isValidInput(command, currSkillList)) {
            if(command.equalsIgnoreCase("exit")) {
              break;
            }
            System.out.println("Whoops, you didn't choose a number within range of 1-"
                    + currSkillList.size() + " Please try again.");
            command = scanner.nextLine();
          }
        }

        // set the item in the routine to the skill at index command (a number from 1 ->
        // the list size)
        routine.add(currSkillList.get(Integer.parseInt(command) - 1));
        currReq++; // increment the index for the current requirement list
        System.out.println(""); // added for spacing / aesthetics
      }
      if(command.equalsIgnoreCase("exit")) {
        System.out.println("Successful exit.");
      } else {
        File file = new File("C:\\Users\\Ariel\\git\\Ariel\\CS400\\P0\\src\\output.txt");
        PrintWriter writer = null;

        // use a try catch statement in-case output file is not in directory
        try {
          writer = new PrintWriter(file);
          // prints or writes out routine out to the File output.txt
          writer.write("Here is your routine!");
          for(int i = 0; i < routine.size() - 1; i++) {
            // split the skill from its difficulty
            String[] skill = routine.get(i).split(" ");
            // print out the skill (without its difficulty number)
            for(int skillIndex = 1; skillIndex < skill.length; skillIndex++) {
              writer.write(" " + skill[skillIndex] + " ");
            }
            writer.write(" *dance* ");
          }
          // repeat the split operation for the last skill.
          String[] skill = routine.get(routine.size() - 1).split(" ");
          // repeat write operation
          for(int skillIndex = 1; skillIndex < skill.length; skillIndex++) {
            writer.write(" " + skill[skillIndex] + " ");
          }
          writer.write(" STICK!");
          System.out.println("Successful routine :)");
          writer.close();
        } catch (FileNotFoundException e) {
          System.out.println(
                  "Sorry, that file was not found. Please try the program again, but with a different file name.");
        }

      }
    } catch (Exception e) {
      System.out.println("File is not found or not valid. Re-run the program.");
      System.out.println(e.getMessage());
    }
  }

  /**
   * Retrieves the requirements in the routine from the List
   * 
   * @param fileLines - a List that holds all the info from the File
   * @return a String[] that holds the requirements
   */
  protected static ArrayList<String> getReqs(List<String> fileLines) {
    ArrayList<String> listOfRequirements = new ArrayList<String>();
    int currentIndex = 0; // to help keep track of the current index.
    for(int i = 0; i < fileLines.size(); i++) {
      if(!fileLines.get(i).equals("")) {
        char firstCharacter = fileLines.get(i).charAt(0);

        if(!isNumeric(firstCharacter)) {
          listOfRequirements.add(fileLines.get(i));
          currentIndex++;
        }
      }
    }

    return listOfRequirements; // return the String[] of skills
  }

  /**
   * Retrieves the skills in the routine from the List
   * 
   * @param fileLines       - a List that holds all the info from the File
   * @param requirementName - name of the requirement that the skills are under.
   * @return a String[] that holds the requirements
   */
  protected static ArrayList<String> getSkills(List<String> fileLines, String requirementName) {
    ArrayList<String> listOfSkills = new ArrayList<String>();
    int startingIndex = 0; // start at the beginning of the list

    // first find where in the list the requirement and list of skills are.
    for(int i = 0; i < fileLines.size(); i++) {
      if(fileLines.get(i).equals(requirementName)) {
        startingIndex = i;
      }
    }
    // start at where the requirement is, and add every skill (until hit a empty
    // line or the end) to the list of skills array.

    for(int i = startingIndex; i < fileLines.size(); i++) {
      // add the skills to the array
      if(!fileLines.get(i).equals("")) {
        char firstCharacter = fileLines.get(i).charAt(0);
        if(isNumeric(firstCharacter)) {
          listOfSkills.add(fileLines.get(i));
        }
      }

      // check if the next one is an empty, if so, break.
      if(i + 1 < fileLines.size() && fileLines.get(i + 1).equals("")) {
        break;
      }

    }
    return listOfSkills; // return the String[] of skills
  }

  /**
   * Tests if there is a valid input for getting a skill
   * 
   * @param input     - input given by the user
   * @param fileLines - list with a certain length.
   * @return true if the input is within the range of 1-fileLines.size()
   */
  protected static boolean isNumeric(char input) {
    // Requirement have an input of only numbers from 1-fileLines.size()
    try {
      String character = Character.toString(input);
      Integer.parseInt(character);
    } catch (Exception e) {
      return false;
    }

    return true; // if the char is able to parse to a String then an Integer, it is numeric.

  }

  /**
   * Tests if there is a valid input for getting a skill (with a String array)
   * 
   * @param input     - input given by the user
   * @param fileLines - list with a certain length.
   * @return true if the input is within the range of 1-fileLines.size()
   */
  protected static boolean isValidInput(String input, ArrayList<String> fileLines) {
    // first, the input must be numeric
    char firstLetter = input.charAt(0);
    if(isNumeric(firstLetter)) {
      String s = Character.toString(firstLetter);
      int numInput = Integer.parseInt(s);
      if(numInput <= fileLines.size()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Prints out a String array
   * 
   * @param skillList - a String array that is going to be printed out
   */
  protected static void printList(ArrayList<String> skillList) {
    System.out.print("| ");
    for(int i = 0; i < skillList.size(); i++) {
      if(skillList.get(i) != null) {
        System.out.print(skillList.get(i) + " | ");
      }
    }
  }

}
