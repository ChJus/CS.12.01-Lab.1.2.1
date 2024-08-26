import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {
  private DynamicArray<Integer> integerDynamicArray;
  private DynamicArray<String> stringDynamicArray;
  private DynamicArray<Character> characterDynamicArray;

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    integerDynamicArray = new DynamicArray<>(Integer.class);
    stringDynamicArray = new DynamicArray<>(String.class);
    characterDynamicArray = new DynamicArray<>(Character.class);
  }

  @org.junit.jupiter.api.AfterEach
  void tearDown() {
    integerDynamicArray = null;
    stringDynamicArray = null;
    characterDynamicArray = null;
  }

  @org.junit.jupiter.api.Test
  void size() {
    assertEquals(0, integerDynamicArray.size());
    integerDynamicArray.add(16);
    assertEquals(1, integerDynamicArray.size());
    integerDynamicArray.add(21);
    integerDynamicArray.add(7);
    assertEquals(3, integerDynamicArray.size());

    assertEquals(0, stringDynamicArray.size());
    stringDynamicArray.add("First");
    assertEquals(1, stringDynamicArray.size());
    stringDynamicArray.add("Second");
    stringDynamicArray.add("Third");
    assertEquals(3, stringDynamicArray.size());

    assertEquals(0, characterDynamicArray.size());
    characterDynamicArray.add('A');
    assertEquals(1, characterDynamicArray.size());
    characterDynamicArray.add('B');
    characterDynamicArray.add('C');
    assertEquals(3, characterDynamicArray.size());

    integerDynamicArray.remove(2);
    assertEquals(2, integerDynamicArray.size());
    integerDynamicArray.remove(1);
    assertEquals(1, integerDynamicArray.size());
    integerDynamicArray.remove(0);
    assertEquals(0, integerDynamicArray.size());
  }

  @org.junit.jupiter.api.Test
  void isEmpty() {
    assertTrue(integerDynamicArray.isEmpty());
    assertTrue(stringDynamicArray.isEmpty());
    assertTrue(characterDynamicArray.isEmpty());

    integerDynamicArray.add(16);
    assertFalse(integerDynamicArray.isEmpty());
    integerDynamicArray.remove(0);
    assertTrue(integerDynamicArray.isEmpty());
  }

  @org.junit.jupiter.api.Test
  void addOne() {
    integerDynamicArray.add(17);
    assertEquals(1, integerDynamicArray.size());
    assertEquals(17, integerDynamicArray.get(0));
    integerDynamicArray.add(16);
    assertEquals(2, integerDynamicArray.size());
    assertEquals(17, integerDynamicArray.get(0));
    assertEquals(16, integerDynamicArray.get(1));

    // IMPORTANT
    // Modified test case to behave more like ArrayList
    // should not quietly return null if out of bounds,
    // this could lead to unpredicted behavior for users of
    // the class. Instead, it is safer to throw a RuntimeException
    // that notifies them of the existence of this problem.
    assertThrows(IndexOutOfBoundsException.class, () -> integerDynamicArray.get(2));
  }

  @org.junit.jupiter.api.Test
  void addTwo() {
    // IMPORTANT
    // Modified test case to make exception more reflective
    // of actual error – makes more sense to throw IndexOutOfBoundsException
    // instead of IllegalArgumentException, this follows the documentation
    // specified by ArrayList (Java SE 8).
    assertThrows(IndexOutOfBoundsException.class, () -> integerDynamicArray.add(1, 16));

    // Commented out message test
    // [wanted to keep my own error message format :-) ]

    // String expectedMessage = "Array Index out of Bounds!";
    // String actualMessage = exception.getMessage();
    // assertEquals(expectedMessage, actualMessage);
  }

  // See modification notes for addTwo();
  @org.junit.jupiter.api.Test
  void addThree() {
    integerDynamicArray.add(15);
    Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> integerDynamicArray.add(2, 16));
