
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           BankTeller.java
// Files:           BankAccount.java, BankAccountTester.java, BankTeller.java, BankTellerTester.java
// Course:          (CS300, Fall, 2019)
//
// Author:          (Ariel Fu)
// Email:           (afu5@wisc.edu)
// Lecturer's Name: (Mouna AYARI BEN HADJ KACEM)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;
import java.util.Scanner;

/**
 * This class models the BankTeller data type
 * 
 * @author Ariel
 *
 */
public class BankTeller {
  private ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();

  /**
   * Creates a new BankTeller object with an empty list of accounts
   */
  public BankTeller() {
    accounts.clear();
  }

  /**
   * Adds newAccount to the list of accounts of this BankTeller
   * 
   * @param newAccount - a new account to add
   * @throws java.lang.IllegalStateException    - if the accountID of the
   *                                            newAccount is used by another
   *                                            account already added to the list
   *                                            of accounts
   * @throws java.lang.IllegalArgumentException - if the newAccount is null
   */
  public void addBankAccount(BankAccount newAccount) {
    if(newAccount == null) {
      throw new IllegalArgumentException("Account can not be null.");
    }
    for(int i = 0; i < accounts.size(); i++) {
      if(newAccount.equals(accounts.get(i))) {
        throw new IllegalStateException(
                "accountID of newAccount is already used by another account.");
      }
    }
    // if there are no errors, add the new account
    accounts.add(0, newAccount);

  }

  /**
   * Adds a new transaction to the account's list of transactions. When added, a
   * withdrawal or deposit transaction should change the account's balance
   * 
   * @param transaction - to add
   * @param account     - bank account
   * @throws java.util.zip.DataFormatException - if the format of the transaction
   *                                           is not correct: withdrawing,
   *                                           depositing, and if the transaction
   *                                           doesn't have a value or a correct
   *                                           keyword.
   * @throws java.lang.NullPointerException    - if the account is null.
   *
   * 
   */
  public void addTransaction(String transaction, BankAccount account) throws DataFormatException {
    // check if the account is null
    if(account == null) {
      throw new NullPointerException("Account cannot be null.");
    }

    // trim and split the transaction
    transaction = transaction.trim();
    String[] splitString = transaction.split("\\s+");

    // if the splitString is longer or shorter than 2, throw a DataFormatException
    if(splitString.length != 2) {
      throw new DataFormatException("The format of the transaction is not correct");
    }

    int transactionAmount = 0;

    // try to convert the value of the transaction to an int. If it throws a
    // NumberFormatException, throw a DataFormatException.
    try {
      transactionAmount = Integer.valueOf(splitString[1]);
    } catch (NumberFormatException e) {
      // if the transaction amount includes non-numeric keys
      throw new DataFormatException("The transaction amount is incorrect/ invalid.");
    }

    try {
      if(splitString[0].equals("0")) {
        account.withdraw(transactionAmount); // withdraw the money
      } else if(splitString[0].equals("1")) {
        account.deposit(transactionAmount); // deposit the money
      } else {
        // if the transaction key is neither 0 or 1
        throw new DataFormatException("The transaction key must be either 0 or 1.");
      }
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      throw new DataFormatException(e.getMessage());
    } catch (IllegalStateException e) {
      System.out.println(e.getMessage());
      throw new DataFormatException(e.getMessage());

    }

    // add account to the ArrayList of accounts.
    accounts.add(0, account);

  }

  /**
   * Returns the bank account that has exactly the provided identifier. Case
   * sensitive comparison must be considered.
   * 
   * @param id - a string that represents an identifier of a bank account
   * 
   * @return a reference to the bank account whose account identifier has an exact
   *         match with the provided string
   * 
   * @throws NoSuchElementException - no account in this BankTeller's accounts
   *                                list has the provided id
   */
  public BankAccount findAccount(String id) throws NoSuchElementException {
    for(int i = 0; i < accounts.size(); i++) {
      if(accounts.get(i).getID().equals(id)) {
        return accounts.get(i);
      }
    }
    throw new NoSuchElementException("The ID does not exist in the list of the accounts.");
  }

  /**
   * Returns the total number of accounts created so far (i.e., the size of the
   * Arraylist of accounts
   * 
   * @return the total number of accounts added to this BankTeller
   */
  public int getAccountsCount() {
    return accounts.size();
  }

  /**
   * Loads a set of transactions from a provided file object. Each transaction is
   * in a separate line. Each transaction line should contain two items: the
   * transaction code "0" or "1" followed by the transaction amount, separated by
   * spaces. Extra spaces at the beginning and at the end of a transaction line
   * should be ignored. Not correctly formatted lines must be skipped. Valid
   * transactions must change the balance of the bank account. If
   * java.util.Scanner object is used to read from the file object, make sure to
   * close the scanner object whenever a FileNotFoundException was thrown or not.
   * 
   * @param file    - a java.io.File object referring to a file that contains a
   *                set of transactions, each in one line
   * @param account - a reference to a BankAccount object
   * 
   * @throws NullPointerException  - if the account is null
   * @throws FileNotFoundException - if the file object does not correspond to an
   *                               actual file within the file system.
   * 
   */
  public void loadTransactions(File file, BankAccount account)
          throws java.io.FileNotFoundException {

    Scanner scnr = null;
    if(!file.exists()) {
      throw new FileNotFoundException("File does not exist within the file system.");
    }
    if(account == null) {
      throw new NullPointerException("Account is null.");
    }

    try {
      scnr = new Scanner(file);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File does not exist within the file system.");
    }

    while (scnr.hasNextLine()) {

      String nextLine = scnr.nextLine().trim();
      // use try and catch for addTransaction in case of invalid transactions
      try {
        // add the transaction to the list of transactions and continue
        addTransaction(nextLine, account);
      } catch (DataFormatException e) {
      }
    }

    if(scnr != null) {
      scnr.close();
    }
  }

}
