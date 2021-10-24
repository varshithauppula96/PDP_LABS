package sentence;

/**
 * Class to represent the punctions in a sentence.
 */
public class PunctuationNode implements Sentence {
  private String str;
  private Sentence rest;

  /**
   * Constructor to initialise the two parameters for implementing the punction class.
   *
   * @param str  orginal sentence
   * @param rest rest of the string
   */
  public PunctuationNode(String str, Sentence rest) {
    this.str = str;
    this.rest = rest;
  }

  @Override
  public int getNumberOfWords() {
    return count(0);
  }

  @Override
  public int count(int acc) {
    return this.rest.count(0 + acc);
  }

  @Override
  public String longestWord() {
    {
      return this.rest.longestWord();
    }
  }

  @Override
  public Sentence merge(Sentence other) {
    Sentence merged = new PunctuationNode(this.str, this.rest.merge(other));
    return merged;
  }

  @Override
  public Sentence clone() {
    return new PunctuationNode(this.str, this.rest.clone());
  }

  @Override
  public boolean isWord() {
    return false;
  }

  @Override
  public String toString() {
    if (this.rest.isWord()) {
      return this.str + " " + this.rest.toString();
    }
    return this.str + this.rest.toString();
  }

}
