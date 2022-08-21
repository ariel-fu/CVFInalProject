import java.io.File;
import java.io.IOException;

public class TesterClass {

  public static void main(String[] args) {
    File file = new File("testImage.png");
    try {
      Memeage test = new Memeage(file, "arielfuad");
      System.out.println("getting message...");
      System.out.println("Message: " + test.getMeme());
      
      file = new File("image01.png");
      System.out.println("getting message...");
      test = new Memeage(file, "Ariel");
      
      System.out.println("Message: " + test.getMeme());
      
      file = new File("image02.png");
      System.out.println("getting message...");
      test = new Memeage(file, "Im from the past");
      
      System.out.println("Message: " + test.getMeme());
    } catch (IllegalArgumentException e) {

      System.out.println("IllegalArgument" + e.getMessage());
    } catch (IOException e) {

      System.out.println(e.getMessage() + "IO EXCEPTION");
    }
    finally{
    
    }
  }

}
