package questions;

/**
 * can be answered in one of two ways: true or false. This type of question can be created by
 * passing the text of the question and the correct answer.
 */
public class TrueFalse extends AbstractQuestionnaires {

  String answer;
  String correctAnswer;

  /**
   * Constructor to initialise the question details.
   *
   * @param question      question text
   * @param correctAnswer the correct answer to the question
   */
  public TrueFalse(String question, String correctAnswer) {
    super(question);
    this.answer = "";
    this.correctAnswer = correctAnswer;
  }

  /**
   * Check if the answer entered by user is correct or incorrect. The two possibly correct answers
   * for a given question are either true or false.
   *
   * @param ans Input answer for a particular question
   * @return if answer is Correct or Incorrect
   */
  @Override
  public String answer(String ans) {
    if (ans.equalsIgnoreCase("True")) {
      answer = "True";
    }
    if (answer.equals(this.correctAnswer)) {
      return "Correct";
    } else {
      return "Incorrect";
    }
  }

  @Override
  public int compareTo(Question o) {
    if (o instanceof AbstractQuestionnaires) {
      AbstractQuestionnaires aques = (AbstractQuestionnaires) o;
      return aques.compareTrueFalse(this);
    }
    return 0;
  }

  protected int compareTrueFalse(TrueFalse l) {
    return -this.getText().compareTo(l.getText());
  }

  protected int compareMultipleChoice(MultipleChoice l) {
    return 1;
  }

  protected int compareLikert(Likert l) {
    return 1;
  }

  protected int compareMultiSelect(MultipleSelect l) {
    return 1;
  }
}
