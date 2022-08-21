import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class P0_Tester {

  public String filePathName = "C:\\Users\\Ariel\\git\\Ariel\\CS400\\P0\\src\\input.txt";

  @Test
  public void testGetReqs() throws IOException {
    List<String> fileLines = Files.readAllLines(Paths.get(filePathName));
    ArrayList<String> listOfReqs = Main.getReqs(fileLines);
    String[] correct = new String[] { "Mount", "Acro Series", "Gym Acro", "Dismounts" };
    for(int i = 0; i < listOfReqs.size(); i++) {
      System.out.println("expected: " + correct[i]);
      System.out.println("result: " + listOfReqs.get(i));
      assertTrue(listOfReqs.get(i).equals(correct[i]));
    }
  }

  @Test
  public void testGetSkills() {

  }

  @Test
  public void testIsNumeric() throws IOException {
    int x = 9;

    char input = '9';
    assertTrue(Main.isNumeric(input));
  }

  @Test
  public void testIsValidInputCharStringArray() {

  }

  @Test
  public void testPrintList() {

  }

}
