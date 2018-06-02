package rapositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Domain.QuizTaken;
import Domain.ServerConnect;

public class QuizTakenRepository {
    private DataSource ds;

	public QuizTakenRepository(DataSource ds) {
    	this.ds = ds;
    }
    
    public QuizTaken getById(int id) throws SQLException {
    	Connection conn = ds.getConnection();
    	PreparedStatement stmt = conn.prepareStatement("select * from QuizzesTaken where ID=?");
    	stmt.setInt(1, id);
    	ResultSet rslt = stmt.executeQuery();
    	if(rslt.next()) {
    		return fetchQuizTaken(rslt);
    	}
    	else {
            throw new IllegalArgumentException("Invalid course id");
    	}
    }
    public List<QuizTaken> getAllQuizTaken() throws SQLException{
    	List<QuizTaken> result = new ArrayList<>();
    	Connection conn = ds.getConnection();
    	PreparedStatement stmt = conn.prepareStatement("select * from QuizzesTaken");
    	ResultSet rslt = stmt.executeQuery();
    	while(rslt.next()) {
    		result.add(fetchQuizTaken(rslt));
    	}
    	return result;
    }
    public void insert(QuizTaken entity) throws SQLException {
    	Connection conn = ds.getConnection();
    	String query ="insert into QuizzesTaken(ID,User_ID,Quiz_ID,start_date,end_date,duration,score"
    			+ "values(null,??????)";
    	PreparedStatement stmt = conn.prepareStatement(query);
    	stmt.setInt(1, entity.get_user().get_userID());
    	stmt.setInt(2, entity.get_quiz().get_id());
    	stmt.setDate(3, entity.get_startdate());  
    	stmt.setDate(4, entity.get_enddate());
    	stmt.setTime(5,entity.getDuration());
    	stmt.setFloat(6, entity.get_score());
    	stmt.executeUpdate();
    	stmt.close();
    }
    
    public void update(QuizTaken entity) throws SQLException {
    	Connection conn = ds.getConnection();
    	String query = "update into QuizzesTaken SET User_ID=?,Quiz_ID=?,start_date=?,end_date=?,duration=?,score =? where ID=?";
    	PreparedStatement stmt = conn.prepareStatement(query);
    	stmt.setInt(1, entity.get_user().get_userID());
    	stmt.setInt(2, entity.get_quiz().get_id());
    	stmt.setDate(3, entity.get_startdate());  
    	stmt.setDate(4, entity.get_enddate());
    	stmt.setTime(5,entity.getDuration());
    	stmt.setFloat(6, entity.get_score());
    	stmt.setInt(7, entity.get_quiztakeId());
        stmt.executeUpdate();
        stmt.close();
    
    }
    
    public void deleteById(int id) throws SQLException {
    	Connection conn = ds.getConnection();
    	PreparedStatement stmt = conn.prepareStatement("delet from QuizzesTaken where ID=?");
    	stmt.executeUpdate();
    	stmt.close();
    }
    
    private QuizTaken fetchQuizTaken(ResultSet rslt) throws SQLException {
    	QuizTaken result = new QuizTaken();
    	result.set_quiztakeId(rslt.getInt("ID"));
    	result.set_enddate(rslt.getDate("end_date"));
    	result.set_startdate(rslt.getDate("start_date"));
    	result.set_score(rslt.getFloat("_score"));
    	result.setDuration(rslt.getTime("duration"));
		UserRepository usrep = new UserRepository(ServerConnect.getDataSource());
    	result.set_user(usrep.getById(rslt.getInt("User_ID")));
		QuizRepository qzrep = new QuizRepository(ServerConnect.getDataSource());
    	result.set_quiz(qzrep.getById(rslt.getInt("Quiz_ID")));
    	return result;
    }
}
