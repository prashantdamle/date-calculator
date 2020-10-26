package com.myapps.datecalculator.util;

import com.myapps.datecalculator.date.Date;

import static com.myapps.datecalculator.constant.Constant.*;

/**
 * A suite of utilities surrounding the use of the Date object.
 *
 * <p>DateUtils contains some common methods considering manipulations of Dates.
 */
public class DateUtils {

  /**
   * Returns full days between two given dates. The first and the last dates are considered partial
   * dates hence both dates are excluded from the calculation.
   *
   * @param firstDate A from date object. This need not be older than the to date object.
   * @param secondDate A to date object. This need not be newer than the from date object.
   * @return Total number of full days between the two given dates.
   */
  public static int getFullDaysBetween(final Date firstDate, final Date secondDate) {
    // Perform null check
    if (firstDate == null || secondDate == null) {
      throw new IllegalArgumentException("Dates must not be null!");
    }

    // Return 0 if both dates are same
    if (firstDate.compareTo(secondDate) == 0) {
      return 0;
    }

    final Date olderDate;
    final Date newerDate;

    // Find out if firstDate is newer than secondDate
    if (firstDate.compareTo(secondDate) < 0) {
      olderDate = firstDate;
      newerDate = secondDate;
    } else {
      olderDate = secondDate;
      newerDate = firstDate;
    }

    // If both dates are in the same year, subtracting day of the year of older date from that of
    // the newer date will give us days between the dates
    if (olderDate.getYear() == newerDate.getYear()) {
      int daysInBetween = newerDate.getDayOfTheYear() - olderDate.getDayOfTheYear();

      // For full days subtract 1 from daysInBetween
      return daysInBetween - 1;
    }

    return calculateFullDaysBetween(olderDate, newerDate);
  }

  /**
   * Returns true if the given year is a leap year.
   *
   * @param year Year as an integer value.
   * @return True if the given integer year is a leap year.
   */
  public static boolean isLeapYear(final int year) {
    // If year is not evenly divisible by 4, then it is NOT a leap year
    if (year % LEAP_YEAR_DIVIDER_4 != 0) {
      return false;
    }
    // If year is evenly divisible by 4 but NOT evenly divisible by 100, then it is a leap year
    if (year % LEAP_YEAR_DIVIDER_100 != 0) {
      return true;
    }
    // If year is evenly divisible by 4 and 100, but also evenly divisible by 400, then it is a
    // leap year
    return year % LEAP_YEAR_DIVIDER_400 == 0;
  }

  private static int calculateFullDaysBetween(final Date olderDate, final Date newerDate) {
    int fullDaysBetween = 0;

    // Add number of days from full years between the two dates
    fullDaysBetween += getFullDaysBetweenFullYears(olderDate, newerDate);

    // Now, add full days from the older year
    fullDaysBetween += (olderDate.getDaysThisYear() - olderDate.getDayOfTheYear());

    // Also add full days from the newer year
    fullDaysBetween += (newerDate.getDayOfTheYear() - 1);

    return fullDaysBetween;
  }

  private static int getFullDaysBetweenFullYears(final Date olderDate, final Date newerDate) {
    // Check if there are any full years in between, if not return 0
    if (newerDate.getYear() - olderDate.getYear() <= 1) {
      return 0;
    }

    // Calculate full years in days
    int fullYearsInDays = 0;
    for (int year = olderDate.getYear() + 1; year < newerDate.getYear(); year++) {
      if (isLeapYear(year)) {
        fullYearsInDays += DAYS_IN_A_LEAP_YEAR;
      } else {
        fullYearsInDays += DAYS_IN_A_NON_LEAP_YEAR;
      }
    }

    return fullYearsInDays;
  }
}
