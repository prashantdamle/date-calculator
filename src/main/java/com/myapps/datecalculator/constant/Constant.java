package com.myapps.datecalculator.constant;

/** A class to store common constants */
public class Constant {
  // Regular expression for date format yyyy-MM-dd
  public static final String DATE_FORMAT_REGEX = "([0-9]{4})-([0-9]{2})-([0-9]{2})";

  // Days in a year
  public static final int DAYS_IN_A_LEAP_YEAR = 366;
  public static final int DAYS_IN_A_NON_LEAP_YEAR = 365;

  // Used for leap year calculation
  public static final int LEAP_YEAR_DIVIDER_4 = 4;
  public static final int LEAP_YEAR_DIVIDER_100 = 100;
  public static final int LEAP_YEAR_DIVIDER_400 = 400;

  // Valid user inputs
  public static final String YES = "Y";
  public static final String NO = "N";

  // Error messages
  public static final String INVALID_ANSWER_ERROR = "Invalid answer. Please enter 'Y' or 'N'...\n";
}
