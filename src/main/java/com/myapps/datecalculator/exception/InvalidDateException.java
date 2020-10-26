package com.myapps.datecalculator.exception;

/** Thrown to indicate that the date is invalid. */
public class InvalidDateException extends Exception {
  public InvalidDateException(final String message) {
    super(message);
  }
}
