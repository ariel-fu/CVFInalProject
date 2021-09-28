
public class Test {

  public static void testInterest() {
    UltraBankAccount ba = new UltraBankAccount(1, 100);
    double currBalance = ba.balance();
    ba.addInterest();
    double threePercent = 0.03 * currBalance;
    double newBalance = threePercent + currBalance;
    System.out.println(ba.balance() == newBalance);
  }

  public static void testInterestComp() {
    UltraBankAccount ba = new UltraBankAccount(1, 100);
    double ogbalance = ba.balance();
    double interest = getInterest(ba.balance());
    ba.addInterest();
    ba.deposit(0.0);
    interest += getInterest(ba.balance());
    ba.addInterest();
    ogbalance += interest;
    System.out.println(ba.balance() == ogbalance);

  }

  public static void testWithdraw() {
    UltraBankAccount ba = new UltraBankAccount(1, 100);
    // withdraw valid amount
    ba.withdraw(10.0);
    System.out.println(ba.balance() == 70.0);
  }

  private static double getInterest(double currBalance) {
    return 0.03 * currBalance;
  }

  public static void main(String[] args) {
    testInterest();
    testInterestComp();
    testWithdraw();
  }

}
