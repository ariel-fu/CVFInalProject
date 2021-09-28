
public class IndexOfMax {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    int[] A = new int[] { 4, 6, 8, 3, 8, 5 };
    int n = 6;
    indexOfMain(n, A);
  }

  public static int indexOfMain(int n, int[] A) {

    int i = 1;
    int m = A[0];
    int b = 0;

    while (i < n) {
      if (A[i] >= m) {
        m = A[i];
        b = i;

      }
      System.out.println("m: " + m + " b: " + b);
      i++;
    }

    return b;
  }

}
