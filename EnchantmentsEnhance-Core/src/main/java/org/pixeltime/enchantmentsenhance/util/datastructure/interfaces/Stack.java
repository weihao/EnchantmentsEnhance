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

package org.pixeltime.enchantmentsenhance.util.datastructure.interfaces;

public interface Stack<T> {
    /**
     * Checks if the stack is empty.
     *
     * @return Returns true if the stack is empty.
     */
    public boolean isEmpty();

    /**
     * Checks the item at the top of the
     * stack without removing it.
     *
     * @return Item at the top of the stack.
     */
    public T peek();

    /**
     * Removes the item at the top of
     * the stack.
     *
     * @return The item that was removed.
     */
    public T pop();

    /**
     * Pushes an item onto the stack.
     *
     * @param item Item to be pushed
     *             onto the stack.
     */
    public void push(T item);

    /**
     * Checks if an item is in the stack.
     *
     * @param item Item to be looked for.
     * @return Returns true if the item is
     * somewhere in the stack.
     */
    public boolean contains(T item);

    /**
     * Number of items in the stack.
     *
     * @return The number of items in
     * the stack.
     */
    public int size();

    /**
     * Clears the stack (removes all of
     * the items from the stack).
     */
    public void clear();

    /**
     * Returns an array with a copy of each element in the stack with the top of
     * the stack being the last element
     *
     * @return the array representation of the stack
     */
    public Object[] toArray();
}