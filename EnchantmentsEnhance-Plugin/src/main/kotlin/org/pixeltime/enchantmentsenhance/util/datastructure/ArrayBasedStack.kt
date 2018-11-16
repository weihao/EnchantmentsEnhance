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

package org.pixeltime.enchantmentsenhance.util.datastructure

import org.pixeltime.enchantmentsenhance.util.datastructure.interfaces.Stack
import java.util.*

class ArrayBasedStack<T>(private var capacity: Int) : Stack<T> {

    private var size = 0
    private var stackArray = arrayOfNulls<Any?>(capacity) as Array<T?>

    override fun isEmpty(): Boolean
            = size == 0

    override fun peek(): T? {
        if (this.isEmpty()) {
            throw EmptyStackException()
        }
        return stackArray[size - 1]
    }

    override fun pop(): T? {
        if (this.isEmpty()) {
            throw EmptyStackException()
        }
        val popped = stackArray[size - 1]
        stackArray[size - 1] = null
        this.size--
        return popped
    }

    override fun push(item: T?) {
        if (item == null) {
            return
        }
        if (this.size >= this.capacity) {
            this.expandCapacity()
        }
        stackArray[size] = item
        this.size++
    }

    override fun contains(item: T?): Boolean {
        if (item == null) {
            return false
        }
        if (this.isEmpty()) {
            return false
        }
        for (i in 0 until this.size) {
            if (stackArray[i] == item) {
                return true
            }
        }
        return false
    }

    override fun size(): Int
            = size

    override fun clear() {
        stackArray = arrayOfNulls<Any?>(capacity) as Array<T?>
        this.size = 0
    }

    override fun toArray(): Array<Any?> {
        val copy = arrayOfNulls<Any?>(this.size())
        for (i in 0 until this.size()) {
            copy[i] = this.stackArray[i]
        }
        return copy
    }

    /**
     * Expands the capacity of the stack by doubling its current capacity.
     */
    private fun expandCapacity() {

        val newArray = arrayOfNulls<Any?>(this.capacity * 2) as Array<T?>

        for (i in 0 until this.capacity) {
            newArray[i] = this.stackArray[i]
        }

        this.stackArray = newArray
        this.capacity *= 2
    }

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append('[')

        var firstItem = true
        for (i in 0 until this.size()) {
            if (!firstItem) {
                builder.append(", ")
            } else {
                firstItem = false
            }

            // String.valueOf will print null or the toString of the item
            builder.append(this.stackArray[i].toString())
        }
        builder.append(']')
        return builder.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null) {
            return false
        }
        if (this.javaClass == other.javaClass) {
            val otherStack = other as ArrayBasedStack<*>
            if (this.size() != otherStack.size()) {
                return false
            }
            val otherArray = otherStack.toArray()
            for (i in 0 until this.size()) {
                if (this.stackArray[i] != otherArray[i]) {
                    return false
                }
            }
            return true
        }
        return false
    }

    fun getCapacity(): Int
            = capacity
}
