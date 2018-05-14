package org.pixeltime.enchantmentsenhance;


import org.junit.Before;
import org.junit.Test;
import org.pixeltime.enchantmentsenhance.util.Util;

import static junit.framework.Assert.assertEquals;

public class UtilTest {
    @Before
    public void setUp() {
    }

    @Test
    public void testRomanToInt() {
        assertEquals(Util.romanToInt("I"), 1);
        assertEquals(Util.romanToInt("II"), 2);
        assertEquals(Util.romanToInt("III"), 3);
        assertEquals(Util.romanToInt("IV"), 4);
        assertEquals(Util.romanToInt("V"), 5);
        assertEquals(Util.romanToInt("VI"), 6);
        assertEquals(Util.romanToInt("VII"), 7);
        assertEquals(Util.romanToInt("VIII"), 8);
        assertEquals(Util.romanToInt("IX"), 9);
        assertEquals(Util.romanToInt("X"), 10);
        assertEquals(Util.romanToInt("11"), 11);
        assertEquals(Util.romanToInt("10000"), 10000);
    }

    @Test
    public void testIntToRoman() {
        assertEquals("I", Util.intToRoman(1));
        assertEquals("II", Util.intToRoman(2));
        assertEquals("III", Util.intToRoman(3));
        assertEquals("IV", Util.intToRoman(4));
        assertEquals("V", Util.intToRoman(5));
        assertEquals("VI", Util.intToRoman(6));
        assertEquals("VII", Util.intToRoman(7));
        assertEquals("VIII", Util.intToRoman(8));
        assertEquals("IX", Util.intToRoman(9));
        assertEquals("X", Util.intToRoman(10));
        assertEquals("11", Util.intToRoman(11));
        assertEquals("10000", Util.intToRoman(10000));
    }


}
