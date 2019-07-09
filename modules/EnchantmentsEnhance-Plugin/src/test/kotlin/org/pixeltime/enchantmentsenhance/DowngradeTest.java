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
