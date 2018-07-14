package model.quiz;

import Domain.ServerConnect;
import model.common.EntityManager;
import model.common.EntitySeeker;
import model.user.UserManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static misc.DBConstants.*;

public class QuizManager implements EntityManager<Quiz, QuizSeeker> {
    private static Quiz getQuiz(ResultSet results) throws SQLException {
        return new Quiz(results.getInt(DB_COLUMN_QUIZ_ID), results.getString(DB_COLUMN_QUIZ_NAME),
                UserManager.getUser(results), results.getBoolean(DB_COLUMN_QUIZ_RANDOM), results.getBoolean(DB_COLUMN_QUIZ_SINGLE),
                results.getBoolean(DB_COLUMN_QUIZ_IMMEDIATE), results.getBoolean(DB_COLUMN_QUIZ_PRACTICE)
        );
    }

    @Override
    public List<Quiz> findEntities(QuizSeeker seeker) {
        List<Quiz> quizzes = new ArrayList<>();
        EntitySeeker.Query query = seeker.generateQuery();
        try (ResultSet results = ServerConnect.getInstance().executeQuery(query.getSql(), query.getParameters())) {
            while (results.next()) {

                quizzes.add(QuizManager.getQuiz(results));
            }
        } catch (SQLException ignored) {

        }
        return quizzes;
    }

    @Override
    public boolean addEntry(Quiz entry) {
        int rows;
        try {
            rows = ServerConnect.getInstance().executeUpdate(
                    String.format("INSERT  INTO %s(%s, %s, %s, %s, %s, %s) " +
                                    "VALUE (?,?,?,?,?,?)", DB_TABLE_QUIZ,
                            DB_COLUMN_QUIZ_AUTHOR, DB_COLUMN_QUIZ_NAME, DB_COLUMN_QUIZ_RANDOM,
                            DB_COLUMN_QUIZ_SINGLE, DB_COLUMN_QUIZ_IMMEDIATE, DB_COLUMN_QUIZ_PRACTICE),
                    Arrays.asList(entry.getQuizAuthor().getId(), entry.getName(), entry.isRandom(),
                            entry.isSinglePage(), entry.hasImmediateCorrection(), entry.isPracticable()));

        } catch (Exception e) {
            return false;
        }
        return rows != 0;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }
}