//    String expectedMessage = "Array Index out of Bounds!";
//    String actualMessage = exception.getMessage();
//    assertEquals(expectedMessage, actualMessage);
  }

  @org.junit.jupiter.api.Test
  void addFour() {
    integerDynamicArray.add(0, 17);
    assertFalse(integerDynamicArray.isEmpty());
    assertEquals(17, integerDynamicArray.get(0));
    integerDynamicArray.add(0, 16);
    assertEquals(2, integerDynamicArray.size());
    assertEquals(16, integerDynamicArray.get(0));
    assertEquals(17, integerDynamicArray.get(1));
    integerDynamicArray.add(0, 15);
    assertEquals(3, integerDynamicArray.size());
    assertEquals(15, integerDynamicArray.get(0));
    assertEquals(16, integerDynamicArray.get(1));
    assertEquals(17, integerDynamicArray.get(2));
    assertThrows(IndexOutOfBoundsException.class, () -> integerDynamicArray.get(3));
  }

  @org.junit.jupiter.api.Test
  void addFive() {
    integerDynamicArray.add(0, 17);
    assertFalse(integerDynamicArray.isEmpty());
    assertEquals(17, integerDynamicArray.get(0));
    integerDynamicArray.add(0, 16);
    assertEquals(2, integerDynamicArray.size());
    assertEquals(16, integerDynamicArray.get(0));
    assertEquals(17, integerDynamicArray.get(1));
    integerDynamicArray.add(1, 15);
    assertEquals(3, integerDynamicArray.size());
    assertEquals(16, integerDynamicArray.get(0));
    assertEquals(15, integerDynamicArray.get(1));
    assertEquals(17, integerDynamicArray.get(2));
    assertThrows(IndexOutOfBoundsException.class, () -> integerDynamicArray.get(3));
  }

  @org.junit.jupiter.api.Test
  void set() {
    integerDynamicArray.add(15);
    integerDynamicArray.set(0, 16);
    assertEquals(1, integerDynamicArray.size());
    assertEquals(16, integerDynamicArray.get(0));

    assertThrows(IndexOutOfBoundsException.class, () -> integerDynamicArray.get(1));

    integerDynamicArray.add(17);
    integerDynamicArray.add(18);

    integerDynamicArray.set(1, 19);

    assertEquals(3, integerDynamicArray.size());
    assertEquals(16, integerDynamicArray.get(0));
    assertEquals(19, integerDynamicArray.get(1));
    assertEquals(18, integerDynamicArray.get(2));
    assertThrows(IndexOutOfBoundsException.class, () -> integerDynamicArray.get(3));
  }

  @org.junit.jupiter.api.Test
  void setOutOfBounds() {
    stringDynamicArray.add("Miss");
    stringDynamicArray.add("you");
    stringDynamicArray.add("Love");

    Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> stringDynamicArray.set(3, "I"));
//    String expectedMessage = "Array Index out of Bounds!";
//    String actualMessage = exception.getMessage();
//    assertEquals(expectedMessage, actualMessage);
  }

  @org.junit.jupiter.api.Test
  void get() {
    assertThrows(IndexOutOfBoundsException.class, () -> stringDynamicArray.get(0));
    stringDynamicArray.add("Miss");
    stringDynamicArray.add("you");
    stringDynamicArray.add("Love");

    assertEquals("Miss", stringDynamicArray.get(0));
    assertEquals("you", stringDynamicArray.get(1));
    assertEquals("Love", stringDynamicArray.get(2));
    assertThrows(IndexOutOfBoundsException.class, () -> stringDynamicArray.get(3));
  }

  @org.junit.jupiter.api.Test
  void getOutOfBounds() {
    stringDynamicArray.add("Miss");
    stringDynamicArray.add("you");
    stringDynamicArray.add("Love");

    Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> stringDynamicArray.add(4, "I"));
