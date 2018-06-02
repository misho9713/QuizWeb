package rapositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Domain.User;
import Domain.UserType;
import Domain.ServerConnect;

public class UserRepository {
   private  DataSource ds;

	public UserRepository(DataSource ds) {
    	this.ds = ds;
    }
    
    public User getById(int id) throws SQLException {
    	Connection conn = ds.getConnection();
    	PreparedStatement stmt = conn.prepareStatement("select * from Users where ID=?");
    	stmt.setInt(1, id);
    	ResultSet rslt = stmt.executeQuery();
    	if(rslt.next()) {
    		return fetchUsers(rslt);
    	}
    	else {
            throw new IllegalArgumentException("Invalid course id");
    	}
    }
    public List<User> getAllUser() throws SQLException{
    	List<User> result = new ArrayList<>();
    	Connection conn = ds.getConnection();
    	PreparedStatement stmt = conn.prepareStatement("select * from Users");
    	ResultSet rslt = stmt.executeQuery();
    	while(rslt.next()) {
    		result.add(fetchUsers(rslt));
    	}
    	return result;
    }
    public void insert(User entity) throws SQLException {
    	Connection conn = ds.getConnection();
    	String query = "insert into Users(ID,UserT_ID,login_name,user_password) values(null,?,?,?)";
    	PreparedStatement stmt = conn.prepareStatement(query);
    	stmt.setInt(1,entity.get_usertype().get_userTypeId());
    	stmt.setString(2, entity.get_loginname());
    	stmt.setString(3, entity.get_userpassword());
    	stmt.executeUpdate();
    	stmt.close();
    	
    }
    public void update(User entity) throws SQLException {
    	Connection conn = ds.getConnection();
    	String query = "update into Users SET UserT_ID=?,login_name=?,user_password=? where ID=?";
    	PreparedStatement stmt = conn.prepareStatement(query);
    	stmt.setInt(1, entity.get_usertype().get_userTypeId());
    	stmt.setString(2, entity.get_loginname());
    	stmt.setString(3, entity.get_userpassword());
    	stmt.setInt(4, entity.get_userID());
    	stmt.executeUpdate();
    	stmt.close();
    }
    public void deleteById(int id) throws SQLException {
    	Connection conn = ds.getConnection();
    	String query = "delete from Users where ID=?";
    	PreparedStatement stmt = conn.prepareStatement(query);
    	stmt.setInt(1, id);
    	stmt.executeUpdate();
    	stmt.close();
    	
    }
    public User fetchUsers(ResultSet rslt) throws SQLException {
    	User usr = new User();
    	usr.set_loginname(rslt.getString("login_name"));
    	usr.set_userID(rslt.getInt("ID"));
    	usr.set_userpassword(rslt.getString("user_password"));
		UserTypeRepository userRep = new UserTypeRepository(ServerConnect.getDataSource());
    	int usrtypeid = rslt.getInt("UserT_ID");
    	UserType usrtype = userRep.getUserTypeById(usrtypeid);
    	usr.set_usertype(usrtype);
    	return usr;
    }
}
