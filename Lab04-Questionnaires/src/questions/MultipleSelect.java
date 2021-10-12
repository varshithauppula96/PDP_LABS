package questions;

/**
 * offers several options, but there are multiple correct answers. This type of question can be
 * created by passing the text of the question, the correct answer and the options. A question may
 * have at least 3 options, but no more than 8.
 */
public class MultipleSelect extends AbstractQuestionnaires {

  String answer;
  String correctAnswer;

  /**
   * constructor to initialise the various parameters pertaining to a Multiple select question.
   *
   * @param question      Multiple select quesiton text
   * @param correctAnswer correct answer as a string of correct options
   * @param options       possible options to a question
   */
  public MultipleSelect(String question, String correctAnswer, String... options) {
    super(question);
    this.answer = "";
    if (options.length < 3) {
      throw new IllegalArgumentException("min 3 options need to be provided");
    }
    if (options.length > 8) {
      throw new IllegalArgumentException("Max 8 options can be provided");
    }
    this.correctAnswer = correctAnswer;

  }


  /**
   * The correct answer and the user's answer are entered as the option numbers in a string. If both
   * the correctAnswer and the users answer are the same then return correct else return incorrect.
   *
   * @param a Input answer
   * @return Answer if its correct or not
   */
  @Override
  public String answer(String a) {
    if (a == this.correctAnswer) {
      return "Correct";
    } else {
      return "Incorrect";
    }
  }

  @Override
  public int compareTo(Question o) {
    if (o instanceof AbstractQuestionnaires) {
      AbstractQuestionnaires aques = (AbstractQuestionnaires) o;
      return aques.compareMultiSelect(this);
    }
    return 0;
  }

  protected int compareMultiSelect(MultipleSelect l) {
    return -this.getText().compareTo(l.getText());
  }

  protected int compareMultipleChoice(MultipleChoice l) {
    return -1;
  }

  protected int compareLikert(Likert l) {
    return 1;
  }

  protected int compareTrueFalse(TrueFalse l) {
    return -1;
  }
}

