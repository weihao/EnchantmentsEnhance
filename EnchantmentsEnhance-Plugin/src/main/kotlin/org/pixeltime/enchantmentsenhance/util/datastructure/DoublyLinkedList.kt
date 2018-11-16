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

import org.pixeltime.enchantmentsenhance.util.datastructure.interfaces.Iterator
import org.pixeltime.enchantmentsenhance.util.datastructure.interfaces.List
import java.util.*

class DoublyLinkedList<T> : List<T> {

    private var size : Int = 0
    private var head : DLNode<T?> = DLNode(null)
    private var tail : DLNode<T?> = DLNode(null)

    init {
        head.next = tail
        tail.previous = head
    }

    override fun add(newEntry: T?) {
        add(getLength(), newEntry)
    }

    override fun add(position: Int, newEntry: T?) {
        if (position < 0 || size < position) {
            throw IndexOutOfBoundsException()
        }
        if (newEntry == null) {
            throw IllegalArgumentException("newEntry" + "Cannot be null")
        }

        val nodeAfter : DLNode<T?>? = if (position == size) {
            tail
        } else {
            getNodeAtIndex(position)
        }

        val addition = DLNode<T?>(newEntry)
        addition.previous = nodeAfter?.previous
        addition.next = nodeAfter
        nodeAfter?.previous?.next = addition
        nodeAfter?.previous = addition
        size++

    }

    override fun clear() {

        head = DLNode(null)
        tail = DLNode(null)
        head.next = tail
        tail.previous = head
        size = 0

    }

    override fun remove(anEntry: T?): Boolean {
        var current = head.next
        while (current != tail) {
            if (current?.data == anEntry) {
                current?.previous?.next = current?.next
                current?.next?.previous = current?.previous
                size--
                return true
            }
            current = current?.next
        }
        return false
    }

    override fun remove(position: Int): Boolean {
        val nodeToBeRemoved = getNodeAtIndex(position)
        nodeToBeRemoved?.previous?.next = nodeToBeRemoved?.next
        nodeToBeRemoved?.next?.previous = nodeToBeRemoved?.previous
        size--
        return true
    }

    override fun getEntry(position: Int): T? {
        return getNodeAtIndex(position)?.data
    }

    override operator fun contains(anEntry: T?): Boolean {
        var current = tail.previous
        for (i in getLength() - 1 downTo 0) {
            if (current?.data == anEntry) {
                return true
            }
            current = current?.previous
        }
        return false
    }

    override fun getLength(): Int {
        return size
    }

    override fun isEmpty(): Boolean {
        return size == 0
    }

    override fun toArray(): Array<T?> {
        val array = arrayOfNulls<Any?>(size) as Array<T?>
        val it = array.iterator()
        for (i in array.indices) {
            array[i] = it.next()
        }
        return array

    }

    /**
     * Gets the node at that index
     *
     * @param index the index of the Node
     * @return node at index
     */
    private fun getNodeAtIndex(index: Int): DLNode<T?>? {
        if (index < 0 || getLength() <= index) {
            throw IndexOutOfBoundsException("No element exists at $index")
        }
        var current = head.next
        for (i in 0 until index) {
            current = current?.next
        }
        return current
    }


    /**
     * Create a new Iterator
     *
     * @return the Iterator
     */
    operator fun iterator(): Iterator<T> {
        return DLIterator()
    }

    /**
     * Swap the position of the data in the list
     *
     * @param x the index of the data to get swapped
     * @param y the index of the data to get swapped
     */
    fun swap(x: Int, y: Int) {
        val node1 = this.getNodeAtIndex(x)
        val node2 = this.getNodeAtIndex(y)

        this.remove(x)
        this.add(x, node2?.data)

        this.remove(y)
        this.add(y, node1?.data)
    }

    private inner class DLNode<T>(val data: T) {
        var next : DLNode<T?>? = null
        var previous : DLNode<T?>? = null
    }

    private inner class DLIterator : Iterator<T> {

        private var current : DLNode<T?>? = head

        override fun hasNext(): Boolean
                = current?.next != tail

        override fun next(): T? {
            if (hasNext()) {
                current = current?.next
                return current?.data
            } else {
                throw NoSuchElementException()
            }
        }
    }
}
