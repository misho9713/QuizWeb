package rapositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Domain.UserType;

public class UserTypeRepository {
	private DataSource ds;

	public UserTypeRepository(DataSource ds) {
    	this.ds = ds;
    }
    public UserType getUserTypeById(int id) throws SQLException {
    	Connection conn = ds.getConnection();
    	PreparedStatement stmt = conn.prepareStatement("select * from UserTypes where ID = ?");
    	stmt.setInt(1, id);
    	ResultSet rslt = stmt.executeQuery();
    	if(rslt.next()) {
    		return fetchUserType(rslt);
    	}
    	else {
            throw new IllegalArgumentException("Invalid course id");
    	}
    }
    public List<UserType> getAllUserType() throws SQLException{
    	List<UserType> result = new ArrayList<>();
    	String query = "select * from UserTypes";
    	Connection conn = ds.getConnection();
    	PreparedStatement stmt = conn.prepareStatement(query);
    	ResultSet rslSet = stmt.executeQuery();
    	while(rslSet.next()) {
    	  UserType usrType	= fetchUserType(rslSet);
    	  result.add(usrType);
    	}
    	stmt.close();
    	return result;
    	
    }
    public void insert(UserType entity) throws SQLException {
    	Connection conn = ds.getConnection();
    	String query = "insert into UserTypes(ID,user_type_name) values(null,?)";
    	PreparedStatement stmt = conn.prepareStatement(query);
    	stmt.setString(1, entity.get_userTypename());
    	stmt.executeUpdate();
    	stmt.close();
    }
    
    public void update(UserType entity) throws SQLException {
    	Connection conn = ds.getConnection();
    	String query ="update UserType SET user_type_name=? where ID = ?";
    	PreparedStatement stmt = conn.prepareStatement(query);
         stmt.setString(1, entity.get_userTypename());
         stmt.setInt(2, entity.get_userTypeId());
         stmt.executeUpdate();
         stmt.close();
    }
    public void deleteById(int id) throws SQLException {
    	Connection conn = ds.getConnection();
    	String query = "delete from UserType where ID =?";
    	PreparedStatement stmt = conn.prepareStatement(query);
    	stmt.setInt(1, id);
    	stmt.executeUpdate();
    	stmt.close();
    }
    private UserType fetchUserType(ResultSet rslt) throws SQLException {
    	UserType usr = new UserType();
    	usr.set_userTypeId(rslt.getInt("ID"));
    	usr.set_userTypename("user_type_name");
    	
    	return usr;
    }
}
