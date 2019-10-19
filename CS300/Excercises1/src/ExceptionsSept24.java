
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ExceptionsSept24 {
  
  private static Scanner scanner;
  public static int divideTwoNumbers() {
    
    try {
      System.out.println("-- LOCKING SOME SHARED RESOURCE, LIKE A FILE");
      System.out.print("Enter division problem (#/#): ");
      String input = scanner.nextLine();
      if(!input.contains("/")) {
        throw new NoSuchElementException("no / was included in user input");

      }
      String[] parts = input.split("/");
      int numerator = Integer.parseInt(parts[0]); // possible NumberFormatException
      int denominator = Integer.parseInt(parts[1]); // possible ArrayIndexOutOfBoundsException
      int quotient = numerator / denominator; // possible ArithmeticException
      return quotient;

    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
      System.out.println("Error, could not read two integers from your input.");
      return divideTwoNumbers();
    }

    catch (ArithmeticException e) {
      System.out.println("Warning, dividing by zero produces quotient of zero.");
      return 0; // catch and handle all other exceptions in this final way: }
    } catch (RuntimeException e) {
      System.out.println("Error: something went terribly wrong :(");
      throw e;
    }

    finally {
      System.out.println("-- UNLOCKING THAT SHARED RESOURCE BEFORE LEAVING TRY-CATCH-FINALLY BLOCK");
    }

  }

  public static void main(String[] args) {
    do {
    scanner = new Scanner(System.in);
    int result = divideTwoNumbers();
    System.out.println("result is: " + result);
    } while(true);
  }
}