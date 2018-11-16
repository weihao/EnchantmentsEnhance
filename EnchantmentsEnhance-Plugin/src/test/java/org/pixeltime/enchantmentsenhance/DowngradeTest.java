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
import org.pixeltime.enchantmentsenhance.util.datastructure.DoublyLinkedList;

import java.util.ArrayList;

public class DowngradeTest extends TestCase {

    public void testLogic() {
        DoublyLinkedList<String> node = new DoublyLinkedList<>();
        ArrayList<String> a = new ArrayList<>();
        a.add("1");
        a.add("2");
        a.add("3");
        a.add("4");
        ArrayList<String> b = new ArrayList<>();
        b.add("one");
        b.add("two");
        b.add("three");
        node.add(a.toString());
        node.add(b.toString());
        String history1 = node.toString();
        String[] temp = history1.split("; ");


    }

}
