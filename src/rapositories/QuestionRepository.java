package rapositories;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;


import Domain.QuestionType;
import Domain.Questions;

public class QuestionRepository {
    private final DataSource ds;

    public QuestionRepository(DataSource ds) {
        this.ds = ds;
    }

    public Questions getQuestionById(int id) throws SQLException {

        try (Connection conn = ds.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement("select * from Questions  where ID = ?")) {
                stmt.setInt(1, id);
                try (ResultSet rslt = stmt.executeQuery()) {
                    if (rslt.next()) {
                        return fetchQuestions(rslt);
                    } else {
                        throw new IllegalArgumentException("Invalid course id");
                    }
                }
            }
        }
    }

    public List<Questions> getAllQuestions() throws SQLException {
        List<Questions> result = new ArrayList<>();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from Questions");
        ResultSet rsSet = stmt.executeQuery();

        while (rsSet.next()) {
            Questions q = fetchQuestions(rsSet);
            result.add(q);
        }
        stmt.close();
        return result;
    }

    public void insert(Questions entity) throws SQLException {
        try (Connection conn = ds.getConnection()) {

            String query = "INSERT INTO Questions ("
                    + " ID,"
                    + " QType_ID,"
                    + " question) VALUES ("
                    + "?, ?,?)";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, entity.getID());
            stmt.setInt(2, entity.get_questiontype().getQuestionTypeId());
            stmt.setString(3, entity.getQuestion());
            stmt.executeUpdate();
            stmt.close();
        }
    }

    public void updete(Questions entity) throws SQLException {
        String query = "UPDATE Questions SET  QType_ID=?, question=? where ID=?";
        Connection conn = ds.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, entity.get_questiontype().getQuestionTypeId());
        stmt.setString(2, entity.getQuestion());
        stmt.setInt(3, entity.getID());
        stmt.executeUpdate();
        stmt.close();
    }

    public void DeleteById(int id) throws SQLException {
        String query = "delete from Questions where ID=?";
        Connection conn = ds.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }

    private Questions fetchQuestions(ResultSet rslt) throws SQLException {
        Questions question = new Questions();
        question.set_questionId(rslt.getInt("ID"));
        question.setQuestion(rslt.getString("question"));
        QuestionTypeRepository qtrep = new QuestionTypeRepository(null);
        QuestionType qtype = qtrep.getByID(rslt.getInt("QType_ID"));
        question.set_questiontype(qtype);
        return question;
    }

}
