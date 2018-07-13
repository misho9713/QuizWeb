package model.quiz;

import model.common.Entity;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import static misc.DBConstants.*;

public class Question implements Entity {
    private final int id;
    private final Quiz quiz;
    private final String text;
    private final QuestionType type;
    private final long timer;
    private final int answerCount;
    private final int weight;

    public Question(Quiz quiz, QuestionType type) {
        this(-1, quiz, "", type, 0, 0, 1);
    }

    public Question(int id, Quiz quiz, String text, QuestionType type, long timer, int answerCount, int weight) {
        this.id = id;
        this.quiz = quiz;
        this.text = text;
        this.type = type;
        this.timer = timer;
        this.answerCount = answerCount;
        this.weight = weight;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public String getText() {
        return text;
    }

    public QuestionType getType() {
        return type;
    }

    public long getTimer() {
        return timer;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public JsonObject toJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(DB_COLUMN_QUESTION_ID, id);
        builder.add(DB_COLUMN_QUESTION_QUIZ_ID, quiz.getId());
        builder.add(DB_COLUMN_QUESTION_TEXT, text);
        builder.add(DB_COLUMN_QUESTION_TYPE, type.toString());
        builder.add(DB_COLUMN_QUESTION_TIMER, timer);
        builder.add(DB_COLUMN_QUESTION_ANSWER_COUNT, answerCount);
        builder.add(DB_COLUMN_QUESTION_WEIGHT, weight);
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

    public enum QuestionType {
        TEXT, FILL_BLANK, MULTIPLE_CHOICE, PICTURE
    }
}
