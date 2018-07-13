package model.quiz;

import model.common.EntitySeeker;
import model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static misc.DBConstants.*;
import static misc.Utils.addIf;

public class QuizSeeker implements EntitySeeker {
    private User author;
    private int id;
    private String name;

    public QuizSeeker() {
        author = null;
        id = -1;
        name = null;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public Query generateQuery() {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM ").
                append(DB_TABLE_QUIZ).append("\n WHERE 1 = 1\n");
        List<Object> parameters = new ArrayList<>();
        addIf(queryBuilder, name, DB_COLUMN_QUIZ_NAME, parameters, Objects::nonNull);
        addIf(queryBuilder, author == null ? null : author.getId(), DB_COLUMN_QUIZ_AUTHOR, parameters, Objects::nonNull);
        addIf(queryBuilder, id, DB_COLUMN_QUIZ_ID, parameters, id -> (int) id > -1);
        return new Query(queryBuilder.toString(), parameters);
    }
}
