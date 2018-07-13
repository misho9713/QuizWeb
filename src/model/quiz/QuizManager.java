package model.quiz;

import Domain.ServerConnect;
import model.common.EntityManager;
import model.common.EntitySeeker;
import model.user.UserManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static misc.DBConstants.*;

public class QuizManager implements EntityManager<Quiz, QuizSeeker> {
    private static Quiz getQuiz(ResultSet results) throws SQLException {
        return new Quiz(results.getInt(DB_COLUMN_QUIZ_ID), results.getString(DB_COLUMN_QUIZ_NAME),
                results.getBoolean(DB_COLUMN_QUIZ_RANDOM), results.getBoolean(DB_COLUMN_QUIZ_SINGLE),
                results.getBoolean(DB_COLUMN_QUIZ_IMMEDIATE), results.getBoolean(DB_COLUMN_QUIZ_PRACTICE),
                UserManager.getUser(results));
    }

    @Override
    public Collection<Quiz> findEntities(QuizSeeker seeker) {
        List<Quiz> quizzes = new ArrayList<>();
        EntitySeeker.Query query = seeker.generateQuery();
        try (ResultSet results = ServerConnect.getInstance().executeQuery(query.getSql(), query.getParameters())) {
            while (results.next()) {

                Quiz quiz = QuizManager.getQuiz(results);
            }
        } catch (SQLException ignored) {

        }
        return quizzes;
    }

    @Override
    public boolean addEntry(Quiz entry) {
        return false;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }
}
