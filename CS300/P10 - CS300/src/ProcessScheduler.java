import java.util.Queue;
import java.util.Scanner;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           ProcessSchedulerTester
// Files:           CustomProcess,ProcessSchedulerTester, WaitngProcessQueue, WaitingQueueADT  
// Course:          300, Fall, and 2019
//
// Author:          (Ariel Fu)
// Email:           afu5@wisc.edu
// Lecturer's Name: Mouna Ayari Ben Hadj Kacem
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         NONE
// Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * This class represents the data type for the main scheduler for our processes.
 */
public class ProcessScheduler {
  private int currentTime; // stores the current time after the last run
  private int numProcessesRun; // stores the number of processes run so far
  private WaitingProcessQueue queue; // this processing unit’s queue

  public ProcessScheduler() {
    queue = new WaitingProcessQueue();
    currentTime = 0;
    numProcessesRun = 0;

  }

  /**
   * This method inserts the given process in the WaitingProcessQueue queue
   * 
   * @param process - process to be inserted into the queue
   */
  public void scheduleProcess(CustomProcess process) {
    queue.insert(process);
    numProcessesRun++;
  }

  /**
   * Runs the ready processes already scheduled in this processScheduler's queue
   * 
   * @return each process in order using the remove method
   */
  public String run() {
    String value = "";
    while (!queue.isEmpty()) {
      CustomProcess removed = queue.removeBest();
      value += "Time " + currentTime + " : Process ID " + removed.getProcessId()
              + " Starting. \r\n";
      currentTime += removed.getBurstTime();
      value += "Time " + currentTime + " : Process ID " + removed.getProcessId()
              + " Completed. \r\n";
      // if the queue is empty, print out
      if(queue.isEmpty()) {
        value += "Time " + currentTime + " : All scheduled processes completed. \r\n";
      }

    }
    return value;
  }

  /**
   * Runs the scanner and schedules accordingly
   * 
   * @param args
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    ProcessScheduler schedule = new ProcessScheduler();
    String stringInputed = "";
    System.out.println("========== Welcome to the SJF Process Scheduler App ========");
    int numberOfProcesses = 0;
    do {
      // ask for input!
      System.out.println("Enter command: ");
      System.out.println("[schedule <burstTime>] or [s <burstTime>]");
      System.out.println("[run] or [r]");
      System.out.println("[quit] or [q]");
      stringInputed = scanner.nextLine();

      // split the input by spaces
      String[] splitString = stringInputed.split(" ");

      // set the operator to the first element
      String command = splitString[0];

      // if the user inputed quit, break out of the do/while loop then say goodbye
      if(command.equalsIgnoreCase("q")) {
        break;
      } else if(command.equalsIgnoreCase("quit")) {
        break;
      }

      // check if it is a valid command
      if(!isValidCommand(command)) {
        System.out.println("WARNING: Please enter a valid command! \n");
        continue;
      }

      // if it is valid, check if it is a valid input
      if(!isValidArgument(splitString)) {
        System.out.println("WARNING: burst time MUST be an integer! \n");
      }

      // if both the command and argument (if it has one) are valid, do the command.
      if(splitString[0].equalsIgnoreCase("r") || splitString[0].equalsIgnoreCase("run")) {

        if(schedule.numProcessesRun > 1) {
          System.out.println("Starting " + schedule.numProcessesRun + " processes");
        } else {
          System.out.println("Starting " + schedule.numProcessesRun + " process");
        }

        System.out.println(schedule.run());
        schedule.numProcessesRun = 0; // reset the number of processes to run.
      } else if(splitString[0].equalsIgnoreCase("s")
              || splitString[0].equalsIgnoreCase("schedule")) {
        // run the same things for input "s" and "schedule"

        // get the burst time
        int burstTime = Integer.parseInt(splitString[1]);
        try {
          // create a new process to schedule with the burst time parsed above
          CustomProcess process = new CustomProcess(burstTime);

          // schedule the process
          schedule.scheduleProcess(process);
          numberOfProcesses++;
          // print out the ID and the burst time of the process recently scheduled.
          System.out.println("Process ID " + process.getProcessId() + " scheduled. Burst Time = "
                  + burstTime + "\n");
        } catch (IllegalArgumentException e) {
          // if the burst time is a non-positive or zero integer, print out the error
          // message.
          System.out.println(e.getMessage());
        }

      }
    } while (!stringInputed.equalsIgnoreCase("quit") || !stringInputed.equalsIgnoreCase("q"));
    // close the scanner if it was used.
    if(scanner != null) {
      scanner.close();
    }
    System.out.println(numberOfProcesses + " processes run in " + schedule.currentTime
            + " units of time!\n" + "Thank you for using our scheduler!\n" + "Goodbye!\n");
  }

  /**
   * Checks if the input is a valid command
   * 
   * @param stringInputed - input from Scanner
   * @return true if the command is a valid command
   */
  public static boolean isValidCommand(String stringInputed) {

    // check if it matches any one of the 6 commands
    if(stringInputed.equalsIgnoreCase("r")) {
      return true;
    } else if(stringInputed.equalsIgnoreCase("run")) {
      return true;
    } else if(stringInputed.equalsIgnoreCase("s")) {
      return true;
    } else if(stringInputed.equalsIgnoreCase("schedule")) {
      return true;
    }
    // if it doesn't match any of the commands, return false
    return false;

  }

  /**
   * Checks if it is a valid argument if it takes in another argument
   * 
   * @param splitString - input from user
   * @return true if the argument is valid
   */
  public static boolean isValidArgument(String[] splitString) {
    String command = splitString[0];
    if(command.equalsIgnoreCase("s")) {
      return isNumeric(splitString[1]);
    } else if(command.equalsIgnoreCase("schedule")) {
      return isNumeric(splitString[1]);
    } else if(command.equalsIgnoreCase("r")) {
      return true;
    } else if(command.equalsIgnoreCase("run")) {
      return true;
    }
    return false;

  }

  /**
   * Checks if the element (String) is numeric
   * 
   * @param string - element that is supposed to be numeric
   * @param result -
   * @return true if string is numeric
   */
  public static boolean isNumeric(String string) {
    // try to parse to an int
    try {
      int test = Integer.parseInt(string);
    } catch (Exception e) {
      // if there is an exception, it is not numeric
      return false;
    }
    return true;
  }

}
