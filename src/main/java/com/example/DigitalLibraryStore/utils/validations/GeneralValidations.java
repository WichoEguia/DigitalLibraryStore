package com.example.DigitalLibraryStore.utils.validations;

import java.util.List;
import java.util.function.Predicate;

public class GeneralValidations {

    // Predicate to check if a string is empty
    public static final Predicate<String> isEmpty = String::isEmpty;

    // Predicate to check if a string is not empty
    public static final Predicate<String> isNotEmpty = s -> !s.isEmpty();

    // Predicate to check if a number is positive
    public static final Predicate<Integer> isPositive = n -> n > 0;

    // Predicate to check if a number is negative
    public static final Predicate<Integer> isNegative = n -> n < 0;

    // Predicate to check if a number is even
    public static final Predicate<Integer> isEven = n -> n % 2 == 0;

    // Predicate to check if a number is odd
    public static final Predicate<Integer> isOdd = n -> n % 2 != 0;

    // Predicate to check if a string contains only digits
    public static final Predicate<String> isNumeric = s -> s.chars().allMatch(Character::isDigit);

    // Predicate to check if a string contains only letters
    public static final Predicate<String> isAlphabetic = s -> s.chars().allMatch(Character::isLetter);

    // Predicate to check if a list is empty
    public static final Predicate<List<?>> isListEmpty = List::isEmpty;

    // Predicate to check if a list is not empty
    public static final Predicate<List<?>> isListNotEmpty = l -> !l.isEmpty();
}
