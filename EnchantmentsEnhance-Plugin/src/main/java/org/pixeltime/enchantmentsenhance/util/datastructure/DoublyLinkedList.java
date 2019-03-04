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