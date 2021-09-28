
public class BankAccount {

  private int accountNum; // account number of this bankAccount
  private double balance; // balance of this bankAccount

  /**
   * Creates a new bank account with the ability to make deposits and withdraws
   * 
   * @param accountNum
   * @param initialBalance
   */
  public BankAccount(int accountNum, double initialBalance) {
    this.accountNum = accountNum;
    this.balance = initialBalance;
  }

  /**
   * Adds teh specified amount to balance of this account
   * 
   * @param amount - amount to deposit
   */
  public void deposit(double amount) {
    balance += amount;
  }

  /**
   * Withdraws money from the account
   * 
   * @param amount - amount of money to withdraw
   * @return false if there is not enough money to withdraw in the balance, true
   *         otherwise
   */
  public boolean withdraw(double amount) {
    if (amount > balance) {
      return false;
    }
    balance -= amount;
    return true;
  }

  /**
   * Returns the balance of this bank account
   * 
   * @return balance of bank account
   */
  public double balance() {
    return balance;
  }

} // end of BankAccount class

class UltraBankAccount extends BankAccount {
  private final int PENALTY = 20; // penalty to be withdrawn along with every
                                  // withdrawal operation
  private final double INTEREST_RATE = 0.03; // interest rate

  /**
   * Creates a new UltraBankAccount with a specified account number and initial
   * balance
   * 
   * @param accountNum     - - account number of this account
   * @param initialBalance - initial balance of this account
   */
  public UltraBankAccount(int accountNum, double initialBalance) {
    super(accountNum, initialBalance);

  }

  /**
   * Adds interest to the balance of this account with respect to the
   * INTEREST_RATE
   */
  public void addInterest() {
    // get the amount of interest
    double interestNum = super.balance() * INTEREST_RATE;
    // deposit interest
    super.deposit(interestNum);

  }

  /**
   * Withdraws the amount + the penalty from the balance
   * 
   * @param amount - amount to withdraw
   * @return if the amount is more than the balance, return false. Otherwise,
   *         return true.
   */
  @Override
  public boolean withdraw(double amount) {
    double withdrawAmount = PENALTY + amount;
    double balance = super.balance();

    // withdraw amount is greater than the balance
    if (withdrawAmount > balance) {
      return false;
    } else {
      // otherwise, withdraw the amount
      super.withdraw(withdrawAmount);
      return true;
    }
  }
}
