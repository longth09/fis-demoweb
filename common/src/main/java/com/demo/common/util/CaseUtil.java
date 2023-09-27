package com.demo.common.util;

import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.Set;

@UtilityClass
public class CaseUtil {
    public static final char SNAKE_CASE_SEPARATOR = '_';

    /**
     * <p>Converts all the delimiter separated words in a String into camelCase,
     * that is each word is made up of a title case character and then a series of lowercase
     * characters.</p>
     *
     * <p>The delimiters represent a set of characters understood to separate words.
     * The first non-delimiter character after a delimiter will be capitalized. The first String
     * character may or may not be capitalized and it's determined by the user input for
     * capitalizeFirstLetter variable.</p>
     *
     * <p>A {@code null} input String returns {@code null}.
     * Capitalization uses the Unicode title case, normally equivalent to upper case and cannot
     * perform locale-sensitive mappings.</p>
     *
     * <pre>
     * CaseUtils.toCamelCase(null, false)                                 = null
     * CaseUtils.toCamelCase("", false, *)                                = ""
     * CaseUtils.toCamelCase(*, false, null)                              = *
     * CaseUtils.toCamelCase(*, true, new char[0])                        = *
     * CaseUtils.toCamelCase("To.Camel.Case", false, new char[]{'.'})     = "toCamelCase"
     * CaseUtils.toCamelCase(" to @ Camel case", true, new char[]{'@'})   = "ToCamelCase"
     * CaseUtils.toCamelCase(" @to @ Camel case", false, new char[]{'@'}) = "toCamelCase"
     * </pre>
     *
     * @param str                   the String to be converted to camelCase, may be null
     * @param capitalizeFirstLetter boolean that determines if the first character of first word
     *                              should be title case.
     * @param delimiters            set of characters to determine capitalization, null and/or empty
     *                              array means whitespace
     * @return camelCase of String, {@code null} if null String input
     */
    public String toCamelCase(String str, final boolean capitalizeFirstLetter,
                              final char... delimiters) {
        if (str == null || str.isBlank()) {
            return str;
        }
        str = str.toLowerCase();
        final int strLen = str.length();
        final int[] newCodePoints = new int[strLen];
        int outOffset = 0;
        final Set<Integer> delimiterSet = generateDelimiterSet(delimiters);
        boolean capitalizeNext = false;
        if (capitalizeFirstLetter) {
            capitalizeNext = true;
        }
        for (int index = 0; index < strLen; ) {
            final int codePoint = str.codePointAt(index);

            if (delimiterSet.contains(codePoint)) {
                capitalizeNext = outOffset != 0;
                index += Character.charCount(codePoint);
            } else if (capitalizeNext || outOffset == 0 && capitalizeFirstLetter) {
                final int titleCaseCodePoint = Character.toTitleCase(codePoint);
                newCodePoints[outOffset++] = titleCaseCodePoint;
                index += Character.charCount(titleCaseCodePoint);
                capitalizeNext = false;
            } else {
                newCodePoints[outOffset++] = codePoint;
                index += Character.charCount(codePoint);
            }
        }
        if (outOffset != 0) {
            return new String(newCodePoints, 0, outOffset);
        }
        return str;
    }

    /**
     * <p>Converts an array of delimiters to a hash set of code points. Code point of space(32) is
     * added as the default value. The generated hash set provides O(1) lookup time.</p>
     *
     * @param delimiters set of characters to determine capitalization, null means whitespace
     * @return Set<Integer>
     */
    private static Set<Integer> generateDelimiterSet(final char[] delimiters) {
        final Set<Integer> delimiterHashSet = new HashSet<>();
        delimiterHashSet.add(Character.codePointAt(new char[]{' '}, 0));
        if (delimiters == null || delimiters.length == 0) {
            return delimiterHashSet;
        }

        for (int index = 0; index < delimiters.length; index++) {
            delimiterHashSet.add(Character.codePointAt(delimiters, index));
        }
        return delimiterHashSet;
    }

    public String toSnakeCase(String str) {
        final StringBuilder result = new StringBuilder();

        boolean lastUppercase = false;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            char lastEntry = i == 0 ? 'X' : result.charAt(result.length() - 1);
            if (ch == ' ' || ch == SNAKE_CASE_SEPARATOR) {
                lastUppercase = false;

                if (lastEntry == SNAKE_CASE_SEPARATOR) {
                    continue;
                } else {
                    ch = SNAKE_CASE_SEPARATOR;
                }
            } else if (Character.isUpperCase(ch)) {
                ch = Character.toLowerCase(ch);
                if (i > 0) {
                    if (lastUppercase) {
                        if (i + 1 < str.length()) {
                            char next = str.charAt(i + 1);
                            if (!Character.isUpperCase(next) && Character.isAlphabetic(next)) {
                                if (lastEntry != SNAKE_CASE_SEPARATOR) {
                                    result.append(SNAKE_CASE_SEPARATOR);
                                }
                            }
                        }
                    } else {
                        if (lastEntry != SNAKE_CASE_SEPARATOR) {
                            result.append(SNAKE_CASE_SEPARATOR);
                        }
                    }
                }
                lastUppercase = true;
            } else {
                lastUppercase = false;
            }
            result.append(ch);
        }
        return result.toString();
    }

}