//    String expectedMessage = "Array Index out of Bounds!";
//    String actualMessage = exception.getMessage();
//    assertEquals(expectedMessage, actualMessage);
  }

  @org.junit.jupiter.api.Test
  void removeIndex() {
    stringDynamicArray.add("Anna's");
    assertEquals("Anna's", stringDynamicArray.remove(0));
    assertEquals(0, stringDynamicArray.size());
    assertTrue(stringDynamicArray.isEmpty());
  }

  @org.junit.jupiter.api.Test
  void removeIndexOutOfBounds() {
    stringDynamicArray.add("Miss");
    stringDynamicArray.add("you");
    stringDynamicArray.add("Love");

    Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> stringDynamicArray.remove(3));
//    String expectedMessage = "Array Index out of Bounds!";
//    String actualMessage = exception.getMessage();
//    assertEquals(expectedMessage, actualMessage);
  }

  @org.junit.jupiter.api.Test
  void removeElement() {
    stringDynamicArray.add("Miss");
    stringDynamicArray.add("you");
    stringDynamicArray.add("Love");

    // Modified to follow ArrayList documentation
    // remove(element) should return a boolean representing
    // whether such an element was removed or not
    assertTrue(stringDynamicArray.remove("Miss"));
    assertEquals(2, stringDynamicArray.size());
    assertEquals("you", stringDynamicArray.get(0));
    assertEquals("Love", stringDynamicArray.get(1));

    assertThrows(IndexOutOfBoundsException.class, () -> stringDynamicArray.get(2));
  }

  @org.junit.jupiter.api.Test
  void removeElementTwo() {
    stringDynamicArray.add("Miss");
    stringDynamicArray.add("you");
    stringDynamicArray.add("Love");
    stringDynamicArray.add("and");
    stringDynamicArray.add("I");
    stringDynamicArray.add("Miss");
    stringDynamicArray.add("you");
    stringDynamicArray.add("and");
    stringDynamicArray.add("I");
    stringDynamicArray.add("Miss");
    stringDynamicArray.add("you");
    stringDynamicArray.add("Love");

    assertEquals(12, stringDynamicArray.size());
    assertTrue(stringDynamicArray.remove("Miss"));
    assertEquals(11, stringDynamicArray.size());

    assertEquals("you", stringDynamicArray.get(0));
    assertEquals("Love", stringDynamicArray.get(1));
    assertEquals("and", stringDynamicArray.get(2));
    assertEquals("I", stringDynamicArray.get(3));
    assertEquals("Miss", stringDynamicArray.get(4));
    assertEquals("you", stringDynamicArray.get(5));
    assertEquals("and", stringDynamicArray.get(6));
    assertEquals("I", stringDynamicArray.get(7));
    assertEquals("Miss", stringDynamicArray.get(8));
    assertEquals("you", stringDynamicArray.get(9));
    assertEquals("Love", stringDynamicArray.get(10));
  }

  @org.junit.jupiter.api.Test
  void removeElementAll() {
    stringDynamicArray.add("Miss");
    stringDynamicArray.add("you");
    stringDynamicArray.add("Love");
    stringDynamicArray.add("and");
    stringDynamicArray.add("I");
    stringDynamicArray.add("Miss");
    stringDynamicArray.add("you");
    stringDynamicArray.add("and");
    stringDynamicArray.add("I");
    stringDynamicArray.add("Miss");
    stringDynamicArray.add("you");
    stringDynamicArray.add("Love");

    assertEquals(12, stringDynamicArray.size());
    assertTrue(stringDynamicArray.removeAll("Miss"));
    assertEquals(9, stringDynamicArray.size());

    assertEquals("you", stringDynamicArray.get(0));
    assertEquals("Love", stringDynamicArray.get(1));
    assertEquals("and", stringDynamicArray.get(2));
    assertEquals("I", stringDynamicArray.get(3));
    assertEquals("you", stringDynamicArray.get(4));
    assertEquals("and", stringDynamicArray.get(5));
    assertEquals("I", stringDynamicArray.get(6));
    assertEquals("you", stringDynamicArray.get(7));
    assertEquals("Love", stringDynamicArray.get(8));
  }

  @org.junit.jupiter.api.Test
  void containsTrue() {
    stringDynamicArray.add("Miss");
    stringDynamicArray.add("you");
    stringDynamicArray.add("Love");

    assertTrue(stringDynamicArray.contains("Miss"));
    assertTrue(stringDynamicArray.contains("you"));
    assertTrue(stringDynamicArray.contains("Love"));
  }

  @org.junit.jupiter.api.Test
  void containsFalse() {
    stringDynamicArray.add("Miss");
    stringDynamicArray.add("you");
    stringDynamicArray.add("Love");

    assertFalse(stringDynamicArray.contains("miss"));
    assertFalse(stringDynamicArray.contains("You"));
    assertFalse(stringDynamicArray.contains("love"));
  }

  @org.junit.jupiter.api.Test
  void clear() {
    stringDynamicArray.add("Miss");
    stringDynamicArray.add("you");
    stringDynamicArray.add("Love");
    stringDynamicArray.add("and");
    stringDynamicArray.add("I");
    stringDynamicArray.add("Miss");
    stringDynamicArray.add("you");
    stringDynamicArray.add("and");
    stringDynamicArray.add("I");
    stringDynamicArray.add("Miss");
    stringDynamicArray.add("you");
    stringDynamicArray.add("Love");

    assertEquals(12, stringDynamicArray.size());
    assertFalse(stringDynamicArray.isEmpty());

    stringDynamicArray.clear();

    assertEquals(0, stringDynamicArray.size());
    assertTrue(stringDynamicArray.isEmpty());

    assertThrows(IndexOutOfBoundsException.class, () -> stringDynamicArray.get(0));
    assertThrows(IndexOutOfBoundsException.class, () -> stringDynamicArray.get(1));
    assertThrows(IndexOutOfBoundsException.class, () -> stringDynamicArray.get(2));
    assertThrows(IndexOutOfBoundsException.class, () -> stringDynamicArray.get(3));
    assertThrows(IndexOutOfBoundsException.class, () -> stringDynamicArray.get(4));
    assertThrows(IndexOutOfBoundsException.class, () -> stringDynamicArray.get(5));
    assertThrows(IndexOutOfBoundsException.class, () -> stringDynamicArray.get(6));
    assertThrows(IndexOutOfBoundsException.class, () -> stringDynamicArray.get(7));
    assertThrows(IndexOutOfBoundsException.class, () -> stringDynamicArray.get(8));
    assertThrows(IndexOutOfBoundsException.class, () -> stringDynamicArray.get(9));
    assertThrows(IndexOutOfBoundsException.class, () -> stringDynamicArray.get(10));
    assertThrows(IndexOutOfBoundsException.class, () -> stringDynamicArray.get(11));
  }

  @org.junit.jupiter.api.Test
  void testGrow() {
    stringDynamicArray.add("Miss");
    stringDynamicArray.add("you");
    stringDynamicArray.add("Love");

    stringDynamicArray.add("Miss");
    stringDynamicArray.add("you");
    stringDynamicArray.add("Love");

    stringDynamicArray.add("Miss");
    stringDynamicArray.add("you");
    stringDynamicArray.add("Love");

    stringDynamicArray.add("Miss");
    stringDynamicArray.add("you");
    stringDynamicArray.add("Love");

    assertEquals("Miss", stringDynamicArray.get(0));
    assertEquals("you", stringDynamicArray.get(1));
    assertEquals("Love", stringDynamicArray.get(2));

    assertEquals("Miss", stringDynamicArray.get(3));
    assertEquals("you", stringDynamicArray.get(4));
    assertEquals("Love", stringDynamicArray.get(5));

    assertEquals("Miss", stringDynamicArray.get(6));
    assertEquals("you", stringDynamicArray.get(7));
    assertEquals("Love", stringDynamicArray.get(8));

    assertEquals("Miss", stringDynamicArray.get(9));
    assertEquals("you", stringDynamicArray.get(10));
    assertEquals("Love", stringDynamicArray.get(11));

    assertEquals(12, stringDynamicArray.size());

    assertThrows(IndexOutOfBoundsException.class, () -> stringDynamicArray.get(12));
  }
}