
public class TesterClass {

  public static void main(String[] args) {
    BankAccount x = new BankAccount("2we54",15);
    x.deposit(150);
    System.out.println(x.getBalance());
    
    String[] y = x.getMostRecentTransactions();

  }

}
