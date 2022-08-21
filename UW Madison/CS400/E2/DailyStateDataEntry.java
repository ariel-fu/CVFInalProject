import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Stores data for a single line of the data file. Each line must have this
 * form: "recordID,stateName,numCases,numDeaths"
 * 
 * @author deppeler ALL RIGHTS RESERVED, FOR STUDENT USE DURING EXAM ONLY.
 */
public class DailyStateDataEntry {

  // PRIVATE DATA data fields provided
  private String recordID;
  private String name;
  private int cases;
  private int deaths;

  /**
   * TODO parse line of data into a DailyStateDataEntry instance.
   * 
   * @param line a line of data as read from a data file.
   * @throws FileNotFoundException - if the file is not found
   */
  public static DailyStateDataEntry parse(String line) {
    // TODO: complete this method to return an instance

    DailyStateDataEntry entry = new DailyStateDataEntry();
    // split by commas
    String[] parseLine = line.split(",");
    entry.recordID = parseLine[0];
    entry.name = parseLine[1];
    entry.cases = Integer.valueOf(parseLine[2]);
    entry.deaths = Integer.valueOf(parseLine[3]);

    return entry;
  }

  /** PUBLIC accessor methods provided */
  public String getRecordID() {
    return recordID;
  }

  public String getStateName() {
    return name;
  }

  public int getDeaths() {
    return deaths;
  }

  public int getCases() {
    return cases;
  }

  @Override
  /** CAN BE CHANGEDOVERRIDED BY STUDENT */
  public String toString() {
    return "" + getRecordID() + "," + getStateName() + "," + getCases() + ","
        + getDeaths();
  }

  /**
   * Mini-test code of StateSummary class. STUDENTS MAY EDIT for their own use.
   */
  public static void main(String[] args) {
    DailyStateDataEntry s = DailyStateDataEntry.parse("1001,Wisconsin,123,12");
    System.out.println(s.getStateName() + "(" + s.getRecordID() + ")");
    System.out.println("Has " + s.getCases() + " cases.");
    System.out.println("And " + s.getDeaths() + " deaths.");

    DailyStateDataEntry t = DailyStateDataEntry
        .parse("d1005,Illinois,2390,431");
    System.out.println(t.toString());

  }

}
