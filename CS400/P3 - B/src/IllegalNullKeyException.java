
/**
 * Thrown when a user attempts to insert, remove, or get a key whose value is
 * null.
 */
@SuppressWarnings("serial")
public class IllegalNullKeyException extends Exception {
  /**
   * default no-arg constructor
   */
  public IllegalNullKeyException() {
  }

  /**
   * This constructor is provided to allow user to include a message
   * 
   * @param msg Additional message for this exception
   */
  public IllegalNullKeyException(String msg) {
    super(msg);
  }

}
