package model;

import java.time.LocalDate;

/**
 * A class representing date
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class Date
{
  private int day;
  private int month;
  private int year;

  /**
   *  0-argument constructing setting the date to current date
   */

  public Date()
  {
    LocalDate today = LocalDate.now();
    this.day = today.getDayOfMonth();
    this.month = today.getMonthValue();
    this.year = today.getYear();
  }

  /**
   * 3-arguments constructor initializing variables with use of set method
   * @param day the day
   * @param month the month (number between 1-12)
   * @param year the year
   */

  public Date(int day, int month, int year)
  {
    set(day, month, year);
  }

  /**
   * Set method used in constructor to set all instance variables and validate the parameters
   * @param day the day
   * @param month the month(number between 1-12)
   * @param year the year
   * @throws IllegalArgumentException if month is not number between 1-12, if day is negative number or is a number bigger than number of days in passed month, if year is smaller than current year
   * or if date we are creating is Saturday or Sunday (bookings cannot be done on these days)
   */

  public void set(int day, int month, int year)
  {

    if (dayOfWeek(day,month,year).equals("Saturday") || dayOfWeek(day,month,year).equals("Sunday"))
    {
      throw new IllegalArgumentException("You cannot make bookings on weekends");
    }
    if (year < LocalDate.now().getYear())
    {
      throw new IllegalArgumentException("Invalid year, u can only add booking in the current year");
    }
    this.year = year;

    if (month < 1 || month > 12)
    {
      throw new IllegalArgumentException("Invalid number of the month");
    }
    this.month = month;

    if (day < 1 || day > daysInMonth())
    {
      throw new IllegalArgumentException("Invalid number of a day");
    }

    this.day = day;
  }

  /**
   * Method returning if the year is leap year
   * @return true if year is leap year, otherwise false
   */
  private boolean isLeapYear()
  {
    return this.year % 4 == 0 && (this.year % 100 != 0 || this.year % 400 == 0);
  }

  /**
   * Method returning number of days in particular month
   * @return number of days in month of the date
   */

  private int daysInMonth()
  {
    switch (this.month)
    {

      case 2:
        if(this.isLeapYear())
        {
          return 29;
        }
        else
        {
          return 28;
        }
      case 4:
      case 6:
      case 9:
      case 11:
        return 30;
      default:
        return 31;
    }
  }

  /**
   * Getter for day
   * @return day of the date
   */

  public int getDay()
  {
    return day;
  }

  /**
   * Getter for month
   * @return month of the date
   */

  public int getMonth()
  {
    return month;
  }

  /**
   * Getter for year
   * @return year of the date
   */
  public int getYear()
  {
    return year;
  }

  /**
   * Method calculating day of the week based on day, month and year
   * @param day the day
   * @param month the month
   * @param year the year
   * @return day of the week of particular date (Monday, Tuesday, Wednesday, Thursday, Friday, Saturday or Sunday)
   */
  public static String dayOfWeek(int day, int month, int year)
  {
    int q = day;
    int m,k,j,h,y;
    if (month == 1 || month == 2)
    {
      m = month + 12;
      y = year - 1;
    }
    else
    {
      m = month;
      y = year;
    }
    k = y % 100;
    j = y / 100;
    h = (q + ((13*(m+1))/5) + k + (k/4) + (j/4) + 5*j)%7;

    switch (h)
    {
      case 0:
        return "Saturday";
      case 1:
        return "Sunday";
      case 2:
        return "Monday";
      case 3:
        return "Tuesday";
      case 4:
        return "Wednesday";
      case 5:
        return "Thursday";
      default:
        return "Friday";
    }
  }

  /**
   *  Checking if the date is before other date
   * @param other other date with which method makes comparison
   * @return true if date is before other date, otherwise false
   */
  public boolean isBefore(Date other)
  {
    if (this.year < other.year)
    {
      return true;
    }
    else if (this.year > other.year)
    {
      return false;
    }
    else
    {
      if (this.month < other.month)
      {
        return true;
      }
      else if (this.month > other.month)
      {
        return false;
      }
      else
      {
        if (this.day < other.day)
        {
          return true;
        }
        else if (this.day > other.day)
        {
          return false;
        }
        else
        {
          return false;
        }
      }
    }
  }

  /**
   * Incrementing the date by one day
   */
  public void stepForwardOneDay()
  {
    this.day += 1;
    if (this.day > this.daysInMonth())
    {
      this.day = 1;
      this.month += 1;
      if (this.month > 12)
      {
        this.month = 1;
        this.year += 1;
      }
    }
  }

  /**
   * Incrementing the date by <code>days</code> days
   * @param days number of days
   */
  public void stepForward(int days)
  {
    for(int i = 0; i<days; i++)
    {
      this.stepForwardOneDay();
    }
  }

  /**
   * Comparing date and <code>obj</code> Object whether they are equal
   * @param obj object that will be compared with date
   * @return true if date and obj are equal, otherwise false
   */
  public boolean equals(Object obj)
  {
    if(!(obj instanceof Date))
    {
      return false;
    }

    Date other = (Date) obj;

    return day == other.day && month == other.month && year == other.year;
  }

  /**
   * Getting copy of the date object
   * @return copy of the date
   */

  public Date copy()
  {
    return new Date(day,month,year);
  }

  /**
   * Creating string representation of the date
   * @return date presented as a string
   */

  public String toString()
  {
    String s = "";
    if (this.day < 10)
    {
      s += 0 + "";
    }
    s += this.day + "/";
    if (this.month < 10)
    {
      s += 0 + "";
    }
    s+= this.month + "/" + this.year;
    return s;
  }
}
