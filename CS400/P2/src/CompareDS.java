
public class CompareDS {

  public static void main(String[] args) {
    long runBST = runBST();
    long runRBT = runRBT();
    
    // compare BST and RBT
    if(runBST > runRBT) {
      System.out.println("BST is faster than RBT by "+ (runBST -runRBT));
    } else {
      System.out.println("RBT is faster than BST by  " + (runRBT-runBST));
    }
  }

  private static long runBST() {
    BST<Integer, Integer> bst = new BST<Integer, Integer>();
    long startTime = 0;
    long endTime = 0;
    long runTime = 0;

    try {
      startTime = System.nanoTime();
      for (int i = 0; i < 30; i++) {
        bst.insert(i, i);
      }
      endTime = System.nanoTime();
      runTime += (endTime - startTime);
    } catch (Exception e) {
      System.out.println("Exception thrown from insert in BST");
    }

    try {
      startTime = System.nanoTime();
      bst.get(29);
      
      endTime = System.nanoTime();
      runTime += (endTime - startTime);
    } catch (Exception e) {
      System.out.println("Exception thrown from get in BST");
    }
    return runTime;
  }

  private static long runRBT() {
    long startTime = 0;
    long runTime = 0;
    long endTime = 0;
    RBT<Integer, Integer> rbt = new RBT<Integer, Integer>();
    try {
      startTime = System.nanoTime();
      for (int i = 0; i < 30; i++) {
        rbt.insert(i, i);
      }
    } catch (Exception e) {
      System.out.println("Exception thrown from insert in RBT");
    }
    endTime = System.nanoTime();
    runTime += (endTime - startTime);

    try {
      startTime = System.nanoTime();
      rbt.get(29);
      endTime = System.nanoTime();
      runTime += (endTime - startTime);
    } catch (Exception e) {
    }
    return runTime;
  }

}
