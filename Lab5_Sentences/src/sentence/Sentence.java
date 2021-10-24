package sentence;

/**
 * Interface for Sentences to be represented as a recursive union.
 */
public interface Sentence {

  /**
   * Return the number of words in the sentence. Punctuation does not count as a word.
   *
   * @return the number of words in the sentence.
   */
  public int getNumberOfWords();

  /**
   * Return the (first) longest word in the sentence. The longest word should not begin or end with
   * punctuation. If the sentence is empty, it should an empty string.
   *
   * @return the longest word in the sentence.
   */
  public String longestWord();

  /**
   * Helper method to implement tail recurrsion count.
   *
   * @param acc accumulator to keep track of count
   * @return count
   */
  public int count(int acc);

  /**
   * Merge two sentences together. The merge sentence is a list that preserves all of the
   * punctuation. The merged list should be returned by this method, and the original lists should
   * be unchanged.
   *
   * @param other the sentence to add to the end of this sentence.
   * @return the merged sentence
   */
  public Sentence merge(Sentence other);

  /**
   * Return a duplicate of this sentence. A duplicate is a list that has the same words and
   * punctuation in the same sequence, but is independent of the original list.
   *
   * @return a duplicate of this sentence
   */
  public Sentence clone();

  /**
   * Convert the sentences into a single string representation. There must be exactly one space
   * between every two words. There is no space between the last word and the end of the sentence.
   *
   * @return a string representation of this sentence
   */
  public String toString();

  /**
   * Method to check whether the node is a word or not.
   *
   * @return the boolean true or false.
   */
  public boolean isWord();
}
