package com.myapps.datecalculator.exception;

/** Thrown to indicate that the date format is invalid. */
public class InvalidDateFormatException extends Exception {
  public InvalidDateFormatException() {
    this(null);
  }

  public InvalidDateFormatException(final Exception e) {
    super("Invalid date format! Date should be in yyyy-MM-dd format. For e.g. 1968-12-18.", e);
  }
}
