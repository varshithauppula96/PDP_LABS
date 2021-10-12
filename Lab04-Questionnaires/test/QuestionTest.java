import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import questions.Likert;
import questions.MultipleChoice;
import questions.MultipleSelect;
import questions.Question;
import questions.TrueFalse;

import static org.junit.Assert.assertEquals;

/**
 * To test the questions interface implementation.
 */
public class QuestionTest {
  Question q1;
  Question q2;
  Question q3;
  Question q4;
  Question q5;
  Question q6;
  Question q7;
  List<Question> ar = new ArrayList<>();

  @Before
  public void setUp() {
    q1 = new Likert("How much do you like java in the range of 1 to 5");
    ar.add(q1);
    q2 = new TrueFalse("Is the sky blue", "True");
    ar.add(q2);
    q3 = new MultipleChoice("What is your favorite color?", "2",
            "Red", "Blue", "yellow", "green", "black");
    ar.add(q3);
    q4 = new MultipleSelect("Pick the wonders of the world?",
            "1 2", "Taj Mahal", "Petra", "Nile", "Lighthouse");
    ar.add(q4);
    q5 = new Likert("How much do you like Icecream in the range of 1 to 5");
    ar.add(q5);
    q6 = new Likert("How much do you like C++ in the range of 1 to 5");
    ar.add(q6);
    q7 = new Likert("How much do you like Lindt in the range of 1 to 5");
    ar.add(q7);

  }


  @Test
  public void getQuestion() {
    assertEquals("How much do you like java in the range of 1 to 5", q1.getText());
    assertEquals("Is the sky blue", q2.getText());
    assertEquals("What is your favorite color?", q3.getText());
    assertEquals("Pick the wonders of the world?", q4.getText());
  }

  @Test
  public void getCorrectAnswerTest() {
    assertEquals("Correct", q1.answer("1"));
    assertEquals("Correct", q2.answer("TRUE"));
    assertEquals("Correct", q3.answer("2"));
    assertEquals("Correct", q4.answer("1 2"));
  }


  @Test
  public void getWrongAnswerTest() {
    assertEquals("Incorrect", q1.answer("8"));
    assertEquals("Incorrect", q2.answer("False"));
    assertEquals("Incorrect", q3.answer("5"));
    assertEquals("Incorrect", q4.answer("1 2 3"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void IllegalQuestions() {
    new MultipleChoice("What is your favorite color?", "2",
            "Red", "Blue", "yellow", "green", "black", "Maroon", "Pink", "Purple", "Cyan");
  }

  @Test
  public void testCompare() {
    assertEquals(1, q1.compareTo(q2));
    assertEquals(-1, q2.compareTo(q3));
    assertEquals(-1, q3.compareTo(q4));
    assertEquals(-1, q4.compareTo(q1));
    assertEquals(1, q4.compareTo(q2));
    assertEquals(1, q4.compareTo(q3));
    assertEquals(0, q4.compareTo(q4));
    assertEquals(1, q3.compareTo(q2));
    assertEquals(-1, q3.compareTo(q4));
    assertEquals(-1, q3.compareTo(q1));
    assertEquals(1, q1.compareTo(q2));
    assertEquals(1, q1.compareTo(q3));
    assertEquals(1, q1.compareTo(q4));
  }

  @Test
  public void TestSorting() {
    Collections.sort(ar);
    List newList = new ArrayList();
    newList.add(q2);
    newList.add(q3);
    newList.add(q4);
    newList.add(q6);
    newList.add(q5);
    newList.add(q7);
    newList.add(q1);
    assertEquals(newList, ar);
  }
}

