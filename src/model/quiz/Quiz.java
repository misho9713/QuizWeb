package model.quiz;

import model.common.Entity;
import model.user.User;

public class Quiz implements Entity {
    private final int id;
    private final String name;
    private final boolean isRandom;
    private final boolean isSinglePage;
    private final boolean hasImmediateCorrection;
    private final boolean canPractice;
    private final User quizAuthor;

    public Quiz(int id, String name, boolean isRandom, boolean isSinglePage, boolean hasImmediateCorrection, boolean canPractice, User quizAuthor) {
        this.id = id;
        this.name = name;
        this.isRandom = isRandom;
        this.isSinglePage = isSinglePage;
        this.hasImmediateCorrection = hasImmediateCorrection;
        this.canPractice = canPractice;
        this.quizAuthor = quizAuthor;
    }

    public Quiz(String name, boolean isRandom, boolean isSinglePage, boolean hasImmediateCorrection, boolean canPractice, User quizAuthor) {
        this(-1, name, isRandom, isSinglePage, hasImmediateCorrection, canPractice, quizAuthor);
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

    public boolean isHasImmediateCorrection() {
        return hasImmediateCorrection;
    }

    public boolean isCanPractice() {
        return canPractice;
    }

    @Override
    public int getId() {
        return id;
    }
}
