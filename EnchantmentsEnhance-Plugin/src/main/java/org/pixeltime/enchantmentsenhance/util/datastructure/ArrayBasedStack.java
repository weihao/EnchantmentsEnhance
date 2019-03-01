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

package org.pixeltime.enchantmentsenhance.util.datastructure;

import org.pixeltime.enchantmentsenhance.util.datastructure.interfaces.Stack;

import java.util.EmptyStackException;

public class ArrayBasedStack<T> implements Stack<T> {
    private T[] stackArray;
    private int size;
    private int capacity;


    /**
     * Constructor.
     *
     * @param capacity This determines the capacity of the array.
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedStack(int capacity) {
        stackArray = (T[]) new Object[capacity];
        this.size = 0;
        this.capacity = capacity;
    }


    /**
     * Default constructor.
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedStack() {
        stackArray = (T[]) new Object[10];
        this.size = 0;
        this.capacity = 10;
    }


    /**
     * Checks if the stack is empty.
     *
     * @return Returns true if the stack is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Checks the item at the top of the
     * stack without removing it.
     *
     * @return Item at the top of the stack.
     */
    @Override
    public T peek() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return stackArray[size - 1];
    }


    /**
     * Removes the item at the top of
     * the stack.
     *
     * @return The item that was removed.
     */
    @Override
    public T pop() {
        if (this.isEmpty()) {

            throw new EmptyStackException();
        }
        T popped = stackArray[size - 1];
        stackArray[size - 1] = null;
        this.size--;
        return popped;
    }


    /**
     * Pushes an item onto the stack.
     *
     * @param item Item to be pushed
     *             onto the stack.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void push(Object item) {
        if (item == null) {
            return;
        }
        if (this.size >= this.capacity) {
            this.expandCapacity();
        }
        stackArray[size] = (T) item;
        this.size++;
    }


    /**
     * Checks if an item is in the stack.
     *
     * @param item Item to be looked for.
     * @return Returns true if the item is
     * somewhere in the stack.
     */
    @Override
    public boolean contains(Object item) {
        if (item == null) {
            return false;
        }
        if (this.isEmpty()) {
            return false;
        }
        for (int i = 0; i < this.size; i++) {
            if (stackArray[i].equals(item)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Number of items in the stack.
     *
     * @return The number of items in
     * the stack.
     */
    @Override
    public int size() {
        return this.size;
    }


    /**
     * Clears the stack (removes all of
     * the items from the stack).
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        stackArray = (T[]) new Object[capacity];
        this.size = 0;
    }


    /**
     * Returns an array with a copy of each element in the stack with the top of
     * the stack being the last element
     *
     * @return the array representation of the stack
     */
    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] copy = (T[]) new Object[this.size()];
        for (int i = 0; i < this.size(); i++) {
            copy[i] = this.stackArray[i];
        }
        return copy;
    }


    /**
     * Expands the capacity of the stack by doubling its current capacity.
     */
    private void expandCapacity() {

        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) new Object[this.capacity * 2];

        for (int i = 0; i < this.capacity; i++) {
            newArray[i] = this.stackArray[i];
        }

        this.stackArray = newArray;
        this.capacity *= 2;
    }


    /**
     * Returns the string representation of the stack.
     * <p>
     * [] (if the stack is empty)
     * [bottom, item, ..., item, top] (if the stack contains items)
     *
     * @return the string representation of the stack.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');

        boolean firstItem = true;
        for (int i = 0; i < this.size(); i++) {
            if (!firstItem) {
                builder.append(", ");
            } else {
                firstItem = false;
            }

            // String.valueOf will print null or the toString of the item
            builder.append(String.valueOf(this.stackArray[i]));
        }
        builder.append(']');
        return builder.toString();
    }


    /**
     * Two stacks are equal iff they both have the same size and contain the
     * same elements in the same order.
     *
     * @param other the other object to compare to this
     * @return {@code true}, if the stacks are equal; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (this.getClass().equals(other.getClass())) {
            ArrayBasedStack<?> otherStack = (ArrayBasedStack<?>) other;
            if (this.size() != otherStack.size()) {
                return false;
            }
            Object[] otherArray = otherStack.toArray();
            for (int i = 0; i < this.size(); i++) {
                if (!(this.stackArray[i].equals(otherArray[i]))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Getter method.
     *
     * @return returns the capacity.
     */
    public int getCapacity() {
        return this.capacity;
    }
}