package rapositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Domain.Quiz;
import Domain.User;
import Domain.ServerConnect;

public class QuizRepository {
   private DataSource ds;
   
   public QuizRepository(DataSource ds) {
	   this.ds = ds;
   }
   
   public Quiz getById(int id) throws SQLException {
	   
	   try(Connection conn = ds.getConnection()){
		   try(PreparedStatement stmt = conn.prepareStatement("select * from Quiz  where ID = ?")){
			   stmt.setInt(1, id);
			   ResultSet resSet = stmt.executeQuery();
			   if(resSet.next()) {
				   return fetchQuiz(resSet);
			   }
			   else {
				   throw new IllegalArgumentException("Invalid course id");
			   }
		   }
	   }
   }
   public List<Quiz> getAllQuiz() throws SQLException{
	   List<Quiz> result = new ArrayList<>();
	   Connection conn = ds.getConnection();
	   PreparedStatement stmt = conn.prepareStatement("select * from Quizzes");
	   ResultSet rslt = stmt.executeQuery();
	   while(rslt.next()) {
		   result.add(fetchQuiz(rslt));
	   }
	   return result;
   }
   
   public void insert(Quiz entity) throws SQLException {
	   Connection conn = ds.getConnection();
	   String query = "insert into Quizzes(ID,quizz_description,random_question,multiple_page,immediate_correction,practice_mode,"
	   		+ "User_ID,create_time) values(null,???????)";
	     PreparedStatement stmt = conn.prepareStatement(query);
	     stmt.setString(1, entity.get_quizzdescription());
	     stmt.setBoolean(2, entity.is_randomquestion());
	     stmt.setBoolean(3, entity.is_multiplepage());
	     stmt.setBoolean(4, entity.is_multiplepage());
	     stmt.setBoolean(5, entity.is_immediatecorrection());
	     stmt.setBoolean(5, entity.is_practicemode());
	     stmt.setInt(6, entity.get_quizauthor().get_userID());
	     stmt.setDate(7, entity.get_createdate());
	     stmt.executeUpdate();
	     stmt.close();
   }
   public void update (Quiz entity) throws SQLException {
	   Connection conn = ds.getConnection();
	   String query = "update into Quizzes SET quizz_description=?,random_question=?,multiple_page=?,immediate_correction=?,"
	   		+ "practice_mode=?,User_ID=?,create_time=? where ID=?";
	   PreparedStatement stmt = conn.prepareStatement(query);
	     stmt.setBoolean(1, entity.is_randomquestion());
	     stmt.setBoolean(2, entity.is_multiplepage());
	     stmt.setBoolean(3, entity.is_multiplepage());
	     stmt.setBoolean(4, entity.is_immediatecorrection());
	     stmt.setBoolean(5, entity.is_practicemode());
	     stmt.setInt(6, entity.get_quizauthor().get_userID());
	     stmt.setDate(7, entity.get_createdate());
	     stmt.setInt(8, entity.get_id());
         stmt.executeUpdate();
         stmt.close();
	  
   }
   public void deleteById(int id) throws SQLException {
	   Connection conn = ds.getConnection();
	   PreparedStatement stmt = conn.prepareStatement("delete from Quizzes where ID=?");
	   stmt.setInt(1, id);
	   stmt.executeUpdate();
	   stmt.close();
   }
   
   private Quiz fetchQuiz(ResultSet rslt) throws SQLException {
	   Quiz qz = new Quiz();
	   qz.set_id(rslt.getInt("ID"));
	   qz.set_createdate(rslt.getDate("create_time"));
	   qz.set_randomquestion(rslt.getBoolean("random_question"));
	   qz.set_immediatecorrection(rslt.getBoolean("immediate_correction"));
	   qz.set_practicemode(rslt.getBoolean("practice_mode"));
	   qz.set_multiplepage(rslt.getBoolean("multiple_page"));
       qz.set_quizzdescription(rslt.getString("quizz_description"));
       int userId = rslt.getInt("User_ID");
	   UserRepository usrrep = new UserRepository(ServerConnect.getDataSource());
       User usr = usrrep.getById(userId);
       qz.set_quizauthor(usr);
	   return qz;
   }
}
