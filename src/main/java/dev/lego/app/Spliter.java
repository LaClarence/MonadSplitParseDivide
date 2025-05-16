package dev.lego.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Spliter {
  static String REGEXP_NUMBER_PART1 = "(\\d+)";
  static String REGEXP_NUMBER_PART2 = "(0*[1-9]\\d*)";
  static String REGEXP_INPUT = REGEXP_NUMBER_PART1 + "," + REGEXP_NUMBER_PART2;
  static Pattern INPUT_PATTERN = Pattern.compile(REGEXP_INPUT);

  static Result<Pair<String>> splitIfValid(String s) {
    if (s == null) {
      return Result.failure(new IllegalArgumentException("Input can not be a null value"));
    }
    Matcher matcher = INPUT_PATTERN.matcher(s);
    if (!matcher.matches()) {
      return Result
          .failure(new IllegalArgumentException("Input '" + s + "' does not match the expected format ()"));
    }

    return Result.success(new Pair<>(matcher.group(1), matcher.group(2)));
  }
}
