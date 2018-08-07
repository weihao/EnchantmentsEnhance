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

package org.pixeltime.enchantmentsenhance.util.datastructures;

import org.pixeltime.enchantmentsenhance.util.datastructures.interfaces.Iterator;
import org.pixeltime.enchantmentsenhance.util.datastructures.interfaces.List;

import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements List<T> {

    private int size;
    private DLNode<T> head;
    private DLNode<T> tail;

    /**
     * Default constructor of DoublyLinkedList
     */
    public DoublyLinkedList() {

        head = new DoublyLinkedList.DLNode<T>(null);
        tail = new DoublyLinkedList.DLNode<T>(null);
        head.setNext(tail);
        tail.setPrevious(head);
        size = 0;

    }

    @Override
    public void add(T newEntry) {
        add(getLength(), newEntry);
    }

    @Override
    public void add(int position, T newEntry) {
        if (position < 0 || size < position) {
            throw new IndexOutOfBoundsException();
        }
        if (newEntry == null) {
            throw new IllegalArgumentException("newEntry" + "Cannot be null");
        }

        DLNode<T> nodeAfter;
        if (position == size) {
            nodeAfter = tail;
        } else {
            nodeAfter = getNodeAtIndex(position);
        }

        DLNode<T> addition = new DLNode<T>(newEntry);
        addition.setPrevious(nodeAfter.previous());
        addition.setNext(nodeAfter);
        nodeAfter.previous().setNext(addition);
        nodeAfter.setPrevious(addition);
        size++;

    }

    @Override
    public void clear() {

        head = new DoublyLinkedList.DLNode<T>(null);
        tail = new DoublyLinkedList.DLNode<T>(null);
        head.setNext(tail);
        tail.setPrevious(head);
        size = 0;

    }

    @Override
    public boolean remove(T anEntry) {
        DLNode<T> current = head.next();
        while (!current.equals(tail)) {
            if (current.getData().equals(anEntry)) {
                current.previous().setNext(current.next());
                current.next().setPrevious(current.previous());
                size--;
                return true;
            }
            current = current.next();
        }
        return false;
    }

    @Override
    public boolean remove(int position) {
        DLNode<T> nodeToBeRemoved = getNodeAtIndex(position);
        nodeToBeRemoved.previous().setNext(nodeToBeRemoved.next());
        nodeToBeRemoved.next().setPrevious(nodeToBeRemoved.previous());
        size--;
        return true;
    }

    @Override
    public T getEntry(int position) {

        return getNodeAtIndex(position).getData();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray() {
        T[] array = (T[]) new Object[size];
        Iterator<T> it = iterator();
        for (int i = 0; i < array.length; i++) {
            array[i] = it.next();
        }
        return array;

    }

    /**
     * Returns a string representation of the list If a list contains A, B, and
     * C, the following should be returned "{A; B; C}" (Without the quotations)
     *
     * @return a string representing the list
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        if (!isEmpty()) {
            DLNode<T> currNode = head.next();
            while (currNode != tail) {
                T element = currNode.getData();
                builder.append(element.toString());
                if (currNode.next != tail) {
                    builder.append("; ");
                }
                currNode = currNode.next();
            }
        }

        builder.append("}");
        return builder.toString();
    }

    @Override
    public boolean contains(T anEntry) {
        DLNode<T> current = tail.previous();
        for (int i = getLength() - 1; i >= 0; i--) {
            if (current.getData().equals(anEntry)) {
                return true;
            }
            current = current.previous();
        }
        return false;
    }

    @Override
    public int getLength() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Gets the node at that index
     *
     * @param index the index of the Node
     * @return node at index
     */
    private DLNode<T> getNodeAtIndex(int index) {
        if (index < 0 || getLength() <= index) {
            throw new IndexOutOfBoundsException("No element exists at "
                    + index);
        }
        DLNode<T> current = head.next();
        for (int i = 0; i < index; i++) {
            current = current.next();
        }
        return current;
    }

    /**
     * Create a new Iterator
     *
     * @return the Iterator
     */
    public Iterator<T> iterator() {
        return new DLIterator();
    }

    /**
     * Swap the position of the datastructures in the list
     *
     * @param x the index of the datastructures to get swapped
     * @param y the index of the datastructures to get swapped
     */
    public void swap(int x, int y) {
        DLNode<T> node1 = this.getNodeAtIndex(x);
        DLNode<T> node2 = this.getNodeAtIndex(y);

        this.remove(x);
        this.add(x, node2.data);

        this.remove(y);
        this.add(y, node1.data);
    }

    /**
     * This represents a node in a doubly linked list. This node stores datastructures, a
     * pointer to the node before it in the list, and a pointer to the node
     * after it in the list
     */
    private static class DLNode<T> {
        private DLNode<T> next;
        private DLNode<T> previous;
        private T data;


        /**
         * Creates a new node with the given datastructures
         *
         * @param d the datastructures to put inside the node
         */
        public DLNode(T d) {
            data = d;
        }


        /**
         * Sets the node after this node
         *
         * @param n the node after this one
         */
        public void setNext(DLNode<T> n) {
            next = n;
        }


        /**
         * Sets the node before this one
         *
         * @param n the node before this one
         */
        public void setPrevious(DLNode<T> n) {
            previous = n;
        }


        /**
         * Gets the next node
         *
         * @return the next node
         */
        public DLNode<T> next() {
            return next;
        }


        /**
         * Gets the node before this one
         *
         * @return the node before this one
         */
        public DLNode<T> previous() {
            return previous;
        }


        /**
         * Gets the datastructures in the node
         *
         * @return the datastructures in the node
         */
        public T getData() {
            return data;
        }
    }

    /**
     * Iterate through the list
     *
     * @author group35
     * @version 2018/04/07
     */
    private class DLIterator implements Iterator<T> {
        private DLNode<T> current;


        /**
         * Create DL Iterator
         */
        public DLIterator() {
            current = head;
        }


        @Override
        public boolean hasNext() {

            return current.next != tail;
        }


        @Override
        public T next() {
            if (hasNext()) {
                current = current.next;
                return current.getData();
            } else {
                throw new NoSuchElementException();
            }
        }

    }
}