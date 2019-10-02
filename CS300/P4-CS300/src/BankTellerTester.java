import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           BankTellerTester.java
// Files:           BankAccount.java, BankAccountTester.java, BankTeller.java, BankTellerTester.java
// Course:          (CS300, Fall, 2019)
//
// Author:          (Ariel Fu)
// Email:           (afu5@wisc.edu)
// Lecturer's Name: (Mouna AYARI BEN HADJ KACEM)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (N/A)
// Partner Email:   (N/A)
// Partner Lecturer's Name: (N/A)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (NONE)
// Online Sources:  (NONE)
//               
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class is a tester for the BankTeller class's public behaviors
 * 
 * @author Ariel
 *
 */
public class BankTellerTester {

  /**
   * Checks whether the constructor of BankTeller class creates a new BankTeller
   * object with an empty ArrayList of bank accounts.
   * 
   * @return true if the constructor creates an empty ArrayList of bank accounts
   */
  public static boolean testBankTellerConstructor() {
    BankTeller testArrayList = new BankTeller();
    if(testArrayList.getAccountsCount() == 0) {

      return true;
    }
    return false;
  }

  /**
   * Checks whether the BankTeller.addBankAccount() method throws an
   * IllegalStateException when it is passed a bank account with an identifier
   * already used.
   * 
   * @return true if it throws an IllegalStateException if there is an identifier
   *         already used
   */
  public static boolean testBankTellerAddBankAccountUsedIdentifier() {
    BankTeller testUsedIdentifier = new BankTeller();
    BankAccount testIdentifier1 = new BankAccount("12ert", 2255);
    BankAccount testIdentifier2 = new BankAccount("12ert", 25256);
    try {
      testUsedIdentifier.addBankAccount(testIdentifier1);
      testUsedIdentifier.addBankAccount(testIdentifier2);
      return false;
    } catch (IllegalStateException e) {
      System.out.println(e.getMessage());
      return true;
    }
  }

  /**
   * This method checks whether the BankTeller.loadTransactions() method that
   * takes a File parameter throws a FileNotFoundException, when it is passed a
   * File object that does not correspond to an actual file within the file
   * system, and a non null reference of type BankAccount.
   * 
   * @return true if it throws a FileNotFoundException if there is no file that
   *         corresponds to an actual file.
   */
  public static boolean testBankTellerLoadTransactionsFileNotFound() {
    File file = new File("EFile.txt");
    BankTeller test = new BankTeller();
    BankAccount testBank = new BankAccount("wef56w", 259);
    try {
      test.loadTransactions(file, testBank);
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
      return true;
    }

    return false;
  }

  /**
   * Checks if loadTransactions will take in a null account throw a null pointer
   * exception
   * 
   * @return true if a NullPointerException is thrown if the account is
   *         initialized to null.
   * 
   */

  public static boolean testBankTellerLoadTransactionsNullPointerException() {
    File file = new File("C:\\Users\\Ariel\\git\\Ariel\\CS300\\P4-CS300\\src\\File");
    BankTeller test = new BankTeller();
    BankAccount nullBank = null;

    try {
      test.loadTransactions(file, nullBank);
    } catch (NullPointerException e) {
      System.out.println(e.getMessage());
      return true;
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
      return false;
    }
    return false;
  }

  public static boolean testBankTellerLoadTransactions() {
    File file = new File("C:\\temp\\account.txt");
    BankTeller test = new BankTeller();
    BankAccount account = new BankAccount("TEST", 100);

    try {
      test.loadTransactions(file, account);
    } catch (Exception e) {
      return false;
    }

    System.out.print(test.getAccountsCount());
    return true;
  }

  /**
   * Tests if addTransaction throws a NullPointerException if the account is
   * initialized to null
   * 
   * @return true if addTransaction throws a NullPointerException if the account
   *         is initialized to null
   */
  public static boolean testBankTellerAddTransactionNullPointer() {
    BankTeller test = new BankTeller();
    BankAccount account = null;
    try {
      test.addTransaction("1 250", account);
    } catch (NullPointerException e) {
      System.out.println(e.getMessage());
      return true;
    } catch (DataFormatException e) {
      System.out.println(e.getMessage());
      // TODO Auto-generated catch block
      return false;
    }

    return false;
  }

