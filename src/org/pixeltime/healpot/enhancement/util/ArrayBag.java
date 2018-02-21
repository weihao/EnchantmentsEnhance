package org.pixeltime.healpot.enhancement.util;

import org.pixeltime.healpot.enhancement.interfaces.BagInterface;

public final class ArrayBag<T> implements BagInterface<T>
{
   private final T[] bag;
   private int numberOfEntries;
   private static final int DEFAULT_CAPACITY = 25;

    /** Creates an empty bag whose capacity is 25. */
    public ArrayBag() 
    {
        this(DEFAULT_CAPACITY);
    } // end default constructor

    /** Creates an empty bag having a given capacity.
       @param desiredCapacity  The integer capacity desired. */
    public ArrayBag(int desiredCapacity)
    {
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempBag = (T[])new Object[desiredCapacity]; // Unchecked cast
      bag = tempBag;
      numberOfEntries = 0;
    } // end constructor

    /** Adds a new entry to this bag.
       @param newEntry  The object to be added as a new entry.
       @return  True if the addition is successful, or false if not. */
    public boolean add(T newEntry)
    {
      boolean result = true;
      if (isArrayFull())
      {
         result = false;
      }
      else
      {  // Assertion: result is true here
         bag[numberOfEntries] = newEntry;
         numberOfEntries++;
      } // end if
      
      return result;
    } // end add
   
    /** Retrieves all entries that are in this bag.
       @return  A newly allocated array of all the entries in this bag. */
    public T[] toArray()
    {
      // The cast is safe because the new array contains null entries.
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast
      
      for (int index = 0; index < numberOfEntries; index++)
      {
         result[index] = bag[index];
      } // end for
      
      return result;
      // Note: The body of this method could consist of one return statement,
      // if you call Arrays.copyOf
    } // end toArray
    
   // Returns true if the array bag is full, or false if not.
    private boolean isArrayFull()
    {
        return numberOfEntries >= bag.length;
    } // end isArrayFull
   
// STUBS:
    /** Sees whether this bag is empty.
        @return  True if this bag is empty, or false if not */
    public boolean isEmpty() 
    {
        return false; //STUB
    } // end isEmpty

    /** Gets the number of entries currently in this bag.
        @return  The integer number of entries currently in this bag */
    public int getCurrentSize() 
    {
        return numberOfEntries;
        //return -1; // STUB
    } // end getCurrentSize

    /** Removes one unspecified entry from this bag, if possible.
       @return  Either the removed entry, if the removal
                was successful, or null */
    public T remove()
   {
      return null; // STUB
   } // end remove
   
    /** Removes one occurrence of a given entry from this bag.
       @param anEntry  The entry to be removed
       @return  True if the removal was successful, or false otherwise */
   public boolean remove(T anEntry)
   {
      return false; // STUB
   } // end remove
    
    /** Removes all entries from this bag. */
    public void clear()
   {
      // STUB
   } // end clear
    
    /** Counts the number of times a given entry appears in this bag.
         @param anEntry  The entry to be counted
         @return  The number of times anEntry appears in the bag */
    public int getFrequencyOf(T anEntry)
   {
      return 0; // STUB
   } // end getFrequencyOf
    
    /** Tests whether this bag contains a given entry.
         @param anEntry  The entry to locate
         @return  True if this bag contains anEntry, or false otherwise */
    public boolean contains(T anEntry)
   {
      return false; // STUB
   } // end contains
} // end ArrayBag




