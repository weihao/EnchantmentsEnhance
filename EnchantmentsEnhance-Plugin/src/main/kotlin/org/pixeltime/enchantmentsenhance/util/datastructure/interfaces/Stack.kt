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

interface Stack<T> {

    /**
     * Checks if the stack is empty.
     *
     * @return Returns true if the stack is empty.
     */
    fun isEmpty(): Boolean

    /**
     * Checks the item at the top of the
     * stack without removing it.
     *
     * @return Item at the top of the stack.
     */
    fun peek(): T?

    /**
     * Removes the item at the top of
     * the stack.
     *
     * @return The item that was removed.
     */
    fun pop(): T?

    /**
     * Pushes an item onto the stack.
     *
     * @param item Item to be pushed
     *             onto the stack.
     */
    fun push(item: T?)

    /**
     * Checks if an item is in the stack.
     *
     * @param item Item to be looked for.
     * @return Returns true if the item is
     * somewhere in the stack.
     */
    fun contains(item: T?): Boolean

    /**
     * Number of items in the stack.
     *
     * @return The number of items in
     * the stack.
     */
    fun size(): Int

    /**
     * Clears the stack (removes all of
     * the items from the stack).
     */
    fun clear()

    /**
     * Returns an array with a copy of each element in the stack with the top of
     * the stack being the last element
     *
     * @return the array representation of the stack
     */
    fun toArray(): Array<Any?>
}
