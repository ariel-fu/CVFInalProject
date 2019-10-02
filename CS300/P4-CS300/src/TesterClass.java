import java.io.File;
import java.io.FileNotFoundException;

public class TesterClass {

  public static void main(String[] args) {
    BankTeller test = new BankTeller();
    BankAccount x = new BankAccount("2we54", 15);
    BankAccount x1 = new BankAccount("2wegsd54", 15);
    BankAccount x2 = new BankAccount("2wwee54", 15);
    BankAccount x3 = new BankAccount("2wdse54", 15);
    BankAccount x4 = new BankAccount("2wgdse54", 15);
    x.deposit(150);
    test.addBankAccount(x);
    test.addBankAccount(x1);
    test.addBankAccount(x2);
    test.addBankAccount(x3);
    test.addBankAccount(x4);
    System.out.println(x.getBalance());

    String[] y = x.getMostRecentTransactions();
    File file = new File("C:\\Users\\Ariel\\git\\Ariel\\CS300\\P4-CS300\\src\\File");
    try {
      test.loadTransactions(file, x);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      System.out.println(e.getMessage());
    }
  }

}
