package com.myapps.datecalculator.date;

import com.myapps.datecalculator.exception.InvalidDateException;
import com.myapps.datecalculator.exception.InvalidDateFormatException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateTest {

  @Test
  public void testDateCreationShouldThrowInvalidDateFormatExceptionWhenJunkDataIsSupplied() {
    InvalidDateFormatException exception =
        assertThrows(
            InvalidDateFormatException.class,
            () -> {
              new Date(RandomStringUtils.randomAlphanumeric(10));
            });
    assertTrue(
        exception
            .getMessage()
            .contains(
                "Invalid date format! Date should be in yyyy-MM-dd format. For e.g. 1968-12-18."));
  }

  @Test
  public void
      testDateCreationShouldThrowInvalidDateFormatExceptionWhenDateInUnsupportedFormatIsSupplied() {
    InvalidDateFormatException exception =
        assertThrows(
            InvalidDateFormatException.class,
            () -> {
              new Date("15-02-1945");
            });
    assertTrue(
        exception
            .getMessage()
            .contains(
                "Invalid date format! Date should be in yyyy-MM-dd format. For e.g. 1968-12-18."));
  }

  @Test
  public void testDateCreationShouldThrowInvalidDateExceptionWhenYearIsBefore1901() {
    InvalidDateException exception =
        assertThrows(
            InvalidDateException.class,
            () -> {
              new Date("1900-02-15");
            });
    assertTrue(exception.getMessage().contains("Invalid date! Year must be between 1901-2999."));
  }

  @Test
  public void testDateCreationShouldThrowInvalidDateExceptionWhenYearIsAfter2999() {
    InvalidDateException exception =
        assertThrows(
            InvalidDateException.class,
            () -> {
              new Date("3000-02-15");
            });
    assertTrue(exception.getMessage().contains("Invalid date! Year must be between 1901-2999."));
  }

  @Test
  public void testDateCreationShouldThrowInvalidDateExceptionWhenMonthIsNotBetween01And12() {
    InvalidDateException exception1 =
        assertThrows(
            InvalidDateException.class,
            () -> {
              new Date("1945-00-15");
            });
    assertTrue(exception1.getMessage().contains("Invalid date! Month must be between 01-12."));

    InvalidDateException exception2 =
        assertThrows(
            InvalidDateException.class,
            () -> {
              new Date("1945-13-15");
            });
    assertTrue(exception2.getMessage().contains("Invalid date! Month must be between 01-12."));
  }

  @Test
  public void testDateCreationShouldThrowInvalidDateExceptionWhenDayIsLessThan01() {
    InvalidDateException exception =
        assertThrows(
            InvalidDateException.class,
            () -> {
              new Date("1945-02-00");
            });
    assertTrue(exception.getMessage().contains("Invalid date! Day must be more than 0."));
  }

  @Test
  public void testDateCreationShouldThrowInvalidDateExceptionWhenDayIsMoreThan31InA31DayMonth() {
    InvalidDateException exception1 =
        assertThrows(
            InvalidDateException.class,
            () -> {
              new Date("1945-01-32");
            });
    assertTrue(
        exception1
            .getMessage()
            .contains(
                "Invalid date! There cannot be more than 31 days in the month of January of year 1945"));

    InvalidDateException exception2 =
        assertThrows(
            InvalidDateException.class,
            () -> {
              new Date("1945-03-32");
            });
    assertTrue(
        exception2
            .getMessage()
            .contains(
                "Invalid date! There cannot be more than 31 days in the month of March of year 1945"));

    InvalidDateException exception3 =
        assertThrows(
            InvalidDateException.class,
            () -> {
              new Date("1945-05-32");
            });
    assertTrue(
        exception3
            .getMessage()
            .contains(
                "Invalid date! There cannot be more than 31 days in the month of May of year 1945"));

    InvalidDateException exception4 =
        assertThrows(
            InvalidDateException.class,
            () -> {
              new Date("1945-07-32");
            });
    assertTrue(
        exception4
            .getMessage()
            .contains(
                "Invalid date! There cannot be more than 31 days in the month of July of year 1945"));

    InvalidDateException exception5 =
        assertThrows(
            InvalidDateException.class,
            () -> {
              new Date("1945-08-32");
            });
    assertTrue(
        exception5
            .getMessage()
            .contains(
                "Invalid date! There cannot be more than 31 days in the month of August of year 1945"));

    InvalidDateException exception6 =
        assertThrows(
            InvalidDateException.class,
            () -> {
              new Date("1945-10-32");
            });
    assertTrue(
        exception6
            .getMessage()
            .contains(
                "Invalid date! There cannot be more than 31 days in the month of October of year 1945"));

    InvalidDateException exception7 =
        assertThrows(
            InvalidDateException.class,
            () -> {
              new Date("1945-12-32");
            });
    assertTrue(
        exception7
            .getMessage()
            .contains(
                "Invalid date! There cannot be more than 31 days in the month of December of year 1945"));
  }

  @Test
  public void testDateCreationShouldThrowInvalidDateExceptionWhenDayIsMoreThan30InA30DayMonth() {
    InvalidDateException exception1 =
        assertThrows(
            InvalidDateException.class,
            () -> {
              new Date("1945-04-31");
            });
    assertTrue(
        exception1
            .getMessage()
            .contains(
                "Invalid date! There cannot be more than 30 days in the month of April of year 1945"));

    InvalidDateException exception2 =
        assertThrows(
            InvalidDateException.class,
            () -> {
              new Date("1945-06-31");
            });
    assertTrue(
        exception2
            .getMessage()
            .contains(
                "Invalid date! There cannot be more than 30 days in the month of June of year 1945"));

    InvalidDateException exception3 =
        assertThrows(
            InvalidDateException.class,
            () -> {
              new Date("1945-09-31");
            });
    assertTrue(
        exception3
            .getMessage()
            .contains(
                "Invalid date! There cannot be more than 30 days in the month of September of year 1945"));

    InvalidDateException exception4 =
        assertThrows(
            InvalidDateException.class,
            () -> {
              new Date("1945-11-31");
            });
    assertTrue(
        exception4
            .getMessage()
            .contains(
                "Invalid date! There cannot be more than 30 days in the month of November of year 1945"));
  }

  @Test
  public void
      testDateCreationShouldThrowInvalidDateExceptionWhenDayIsMoreThan29InFebruaryInALeapYear() {
    InvalidDateException exception1 =
        assertThrows(
            InvalidDateException.class,
            () -> {
              new Date("1944-02-30");
            });
    assertTrue(
        exception1
            .getMessage()
            .contains(
                "Invalid date! There cannot be more than 29 days in the month of February of year 1944"));
  }

  @Test
  public void
      testDateCreationShouldThrowInvalidDateExceptionWhenDayIsMoreThan28InFebruaryInANonLeapYear() {
    InvalidDateException exception1 =
        assertThrows(
            InvalidDateException.class,
            () -> {
              new Date("1945-02-30");
            });
    assertTrue(
        exception1
            .getMessage()
            .contains(
                "Invalid date! There cannot be more than 28 days in the month of February of year 1945"));
  }

  @Test
  public void testDateCreationShouldSucceedWhenAValidDateStringIsProvided() {
    try {
      Date date = new Date("1945-02-15");
      assertEquals("1945-02-15", date.toString());
    } catch (InvalidDateFormatException | InvalidDateException e) {
      fail("Possible code smell! Exception was not expected.");
    }
  }

  @Test
  public void testDateCreationShouldSucceedWhenDayIs29InFebruaryInALeapYear() {
    try {
      Date date = new Date("1944-02-29");
      assertEquals("1944-02-29", date.toString());
    } catch (InvalidDateFormatException | InvalidDateException e) {
      fail("Possible code smell! Exception was not expected.");
    }
  }

  @Test
  public void testGetDaysThisYearShouldReturn366InALeapYear() {
    try {
      Date date = new Date("1944-02-15");
      assertEquals(366, date.getDaysThisYear());
    } catch (InvalidDateFormatException | InvalidDateException e) {
      fail("Possible code smell! Exception was not expected.");
    }
  }

  @Test
  public void testGetDaysThisYearShouldReturn365InANonLeapYear() {
    try {
      Date date = new Date("1945-02-15");
      assertEquals(365, date.getDaysThisYear());
    } catch (InvalidDateFormatException | InvalidDateException e) {
      fail("Possible code smell! Exception was not expected.");
    }
  }

  @Test
  public void testGetDaysThisMonthShouldReturn31ForAll31DayMonths() {
    try {
      assertEquals(31, new Date("1945-01-15").getDaysThisMonth());
      assertEquals(31, new Date("1945-03-15").getDaysThisMonth());
      assertEquals(31, new Date("1945-05-15").getDaysThisMonth());
      assertEquals(31, new Date("1945-07-15").getDaysThisMonth());
      assertEquals(31, new Date("1945-08-15").getDaysThisMonth());
      assertEquals(31, new Date("1945-10-15").getDaysThisMonth());
      assertEquals(31, new Date("1945-12-15").getDaysThisMonth());
    } catch (InvalidDateFormatException | InvalidDateException e) {
      fail("Possible code smell! Exception was not expected.");
    }
  }

  @Test
  public void testGetDayOfTheYearShouldReturnOneOnJanuary01() {
    try {
      Date date = new Date("1945-01-01");
      assertEquals(1, date.getDayOfTheYear());
    } catch (InvalidDateFormatException | InvalidDateException e) {
      fail("Possible code smell! Exception was not expected.");
    }
  }

  @Test
  public void testGetDayOfTheYearShouldReturn366OnDecember31InALeapYear() {
    try {
      Date date = new Date("1944-12-31");
      assertEquals(366, date.getDaysThisYear());
    } catch (InvalidDateFormatException | InvalidDateException e) {
      fail("Possible code smell! Exception was not expected.");
    }
  }

  @Test
  public void testGetDayOfTheYearShouldReturn365OnDecember31InANonLeapYear() {
    try {
      Date date = new Date("1945-12-31");
      assertEquals(365, date.getDaysThisYear());
    } catch (InvalidDateFormatException | InvalidDateException e) {
      fail("Possible code smell! Exception was not expected.");
    }
  }

  @Test
  public void testCompareToShouldReturnZeroWhenBothDatesAreSame() {
    try {
      Date date1 = new Date("1945-02-15");
      Date date2 = new Date("1945-02-15");
      assertEquals(0, date1.compareTo(date2));
    } catch (InvalidDateFormatException | InvalidDateException e) {
      fail("Possible code smell! Exception was not expected.");
    }
  }

  @Test
  public void testCompareToShouldReturnMinusOneWhenSuppliedDateIsNewer() {
    try {
      Date date = new Date("1945-02-15");

      Date date1 = new Date("1945-02-16");
      Date date2 = new Date("1945-03-15");
      Date date3 = new Date("1946-02-15");
      assertEquals(-1, date.compareTo(date1));
      assertEquals(-1, date.compareTo(date2));
      assertEquals(-1, date.compareTo(date3));
    } catch (InvalidDateFormatException | InvalidDateException e) {
      fail("Possible code smell! Exception was not expected.");
    }
  }

  @Test
  public void testCompareToShouldReturnOneWhenSuppliedDateIsOlder() {
    try {
      Date date = new Date("1945-02-15");

      Date date1 = new Date("1945-02-14");
      Date date2 = new Date("1945-01-15");
      Date date3 = new Date("1944-02-15");
      assertEquals(1, date.compareTo(date1));
      assertEquals(1, date.compareTo(date2));
      assertEquals(1, date.compareTo(date3));
    } catch (InvalidDateFormatException | InvalidDateException e) {
      fail("Possible code smell! Exception was not expected.");
    }
  }

  @Test
  public void testToStringShouldReturnDateInyyyyMMddFormat() {
    try {
      Date date = new Date("1945-02-15");
      assertEquals("1945-02-15", date.toString());
    } catch (InvalidDateFormatException | InvalidDateException e) {
      fail("Possible code smell! Exception was not expected.");
    }
  }
}
