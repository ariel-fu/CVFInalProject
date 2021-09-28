
public class assignment9 {
  private static int totalStuff;

  private static int p1(int c, int[] a, int n) {
    int i = 1;
    int y = a[0];
    totalStuff = 0;
    while (i <= n) {
      System.out.println("# iterations: " + i + " y: " + y);
      totalStuff += 2;
      y = y + a[i] * power(c, i);
      i++;
    }
    return y;
  }

  private static int power(int a, int b) {
    int i = 0;
    int y = 1;
    while (i < b) {
      y = y * a;
      i++;
    }
    return y;
  }

  private static int p2(int c, int[] a, int n) {
    int i = 1;
    int y = a[n];

    while (i <= n + 1) {
      System.out.println("# iterations: " + i + " y: " + y);
      y = y * c + a[n - i];
      i++;
    }

    return y;
  }

  public static void main(String[] args) {
    int[] a = new int[] { 1, 2, 2, 3, 1 };
    // p2(3, a, a.length - 1);
    System.out.println();
    p1(3, a, a.length);
  }
}
