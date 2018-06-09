package Domain;

public class QuestionType {

    private String questionType;

    private int questionTypeId;

    public QuestionType(String questionType, int questionTypeId) {
        this.questionType = questionType;
        this.questionTypeId = questionTypeId;
    }

    public QuestionType() {

    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public int getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(int questionTypeId) {
        this.questionTypeId = questionTypeId;
    }
}
