package org.pixeltime.enchantmentsenhance.util.datastructure;

import java.util.Iterator;
import java.util.LinkedList;

public class DoublyLinkedList<T> extends LinkedList<T> {
    /**
     * Returns a string representation of the list If a list contains A, B, and
     * C, the following should be returned "{A; B; C}" (Without the quotations)
     *
     * @return a string representing the list
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            builder.append(next.toString());
            if (iterator.hasNext()) {
                builder.append("; ");
            }
        }
        builder.append("}");
        return builder.toString();
    }
}