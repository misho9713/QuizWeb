package model.quiz;

import model.common.Entity;
import model.user.User;

import javax.json.Json;
import javax.json.JsonObject;

import static misc.DBConstants.*;

public class Quiz implements Entity {
    private final int id;
    private final String name;
    private final boolean isRandom;
    private final boolean isSinglePage;
    private final boolean hasImmediateCorrection;
    private final boolean practicable;
    private final User quizAuthor;

    public Quiz(int id, String name, User quizAuthor, boolean isRandom, boolean isSinglePage, boolean hasImmediateCorrection, boolean practicable) {
        this.id = id;
        this.name = name;
        this.isRandom = isRandom;
        this.isSinglePage = isSinglePage;
        this.hasImmediateCorrection = hasImmediateCorrection;
        this.practicable = practicable;
        this.quizAuthor = quizAuthor;
    }

    public Quiz(String name, User quizAuthor) {
        this(name, quizAuthor, false, false, false, true);
    }

    public Quiz(String name, User quizAuthor, boolean isRandom, boolean isSinglePage, boolean hasImmediateCorrection, boolean practicable) {
        this(-1, name, quizAuthor, isRandom, isSinglePage, hasImmediateCorrection, practicable);
    }

    public User getQuizAuthor() {
        return quizAuthor;
    }

    public String getName() {
        return name;
    }

    public boolean isRandom() {
        return isRandom;
    }

    public boolean isSinglePage() {
        return isSinglePage;
    }

    public boolean hasImmediateCorrection() {
        return hasImmediateCorrection;
    }

    public boolean isPracticable() {
        return practicable;
    }

    @Override
    public JsonObject toJson() {

        return Json.createObjectBuilder()
                .add(DB_COLUMN_QUIZ_ID, id)
                .add(DB_COLUMN_QUIZ_NAME, name)
                .add(DB_COLUMN_QUIZ_AUTHOR, quizAuthor.toJson())
                .add(DB_COLUMN_QUIZ_RANDOM, isRandom)
                .add(DB_COLUMN_QUIZ_SINGLE, isSinglePage)
                .add(DB_COLUMN_QUIZ_IMMEDIATE, hasImmediateCorrection)
                .add(DB_COLUMN_QUIZ_PRACTICE, practicable)
                .build();
    }

    @Override
    public String toString() {
        return toJson().toString();
    }

    @Override
    public int getId() {
        return id;
    }
}
