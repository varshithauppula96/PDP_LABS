package lookandsay;

import java.math.BigInteger;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * LookAndSayIterator is a class that implements the RIterator interface and finds the sequence
 * based on the seed and next value. The iterator moves in both the forward and backward direction.
 */
public class LookAndSayIterator implements RIterator<BigInteger> {

  private static final BigInteger SEED = new BigInteger("1");
  private static final BigInteger END_VAL =
          new BigInteger(String.join("", Collections.nCopies(100, "9")));

  private final BigInteger endValue;
  private BigInteger currValue;
  private int prevCount;

  /**
   * A Constructor that initializes the seed and end values.
   *
   * @param startingSeed starting seed value
   * @param endValue     ending value
   * @throws IllegalArgumentException if starting seed or ending value is null, negative, zero or
   *                                  contains zeros or if starting seed greater than ending value
   */
  public LookAndSayIterator(final BigInteger startingSeed, final BigInteger endValue)
          throws IllegalArgumentException {
    if (startingSeed == null || endValue == null) {
      throw new IllegalArgumentException("Seed cannot be null!");
    }
    if (startingSeed.signum() < 1
            || endValue.signum() < 1) {
      throw new IllegalArgumentException("Seed and end value cannot be 0");
    }

    if (startingSeed.compareTo(endValue) >= 0) {
      throw new IllegalArgumentException("Seed needs to be lesser than end value");
    }
    if (startingSeed.toString().contains("0")) {
      throw new IllegalArgumentException("Invalid values of start seed or end value");
    }
    this.endValue = endValue;
    this.currValue = startingSeed;
    prevCount = 0;
  }

  /**
   * Constructor that takes in seed and generates and end value with 100 9s.
   *
   * @param startingSeed starting seed value
   */
  public LookAndSayIterator(final BigInteger startingSeed) {
    this(startingSeed, END_VAL);
  }

  /**
   * Default constructor with seed as 1 and end value with 100 9s.
   */
  public LookAndSayIterator() {
    this(SEED, END_VAL);
  }

  /**
   * Two numbers from the back are considered as the count and the currentVal. The sequence of
   * current number repeated count number of times is returned.
   *
   * @return prev no
   */
  private BigInteger getPreviousValue() {
    final StringBuilder sequence = new StringBuilder();
    final char[] charList = currValue.toString().toCharArray();

    for (int i = 0; i < charList.length; i = i + 2) {
      sequence.append(String.join("", Collections.nCopies(
              Integer.parseInt(String.valueOf(charList[i])),
              String.valueOf(charList[i + 1]))));
    }

    return new BigInteger(sequence.toString());
  }


  @Override
  public boolean hasPrevious() {
    return currValue.toString().length() % 2 == 0 && getPreviousValue().compareTo(endValue) <= 0;
  }


  @Override
  public BigInteger prev() {
    if (!this.hasPrevious()) {
      throw new NoSuchElementException("No such prev element");
    }
    prevCount++;
    currValue = this.getPreviousValue();
    if (prevCount == 1) {
      if (!this.hasPrevious()) {
        return currValue;
      }
      return currValue = this.getPreviousValue();
    }
    return currValue;
  }

  /**
   * The next number is determined based on the count of consecutive current number occurrence and
   * adding that to current value.
   *
   * @return next number
   */
  private BigInteger getNextValue() {
    final StringBuilder sequence = new StringBuilder();
    int count = 1;
    final char[] charList = currValue.toString().toCharArray();
    char prevChar = charList[0];

    for (int i = 1; i < charList.length; i++) {
      if (charList[i] == prevChar) {
        count++;
      } else {
        sequence.append(count).append(prevChar);

        count = 1;
      }
      prevChar = charList[i];
    }
    sequence.append(count).append(prevChar);

    return new BigInteger(sequence.toString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasNext() {
    return currValue.compareTo(endValue) <= 0;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BigInteger next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException("No next number in the sequence.");
    }
    final BigInteger temp1 = currValue;
    currValue = this.getNextValue();
    return temp1;
  }


}
