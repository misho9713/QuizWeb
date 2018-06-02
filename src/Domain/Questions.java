package Domain;

public class Questions implements Entity {
	
   private int _questionId;
   
   private String question;

   private QuestionType _questiontype;

   public QuestionType get_questiontype() {
	return _questiontype;
}

public void set_questiontype(QuestionType _questiontype) {
	this._questiontype = _questiontype;
}
 @Override
 public int getID() {
	return _questionId;
 }

 public void set_questionId(int _questionId) {
	this._questionId = _questionId;
 }

 public String getQuestion() {
	return question;
 }

 public void setQuestion(String question) {
	this.question = question;
 }

@Override
public String getDataBaseName() {
	// TODO Auto-generated method stub
	return "Questions";
}

}
