/**
 * This class models a Class Roster that represents a collection of Students
 * enrolled in a given course
 * 
 * @author Your Name
 *
 */
public class Roster {
  private final String COURSE_ID; // identifier of the course associated with this roster
  private Student[] students; // oversize array that stores the list of students enrolled so far
  private int size; // number of students stored in this roster

  /**
   * Creates a new empty roster and sets its COURSE_ID and capacity. When
   * initially created, this new roster object contains zero students
   * 
   * @throws IllegalArgumentException if capacity is negative ( less or equal to
   *                                  zero)
   */
  public Roster(String COURSE_ID, int capacity) {
    // Complete this constructor
    if(capacity <= 0) {
      throw new IllegalArgumentException("Error: Can’t create a roster with negative capacity");
    }
    this.COURSE_ID = COURSE_ID;
    students = new Student[capacity];

  }

  /**
   * Adds a new Student to this roster
   * 
   * @param newStudent to be added to this roster
   * @throws IllegalArgumentException if newStudent is null
   * @throws IllegalStateException    if the array students reaches its capacity
   */
  public void addStudent(Student newStudent) {
    if(newStudent == null) {
      throw new IllegalArgumentException("Error: student was not properly created.");
    }
    if(size == students.length) {
      throw new IllegalStateException("Roster is full.");
    }
    students[size] = newStudent;
    size++;
    // Complete the implementation of this addStudent() method conforming to its
    // javadoc description
  }

  /**
   * Getter of COURSE_ID field
   * 
   * @return the COURSE_ID of this roster
   */
  public String getCOURSE_ID() {
    return COURSE_ID;
  }

  /**
   * Getter of the size of this students roster
   * 
   * @return the size of this roster
   */
  public int getSize() {
    return size;
  }

}
