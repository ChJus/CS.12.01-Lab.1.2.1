// From https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html:
// Each ArrayList instance has a capacity. The capacity is the size of the
// array used to store the elements in the list. It is always at least as
// large as the list size. As elements are added to an ArrayList, its
// capacity grows automatically. The details of the growth policy are
// not specified beyond the fact that adding an element has constant
// amortized time cost.
// Constant amortized time cost: cost of adding elements gradually pays off
// as number of calls increases.
// From somewhere (can't recall where), I remember hearing a common implementation
// of ArrayList doubles its the capacity once filled.

import java.util.Arrays;

// Supports any type (class) that extends Object (all classes accepted)
public class DynamicArray<Type> {
  // Store a private representation of the dynamic array as a static one
  private Type[] array;

  // Keeps a record of the number of elements stored in the dynamic array
  // Default is 0 (empty)
  private int numElements = 0;

  // Keeps track of the size of the private static array representation
  // Default to size 1.
  private int capacity = 1;

  // Instantiates the inner representation
  public DynamicArray() {
    // Type casts Object array with capacity to [Type]
    array = (Type[]) new Object[capacity];
  }

  // Same instantiation process as with no-argument constructor.
  public DynamicArray(Class<Type> c) {
    array = (Type[]) new Object[capacity];
  }

  // Returns the number of elements in array
  public int size() {
    return numElements;
  }

  // Returns whether the array is empty
  public boolean isEmpty() {
    return numElements == 0;
  }

  // Gets the element at a specific index
  // Note the array is zero-indexed
  public Type get(int index) {
    // Only accept valid argument:
    // index is non-negative and at most equal to numElements - 1
    if (index < numElements && index >= 0)
      return array[index];
    else // otherwise throw IndexOutOfBoundsException with debugging details.
      throw new IndexOutOfBoundsException("Index " + index + " out of bounds for array with " + numElements + " elements. ");
  }

  // Returns whether the array contains an element
  // Note: only detects whether array contains element, doesn't
  // tell you where the element is, or whether there are multiple instances
  // of that element in the array.
  public boolean contains(Type element) {
    for (int i = 0; i < numElements; i++) {
      // Invoke the Object.equals method on the object
      // Note that there should be a default representation for all objects
      // i.e., comparison of memory address
      // Classes can override the equals method to qualitatively compare the data
      // contained in objects instead.
      if (array[i].equals(element)) return true;
    }
    return false;
  }

  // Adds an element to the array.
  public void add(Type element) {
    numElements++; // Increments the count of the number of elements in the array
    expand();      // Checks to see if the array would be full after adding the element
    array[numElements - 1] = element; // assign the earliest non-empty space to the element
  }

  // Adds an element to the array at a specific index
  public void add(int index, Type element) {
    // Ensure the index is valid (non-negative, less than or equal to numElements)
    // Note when it is equal to numElements, equivalent to adding element to the end of the array.
    if (index < 0 || index > numElements) {
      throw new IndexOutOfBoundsException("Cannot insert element at " + index + " in an array with " + numElements + " elements.");
    }

    numElements++; // Increment count of number of elements in array
    expand();      // Checks to see if array is full after adding element.

    // Keep a temporary copy of the elements after the index at which the new element is to be added.
    Type[] temp = Arrays.copyOfRange(array, index, numElements);

    // Place element at index
    array[index] = element;

    // Copy remaining array contents, but shift one index
    // System.arraycopy(Type[] src, int srcPositionStart, Type[] dest, int destPositionStart, int numElementsToCopy);
    System.arraycopy(temp, 0, array, index + 1, temp.length);
  }

  // Sets element at index to one specified.
  public void set(int index, Type element) {
    // Ensure that the index is valid (non-negative and within range)
    if (index < 0 || index >= numElements) {
      throw new IndexOutOfBoundsException("Cannot set element at " + index + " in an array with " + numElements + " elements.");
    }

    // Assign new element to index
    array[index] = element;
  }

  // Removes element at specific index.
  public Type remove(int index) {
    // Ensure that the index is valid (non-negative and within range)
    if (index < 0 || index >= numElements) {
      throw new IndexOutOfBoundsException("Cannot remove element at " + index + " in an array with " + numElements + " elements.");
    }

    // Decrement counter of number of elements
    numElements--;

    // Keep the element (for returning later on)
    Type obj = array[index];

    // Shift all elements back one index (deletes element at index in the process)
    // Arrays.copyOfRange(Type[] array, int fromIndexInclusive, int toIndexExclusive);
    Type[] temp = Arrays.copyOfRange(array, index + 1, array.length);

    // System.arraycopy(Type[] src, int srcPositionStart, Type[] dest, int destPositionStart, int numElementsToCopy);
    System.arraycopy(temp, 0, array, index, temp.length);
    return obj;
  }

  // Removes element specified (if exists)
  // Returns whether the array was modified
  public boolean remove(Type element) {
    // Check if array contains element, if so, remove first instance of element.
    if (contains(element)) {
      remove(indexOf(element));
      return true;
    } else {
      return false;
    }
  }

  // Removes all instances of an element in the array.
  // Returns whether the array was modified
  public boolean removeAll(Type element) {
    boolean hasRemoved = false;
    while (contains(element)) { // While the array still contains the element
      hasRemoved = true;        // Indicate that the array was modified
      remove(indexOf(element)); // Remove the element
    }
    return hasRemoved;
  }

  // Completely clears the array
  // Practically equivalent to instantiating a new DynamicArray
  public void clear() {
    capacity = 1;    // Reset to default values
    numElements = 0;
    array = (Type[]) new Object[capacity];
  }

  // Returns the index of the first occurrence of the element (if it exists), otherwise -1
  private int indexOf(Type element) {
    for (int i = 0; i < array.length; i++) {
      // Check if element at index equals the specified element
      // using Object.equals();
      if (array[i].equals(element)) return i;
    }
    return -1;
  }

  // Checks if array needs to be expanded.
  private void expand() {
    if (numElements == capacity) {            // If the array is full
      capacity *= 2;                          // Double the size of the array (capacity)
      array = Arrays.copyOf(array, capacity); // Copy array contents to a new array with new capacity.
    }
  }

  // Overrides the Object.toString() method to
  // return more informative information regarding the contents of the array.
  @Override
  public String toString() {
    return Arrays.toString(array);
  }
}
