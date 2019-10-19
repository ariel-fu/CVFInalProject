/**
 * Tests the Roster class for two exceptions: IllegalArgumentException and IllegalStateException
 * @author Ariel
 *
 */
public class TestRoster {
  /**
   * Checks whether your addStudent() method implemented in Student class throws
   * the correct exception when called on a null reference
   * 
   * @return true if addStudent() method throws an IllegalArgumentException if its
   *         called to add a null reference to a students roster, false otherwise
   */
  public static boolean addNullStudentTest() {
    boolean passed = false; // test result
    // Test scenario - Create a new roster
    Roster roster = new Roster("CS300", 100);
    // call addStudent(null) appropriately and check whether an
    // IllegalArgumentException is thrown
    try {
      // Missing code
      roster.addStudent(null);
    } catch (Exception IllegalArgumentException) {
      // Missing code
      passed = true;
    }

    return passed;

  }

  /**
   * Checks the correctness of the implementation of addStudent() method when the
   * roster is full Creates a new Roster object (with COURSE_ID "CS001" for
   * instance and capacity 2 for instance). Then, try to add three students and
   * check whether addStudent() method throws an IllegalStateException when it is
   * called and the roster is full (test passes).
   * 
   * @return true if test passes, false otherwise
   */
  public static boolean addTooMuchStudentsTest() {
    boolean passed = false;
    // Complete the implementation of this method according to its javadoc
    // description
    Student newStudent1 = new Student("Jack", "false");
    Student newStudent2 = new Student("Jacbgk", "falsse");
    Student newStudent3 = new Student("Jadck", "falsee");
    // create full roster
    Roster roster = new Roster("CS001", 2);
    try {
      // add three students

      roster.addStudent(newStudent1);
      roster.addStudent(newStudent2);
      roster.addStudent(newStudent3);
    } catch (Exception IllegalStateException) {
      passed = true;
    }
    return passed;
  }
  
  
  public static void main(String[] args) {
   System.out.println(TestRoster.addNullStudentTest());
   System.out.println(TestRoster.addTooMuchStudentsTest());
  }
}
