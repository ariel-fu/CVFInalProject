
public interface LocationInterface {


/**
 * Was this Location a hit?
 * @return true if a ship is there
 */
  public boolean checkHit();

/**
 * Was this location a miss?
 * @return true if not ships are there
 */
  public boolean checkMiss();

/**
 * Was this location unguessed?
 * @return true if location is unguessed
 */
  public boolean isUnguessed();

/**
 * Mark this location a hit.
 */
  public void markHit();

/**
 * Mark this location a miss.
 */
  public void markMiss();

/**
 * Return whether or not this location has a ship.
 * @return true if a ship is at current location
 */
  public boolean hasShip();

/**
 * Set the value of whether this location has a ship.
 * @param val
 */
  public void setShip(boolean val);

/**
 * Set the status of this Location.
 * @param status
 */
  public void setStatus(int status);

/**
 * Get the status of this Location.
 * @return
 */
  public int getStatus();
}
