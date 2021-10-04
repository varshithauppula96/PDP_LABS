package weather;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class to check functionality and working of java class Stevenson Reading.
 */
public class WeatherReadingTest {
  private StevensonReading test1;

  @Before
  public void setUp() {
    test1 = new StevensonReading(22.1, 5, 3, 11);
  }

  @Test
  public void testTemperature() {
    assertEquals(22, test1.getTemperature());

  }

  @Test
  public void testDewPoint() {

    assertEquals(5, test1.getDewPoint());
  }

  @Test
  public void testWindSpeed() {
    assertEquals(3, test1.getWindSpeed(), 0);
  }

  @Test
  public void testRain() {
    assertEquals(11, test1.getTotalRain());
  }

  @Test
  public void testRelativeHum() {
    assertEquals(15, test1.getRelativeHumidity());
  }

  @Test
  public void testHeatIndex() {
    assertEquals(21, test1.getHeatIndex());
  }

  @Test
  public void testWindChill() {
    assertEquals(74, test1.getWindChill());
  }

  @Test
  public void testString() {
    assertEquals("Reading: T = 22, D = 5, v = 3, rain = 11", test1.toString());
  }

  @Test
  public void testEquals() {
    StevensonReading test2 = new StevensonReading(22.1, 5, 3, 11);
    StevensonReading test3 = new StevensonReading(30, 12.1, 3, 12);
    assertEquals(test1, test2);
    assertNotEquals(test1, test3);
  }

  @Test
  public void testHashCode() {
    StevensonReading test2 = new StevensonReading(22.1, 5, 3, 11);
    assertTrue(test1.hashCode() == test2.hashCode());

  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTemperature() {
    new StevensonReading(-324, 14, 2, 11);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDewPoint() {
    new StevensonReading(20, 34, 2, 11);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidWindSpeed() {
    new StevensonReading(20, 12, -2, 11);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidRain() {
    new StevensonReading(20, 10, 2, -11);
  }


}