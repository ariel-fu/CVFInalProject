
public class Student {
  

    // Class variables
    // Static variables (belonging to the class and shared by all the created objects)
    private static int nextUniqueID = 1; // represents the uniqueID
                                         // of the next student to be created
    private static int numberOfStudents = 0; // represents the number of students
                                             // created so far

    // Instance fields
    // Private fields belonging to the Student objects
    private String first; // represents the first name of the student
    private String last; // represents the last name of the student
    private final int UNIQUE_ID; // represents the unique ID (campus ID) of the student


    /**
     * Constructor creates a new instance of the class Student with a given name
     * 
     * @param first - the first name of this student
     * @param last - the last name of this student
     */
    public Student(String first, String last) {
      this.first = first;
      this.last = last;
      UNIQUE_ID = nextUniqueID;
      nextUniqueID++;
      numberOfStudents++;
    }


    /**
     * Accessor to the name field
     * 
     * @return the name of this student
     */
    public String getFirst() {
      return first;
    }

    /**
     * Setter of the first name field
     * 
     * @param first the first name to set
     */
    public void setFirst(String first) {
      this.first = first;
    }

    /**
     * Accessor to the last name field
     * 
     * @return the last name of this student
     */
    public String getLast() {
      return last;
    }

    /**
     * Setter for the last name field
     * 
     * @param last the last name to set
     */
    public void setLast(String last) {
      this.last = last;
    }

    /**
     * Accessor to the numberOfStudents
     * 
     * @return the numberOfStudents created so far
     */
    public static int getNumberOfStudents() {
      return numberOfStudents;
    }

    /**
     * Accessor to the unique ID field
     * 
     * @return the uniqueID of this student
     */
    public int getUniqueID() {
      return UNIQUE_ID;
    }



}
