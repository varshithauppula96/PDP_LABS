package questions;
import questions.Question;

public abstract class AbstractQuestionnaires implements questions.Question {
  private String questions;


  public AbstractQuestionnaires(String question) {
    this.questions = question;
  }

  @Override
  public String getText() {
    return this.questions ;
  }

}