  /**
   * Tests if the method throws a DataFormatException if the transaction has an
   * invalid length
   * 
   * @return true if a DataFormatException is thrown if the transaction has an
   *         invalid length
   */
  public static boolean testBankTellerAddTransactionInvalidLength() {
    BankTeller test = new BankTeller();
    BankAccount account = new BankAccount("we", 259);
    try {
      test.addTransaction("1", account);
    } catch (DataFormatException e) {
      System.out.println(e.getMessage());
      return true;
    }
    return false;
  }

  /**
   * Tests if the method throws a DataFormatException if the transaction amount is
   * not numeric
   * 
   * @return true if a DataFormatException is thrown
   */
  public static boolean testBankTellerAddTransactionInvalidAmount() {
    BankTeller test = new BankTeller();
    BankAccount account = new BankAccount("ewsd", 52);
    try {
      test.addTransaction("0 2sd", account);
    } catch (DataFormatException e) {
      System.out.println(e.getMessage());
      return true;
    }
    return false;
  }

  /**
   * Tests if the user inputs a invalid key.
   * 
   * @return true if invalid key is inputted and a DataFormatException is thrown.
   */
  public static boolean testBankTellerAddTransactionInvalidKey() {
    BankTeller test = new BankTeller();
    BankAccount account = new BankAccount("23sd", 25);
    try {
      test.addTransaction("3 259", account);
    } catch (DataFormatException e) {
      System.out.println(e.getMessage());
      return true;
    }
    return false;
  }

  /**
   * Search for a account that does not exist. Checks if it throws a
   * NoSuchElementException.
   * 
   * @return true if a NoSuchException is thrown
   */
  public static boolean testBankTellerFindAccount() {
    BankTeller test = new BankTeller();

    BankAccount newAccount = new BankAccount("ABC", 100);
    test.addBankAccount(newAccount);
    BankAccount account = test.findAccount("ABC");

    if(!newAccount.equals(account)) {
      System.out.print("Failed to find ABC");
      return false;
    }

    try {
      test.findAccount("5d5");
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
      return true;
    }
    return false;

  }

  /**
   * Tests if addAccounts will add to the ArrayList
   * 
   * @return true if the new ArrayList size = 2
   */
  public static boolean testBankTellerGetAccountCount() {
    BankTeller test = new BankTeller();
    BankAccount account1 = new BankAccount("we5", 250);
    BankAccount account2 = new BankAccount("wsde5", 250);
    int size = 2;
    test.addBankAccount(account1);
    test.addBankAccount(account2);
    if(size == test.getAccountsCount()) {
      return true;
    }
    return false;
  }

  /**
   * Tests if the account is null will it throw an IllegalArgumentException.
   * 
   * @return true if the account is null and the method threw a
   *         IllegalArgumentException.
   */
  public static boolean testBankTellerAddAccountNull() {
    BankTeller test = new BankTeller();
    BankAccount account = null;

    try {
      test.addBankAccount(account);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      return true;
    }
    return false;
  }

  /**
   * Tests if the method will return an IllegalStateException if the accounts' IDs
   * are equal to each other and you try to add them to the ArrayList of accounts.
   * 
   * @return true if an IllegalStateException is thrown if the accounts' IDs are
   *         equal to each other.
   */
  public static boolean testBankTellerAddAccountEqual() {
    BankTeller test = new BankTeller();
    BankAccount account1 = new BankAccount("ew34", 250);
    BankAccount account2 = new BankAccount("ew34", 256);
    try {
      test.addBankAccount(account1);
      test.addBankAccount(account2);
    } catch (IllegalStateException e) {
      System.out.println(e.getMessage());
      return true;
    }
    return false;
  }

  /**
   * Tests if the accounts list will increase the more accounts you put in.
   * 
   * @return true if no exception is thrown and the accounts list is at a size of
   *         2.
   */
  public static boolean testBankTellerAddAccount() {
    BankTeller test = new BankTeller();
    BankAccount account1 = new BankAccount("ew34", 250);
    BankAccount account2 = new BankAccount("ew34ss", 256);
    try {
      test.addBankAccount(account1);
      test.addBankAccount(account2);
    } catch (IllegalStateException e) {
      return false;
    }

    if(test.getAccountsCount() == 2) {
      return true;
    }
    return false;
  }

