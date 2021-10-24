package sentence;

/**
 * Class for empty node which is added at the end of the linked list.
 */
public class EmptyNode implements Sentence {


  @Override
  public int getNumberOfWords() {
    return 0;
  }

  @Override
  public String longestWord() {
    return "";
  }

  @Override
  public int count(int acc) {
    return acc;
  }


  @Override
  public Sentence merge(Sentence other) {
    return other;
  }

  @Override
  public Sentence clone() {
    return new EmptyNode();
  }

  @Override
  public String toString() {
    return "";
  }

  @Override
  public boolean isWord() {
    return false;
  }
}
