/*
 *     Copyright (C) 2017-Present 25 (https://github.com/25)
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.pixeltime.enchantmentsenhance;

import junit.framework.TestCase;
import org.pixeltime.enchantmentsenhance.util.datastructure.DoublyLinkedList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class DoublyLinkedListTest extends TestCase {

    private DoublyLinkedList<String> list;


    /**
     * Set up the initials
     */
    public void setUp() {
        list = new DoublyLinkedList<String>();
    }


    /**
     * Tests that an IndexOutOfBounds exception is thrown when the index is
     * greater than or equal to size and less than zero
     */
    public void testRemoveException() {
        list.add("A");
        Exception e = null;
        try {
            list.remove(2);
        } catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
        e = null;
        try {
            list.remove(-1);
        } catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }


    /**
     * This tests when the add method is called and the index is greater than
     * size or less than zero
     */
    public void testAddException() {
        list.add("A");
        Exception e = null;
        try {
            list.add(2, "B");
        } catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
        e = null;
        try {
            list.add(-1, "B");
        } catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }


    /**
     * Test contains when it does and does not contain the object
     */
    public void testContains() {
        assertFalse(list.contains("A"));
        list.add("A");
        assertTrue(list.contains("A"));
        assertFalse(list.contains("B"));
        list.add("B");
        assertTrue(list.contains("B"));
    }


    /**
     * Test isEmpty()
     */
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add("A");
        assertFalse(list.isEmpty());
    }

    /**
     * Test toString()
     */
    public void testToString() {
        assertEquals("{}", list.toString());
        list.add("A");
        assertEquals("{A}", list.toString());
        list.add("B");
        assertEquals("{A; B}", list.toString());
    }


    /**
     * Tests removing from an empty list
     */
    public void testRemoveFromEmpty() {
        list.add("dance");
        list.add(0, "safety");
        list.clear();
        assertFalse(list.remove("safety"));
        Exception exception;
        exception = null;
        try {
            list.remove(0);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        assertTrue(exception instanceof IndexOutOfBoundsException);

        DoublyLinkedList<String> emptyList = new DoublyLinkedList<String>();
        exception = null;
        try {
            emptyList.remove(0);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        assertTrue(exception instanceof IndexOutOfBoundsException);
    }


    /**
     * Test hasNext()
     */
    public void testIteratorHasNext() {
        Iterator<String> iter = list.iterator();

        assertFalse(iter.hasNext());
        list.add("aaaa");
        list.add("bbbb");
        list.add("cccc");
        list.add("dddd");
        list.add("eeee");
        Iterator<String> iter1 = list.iterator();
        assertTrue(iter1.hasNext());

    }


    /**
     * Test next()
     */
    public void testNext() {
        list.add("aaaa");
        list.add("bbbb");
        list.add("cccc");
        list.add("dddd");
        list.add("eeee");
        Iterator<String> iter = list.iterator();
        assertEquals("aaaa", iter.next());
        assertEquals("bbbb", iter.next());
        assertEquals("cccc", iter.next());
        assertEquals("dddd", iter.next());
        assertEquals("eeee", iter.next());

        Exception exception = null;
        try {
            iter.next();
        } catch (Exception e) {
            exception = e;
        }
        assertTrue(exception instanceof NoSuchElementException);
        assertFalse(iter.hasNext());
    }


    /**
     * Test toArray()
     */
    public void testToArray() {

        list.add("aaaa");
        list.add("bbbb");
        list.add("cccc");
        list.add("dddd");

        assertEquals(Arrays.toString(list.toArray()),
                "[aaaa, bbbb, cccc, dddd]");

    }


    public void testLogic() {
        Integer[] temp = {1, 2, 3, 4};
        List<Integer> temp2 = Arrays.asList(temp);
        assertEquals(temp2.toString(), Arrays.toString(temp));

        String test = "Damage_All:2";
        String[] test2 = test.split("\\?");
        assertEquals(test2.length, 1);
    }
}
