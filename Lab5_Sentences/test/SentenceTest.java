import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import sentence.EmptyNode;
import sentence.PunctuationNode;
import sentence.Sentence;
import sentence.WordNode;


/**
 * Class to test the functionality of Sentences class.
 */
public class SentenceTest {
  private Sentence s1;
  private Sentence s2;
  private Sentence c1;
  private Sentence c2;
  private Sentence s4;

  @Before
  public void setUp() {
    Sentence sent1 = new WordNode("Sunny",
            new WordNode("day",
                    new PunctuationNode("!!",
                            new EmptyNode())));

    s1 = sent1;

    Sentence sent2 = new WordNode("Myy",
            new WordNode("name",
                    new WordNode("is",
                            new WordNode("Varshitha",
                                    new EmptyNode()))));

    s2 = sent2;

    Sentence sent3 = new WordNode("eats",
            new WordNode("shoots",
                    new PunctuationNode(",",
                            new WordNode("and",
                                    new WordNode("tes",
                                            new WordNode("sa",
                                                    new WordNode("leaves",
                                                            new EmptyNode())))))));
    c1 = sent3;

    s4 = new EmptyNode();
  }

  @Test
  public void TestConstructor() {
    assertEquals("Sunny day!!", this.s1.toString());
    assertEquals("Myy name is Varshitha", this.s2.toString());
  }

  @Test
  public void TestGetNumberOfWords() {
    assertEquals(6, this.c1.getNumberOfWords());
    assertEquals(4, this.s2.getNumberOfWords());
    assertEquals(0,this.s4.getNumberOfWords());
  }

  @Test
  public void TestLongestWord() {
    assertEquals("Varshitha", this.s2.longestWord());
    assertEquals("shoots", this.c1.longestWord());
    assertEquals("",this.s4.longestWord());
  }

  @Test
  public void TestClone() {
    Sentence temp = this.s1.clone();
    Sentence temp2 = this.s4.clone();
    Sentence temp3 = this.s2.clone();
    assertEquals("Sunny day!!", temp.toString());
    assertEquals("",temp2.toString());
    assertEquals("Myy name is Varshitha",temp3.toString());
  }

  @Test
  public void mergeTest() {
    assertEquals("Sunny day!! Myy name is Varshitha", s1.merge(s2).toString());
    assertEquals("Sunny day!!", s1.merge(s4).toString());
    assertEquals("Myy name is Varshitha Sunny day!!", s2.merge(s1).toString());
    assertEquals("eats shoots, and tes sa leaves Sunny day!!", c1.merge(s1).toString());
  }
}