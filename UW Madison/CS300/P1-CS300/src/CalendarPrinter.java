
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Calendar printer taking in a month and a year as input
// Files:           (CalendarPrinter.class)
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

import java.util.Scanner;

public class CalendarPrinter {
  private final static String[] DAYS_OF_WEEK = { "MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN" };
  private final static String[] MONTHS_OF_YEAR = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL",
      "AUG", "SEP", "OCT", "NOV", "DEC" };

  public static void main(String[] args) {
    System.out.println("Welcome to the Calendar Printer.");
    System.out.println("================================");
    Scanner sc = new Scanner(System.in);
    String month = "";
    String year = "";
    System.out.print("Enter the month to print: ");
    month = sc.nextLine();
    System.out.print("Enter the year to print: ");
    year = sc.nextLine();

    String[][] cal = generateCalendar(month, year);
    for(int i = 0; i < cal.length; i++) {
      for(int j = 0; j < cal[0].length; j++) {
        // one of the weekdays will have one space
        if(cal[i][j].length() == 3) {
          System.out.print(" ");
        } else {
          // numbers or empty spaces will have two spaces
          System.out.print("  ");
        }
        System.out.print(" " + cal[i][j]);
        // single numbers or empty spaces will have add. space at end
        if(cal[i][j].length() == 1) {
          System.out.print(" ");
        }

      }
      System.out.println();
    }
    System.out.println("================================");
    System.out.println("Thanks, and have a nice day.");

  }

  /**
   * Calculates the number of centuries (rounded down) that is represented by the
   * specified year (ie. the integer part of year/100).
   * 
   * @param year to compute the century of (based on the Gregorian Calendar AD)
   *             String must contain the digits of a single non-negative int for
   *             year.
   * @return number of centuries in the specified year
   */
  public static int getCentury(String year) {
    int yearInt = Integer.parseInt(year);
    int centuryRounded = yearInt / 100;
    return centuryRounded;

  }

  /**
   * Calculates the number of years between the specified year, and the first year
   * in the specified year's century. This number is always between 0 - 99.
   * 
   * @param year to compute the year within century of (Gregorian Calendar AD)
   *             String must contain the digits of a single non-negative int for
   *             year.
   * @return number of years since first year in the current century
   */
  public static int getYearWithinCentury(String year) {
    int x = Integer.parseInt(year);
    x %= 100;
    return x;
  }

  /**
   * This method computes whether the specified year is a leap year or not.
   * 
   * @param yearString is the year that is being checked for leap-year-ness String
   *                   must contain the digits of a single non-negative int for
   *                   year.
   * @return true when the specified year is a leap year, and false otherwise
   */
  public static boolean getIsLeapYear(String yearString) {
    int year = Integer.parseInt(yearString);
    // divide by 4 - not leap yr
    if(year % 4 != 0) {
      return false;
    } else if(year % 100 != 0) {
      return true; // divide by 100 - leap yr
    } else if(year % 400 != 0) {
      return false; // divide by 400 - not leap yr
    } else {
      return true; // otherwise leap yr
    }
  }

  /**
   * Converts the name or abbreviation for any month into the index of that
   * month's abbreviation within MONTHS_OF_YEAR. Matches the specified month based
   * only on the first three characters, and is case in-sensitive.
   * 
   * @param month which may or may not be abbreviated to 3 or more characters
   * @return the index within MONTHS_OF_YEAR that a match is found at and returns
   *         -1, when no match is found
   */
  public static int getMonthIndex(String month) {
    // gets the substring of the first three characters
    month = month.substring(0, 3);
    if(month.equalsIgnoreCase(MONTHS_OF_YEAR[0])) {
      return 13;
    } else if(month.equalsIgnoreCase(MONTHS_OF_YEAR[1])) {
      return 14;
    } else if(month.equalsIgnoreCase(MONTHS_OF_YEAR[2])) {
      return 3;
    } else if(month.equalsIgnoreCase(MONTHS_OF_YEAR[3])) {
      return 4;
    } else if(month.equalsIgnoreCase(MONTHS_OF_YEAR[4])) {
      return 5;
    } else if(month.equalsIgnoreCase(MONTHS_OF_YEAR[5])) {
      return 6;
    } else if(month.equalsIgnoreCase(MONTHS_OF_YEAR[6])) {
      return 7;
    } else if(month.equalsIgnoreCase(MONTHS_OF_YEAR[7])) {
      return 8;
    } else if(month.equalsIgnoreCase(MONTHS_OF_YEAR[8])) {
      return 9;
    } else if(month.equalsIgnoreCase(MONTHS_OF_YEAR[9])) {
      return 10;
    } else if(month.equalsIgnoreCase(MONTHS_OF_YEAR[10])) {
      return 11;
    } else if(month.equalsIgnoreCase(MONTHS_OF_YEAR[11])) {
      return 12;
    } else {
      return -1;
    }
  }

  /**
   * Calculates the number of days in the specified month, while taking into
   * consideration whether or not the specified year is a leap year.
   * 
   * @param month which may or may not be abbreviated to 3 or more characters
   * @param year  of month that days are being counted for (Gregorian Calendar AD)
   *              String must contain the digits of a single non-negative int for
   *              year.
   * @return the number of days in the specified month (between 28-31)
   */
  public static int getNumberOfDaysInMonth(String month, String year) {
    int monthIndex = getMonthIndex(month);
    if(monthIndex == 13) {
      return 31;
    } else if(monthIndex == 14) {
      // exception of leap year
      if(getIsLeapYear(year)) {
        return 29;
      } else {
        return 28;
      }
      // rest of year
    } else if(monthIndex == 3) {
      return 31;
    } else if(monthIndex == 4) {
      return 30;
    } else if(monthIndex == 5) {
      return 31;
    } else if(monthIndex == 6) {
      return 30;
    } else if(monthIndex == 7) {
      return 31;
    } else if(monthIndex == 8) {
      return 31;
    } else if(monthIndex == 9) {
      return 30;
    } else if(monthIndex == 10) {
      return 31;
    } else if(monthIndex == 11) {
      return 30;
    } else if(monthIndex == 12) {
      return 31;
    } else {
      return -1;
    }
  }

  /**
   * Calculates the index of the first day of the week in a specified month. The
   * index returned corresponds to position of this first day of the week within
   * the DAYS_OF_WEEK class field.
   * 
   * @param month which may or may not be abbreviated to 3 or more characters
   * @param year  of month to determine the first day from (Gregorian Calendar AD)
   *              String must contain the digits of a single non-negative int for
   *              year.
   * @return index within DAYS_OF_WEEK of specified month's first day
   */
  public static int getFirstDayOfWeekInMonth(String month, String year) {
    int yearInt = Integer.parseInt(year);
    int monthIndex = getMonthIndex(month);
    // jan + feb are months 13 & 14 of last year
    if(monthIndex == 13 || monthIndex == 14) {
      yearInt -= 1;
    }
    int zeroBasedCentury = yearInt / 100;
    int yearOfCentury = getYearWithinCentury(yearInt + "");

    int firstDate = (13 * (monthIndex + 1)) / 5;
    firstDate += 1;
    firstDate += yearOfCentury;
    firstDate += (yearOfCentury / 4);
    firstDate += (zeroBasedCentury / 4);
    firstDate += (5 * zeroBasedCentury);
    firstDate %= 7;
    // convert from 0=Sat to 5=Sat
    firstDate += 5;
    firstDate %= 7;

    return firstDate;
  }

  /**
   * Creates and initializes a 2D String array to reflect the specified month. The
   * first row of this array [0] should contain labels representing the days of
   * the week, starting with Monday, as abbreviated in DAYS_OF_WEEK. Every later
   * row should contain dates under the corresponding days of week. Entries with
   * no corresponding date in the current month should be filled with a single
   * period. There should not be any extra rows that are either blank, unused, or
   * completely filled with periods. For example, the contents for September of
   * 2019 should look as follows, where each horizontal row is stored in different
   * array within the 2d result:
   *
   * MON TUE WED THU FRI SAT SUN . . . . . . 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
   * 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 . . . . . .
   *
   * @param month which may or may not be abbreviated to 3 or more characters
   * @param year  of month generate calendar for (Gregorian Calendar AD) String
   *              must contain the digits of a single non-negative int for year.
   * @return 2d array of strings depicting the contents of a calendar
   */
  public static String[][] generateCalendar(String month, String year) {
    String[][] calendar;
    String empty = ".";
    int date = 0;
    int numOfDays = getNumberOfDaysInMonth(month, year);

    int firstDay = getFirstDayOfWeekInMonth(month, year);

    // rows in arr. are based on what day it starts and number of days in the year
    if((firstDay + numOfDays) < 35) {
      calendar = new String[6][7];
    } else {
      calendar = new String[7][7];
    }

    // set the "header" of week days before dates
    for(int i = 0; i < calendar[0].length; i++) {
      calendar[0][i] = DAYS_OF_WEEK[i];
    }

    // set the first week from starting date to last day in first week
    for(int i = firstDay; i < 7; i++) {
      date++;
      calendar[1][i] = "" + date;
    }

    for(int i = 2; i < calendar.length; i++) {
      for(int j = 0; j < calendar[0].length; j++) {
        date++;
        if(date <= numOfDays) {
          calendar[i][j] = "" + date;
        } else {
          calendar[i][j] = empty;
        }
      }
    }

    // null spots are filled with .
    for(int i = 1; i < calendar.length; i++) {
      for(int j = 0; j < calendar[0].length; j++) {
        if(calendar[i][j] == null) {
          calendar[i][j] = empty;
        }
      }
    }

    return calendar;

  }

}
