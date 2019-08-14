package org.pixeltime.enchantmentsenhance.util.datastructure

import java.util.LinkedList

class DoublyLinkedList<T> : LinkedList<T>() {
    /**
     * Returns a string representation of the list If a list contains A, B, and
     * C, the following should be returned "{A; B; C}" (Without the quotations)
     *
     * @return a string representing the list
     */
    override fun toString(): String {
        val builder = StringBuilder("{")
        val iterator = this.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            builder.append(next.toString())
            if (iterator.hasNext()) {
                builder.append("; ")
            }
        }
        builder.append("}")
        return builder.toString()
    }
}