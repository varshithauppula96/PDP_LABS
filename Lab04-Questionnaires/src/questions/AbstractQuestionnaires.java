package questions;


/**
 * Abstract class which define the methods to get the question and implements the interface
 * Question.
 */
public abstract class AbstractQuestionnaires implements questions.Question {
  private String questions;


  /**
   * Constructor to initialise the question text.
   *
   * @param question input questions text of a particular question
   */
  public AbstractQuestionnaires(String question) {
    this.questions = question;
  }

  /**
   * Function to return the question text.
   *
   * @return qustions
   */
  @Override
  public String getText() {
    return this.questions;
  }

  protected abstract int compareLikert(Likert likert);

  protected abstract int compareMultipleChoice(MultipleChoice multipleChoice);

  protected abstract int compareMultiSelect(MultipleSelect multipleSelect);

  protected abstract int compareTrueFalse(TrueFalse trueFalse);
}
