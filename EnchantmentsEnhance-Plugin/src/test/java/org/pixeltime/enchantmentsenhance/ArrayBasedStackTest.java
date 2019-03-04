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
import org.pixeltime.enchantmentsenhance.util.datastructure.ArrayBasedStack;

import java.util.EmptyStackException;

public class ArrayBasedStackTest extends TestCase {

    private ArrayBasedStack<String> stack;


    /**
     * Initialization.
     */
    public void setUp() {
        stack = new ArrayBasedStack<String>(10);
        stack.push("apple");
    }


    /**
     * This is a test case.
     */
    public void testIsEmpty() {
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }


    /**
     * This is a test case.
     */
    public void testPeek() {
        assertEquals(stack.peek(), "apple");
        stack.pop();
        Exception e = null;
        try {
            stack.peek();
        } catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof EmptyStackException);
    }


    /**
     * This is a test case.
     */
    public void testPop() {
        stack.pop();
        Exception e = null;
        try {
            stack.pop();
        } catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof EmptyStackException);

    }


    /**
     * This is a test case.
     */
    public void testPush() {
        int currentCapacity = stack.getCapacity();
        for (int i = 0; i < currentCapacity; i++) {
            stack.push("random" + Math.random());
        }
        assertEquals(currentCapacity * 2, stack.getCapacity());
    }


    /**
     * This is a test case.
     */
    public void testPushWhenNull() {
        stack.pop();
        stack.push(null);
        assertEquals(0, stack.size());
    }


    /**
     * This is a test case.
     */
    public void testContains() {
        assertTrue(stack.contains("apple"));
        assertFalse(stack.contains(null));
        assertFalse(stack.contains("banana"));
        stack.pop();
        assertFalse(stack.contains("orange"));
    }


    /**
     * This is a test case.
     */
    public void testSize() {
        assertEquals(1, stack.size());
    }


    /**
     * This is a test case.
     */
    public void testToString() {
        assertEquals(stack.toString(), "[apple]");
        stack.push("banana");
        stack.push("orange");
        stack.push("grape");
        assertEquals(stack.toString(), "[apple, banana, orange, grape]");
    }


    /**
     * This is a test case.
     */
    public void testClear() {
        stack.clear();
        assertEquals(0, stack.size());
    }


    /**
     * This is a test case.
     */
    public void testEquals() {
        assertTrue(stack.equals(stack));
        ArrayBasedStack<?> nulltest = null;
        assertFalse(stack.equals(nulltest));
        assertFalse(stack.equals("random"));
        ArrayBasedStack<String> test = new ArrayBasedStack<String>(10);
        test.push("banana");
        test.push("orange");
        assertFalse(stack.equals(test));
        test.pop();
        assertFalse(stack.equals(test));
        test.push("apple");
        stack.push("banana");
        assertFalse(stack.equals(test));
        test.pop();
        test.pop();
        test.push("apple");
        test.push("banana");
        assertEquals(stack, test);
    }


    /**
     * This is a test case.
     */
    public void testConstructor() {
        ArrayBasedStack aStack = new ArrayBasedStack();
        assertEquals(aStack.size(), 0);
        assertTrue(aStack.isEmpty());
    }


    public void testDebuggerViews() {

        ArrayBasedStack<String> testStack = new ArrayBasedStack<String>();

        testStack.push("1");

        testStack.push("2");

        testStack.push("3");

        testStack.push("4");

        assertEquals("[1, 2, 3, 4]", testStack.toString());

        testStack.pop();

        assertEquals("[1, 2, 3]", testStack.toString());

        Object[] toArrayResult = testStack.toArray();

        assertEquals("1", toArrayResult[0].toString());
        assertEquals(toArrayResult.length, 3);
    }

}