package model.quiz;

import model.common.Entity;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import static misc.DBConstants.*;

public class QuestionAnswer implements Entity {

    private final int id;
    private final Question question;
    private final String answer;
    private final boolean isCorrect;

    public QuestionAnswer(int id, Question question, String answer, boolean isCorrect) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    public Question getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    @Override
    public JsonObject toJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(DB_COLUMN_QUESTION_ANSWER_ID, id);
        builder.add(DB_COLUMN_QUESTION_ANSWER_QUESTION_ID, question.getId());
        builder.add(DB_COLUMN_QUESTION_ANSWER_TEXT, answer);
        builder.add(DB_COLUMN_QUESTION_ANSWER_CORRECT, isCorrect);

        return builder.build();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }
}
