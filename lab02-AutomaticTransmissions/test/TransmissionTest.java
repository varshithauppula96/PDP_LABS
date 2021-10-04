import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import transmission.AutomaticTransmission;

/**
 * Test class to test the Transmission class functionality and working.
 */
public class TransmissionTest {
  private AutomaticTransmission test1;

  @Before
  public void setUp() {
    test1 = new AutomaticTransmission(3, 6, 10, 13, 15);
    assertEquals(0, test1.getSpeed());
    assertEquals(0, test1.getGear());
  }

  @Test
  public void testIncreaseSpeed() {
    test1.increaseSpeed();
    assertEquals(1, test1.getSpeed());
    test1.increaseSpeed();
    assertEquals(2, test1.getSpeed());
    test1.increaseSpeed();
    assertEquals(3, test1.getSpeed());

    test1.increaseSpeed();
    assertEquals(4, test1.getSpeed());


  }

  @Test
  public void testGear() {
    test1.increaseSpeed();
    assertEquals(1, test1.getGear());
    test1.increaseSpeed();

    assertEquals(1, test1.getGear());
    test1.increaseSpeed();
    assertEquals(2, test1.getGear());
    test1.increaseSpeed();
    test1.increaseSpeed();
    test1.increaseSpeed();
    test1.increaseSpeed();
    test1.increaseSpeed();
    assertEquals(8, test1.getSpeed());
    assertEquals(3, test1.getGear());
    test1.decreaseSpeed();
    test1.decreaseSpeed();
    test1.decreaseSpeed();
    assertEquals(5, test1.getSpeed());
    assertEquals(2, test1.getGear());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeSpeed() {
    new AutomaticTransmission(-4, 8, 10, 12, 17);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSpeed() {
    new AutomaticTransmission(4, 4, 10, 12, 17);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnorderedSpeed() {
    new AutomaticTransmission(6, 4, 2, 12, 17);
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidDecreaseSpeed() {

    test1.decreaseSpeed();
    test1.decreaseSpeed();
    test1.decreaseSpeed();

  }

  @Test
  public void testToString() {
    AutomaticTransmission test2 = new AutomaticTransmission(5, 9, 10, 13, 15);
    assertEquals("Transmission (speed = 0, gear = 0)", test2.toString());
    test2.increaseSpeed();
    test2.increaseSpeed();
    test2.increaseSpeed();
    test2.increaseSpeed();
    test2.increaseSpeed();
    test2.increaseSpeed();
    assertEquals("Transmission (speed = 6, gear = 2)", test2.toString());

  }
}
