package model.quiz;

import Domain.ServerConnect;
import model.common.EntityManager;
import model.common.EntitySeeker;
import model.user.User;
import model.user.UserManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static misc.DBConstants.*;

public class QuizManager implements EntityManager<Quiz, QuizSeeker> {
    private static QuizManager instance;

    public static Question getQuestion(ResultSet rs) throws SQLException {
        Quiz quiz;
        try {
            quiz = getQuiz(rs);
        } catch (SQLException e) {
            quiz = getInstance().get(rs.getInt(DB_COLUMN_QUESTION_QUIZ_ID));
        }
        return new Question(
                rs.getInt(DB_COLUMN_QUESTION_ID),
                quiz,
                rs.getString(DB_COLUMN_QUESTION_TEXT),
                Question.QuestionType.valueOf(rs.getString(DB_COLUMN_QUESTION_TYPE).toUpperCase()),
                rs.getLong(DB_COLUMN_QUESTION_TIMER),
                rs.getInt(DB_COLUMN_QUESTION_ANSWER_COUNT),
                rs.getInt(DB_COLUMN_QUESTION_WEIGHT)
        );
    }

    public static QuizManager getInstance() {
        return instance == null ? instance = new QuizManager() : instance;
    }

    private static Quiz getQuiz(ResultSet results) throws SQLException {
        User author;
        try {
            author = UserManager.getUser(results);
        } catch (SQLException e) {
            author = new UserManager().get(results.getInt(DB_COLUMN_QUIZ_AUTHOR));
        }
        return new Quiz(results.getInt(DB_COLUMN_QUIZ_ID), results.getString(DB_COLUMN_QUIZ_NAME),
                author, results.getBoolean(DB_COLUMN_QUIZ_RANDOM), results.getBoolean(DB_COLUMN_QUIZ_SINGLE),
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


    @Override
    public Quiz get(int id) {
        String sql = String.format("SELECT *\n FROM %s\n  INNER JOIN %s u ON %s.%s = u.%s WHERE %s = ?",
                DB_TABLE_QUIZ, DB_TABLE_USER, DB_TABLE_QUIZ, DB_COLUMN_QUIZ_AUTHOR, DB_COLUMN_USER_ID, DB_COLUMN_QUIZ_ID);
        try (ResultSet rs = ServerConnect.getInstance().executeQuery(sql, Collections.singletonList(id))) {
            if (rs.next()) {
                return getQuiz(rs);
            }
        } catch (Exception ignored) {

        }
        return null;
    }

    public List<Question> getAllQuestionsOf(Quiz quiz) {
        List<Question> questions = new ArrayList<>();
        String query = String.format("SELECT *\n" +
                        "FROM %s\n" +
                        "  LEFT JOIN %s q ON %s.%s = q.%s\n" +
                        "WHERE q.%s = ?",
                DB_TABLE_QUESTION, DB_TABLE_QUIZ, DB_TABLE_QUESTION, DB_COLUMN_QUESTION_QUIZ_ID, DB_COLUMN_QUIZ_ID, DB_COLUMN_QUIZ_ID);
        try (ResultSet rs = ServerConnect.getInstance().executeQuery(query, Collections.singletonList(quiz.getId()))) {
            while (rs.next()) {
                questions.add(getQuestion(rs));
            }
        } catch (Exception ignored) {

        }
        return questions;
    }

    private List<QuestionAnswer> getAllAnswersOf(Question question) {
        List<QuestionAnswer> answers = new ArrayList<>();
        String query = String.format("SELECT *\n" +
                        "FROM %s\n" +
                        "  LEFT JOIN %s q ON %s.%s = q.%s\n" +
                        "WHERE q.%s = ?;",
                DB_TABLE_QUESTION_ANSWER, DB_TABLE_QUESTION, DB_TABLE_QUESTION_ANSWER, DB_COLUMN_QUESTION_ANSWER_QUESTION_ID, DB_COLUMN_QUESTION_ID, DB_COLUMN_QUESTION_ID);
        try (ResultSet rs = ServerConnect.getInstance().executeQuery(query, Collections.singletonList(question.getId()))) {
            while (rs.next()) {
                answers.add(getAnswer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }

    private static Question findQuestion(int id) {
        String sql = String.format("SELECT *\n" +
                        "FROM %s\n" +
                        "  LEFT JOIN %s q ON %s.%s = q.%s\n" +
                        "WHERE q.%s = ?;", DB_TABLE_QUESTION, DB_TABLE_QUIZ, DB_TABLE_QUESTION,
                DB_COLUMN_QUESTION_QUIZ_ID, DB_COLUMN_QUIZ_ID, DB_COLUMN_QUIZ_ID);
        try (ResultSet rs = ServerConnect.getInstance().executeQuery(sql, Collections.singletonList(id))) {
            if (rs.next()) return getQuestion(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private QuestionAnswer getAnswer(ResultSet rs) throws SQLException {
        Question question;
        try {
            question = getQuestion(rs);
        } catch (SQLException e) {
            question = findQuestion(rs.getInt(DB_COLUMN_QUESTION_ANSWER_QUESTION_ID));
        }
        return null;
    }

    private List<Question> getAllQuestionsOf(int id) {

        return getAllQuestionsOf(get(id));
    }
}
