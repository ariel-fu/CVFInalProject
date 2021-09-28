
public class SwiftPower {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    int a = 5;
    int b = 5;
    System.out.println(swiftPower(a, b));
  }

  private static int swiftPower(int a, int b) {
    int r;
    if (b == 1) {
      return a;
    } else {
      r = swiftPower(a, Math.floorDiv(b, 2));
      System.out.println("r: " + r);
    }

    if (b % 2 == 0) {
      return r * r;
    } else {
      return a * r * r;
    }
  }
}
