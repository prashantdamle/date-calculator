package com.myapps.datecalculator.util;

import com.myapps.datecalculator.date.Date;
import com.myapps.datecalculator.exception.InvalidDateException;
import com.myapps.datecalculator.exception.InvalidDateFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateUtilsTest {

  @Test
  public void testIsLeapYearShouldReturnFalseWhenYearIsNotEvenlyDivisibleBy4() {
    assertFalse(DateUtils.isLeapYear(1945));
  }

  @Test
  public void testIsLeapYearShouldReturnFalseWhenYearIsEvenlyDivisibleBy4And100ButNotBy400() {
    assertFalse(DateUtils.isLeapYear(1900));
  }

  @Test
  public void testIsLeapYearShouldReturnTrueWhenYearIsEvenlyDivisibleBy4ButNotBy100() {
    assertTrue(DateUtils.isLeapYear(1944));
  }

  @Test
  public void testIsLeapYearShouldReturnTrueWhenYearIsEvenlyDivisibleBy4And100And400() {
    assertTrue(DateUtils.isLeapYear(2000));
  }

  @Test
  public void
      testGetFullDaysBetweenShouldThrowIllegalArgumentExceptionWhenEitherOfTheArgumentsIsNull()
          throws InvalidDateFormatException, InvalidDateException {
    Date date = new Date("1945-02-15");

    IllegalArgumentException exception1 =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              DateUtils.getFullDaysBetween(null, null);
            });
    assertTrue(exception1.getMessage().contains("Dates must not be null!"));

    IllegalArgumentException exception2 =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              DateUtils.getFullDaysBetween(null, date);
            });
    assertTrue(exception2.getMessage().contains("Dates must not be null!"));

    IllegalArgumentException exception3 =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              DateUtils.getFullDaysBetween(date, null);
            });
    assertTrue(exception3.getMessage().contains("Dates must not be null!"));
  }

  @Test
  public void testGetFullDaysBetweenShouldReturn0WhenBothDatesAreSame() {
    try {
      Date date1 = new Date("1945-02-15");
      Date date2 = new Date("1945-02-15");

      assertEquals(0, DateUtils.getFullDaysBetween(date1, date2));
    } catch (InvalidDateFormatException | InvalidDateException e) {
      fail("Possible code smell! Exception was not expected.");
    }
  }

  @Test
  public void testGetFullDaysBetweenShouldReturn0WhenDatesDifferByOneDay() {
    try {
      Date date1 = new Date("1945-02-14");
      Date date2 = new Date("1945-02-15");

      assertEquals(0, DateUtils.getFullDaysBetween(date1, date2));
      assertEquals(0, DateUtils.getFullDaysBetween(date2, date1));
    } catch (InvalidDateFormatException | InvalidDateException e) {
      fail("Possible code smell! Exception was not expected.");
    }
  }

  @Test
  public void testGetFullDaysBetweenShouldReturn365WhenDatesDifferByOneYearAndOneDay() {
    try {
      Date date1 = new Date("1945-02-15");
      Date date2 = new Date("1946-02-16");

      assertEquals(365, DateUtils.getFullDaysBetween(date1, date2));
      assertEquals(365, DateUtils.getFullDaysBetween(date2, date1));
    } catch (InvalidDateFormatException | InvalidDateException e) {
      fail("Possible code smell! Exception was not expected.");
    }
  }

  @Test
  public void
      testGetFullDaysBetweenShouldReturn366WhenDatesDifferByOneYearAndOneDayAndTheRangeCoversALeapYear() {
    try {
      Date date1 = new Date("1944-02-15");
      Date date2 = new Date("1945-02-16");

      assertEquals(366, DateUtils.getFullDaysBetween(date1, date2));
      assertEquals(366, DateUtils.getFullDaysBetween(date2, date1));
    } catch (InvalidDateFormatException | InvalidDateException e) {
      fail("Possible code smell! Exception was not expected.");
    }
  }

  @Test
  public void testGetFullDaysBetweenShouldReturn1825WhenDatesDifferBy5Years() {
    try {
      Date date1 = new Date("1945-02-15");
      Date date2 = new Date("1950-02-15");
      Date date3 = new Date("1955-02-15");

      assertEquals(1825, DateUtils.getFullDaysBetween(date1, date2));
      assertEquals(1825, DateUtils.getFullDaysBetween(date2, date1));

      assertEquals(1825, DateUtils.getFullDaysBetween(date2, date3));
      assertEquals(1825, DateUtils.getFullDaysBetween(date3, date2));
    } catch (InvalidDateFormatException | InvalidDateException e) {
      fail("Possible code smell! Exception was not expected.");
    }
  }
}
