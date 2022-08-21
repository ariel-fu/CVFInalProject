
public class Location implements LocationInterface {
  public static final int UNGUESSED = 0;
  public static final int HIT = 1;
  public static final int MISSED = 2;
  
  public Location() {
    
  }
  public boolean checkHit() {
    if(hasShip()) {
      return true;
    } 
    return false;
  }

  public boolean checkMiss() {
    if(!hasShip()) {
      return true;
    }
    return false;
  }

  public boolean isUnguessed() {
    if(checkHit() || checkMiss()) {
      return false;
    }
    return true;
  }

  public void markHit() {
    
    
  }

  public void markMiss() {
    
    
  }

  public boolean hasShip() {
    
    return false;
  }

  public void setShip(boolean val) {
    
    
  }

  public void setStatus(int status) {
    
    
  }

  public int getStatus() {
    
    return 0;
  }
  
}
