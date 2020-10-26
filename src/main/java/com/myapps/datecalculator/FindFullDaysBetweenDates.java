package com.myapps.datecalculator;

import com.myapps.datecalculator.date.Date;
import com.myapps.datecalculator.exception.InvalidDateException;
import com.myapps.datecalculator.exception.InvalidDateFormatException;

import java.util.Scanner;

import static com.myapps.datecalculator.constant.Constant.*;
import static com.myapps.datecalculator.util.DateUtils.getFullDaysBetween;

/**
 * The main application class that takes two dates in yyyy-MM-dd format as user inputs and shows the
 * number of full days between them.
 */
public class FindFullDaysBetweenDates {
  private static final Scanner SCANNER = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.printf(">FindFullDaysBetweenDates%n%n");

    while (true) {

      // Read two dates from user
      final Date firstDate = readDate(1);
      final Date secondDate = readDate(2);

      System.out.printf(
          "%nThere are '%d' full days between %s and %s%n",
          getFullDaysBetween(firstDate, secondDate), firstDate, secondDate);

      // Ask if user wants to re-run the program
      if (!continueProgram()) {
        break;
      }
    }
  }

  private static Date readDate(final int dateIndex) {
    while (true) {
      System.out.printf("Enter Date %s: ", dateIndex);

      try {
        final String userInput = SCANNER.nextLine();
        return new Date(userInput);
      } catch (final InvalidDateFormatException | InvalidDateException e) {
        System.out.println(e.getMessage());
        System.out.println();
      }
    }
  }

  private static boolean continueProgram() {
    System.out.println();

    while (true) {
      System.out.print("Continue (Y/N)?: ");

      final String userInput = SCANNER.nextLine();

      if (YES.equalsIgnoreCase(userInput)) {
        System.out.println();
        return true;
      }
      if (NO.equalsIgnoreCase(userInput)) {
        return false;
      }
      System.out.println(INVALID_ANSWER_ERROR);
    }
  }
}
