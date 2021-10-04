

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * A JUnit test class for the Person class.
 */
public class BookTest {

  private Book talisman;
  Person stephen = new Person("stephen", "king", 1989);

  @Before
  public void setUp() {

    talisman = new Book("talisman", stephen, 588);
  }

  @Test
  public void testFirst() {
    assertEquals("talisman", talisman.getTitle());

  }

  @Test
  public void testSecond() {
    assertEquals(stephen, talisman.getAuthor());
  }

  @Test
  public void testPrice() {
    assertEquals(588, talisman.getPrice(), 0);
  }

}
