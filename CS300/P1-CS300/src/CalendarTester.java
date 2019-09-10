
public class CalendarTester {

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

    return true;
  }
  
  
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
