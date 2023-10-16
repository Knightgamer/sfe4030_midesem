/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pkg4030midsem.roman;

import java.util.Map;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * import static org.junit.Assert.assertEquals; import static
 * org.junit.Assert.fail;
 *
 * @author sbp17
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class RomanTest {

    private static Map<Character, Integer> map;

    static {
        map = new HashMap<Character, Integer>() {
            {
                put('I', 1);
                put('V', 5);
                put('X', 10);
                put('L', 50);
                put('C', 100);
                put('D', 500);
                put('M', 1000);
            }
        };
    }

    public int romanToInt(String s) {
        int convertedNumber = 0;
        int prevValue = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            char currentChar = s.charAt(i);
            if (!map.containsKey(currentChar)) {
                // Invalid character in the Roman numeral
                throw new IllegalArgumentException("Invalid Roman numeral: " + currentChar);
            }

            int currentNumber = map.get(currentChar);

            if (currentNumber < prevValue) {
                convertedNumber -= currentNumber;
            } else {
                convertedNumber += currentNumber;
            }

            prevValue = currentNumber;
        }

        return convertedNumber;
    }

    @Test
    public void testSingleLetters() {
        assertEquals(1, romanToInt("I"));
        assertEquals(5, romanToInt("V"));
        assertEquals(10, romanToInt("X"));
        assertEquals(50, romanToInt("L"));
        assertEquals(100, romanToInt("C"));

        assertEquals(500, romanToInt("D"));
        assertEquals(1000, romanToInt("M"));

    }

    @Test
    public void testManyLettersXI() {
        assertEquals(11, romanToInt("XI"));
    }

    @Test
    public void testSubtractiveNotationIV() {
        assertEquals(4, romanToInt("IV"));
    }

    @Test
    public void testWithAndWithoutSubtractiveNotationXIV() {
        Roman roman = new Roman();
        assertEquals(14, roman.romanToInt("XIV"));
    }

    @Test
    public void testRepetitionII() {
        assertEquals(2, romanToInt("II"));
    }

    @Test
    public void testFirstNumberI() {
        assertEquals(1, romanToInt("I"));
    }

    @Test
    public void testInvalidLetter() {
        try {
            romanToInt("Z");
            fail("Expected an IllegalArgumentException to be thrown for 'Z'");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid Roman numeral: Z", e.getMessage());
        }
    }

    @Test
    public void testInvalidAndValidLetterXIZ() {
        try {
            romanToInt("XIZ");
            fail("Expected an IllegalArgumentException to be thrown for 'XIZ'");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid Roman numeral: Z", e.getMessage());
        }
    }

    @Test
    public void testNotValidVV() {
        try {
            romanToInt("VV");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid Roman numeral: V", e.getMessage());
        }
    }

    @Test(expected = NullPointerException.class)
    public void testNullInput() {
        Roman roman = new Roman();
        roman.romanToInt(null);
    }

}
