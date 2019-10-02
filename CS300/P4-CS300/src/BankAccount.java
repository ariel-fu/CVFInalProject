
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           BankAccount.java
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

import java.util.ArrayList;
import java.util.zip.DataFormatException;

/**
 * This class models a very simple account at a bank
 * 
 * @author Ariel
 *
 */
public class BankAccount {

  private String accountID;
  private int balance;
  private ArrayList<String> transactions = new ArrayList<String>();

  /**
   * Creates a new bank account with a given account identifier and an initial
   * balance
   * 
   * @param accountID      - this account's unique identifier
   * @param initialBalance - this account's initial balance
   * @throws IllegalArgumentException - with a descriptive message if initBalance
   *                                  is less than 10
   */
  public BankAccount(String accountID, int initialBalance) {
    this.accountID = accountID;
    if(initialBalance < 10) {
      throw new IllegalArgumentException("Error: initial balance has to be $10 or greater");
    }

    deposit(initialBalance);
  }

  /**
   * This method deposits an amount to this bank account. It also adds the
   * transaction /"1 " + depositAmount/ to this account list of transactions and
   * updates this bank account's balance.
   * 
   * @param depositAmount
   * @throws IllegalArgumentException - if the depositAmount is negative
   */
  public void deposit(int depositAmount) {
    if(depositAmount < 0) {
      throw new IllegalArgumentException("Deposit amount can't be negative.");
    }
    // add the transaction to the ArrayList of transactions and add the amount to
    // the total balance
    transactions.add(0, "1 " + depositAmount);
    balance += depositAmount;
  }

  /**
   * Checks if an other bank account is equal to this one
   * 
   * @param other - another BankAccount object
   * @return true if this bankAccount's identifier equals the other bankAccount's
   *         identifier. This comparison is case sensitive.
   */
  public boolean equals(BankAccount other) {
    if(this.getID().equals(other.getID())) {
      return true;
    }
    return false;
  }

  /**
   * Gets the current balance of this bank account
   * 
   * @return the current balance of this bank account
   */
  public int getBalance() {
    return balance;
  }

  /**
   * Gets the unique identifier of this account
   * 
   * @return accountID
   */
  public String getID() {
    return accountID;
  }

  /**
   * Gets the most recent FIVE transactions in an array of length 5. This array
   * may contain null references if the total number of transactions is less than
   * 5. If getTransactionsCount() is less than 5, these transactions should be
   * stored at the range of indices 0 .. getTransactionsCount()-1. For instance,
   * if the total number of transactions is 0, this array will contain five null
   * references. If the total number of transactions is 1, it will contain the
   * transaction at index 0, followed by 4 null references, and so on.Gets the
   * most recent FIVE transactions in an array of length 5. This array may contain
   * null references if the total number of transactions is less than 5. If
   * getTransactionsCount() is less than 5, these transactions should be stored at
   * the range of indices 0 .. getTransactionsCount()-1. For instance, if the
   * total number of transactions is 0, this array will contain five null
   * references. If the total number of transactions is 1, it will contain the
   * transaction at index 0, followed by 4 null references, and so on.
   * 
   * @return the most recent transactions in an array that may contain up to 5
   *         string references
   */
  public String[] getMostRecentTransactions() {
    String[] recentTransactions = new String[5];

    int max = transactions.size();
    if(max > 5) {
      max = 5;
    }

    for(int i = 0; i < max; i++) {

      recentTransactions[i] = transactions.get(i);
    }

    return recentTransactions;
  }

  /**
   * Gets the total number of transactions performed on this bank account, meaning
   * the size of the ArrayList of transactions of this bank account
   * 
   * @return the total number of transactions performed on this account
   */
  public int getTransactionsCount() {
    return transactions.size();
  }

  /**
   * 
   * @param withdrawAmount
   * @throws java.util.zip.DataFormatException - if withdrawalAmount is negative
   *                                           or is not a multiple of 10
   * @throws java.lang.IllegalStateException   - if the withdrawalAmount is larger
   *                                           than this bank account's balance
   */
  public void withdraw(int withdrawAmount) throws DataFormatException {

    if(withdrawAmount < 0) {
      throw new java.util.zip.DataFormatException("Error: withdrawalAmount cannot be negative.");
    }

    if(withdrawAmount > balance) {
      throw new java.lang.IllegalStateException("Error: withdraw amount (" + withdrawAmount
              + ") must be less than account balance: " + balance);
    }

    if(withdrawAmount % 10 != 0) {
      throw new java.util.zip.DataFormatException(
              "Error: withdrawalAmount must be a multiple of 10");
    }

    // if no errors are thrown, add transaction to ArrayList
    // and subtract the money from total balance.
    transactions.add(0, "0 " + withdrawAmount);
    balance -= withdrawAmount;

  }

}
