/*
 *     Copyright (C) 2017-Present HealPot
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
import org.pixeltime.enchantmentsenhance.util.datastructures.DoublyLinkedList;
import org.pixeltime.enchantmentsenhance.util.datastructures.interfaces.Iterator;

import java.util.Arrays;
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
     * Tests that objects can be removed at the beginning and end and that the
     * size is changed
     */
    public void testRemoveIndex() {
        list.add("A");
        list.add("B");
        assertTrue(list.remove(1));
        assertEquals(1, list.getLength());
        list.add("B");
        assertTrue(list.remove(0));
        assertEquals(1, list.getLength());
    }


    /**
     * Tests the add method. Ensures that it adds the object is added at the end
     * and the size is increased
     */
    public void testAdd() {
        assertEquals(0, list.getLength());
        list.add("A");
        assertEquals(1, list.getLength());
        list.add("B");
        assertEquals(2, list.getLength());
        assertEquals("B", list.getEntry(1));

    }


    /**
     * Tests that objects can be added at the beginning and end and that they
     * are placed correctly
     */
    public void testAddIndex() {
        list.add("B");
        list.add(0, "A");
        assertEquals("A", list.getEntry(0));
        assertEquals(2, list.getLength());
        list.add(2, "D");
        assertEquals("D", list.getEntry(2));
        list.add(2, "C");
        assertEquals("C", list.getEntry(2));
    }


    /**
     * This tests that the add method throws a null pointer exception when
     * adding null datastructures to the list
     */
    public void testAddNullException() {
        Exception e = null;
        try {
            list.add(null);
        } catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);
    }


    /**
     * This tests that the add method throws a Invalid argument when adding null
     * datastructures to the list
     */
    public void testAddIndexNullException() {
        Exception e = null;
        try {
            list.add(0, null);
        } catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);
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
     * Tests removing a object changes the size appropiately and that you can
     * remove the first and last elements
     */
    public void testRemoveObj() {
        assertFalse(list.remove(null));
        list.add("A");
        list.add("B");
        assertTrue(list.remove("A"));
        assertEquals("B", list.getEntry(0));
        assertEquals(1, list.getLength());
        list.add("C");
        assertTrue(list.remove("C"));
        assertEquals("B", list.getEntry(0));
    }


    /**
     * Tests get when the index is greater than or equal to size and when the
     * index is less than zero
     */
    public void testGetException() {
        Exception exception = null;
        try {
            list.getEntry(-1);
        } catch (Exception e) {
            exception = e;
        }
        assertTrue(exception instanceof IndexOutOfBoundsException);
        exception = null;
        list.add("A");
        try {
            list.getEntry(1);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        assertTrue(exception instanceof IndexOutOfBoundsException);
    }


    /**
     * Test geEntry()
     */
    public void testGet() {
        list.add("aaaa");
        list.add("bbbb");
        list.add("cccc");
        list.add("dddd");

        assertEquals("aaaa", list.getEntry(0));
        assertEquals("bbbb", list.getEntry(1));
        assertEquals("cccc", list.getEntry(2));
        assertEquals("dddd", list.getEntry(3));

        Exception exception = null;
        try {
            list.getEntry(10);
        } catch (Exception e) {
            exception = e;
        }
        assertTrue(exception instanceof IndexOutOfBoundsException);
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
     * Test clear()
     */
    public void testClear() {
        list.add("A");
        list.clear();
        assertEquals(0, list.getLength());
        assertFalse(list.contains("A"));
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


    /**
     * Test toArray()
     */
    public void testSwap() {
        list.add("aaaa");
        list.add("bbbb");
        list.add("cccc");
        list.add("dddd");
        list.swap(2, 3);
        assertEquals(list.toString(), "{aaaa; bbbb; dddd; cccc}");
        list.swap(2, 1);
        assertEquals(list.toString(), "{aaaa; dddd; bbbb; cccc}");
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
