package questions;

/**
 * Offers several options, only one of which is correct. This type of question can be created by
 * passing the text of the question, the correct answer and the options. A question may have at
 * least 3 options, but no more than 8.
 */
public class MultipleChoice extends AbstractQuestionnaires {

  String answer;
  String correctAnswer;

  /**
   * Constructor to initialise the various parameters pertaining to a multiple choice question.
   *
   * @param question      the question text
   * @param correctAnswer the correct answer for a particular question
   * @param options       the different possible answers for a given question
   */
  public MultipleChoice(String question, String correctAnswer, String... options) {
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
   * Checks if the option entered as one of the option numbers in a string is equal to the right
   * answer.
   *
   * @param a Input answer for a particular multiple choice question.
   * @return answer as Correct or Incorrect
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
      return aques.compareMultipleChoice(this);
    }
    return 0;
  }

  protected int compareMultipleChoice(MultipleChoice l) {
    return -this.getText().compareTo(l.getText());
  }

  protected int compareMultiSelect(MultipleSelect l) {
    return 1;
  }

  protected int compareLikert(Likert l) {
    return 1;
  }

  protected int compareTrueFalse(TrueFalse l) {
    return -1;
  }
}
