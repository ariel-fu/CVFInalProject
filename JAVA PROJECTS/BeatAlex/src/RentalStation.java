/////////////////////////////// EXAM FILE HEADER ///////////////////////////////
// Full Name: 
// Campus ID: 
// WiscEmail: 
////////////////////////////////////////////////////////////////////////////////

// Fill in the following blanks to complete the implementations of these two classes. Each of these
// methods can be implemented in three lines of code or less. When creating new exception objects,
// including messages within these objects is optional.

import java.util.ArrayList;

/**
 * Represents a single Bike that can be checked out and back in through a
 * RentalStation.
 */
class Bike {
  private static int nextId = 3000; // the unique id assigned to the next bike
                                    // created
  private final int ID; // the current bike's unique ID, which cannot be changed
  private boolean isCheckedOut; // true when this bike is checked out, false
                                // otherwise

  /**
   * Constructs a new bike that is currently checked in (not checked out).
   */
  public Bike() {
    /* Complete the missing code */

    this.ID = 0; // You have to change this statement (added only to let this
                 // incomplete source file
                 // to compile)
    this.isCheckedOut = false;
  }

  /**
   * Changes the state of this bike to reflect that it has been checked back in.
   * 
   * @throws IllegalStateException when called on a bike that is already checked
   *                               in.
   */
  public void checkin() throws IllegalStateException {
    if (!this.isCheckedOut) {
      throw new IllegalStateException("Bike is already checked in, thanks!");
    } else {
      // bike is checked back in
      // THANK YOU, PLEASE COME AGAIN:)
      this.isCheckedOut = false;
    }
  }

  /**
   * Changes the state of this bike to reflect that it has been checked out.
   * 
   * @throws IllegalStateException when called on a bike that is already checked
   *                               out.
   */
  public void checkout() throws IllegalStateException {
    if (this.isCheckedOut) {
      throw new IllegalStateException(
          "Already checked out, please choose a different bike :)");
    } else {
      this.isCheckedOut = true;
    }
    /* Complete the missing code */
  }
}

/**
 * Represents a rental station from which bikes can be checked in and out.
 */
public class RentalStation {
  private ArrayList<Bike> bikesAvailable; // list of the bikes that can be
                                          // checked out

  /**
   * Constructs a new rental station with the specified number of bike objects
   * stored within bikesAvailable. All of these bikes should be checked in.
   * 
   * @param numberOfBikes is the number of bikes that this rental station begins
   *                      with.
   */
  public RentalStation(int numberOfBikes) {
    bikesAvailable = new ArrayList<Bike>();
    // populate with (numberOfBikes) Bikes
    for (int i = 0; i < numberOfBikes; i++) {
      // all bikes start with being "checked in"
      bikesAvailable.add(new Bike());
    }
    /* Complete the missing code */
  }

  /**
   * Checks in a bike: changing bike's state, and adds it to bikesAvailable.
   * 
   * @param bike is a reference to the bike that is being checked in.
   * @throws IllegalStateException when the specified bike is already checked
   *                               in.
   */
  public void checkin(Bike bike) throws IllegalStateException {
    // automatically throws an IllegalStateException (from Bike class :))
    bike.checkin();
    /* Complete the missing code */
  }

  /**
   * Checks out one bike at your choice from the bikesAvailable list (the first
   * bike, or last one, for instance): change bike's state, and remove it from
   * bikesAvailable.
   * 
   * @return a reference to the bike that is being checked out.
   * @throws IllegalStateException     when bike in bikesAvailable is already
   *                                   checked out (you don't need to check
   *                                   every bike, only the one you attempt to
   *                                   check out).
   * @throws IndexOutOfBoundsException when no bikes available to be checked
   *                                   out. (note that ArrayList.remove()
   *                                   sometimes throws this kind of exception).
   */
  public Bike checkout() throws IllegalStateException {
    /* Complete the missing code */
    // throws IndexOutOfBoundsException :)
    Bike bike = bikesAvailable.get(bikesAvailable.size() - 1);
    // throws IllegalStateException
    bike.checkout();
    // return reference to checked-out bike
    return bike;

  }
}