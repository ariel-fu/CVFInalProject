import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * TODO Stores (key,value) pair for each (State,List<DailyStateDataEntry>).
 * 
 * This class also provides additional data functionality.
 * 
 * @author deppeler ALL RIGHTS RESERVED, FOR STUDENT USE DURING EXAM ONLY.
 */
public class DS {

  /** TODO add private fields for your data structure here */

  /** PRO-TIP: use Hashtable or TreeMap */

  private Hashtable<String, List<DailyStateDataEntry>> hashtable;

  /** PRO-TIP: store as (State,List<DailyStateDataEntry>) pairs. */

  /** TODO initial internal data structure field(s) here */
  public DS() {
    hashtable = new Hashtable<>(52);
  }

  /** TODO Add a daily entry to the list for a given state */
  public void add(DailyStateDataEntry dataEntry) {
    String stateName = dataEntry.getStateName();

    if (hashtable.get(stateName) == null) {
      // not in the ds
      List<DailyStateDataEntry> list = new ArrayList<DailyStateDataEntry>();
      list.add(dataEntry);
      hashtable.put(stateName, list);
    } else {
      // get the list
      List<DailyStateDataEntry> list = hashtable.get(stateName);
      // add to the list
      list.add(dataEntry);
      // put the list back into the hashtable
      hashtable.replace(stateName, list);
    }

    /**
     * PRO-TIP get state name from record If not found, add to internal ds Then,
     * add this data entry to the correct list for the state.
     */

  }

  /** TODO Return a summary of all the records for specified State */
  public StateSummary getStateSummaryFor(String stateName) {
    List<DailyStateDataEntry> recordsList = hashtable.get(stateName);
    // returns null if the state is not in the hashtable
    if (recordsList == null) {
      return null;
    } else {
      // else, returns a StateSummary
      return new StateSummary(stateName, recordsList);
    }
    /**
     * PRO TIP: use your internal data structure to get list of records for the
     * state and then instantiate and return a StateSummary instance
     */

  }

  /** TODO Return an array of all state names in sorted order. */
  public String[] getStateNamesInSortedOrder() {
    // convert all state names in the hashtable to an ArrayList
    List<String> listOfStates = new ArrayList<String>(hashtable.keySet());

    // sort the arraylist, default for sort is ascending
    Collections.sort(listOfStates);
    String[] states = new String[listOfStates.size()];
    // add to a String[]
    for (int i = 0; i < listOfStates.size(); i++) {
      states[i] = listOfStates.get(i);
    }
    // TODO complete this method so that it returns an array
    // of all state and territory (no dups) in the data structure

    // There must not be any null elements as the user
    // must be able to use the length to know how many state/territories

    // partial credit if not in sorted order by state name here

    return states;

  }

  /**
   * Mini-test code of StateSummary class. STUDENTS MAY EDIT for their own use.
   */
  public static void main(String[] args) throws FileNotFoundException {
    DS ds = Main.parseData("test.csv");
    String[] names = ds.getStateNamesInSortedOrder();
    for (String name : names) {
      System.out.print(name + ",");
    }
    System.out.println();
    System.out.println(ds.getStateSummaryFor("Dane"));
    System.out.println(ds.getStateSummaryFor("Green"));
    System.out.println(ds.getStateSummaryFor("Milwaukee"));
  }

}
