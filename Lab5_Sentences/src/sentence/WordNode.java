package sentence;

/**
 * Class for each word in a sentence.
 */
public class WordNode implements Sentence {

  private String str;
  private Sentence rest;
  String max;

  /**
   * Constructor to initialise the parameters for word class.
   *
   * @param str  orginal string
   * @param rest remaining of the string
   */
  public WordNode(String str, Sentence rest) {
    this.str = str;
    this.rest = rest;
    this.max = "";
  }

  @Override
  public int getNumberOfWords() {
    return count(0);
  }

  @Override
  public int count(int acc) {
    return this.rest.count(1 + acc);
  }

  @Override
  public String longestWord() {

    String restLongest = rest.longestWord();
    return str.length() < restLongest.length() ? restLongest : str;

  }

  @Override
  public Sentence merge(Sentence other) {
    Sentence merged = new WordNode(this.str, this.rest.merge(other));
    return merged;
  }

  @Override
  public Sentence clone() {
    return new WordNode(this.str, this.rest.clone());
  }


  public boolean isWord() {
    return true;
  }

  @Override
  public String toString() {
    if (this.rest.toString().equals("")) {
      return this.str + this.rest.toString();
    }
    if (this.rest.isWord()) {
      return this.str + " " + this.rest.toString();
    }
    return this.str + this.rest.toString();
  }

}
