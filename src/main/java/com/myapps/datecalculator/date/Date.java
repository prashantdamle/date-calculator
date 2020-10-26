package com.myapps.datecalculator.date;

import com.myapps.datecalculator.constant.Constant;
import com.myapps.datecalculator.exception.InvalidDateException;
import com.myapps.datecalculator.exception.InvalidDateFormatException;
import com.myapps.datecalculator.util.DateUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.myapps.datecalculator.constant.Constant.DAYS_IN_A_LEAP_YEAR;
import static com.myapps.datecalculator.constant.Constant.DAYS_IN_A_NON_LEAP_YEAR;
import static com.myapps.datecalculator.date.Date.Month.fromInt;
import static java.lang.Integer.compare;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.util.regex.Pattern.compile;
import static org.apache.commons.lang3.StringUtils.capitalize;

/**
 * A class that represents a date in yyyy-MM-dd format. It provides few helper methods like date
 * comparison and days in a month or year.
 */
@Getter
@EqualsAndHashCode
public class Date implements Comparable<Date> {

  private static final Pattern DATE_PATTERN = compile(Constant.DATE_FORMAT_REGEX);

  private final int year;
  private final Month month;
  private final int day;

  private final boolean isLeapYear;

  /**
   * Creates a date object from the given string in the format yyyy-MM-dd.
   *
   * @param date A date string in the format yyyy-MM-dd
   * @throws InvalidDateFormatException Thrown if the given date string is not in the format
   *     yyyy-MM-dd.
   * @throws InvalidDateException Thrown if the given date string is an invalid date, or it is
   *     outside the supported date range {@link SupportedYearRange#YEAR_MIN} and {@link
   *     SupportedYearRange#YEAR_MAX}.
   */
  public Date(final String date) throws InvalidDateFormatException, InvalidDateException {
    final Matcher matcher = DATE_PATTERN.matcher(date);

    // Check if the given date string format is valid
    if (!matcher.matches()) {
      throw new InvalidDateFormatException();
    }

    // Parse date string and validate
    try {
      // Parse year and validate if it is supported within range
      this.year = parseInt(matcher.group(1));
      validateYear();

      // Set leap year flag
      this.isLeapYear = DateUtils.isLeapYear(this.year);

      // Parse month and check if it is valid
      this.month = fromInt(parseInt(matcher.group(2)));
      validateMonth();

      // Parse day and check if it is valid
      this.day = parseInt(matcher.group(3));
      validateDay();
    } catch (final NumberFormatException e) {
      throw new InvalidDateFormatException(e);
    }
  }

  private void validateYear() throws InvalidDateException {
    if (this.year < SupportedYearRange.YEAR_MIN.getExtrema()
        || this.year > SupportedYearRange.YEAR_MAX.getExtrema()) {
      throw new InvalidDateException("Invalid date! Year must be between 1901-2999.");
    }
  }

  private void validateMonth() throws InvalidDateException {
    // A month would not have been assigned if it falls outside valid month range
    if (this.month == null) {
      throw new InvalidDateException("Invalid date! Month must be between 01-12.");
    }
  }

  private void validateDay() throws InvalidDateException {
    if (this.day < 1) {
      throw new InvalidDateException("Invalid date! Day must be more than 0.");
    }

    int daysThisMonth = this.getDaysThisMonth();

    if (this.day > daysThisMonth) {
      throw new InvalidDateException(
          format(
              "Invalid date! There cannot be more than %s days in the month of %s of year %d",
              daysThisMonth, capitalize(this.month.toString().toLowerCase()), this.year));
    }
  }

  /**
   * Returns the total number of days in this year.
   *
   * @return Total number of days in this year.
   */
  public int getDaysThisYear() {
    return this.isLeapYear ? DAYS_IN_A_LEAP_YEAR : DAYS_IN_A_NON_LEAP_YEAR;
  }

  /**
   * Returns the total number of days in this month.
   *
   * @return Total number of days in this month.
   */
  public int getDaysThisMonth() {
    return this.month.getDays(this.isLeapYear);
  }

  /**
   * Returns the date as an integer value representing the day as a number within this year.
   * Possible values range from 1 to 366.
   *
   * @return An integer value representing the day as a number within this year.
   */
  public int getDayOfTheYear() {
    int dayOfTheYear = this.getDay();

    // Though Enum.values() returns enum values in their natural order, we are sorting them by their
    // value to ensure any future reordering of values in Month enum doesn't affect this method's
    // logic
    Date.Month[] months = Date.Month.values();
    Arrays.sort(months, Comparator.comparing(Date.Month::getValue));

    for (final Date.Month month : months) {
      if (month != this.getMonth()) {
        dayOfTheYear += month.getDays(this.isLeapYear);
        continue;
      }
      break;
    }
    return dayOfTheYear;
  }

  @Override
  public int compareTo(final Date date) {
    // Return 0 if both dates are the same
    if (this.equals(date)) {
      return 0;
    }

    // If years are same, simply compare day of the year
    if (this.year == date.getYear()) {
      return compare(this.getDayOfTheYear() - date.getDayOfTheYear(), 0);
    }

    // Return -1 if current object's year is older than supplied date's year
    if (this.year < date.getYear()) {
      return -1;
    }

    // Return 1 as current object's year is newer than supplied date's year
    return 1;
  }

  @Override
  public String toString() {
    // Return the date as a string in yyyy-MM-dd format
    return String.format("%04d-%02d-%02d", this.year, this.month.getValue(), this.day);
  }

  @RequiredArgsConstructor
  @Getter
  private enum SupportedYearRange {
    YEAR_MIN(1901),
    YEAR_MAX(2999);

    private final int extrema;
  }

  @RequiredArgsConstructor
  public enum Month {
    JANUARY(1, 31),
    FEBRUARY(2, 28),
    MARCH(3, 31),
    APRIL(4, 30),
    MAY(5, 31),
    JUNE(6, 30),
    JULY(7, 31),
    AUGUST(8, 31),
    SEPTEMBER(9, 30),
    OCTOBER(10, 31),
    NOVEMBER(11, 30),
    DECEMBER(12, 31);

    @Getter private final int value;
    private final int days;

    public static Month fromInt(final int month) {
      for (final Month monthValue : Month.values()) {
        if (month == monthValue.getValue()) {
          return monthValue;
        }
      }
      // Return null if the given integer value fall outside the month value range
      return null;
    }

    public int getDays(final boolean isLeapYear) {
      if (this == FEBRUARY && isLeapYear) {
        return 29;
      }
      return this.days;
    }
  }
}
