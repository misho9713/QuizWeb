package rapositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Domain.QuestionType;

public class QuestionTypeRepository {
    private final DataSource ds;

    public QuestionTypeRepository(DataSource ds) {
        this.ds = ds;
    }

    public QuestionType getByID(int id) throws SQLException {
        try (Connection conn = ds.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement("select * from QuestionTypes  where ID = ?")) {
                stmt.setInt(1, id);

                try (ResultSet rslt = stmt.executeQuery()) {
                    if (rslt.next()) {
                        return fetchQuestionType(rslt);
                    } else {
                        throw new IllegalArgumentException("Invalid course id");
                    }
                }
            }
        }

    }

    public List<QuestionType> getAllQuestionType() throws SQLException {
        List<QuestionType> resSet = new ArrayList<>();
        try (Connection conn = ds.getConnection()) {
            try (PreparedStatement stm = conn.prepareStatement("select  * from QuestionTypes as qt order by ID desc")) {
                try (ResultSet res = stm.executeQuery()) {
                    while (res.next()) {
                        resSet.add(fetchQuestionType(res));
                    }
                    return resSet;
                }
            }

        }

    }

    public void Update(QuestionType entity) throws SQLException {
        Connection conn = ds.getConnection();
        String query = "update QuestionTypes SET question_name=? where ID=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, entity.getQuestionType());
        stmt.setInt(2, entity.getQuestionTypeId());
        stmt.executeUpdate();
        stmt.close();
    }

    public void insert(QuestionType entity) throws SQLException {
        Connection conn = ds.getConnection();
        String query = "insert into QuestionTypes(ID,question_name) values(null,?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, entity.getQuestionType());
        stmt.executeUpdate();
        stmt.close();
    }

    public void deleteById(int id) throws SQLException {
        Connection conn = ds.getConnection();
        String query = "delete from QuestionTypes where ID=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }

    public QuestionType fetchQuestionType(ResultSet rslt) throws SQLException {
        QuestionType qtype = new QuestionType();
        qtype.setQuestionTypeId(rslt.getInt("ID"));
        qtype.setQuestionType(rslt.getString("question_name"));

        return qtype;

    }

}