  /**
   * Tests if a valid transaction will result in a change in the bank account
   * balance
   * 
   * @return true if the balance is equal to the initial balance + deposit amount
   *         and if the transactions list increased.
   */
  public static boolean testAddTransaction() {
    BankTeller test = new BankTeller();
    BankAccount account = new BankAccount("we", 250);
    try {
      test.addTransaction("1 50", account);
    } catch (DataFormatException e) {
      return false;
    }

    if(account.getBalance() == 300) {
      if(account.getTransactionsCount() == 2) {
        return true;
      }
    }
    return false;
  }

  /**
   * Tests foundAccount to find an account that already exists
   * 
   * @return true if the account is found and throws no errors
   */
  public static boolean testFindAccoun() {
    BankTeller test = new BankTeller();
    BankAccount account1 = new BankAccount("wejkd", 250);
    BankAccount account2 = new BankAccount("wejkd1", 250);
    BankAccount account3 = new BankAccount("wejkwed", 250);
    BankAccount account4 = new BankAccount("wesdjkd", 250);
    try {
      test.addBankAccount(account1);
      test.addBankAccount(account2);
      test.addBankAccount(account3);
      test.addBankAccount(account4);
      
      test.findAccount("wejkd");
    } catch(NoSuchElementException e) {
      return false;
    }
    return true;
  }
  
  
  
  /**
   * Main is for testing and printing out results of the test methods
   * 
   * @param args
   */

  public static void main(String[] args) {
    testBankTellerLoadTransactions();
    System.out
            .println("~Test ArrayList length of 0 in constructor: " + testBankTellerConstructor());
    System.out.println(
            "_----------------------------End of constructor testing------------------------------------------");
    System.out.println();
    System.out.println("~Test bank account with a used identifier: "
            + testBankTellerAddBankAccountUsedIdentifier());
    System.out.println(
            "_-----------------------------End of addBankAccount-----------------------------------------");
    System.out.println();
    System.out.println("~Test File not found: " + testBankTellerLoadTransactionsFileNotFound());
    System.out.println("_----------------------------------------------------------------------");
    System.out.println("~Test LoadTransactions null account: "
            + testBankTellerLoadTransactionsNullPointerException());
    System.out.println(
            "_---------------------End of LoadTransaction-------------------------------------------------");
    System.out.println();
    System.out.println(
            "~Test addTransactions null pointer: " + testBankTellerAddTransactionNullPointer());
    System.out.println("_----------------------------------------------------------------------");
    System.out.println(
            "~Test Invalid Transaction length: " + testBankTellerAddTransactionInvalidLength());
    System.out.println("_----------------------------------------------------------------------");
    System.out.println(
            "~Test Non-numeric transaction amount: " + testBankTellerAddTransactionInvalidAmount());
    System.out.println("_----------------------------------------------------------------------");
    System.out.println("~Test Invalid Key: " + testBankTellerAddTransactionInvalidKey());
    System.out.println("~Test Valid transaction: " + testAddTransaction());
    System.out.println(
            "_----------------------------End of AddTransaction------------------------------------------");

    System.out.println();
    System.out.println("~Test Account that does not exist: " + testBankTellerFindAccount());
    System.out.println("~Test Account that does exist: " + testFindAccoun());
    System.out.println(
            "_----------------------------End of FindAccount-------------------------------------");
    System.out.println();
    System.out.println("~Test GetAccountsCount: " + testBankTellerGetAccountCount());

    System.out.println();
    System.out.println("~Test adding a null account: " + testBankTellerAddAccountNull());
    System.out.println();
    System.out.println(
            "~Test adding an account ID that exists already: " + testBankTellerAddAccountEqual());

    System.out.println("~Test add 2 accounts: " + testBankTellerAddAccount());

    System.out.println(
            "_----------------------------End of AddAccount-------------------------------------");
  }

}
