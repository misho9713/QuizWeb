package model.quiz;

import model.common.Entity;
import model.user.User;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import static misc.DBConstants.*;

public class UserAnswer implements Entity {
    private final int id;
    private final User user;
    private final Question question;
    private final QuestionAnswer questionAnswer;
    private final String text;
    private final int timeTaken;

    public UserAnswer(int id, User user, Question question, QuestionAnswer questionAnswer, String text, int timeTaken) {
        this.id = id;
        this.user = user;
        this.question = question;
        this.questionAnswer = questionAnswer;
        this.text = text;
        this.timeTaken = timeTaken;
    }

    public Question getQuestion() {
        return question;
    }

    public QuestionAnswer getQuestionAnswer() {
        return questionAnswer;
    }

    public String getText() {
        return text;
    }

    public int getTimeTaken() {
        return timeTaken;
    }

    public User getUser() {

        return user;
    }

    @Override
    public JsonObject toJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(DB_COLUMN_USER_ANSWER_ID, id);
        builder.add(DB_COLUMN_USER_ANSWER_USER_ID, user.getId());
        builder.add(DB_COLUMN_USER_ANSWER_QUESTION_ID, question.getId());
        if (questionAnswer != null)
            builder.add(DB_COLUMN_USER_ANSWER_QUESTION_ANSWER_ID, questionAnswer.getId());
        builder.add(DB_COLUMN_USER_ANSWER_TEXT, text);
        builder.add(DB_COLUMN_USER_ANSWER_TIME_TAKEN, timeTaken);

        return builder.build();
    }

    @Override
    public int getId() {
        return id;
    }
}
