package questions;

/**
 * Type of question which can be answered on a fixed, 5-point Likert scale (Strongly Agree, Agree,
 * Neither Agree nor Disagree, Disagree, Strongly Disagree).
 */
public class Likert extends AbstractQuestionnaires {
  String answer;

  /**
   * Constructor to initialise likert type of questions by calling the parent constructor.
   *
   * @param question question of type Likert
   */
  public Likert(String question) {
    super(question);
    this.answer = "";
  }


  /**
   * Take the input answer for the particular question and check if its one of the 5 valid likert
   * scale values. If it is, then return answer as Correct else return it as Incorrect.
   *
   * @param a Input answer for a particular question
   * @return answer
   */
  @Override
  public String answer(String a) {
    if (a.equals("1") || a.equals("2")
            || a.equals("3")
            || a.equals("4") || a.equals("5")) {
      return "Correct";
    } else {
      return "Incorrect";
    }

  }

  @Override
  public int compareTo(Question o) {
    if (o instanceof AbstractQuestionnaires) {
      AbstractQuestionnaires aques = (AbstractQuestionnaires) o;
      return aques.compareLikert(this);
    }
    return 0;
  }

  protected int compareLikert(Likert l) {
    return -this.getText().compareTo(l.getText());
  }

  protected int compareMultiSelect(MultipleSelect l) {
    return -1;
  }

  protected int compareMultipleChoice(MultipleChoice l) {
    return -1;
  }

  protected int compareTrueFalse(TrueFalse l) {
    return -1;
  }

}

