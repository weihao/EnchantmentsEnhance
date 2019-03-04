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

package org.pixeltime.enchantmentsenhance.util.datastructure.interfaces;

import java.util.NoSuchElementException;

public interface List<T> {

    /**
     * Add an Entry to the end a list
     *
     * @param newEntry the new Entry
     * @throws IllegalArgumentException if
     *                                  New entry is null
     */
    public void add(T newEntry);


    /**
     * Add an Entry to a specific location
     *
     * @param position the location
     * @param newEntry new Entry
     * @throws IndexOutOfBoundsException if the position
     *                                   Is less than 0 or greater than size
     * @throws IllegalArgumentException  if
     *                                   the new entry is null
     */
    public void add(int position, T newEntry);


    /**
     * reset the list
     */
    public void clear();


    /**
     * Remove an entry
     *
     * @param anEntry the entry to removed
     * @return True if the element is removed
     * @throws NoSuchElementException if anEntry is null
     *                                Or No such element is found
     */
    public boolean remove(T anEntry);


    /**
     * Remove the element at specific location
     *
     * @param position the location of the element
     * @return True if the element is removed
     * @throws IndexOutOfBoundsException if the index is less than 0
     *                                   Or greater than size
     */
    public boolean remove(int position);


    /**
     * Get the entry at specific index
     *
     * @param position the index of entry
     * @return the entry at specific index
     */
    public T getEntry(int position);


    /**
     * Get the array of the list
     *
     * @return the array of the list
     */
    public T[] toArray();


    /**
     * To determine whether the list contains
     * Specific entry
     *
     * @param anEntry the entry to search
     * @return True if there is such element in the list
     */
    public boolean contains(T anEntry);


    /**
     * Get the size of the list
     *
     * @return the size of the list
     */
    public int getLength();


    /**
     * To determine whether the list is empty
     *
     * @return True if the list if empty
     */
    public boolean isEmpty();


    /**
     * Get the information of the list
     *
     * @return the information of the list
     */
    public String toString();

}