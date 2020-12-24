package org.pixeltime.enchantmentsenhance;


import com.google.common.hash.Hashing;
import org.junit.Before;
import org.junit.Test;
import org.pixeltime.enchantmentsenhance.util.Util;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UtilTest {
    @Before
    public void setUp() {
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
        assertEquals("XI", Util.intToRoman(11));
        assertEquals("MMMMCMXCIX", Util.intToRoman(4999));
    }


    @Test
    public void testRainbowlizeCode() {
        String str = "                ";
        assertTrue(Util.rainbowlizeCode(str).length() > 0);
        assertTrue(Util.rainbowlize(str).length() > 0);
    }

    @Test
    public void testHashing() {
        String sha256hex = Hashing.sha256().hashString("xD", StandardCharsets.UTF_8).toString().toUpperCase();
        assertEquals(64, sha256hex.length());
    }

    @Test
    public void testParsing() {
        String temp = "";
        for (String s : temp.split(", ")) {
            assertTrue(s.isEmpty());
        }
    }

    @Test
    public void testArray() {
        int[] array1 = new int[]{2312, 42, 54, 7682};
        int[] array2 = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        int[] array3 = Arrays.copyOf(array1, array2.length);
        assertEquals(array3.length, array2.length);
    }

    @Test
    public void testRandom() {
        assertTrue(Math.random() >= 0);
    }
}