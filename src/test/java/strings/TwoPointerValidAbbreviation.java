package strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TwoPointerValidAbbreviation {

  static boolean isValidAbbreviation(final String word, final String abbreviation) {
    final char ZERO = '0';

    // iterate through the word and abbreviation using 2 pointers
    int wordIndex = 0, abbrIndex = 0;
    while (abbrIndex < abbreviation.length()) {
      final char abbrChar = abbreviation.charAt(abbrIndex);

      if (ZERO == abbrChar) {
        return false;
      }

      if (Character.isDigit(abbrChar)) {
        int number = 0;
        while (abbrIndex < abbreviation.length() && Character.isDigit(
            abbreviation.charAt(abbrIndex))) {
          number = number * 10 + (abbreviation.charAt(abbrIndex) - ZERO);
          abbrIndex++;
        }

        wordIndex += number;
      } else {
        if (wordIndex >= word.length() || abbrChar != word.charAt(wordIndex)) {
          return false;
        }

        wordIndex++;
        abbrIndex++;
      }
    }

    // verify that both abbreviation and word ended at the same time
    return wordIndex == word.length() && abbrIndex == abbreviation.length();
  }

  static Stream<Arguments> abbreviationTestCases() {
    return Stream.of(
        // single digit true
        Arguments.of("something", "s3thing", true),
        // single digit false
        Arguments.of("something", "s4thing", false),
        // double digit true
        Arguments.of("internationalisation", "i18n", true),
        // double digit false
        Arguments.of("internationalisation", "i17n", false),
        // multiple replacements true
        Arguments.of("something", "3eth2g", true),
        // multiple replacements false
        Arguments.of("something", "3eth3g", false),
        // number in the end - true
        Arguments.of("car", "ca1", true),
        // number in the end - false
        Arguments.of("car", "ca2", false),
        // abbreviation longer than word
        Arguments.of("car", "ca1s", false),
        // word longer than abbreviation
        Arguments.of("cars", "ca1", false),
        // digit more that word length
        Arguments.of("car", "c9r", false),
        // text mismatch
        Arguments.of("car", "c1t", false),
        // leading zero in abbreviation
        Arguments.of("word", "w01d", false),
        // empty replacement
        Arguments.of("word", "wo0rd", false));
  }

  @ParameterizedTest
  @MethodSource("abbreviationTestCases")
  void testValidAbbreviation(
      final String word,
      final String abbreviation,
      final boolean expectedResult) {
    final boolean result = isValidAbbreviation(word, abbreviation);

    assertEquals(expectedResult, result);
  }

}
