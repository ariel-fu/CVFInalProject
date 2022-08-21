
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Calendar printer taking in a month and a year as input
// Files:           (CalendarPrinter.class, CalendarPrinter.java, CalendarTester.class)
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
// Online Sources:  1.Google calendar (https://calendar.google.com/calendar/r/month/2019/7/1)
//               
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * @author Ariel
 *
 */
public class CalendarTester {

  /**
   * Tests all methods made in CalendarPrinter.java
   * If one of the method's output is incorrect, it will print out false
   * Prints out true if the method passes the test
   * NO parameters
   * NO return statements
   *
   */
  public static void main(String[] args) {
    boolean result;
    result = testGenerateCalendar();
    System.out.println(result);
    
    result = testGetCentury();
    System.out.println(result);
    
    result = testGetFirstDayOfWeekInMonth();
    System.out.println(result);
    
    result = testGetIsLeapYear();
    System.out.println(result);
    
    result = testGetMonthIndex();
    System.out.println(result);
    
    result = testGetNumberOfDaysInMonth();
    System.out.println(result);
    
    result = testGetYearWithinCentury();
    System.out.println(result);
  }


  /**
   * Tests getCentury() from CalendarPrinter.java
   * test 4 different scenarios: 0, middle year, year close to 0, a year far from 0
   * @param none
   * @return true if passes the test, false if it fails
   *
   */
  public static boolean testGetCentury() {
    //0
    if(CalendarPrinter.getCentury("2") != 0) {
      return false;
    }
    //average year
    if(CalendarPrinter.getCentury("2019") != 20) {
      return false;
    }
    //small year
    if(CalendarPrinter.getCentury("512") != 5) {
      return false;
    }
    //large year
    if(CalendarPrinter.getCentury("44444") != 444) {
      return false;
    }

    return true;
  }

  /**
   * Tests getYearWithinCentury from CalendarPrinter.java
   * test 4 different scenarios: a middle year, 0, large year, small year
   * @param none
   * @return true if passes the test, false if it fails
   *
   */
  public static boolean testGetYearWithinCentury() {
    //normal date
    if(CalendarPrinter.getYearWithinCentury("2001") != 1) {
      return false;
    }
    //0
    if(CalendarPrinter.getYearWithinCentury("2000") != 0) {
      return false;
    }
    //large number
    if(CalendarPrinter.getYearWithinCentury("1952") != 52) {
      return false;
    }
    //small date
    if(CalendarPrinter.getYearWithinCentury("512") != 12) {
      return false;
    }

    return true;
  }


  /**
   * Tests getIsLeapYear() from CalendarPrinter.java
   * tests 4 different scenarios: not leap year, leap year that is divisible, and common year that is divisible
   * @param none
   * @return true if passes the test, false if it fails
   *
   */
  public static boolean testGetIsLeapYear() {
    // not leap year
    if(CalendarPrinter.getIsLeapYear("1995")) {
      return false;
    }
    // divide by 100
    if(CalendarPrinter.getIsLeapYear("1900")) {
      return false;
    }
    // divide by 4
    if(!CalendarPrinter.getIsLeapYear("4")) {
      return false;
    }
    // divide by 400
    if(!CalendarPrinter.getIsLeapYear("1600")) {
      return false;
    }
    return true;
  }


  /**
   * Tests getMonthIndex() from CalendarPrinter.java
   * has 4 different scenarios:
   * beginning of the year (last month of last year)
   * actual beginning of the year (march bc FEB + JAN are the last months of last year)
   * a month in the middle of the year
   * and an invalid input
   * @param none
   * @return true if passes the test, false if it fails
   *
   */
  public static boolean testGetMonthIndex() {
    //beginning
    if(CalendarPrinter.getMonthIndex("january") != 13) {
      return false;
    }
    //beginning
    if(CalendarPrinter.getMonthIndex("march") != 3) {
      return false;
    }
    //end
    if(CalendarPrinter.getMonthIndex("december") != 12) {
      return false;
    }
    //middle
    if(CalendarPrinter.getMonthIndex("junee") != 6) {
      return false;
    }
    //invalid input
    if(CalendarPrinter.getMonthIndex("heesl") != -1) {
      return false;
    }
    return true;
  }

  /**
   * Tests getNumberOfDaysInMonth() from CalendarPrinter.java
   * has 4 different scenarios:
   * a month containing 31 days, a month containing 30 days, a leap year,
   * and a non leap year
   * @param none
   * @return true if passes the test, false if it fails
   *
   */
  public static boolean testGetNumberOfDaysInMonth() {
    //31 days
    if(CalendarPrinter.getNumberOfDaysInMonth("Jan", "2020") != 31) {
      return false;
    }
    //leap year
    if(CalendarPrinter.getNumberOfDaysInMonth("Feb", "2020") != 29) {
      return false;
    }
    //not a leap year
    if(CalendarPrinter.getNumberOfDaysInMonth("Feb", "2019") != 28) {
      return false;
    }
    //30 days
    if(CalendarPrinter.getNumberOfDaysInMonth("Sept", "2020") != 30) {
      return false;
    }
    //invalid input
    if(CalendarPrinter.getNumberOfDaysInMonth("wefs", "1596") !=-1) {
      return false;
    }
    return true;
  }
 

  /**
   * Tests getCentury() from CalendarPrinter.java
   * tests 6 different scenarios
   * @param none
   * @return true if passes the test, false if it fails
   *
   */
  public static boolean testGetFirstDayOfWeekInMonth() {
    //past, middle day
    if(CalendarPrinter.getFirstDayOfWeekInMonth("may", "1975") != 3) {
      return false;
    }
    //really far past
    if(CalendarPrinter.getFirstDayOfWeekInMonth("jan", "1431") != 5) {
      return false;
    }
    //leap year + future
    if(CalendarPrinter.getFirstDayOfWeekInMonth("feb", "2020") != 5) {
      return false;
    }
    //first day
    if(CalendarPrinter.getFirstDayOfWeekInMonth("april", "2019") != 0) {
      return false;
    }
    //really far future
    if(CalendarPrinter.getFirstDayOfWeekInMonth("aug", "2905") != 5) {
      return false;
    }
    //less than 4 digits
    if(CalendarPrinter.getFirstDayOfWeekInMonth("aug", "521") != 4) {
      return false;
    }

    return true;
  }
  

  /**
   * Tests getCentury() from CalendarPrinter.java
   * tests a variety of different scenarios:
   * the length, the last significant element of the array, the first significant element of the array,
   * and the a significant element in the middle of the array.
   * @param none
   * @return true if passes the test, false if it fails
   *
   */
  public static boolean testGenerateCalendar() {
    String[][] test = CalendarPrinter.generateCalendar("Feb", "2020");
    //length
    if(test.length!=6) {
      return false;
    }
    //end
    if(!test[5][5].equals("29")) {
      return false;
    }
    //beginning
    if(!test[1][5].equals("1")) {
      return false;
    }
    //middle
    if(!test[2][5].equals("8")) {
      return false;
    }
    return true;
  }

}
