import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.NoSuchElementException;

import lookandsay.LookAndSayIterator;
import lookandsay.RIterator;

import static org.junit.Assert.assertEquals;

/**
 * Class to test the iterator functionalities like testing previous value, next value, constructors.
 */
public class LookAndSayIteratorTest {
  private RIterator<?> test1;

  @Before
  public void setUp() {
    test1 = new LookAndSayIterator(new BigInteger("111231"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalTwoParam() {
    new LookAndSayIterator(new BigInteger("-4"), new BigInteger("21"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTwoParamInvalidEnd() {
    new LookAndSayIterator(new BigInteger("312"), new BigInteger("21"));
  }


  @Test(expected = IllegalArgumentException.class)
  public void testSeedValueLessThanZero1() {
    new LookAndSayIterator(new BigInteger("-8"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOneParameterConstructor() {
    new LookAndSayIterator(new BigInteger("200"));
  }

  @Test
  public void testNext() {
    test1.next();
    assertEquals("31121311", test1.next().toString());

  }

  @Test(expected = NoSuchElementException.class)
  public void testNoNextValue() {
    BigInteger seedValue = new BigInteger("21");
    BigInteger endValue = new BigInteger("40");
    test1 = new LookAndSayIterator(seedValue, endValue);
    test1.next();
    test1.next();
  }

  @Test
  public void testPrevious() {
    assertEquals("12111", test1.prev().toString());
  }


  @Test(expected = NoSuchElementException.class)
  public void testNoPrevValue() {
    test1 = new LookAndSayIterator();
    test1.prev();
  }


  @Test
  public void testHasNext() {
    assertEquals(true, test1.hasNext());
  }


  @Test
  public void testHasPrevious() {
    assertEquals(true, test1.hasPrevious());
  }

  @Test
  public void testNoHasNext() {
    BigInteger seedValue = new BigInteger("21");
    BigInteger endValue = new BigInteger("123");
    test1 = new LookAndSayIterator(seedValue, endValue);
    test1.next();
    assertEquals(false, test1.hasNext());

  }


  @Test
  public void testNoHasPrevious() {
    test1 = new LookAndSayIterator(new BigInteger("12111"));
    assertEquals(false, test1.hasPrevious());
  }


}