
// Title:           (descriptive title of the program making use of this file)
// Files:           (a list of all source files used by that program)
// Course:          (CS300, Fall, 2019)
//
// Author:          Ariel Fu
// Email:           afu5@wisc.edu
// Lecturer's Name: Mouna 
//
import java.util.Scanner;

public class CalendarPrinter {
  private final static String[] DAYS_OF_WEEK = { "MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN" };
  private final static String[] MONTHS_OF_YEAR = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL",
      "AUG", "SEP", "OCT", "NOV", "DEC" };

  public static void main(String[] args) {
    System.out.println("Welcome to the Calendar Printer.");
    System.out.println("================================");
    Scanner sc = new Scanner(System.in);
    String stringInputed = "";

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
    while (x >= 99) {
      x %= 1000;
    }
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
    int y = Integer.parseInt(yearString);
    if(y % 4 != 0) {
      return false;
    } else if(y % 100 != 0) {
      return true;
    } else if(y % 400 != 0) {
      return false;
    } else {
      return true;
    }
  }

  // Note implementation tips in Appendix I below.
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
    month = month.substring(0, 2);
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
      if(getIsLeapYear(year)) {
        return 29;
      } else {
        return 28;
      }
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
    int J = yearInt / 100;
    int K = yearInt % 100;
    int m = getMonthIndex(month);
    int h = (1 + ((13*(m+1))/5) + K + (K/4) + (J/4) + 5*J) % 7;
    return h;
  }

  // Note implementation tips in Appendix I below.
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
    
  }

}
