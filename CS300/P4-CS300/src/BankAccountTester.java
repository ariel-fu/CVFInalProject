
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           BankAccountTester.java
// Files:           BankAccount.java, BankAccountTester.java, BankTeller.java, BankTellerTester.java
// Course:          (CS300, Fall, 2019)
//
// Author:          (Ariel Fu)
// Email:           (afu5@wisc.edu)
// Lecturer's Name: (Mouna AYARI BEN HADJ KACEM)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////

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
 * This class represents a tester for the BankAccount class's public behaviors
 * 
 * @author Ariel
 *
 */
public class BankAccountTester {

  /**
   * This method checks whether the BankAccount constructor throws an
   * IllegalArgumentException when it is passed a balance smaller than 10.
   * 
   * @return true if the balance is smaller than 10 and the BankAccount
   *         constructor throws an IllegalArgumentException
   */
  public static boolean testBankAccountConstructorNotValidInitialBalance() {
    try {
      BankAccount testInvalidBalance = new BankAccount("12ed", 1);
      return false;
    } catch (IllegalArgumentException e) {
      return true;
    }
  }

  /**
   * Calls the constructor of BankAccount class to create a new BankAccount object
   * with a given id and a valid initial balance (greater of equal to 10).
   * 
   * @return true if no exceptions are thrown
   */
  public static boolean testBankAccountConstructorValidInitialBalance() {
    try {
      BankAccount testValidBalance = new BankAccount("12ted", 12);
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Checks whether BankAccount.deposit() method throws an
   * IllegalArgumentException when it is passed a negative number.
   * 
   * @return true if an IllegalArgumentException is thrown when the input is a
   *         negative number
   */
  public static boolean testBankAccountDepositNegativeAmount() {
    BankAccount testDeposit = new BankAccount("ew2", 12);
    try {
      testDeposit.deposit(-2);
      return false;
    } catch (IllegalArgumentException e) {
      return true;
    }
  }

  /**
   * Checks whether BankAccount.equals() method returns true when it compares a
   * bank account to another one having the same account identifier (case
   * sensitive comparison); and it returns false otherwise.
   * 
   * @return true if both accounts have the same account identifier
   */
  public static boolean testBankAccountEquals() {
    BankAccount bankAccount1 = new BankAccount("1259east", 15);
    BankAccount bankAccount2 = new BankAccount("1259east", 95);

    return bankAccount1.equals(bankAccount2);
  }

  /**
   * Checks whether BankAccount.withdraw() method throws a DataFormatException
   * when it is passed a negative number or a number not multiple of 10.
   * 
   * @return true if it throws a DataFormatException (invalid amount)
   */
  public static boolean testBankAccountWithdrawInvalidAmount() {
    BankAccount testInvalidWithdraw = new BankAccount("wefv5", 25);
    try {
      testInvalidWithdraw.withdraw(-2);
    } catch (DataFormatException exception1) {
      try {
        testInvalidWithdraw.withdraw(5);
      } catch (DataFormatException exception2) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks whether BankAccount.withdraw() method throws an IllegalStateException
   * when it is passed a number larger than the account's balance.
   * 
   * @return true if it throws an IllegalStateException
   */
  public static boolean testBankAccountWithdrawLargerOfBalanceAmount() {
    BankAccount testLargerWithdraw = new BankAccount("we23", 20);
    try {
      testLargerWithdraw.withdraw(29);
      return false;
    } catch (IllegalStateException e) {
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Checks whether BankAccount.withdraw() method returns without any exception
   * when it is passed a positive number smaller than the account's balance.
   * 
   * @return true if it returns with no exceptions
   */
  public static boolean testBankAccountWithdrawValidAmount() {
    BankAccount testValidAmount = new BankAccount("21we", 289);

    try {
      testValidAmount.withdraw(50);
      return true;
    } catch (IllegalStateException e) {
      return false;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Tests a valid constructor
   * 
   * @return true if all the values are initialized correctly
   */
  public static boolean testBankAccountConstructor() {
    BankAccount test = new BankAccount("sdfewfds", 250);
    if(test.getBalance() == 250) {
      if(test.getTransactionsCount() == 1) {
        if(test.getID().equals("sdfewfds")) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Tests depositing a valid amount
   * 
   * @return true if the total balance is equal to the initial balance + deposit
   *         amount
   */
  public static boolean testBankAccountDeposit() {
    BankAccount test = new BankAccount("sdewf542ds", 250);
    try {
      test.deposit(100);
    } catch (IllegalArgumentException e) {
      return false;
    }

    if(test.getBalance() == 350) {
      return true;
    }
    return false;

  }

  /**
   * Tests a valid withdraw amount
   * 
   * @return true if the total balance after the withdraw is the initial balance -
   *         withdraw amount
   */
  public static boolean testBankAccountWithdraw() {
    BankAccount test = new BankAccount("sdewf542ds", 250);
    try {
      test.withdraw(100);
    } catch (IllegalStateException e) {
      return false;
    } catch (DataFormatException e) {
      return false;
    }

    if(test.getBalance() == 150) {
      return true;
    }
    return false;

  }

  
  /**
   * Calls the different test methods
   * 
   * @param args - input arguments
   */
  public static void main(String[] args) {
    System.out.println(
            "~Invalid initial balance: " + testBankAccountConstructorNotValidInitialBalance());
    System.out.println(
            "-------------------------------------------------------------------------------");
    System.out
            .println("~Valid initial balance: " + testBankAccountConstructorValidInitialBalance());
    System.out.println(
            "-------------------------------------------------------------------------------");
    System.out.println("~BankAccount equals: " + testBankAccountEquals());
    System.out.println(
            "-------------------------------------------------------------------------------");
    System.out.println("~Withdraw invalid amount: " + testBankAccountWithdrawInvalidAmount());
    System.out.println(
            "-------------------------------------------------------------------------------");
    System.out
            .println("~Withdraw too much money: " + testBankAccountWithdrawLargerOfBalanceAmount());
    System.out.println(
            "-------------------------------------------------------------------------------");
    System.out.println("~Withdraw valid amount: " + testBankAccountWithdrawValidAmount());
    System.out.println(
            "-------------------------------------------------------------------------------");
    System.out.println("~Deposit negative amount: " + testBankAccountDepositNegativeAmount());
    System.out.println(
            "-------------------------------------------------------------------------------");
    System.out.println("~Valid constructor: " + testBankAccountConstructor());
    System.out.println(
            "-------------------------------------------------------------------------------");
    System.out.println("~Deposit valid amount: " + testBankAccountDeposit());
    System.out.println(
            "-------------------------------------------------------------------------------");
    System.out.println("~Withdraw valid amount: " + testBankAccountWithdraw());

  }
}
