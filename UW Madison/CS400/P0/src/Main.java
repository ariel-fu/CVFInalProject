import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Paths;
////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           Main.java
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
 * Main class runs my gymnastic beam routine builder. This class asks the user
 * for an input of a variety of skills to add into their routine.
 * 
 * @author Ariel
 * 
 */

public class Main {
  public static final String title = "Name: Ariel Fu, Email: afu5@wisc.edu, Lecture: Lecture 001";
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    System.out.print(title + "\r\n");

    System.out.println("Welcome to the gymnastics beam routine builder.");
    System.out.println(
            "Please input your list of requirements / file path name: ");
    // get the file path name, convert to a file, then create a new scanner that
    // takes in input from the file.
    String filePathName = "";
    try {
      filePathName = scanner.nextLine();

      List<String> fileLines = Files.readAllLines(Paths.get(filePathName));
      String command = "";
      // an ArrayList of requirements
      ArrayList<Requirement> reqs = getReqs(fileLines);

      // a List of the routine, all skills picked will be added to this list.
      ArrayList<Skill> routine = new ArrayList<Skill>();

      for(int i = 0; i < reqs.size(); i++) {
        Requirement req = reqs.get(i);
        System.out.println("Please choose a " + req.getName()
                + " requirement. Enter exit at any time to quit.");

        System.out.println("Skills: ");
        printList(req.getListOfSkills());
        System.out.println();

        // String array of skills under current requirement
        ArrayList<Skill> currSkillList = req.getListOfSkills();
        // prompt user to choose a skill
        System.out.println(
                "Choose a skill using the numbers 1-" + currSkillList.size());

        command = scanner.nextLine();
        command = command.trim(); // take out any empty spaces in front or after
                                  // the command.

        // break if the user entered exit.
        if(command.equalsIgnoreCase("exit")) {
          break;
        } else if(!isValidInput(command, currSkillList)) {
          // if it is not a valid input, keep prompting user for input until
          // they give a valid input.
          while (!isValidInput(command, currSkillList)) {
            if(command.equalsIgnoreCase("exit")) {
              break;
            }
            System.out.println(
                    "Whoops, you didn't choose a number within range of 1-"
                            + currSkillList.size() + " Please try again.");
            command = scanner.nextLine();
          }
        }

        // set the item in the routine to the skill at index command (a number
        // from 1 -> the list size)
        routine.add(currSkillList.get(Integer.parseInt(command) - 1));

        // if it is at the end of the routine
        if(i == reqs.size() - 1) {
          // add the skill that signals the end
          routine.add(new Skill("STICK IT!!!"));
        } else {
          // else add the dance in between the skills
          routine.add(new Skill("*DANCE*"));
        }

        System.out.println(""); // added for spacing
      }

      // if command is exit, print out a successful exit and quit.
      if(command.equalsIgnoreCase("exit")) {
        System.out.println("Successful exit.");
      } else {
        // otherwise, print the routine out to console.
        System.out.println("Your routine \r\n");
        printList(routine);
        scanner.close(); // close scanner before writing to file

        File file = new File("output.txt");
        PrintWriter writer = null;
        // use a try catch statement in-case output file is not in directory
        try {
          writer = new PrintWriter(file);
          // and print the routine out to the output file.
          output(writer, routine);
          writer.close();

        } catch (FileNotFoundException e) {
          System.out.println("File not found.");
        }

      }

    } catch (Exception e) {
      System.out.println("File is not found or not valid. Re-run the program.");
      scanner.close(); // close the scanner
    }
  }

  /**
   * Helper method that writes to the output file
   * 
   * @param writer  - PrintWriter that writes data to the file
   * @param routine - the routine to be written to the file.
   */
  private static void output(PrintWriter writer, ArrayList<Skill> routine) {
    // prints or writes out routine out to the File output.txt
    writer.write("Here is your routine! \r\n");
    for(int i = 0; i < routine.size(); i++) {
      writer.write(routine.get(i).toString());
      writer.write("\r\n");
    }

    System.out.println("\r\nSuccessful routine :)");
  }

  /**
   * Retrieves the requirements in the routine from the List
   * 
   * @param fileLines - a List that holds all the info from the File
   * @return a String[] that holds the requirements
   */
  private static ArrayList<Requirement> getReqs(List<String> fileLines) {
    ArrayList<Requirement> listOfRequirements = new ArrayList<Requirement>();
    ArrayList<String> data = new ArrayList<String>();
    for(int i = 0; i < fileLines.size(); i++) {
      if(fileLines.get(i).equals("")) {
        listOfRequirements.add(new Requirement(data));
        data = new ArrayList<String>();
      } else {
        data.add(fileLines.get(i));
      }
    }
    listOfRequirements.add(new Requirement(data));
    return listOfRequirements; // return the String[] of skills
  }

  /**
   * Tests if there is a valid input for getting a skill (with a String array)
   * 
   * @param input     - input given by the user
   * @param fileLines - list with a certain length.
   * @return true if the input is within the range of 1-fileLines.size()
   */
  private static boolean isValidInput(String input,
          ArrayList<Skill> fileLines) {
    // check if the input is null or an empty String.
    if(input == null) {
      return false;
    } else if(input.equals("")) {
      return false;
    }
    // split the string and see if the element at index 0 is numeric
    String[] split = input.split(" ");
    // if there are more that just the number to be chosen, it is not a valid
    // input.
    if(split.length > 1) {
      return false;
    }
    try {
      int num = Integer.parseInt(split[0]); // try to parse to an int.
      // if it is an integer, check if it is within bounds (within the size of
      // the ArrayList)
      if(num <= fileLines.size() && num > 0) {
        return true;
      }
    } catch (Exception e) {
      return false;
    }

    return false;
  }

  /**
   * Prints out a String array
   * 
   * @param skillList - a String array that is going to be printed out
   */
  private static void printList(ArrayList<Skill> skillList) {
    for(int i = 0; i < skillList.size(); i++) {
      if(skillList.get(i) != null) {
        System.out.println((i + 1) + ". " + skillList.get(i));
      }
    }
  }

}
