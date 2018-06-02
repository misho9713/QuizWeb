package Domain;

import java.util.List;

public class User {
    private int _userID;
    
    private UserType _usertype;
        
	private String  _loginname;
    
    private String _userpassword;
    
    private List<Quiz> createdquizzes;
    
	public UserType get_usertype() {
		return _usertype;
	}

	public void set_usertype(UserType _usertype) {
		this._usertype = _usertype;
	}

    public int get_userID() {
		return _userID;
	}

	public void set_userID(int _userID) {
		this._userID = _userID;
	}

	public String get_loginname() {
		return _loginname;
	}

	public void set_loginname(String _loginname) {
		this._loginname = _loginname;
	}

	public String get_userpassword() {
		return _userpassword;
	}

	public void set_userpassword(String _userpassword) {
		this._userpassword = _userpassword;
	}

	public List<Quiz> getCreatedquizzes() {
		return createdquizzes;
	}

	public void setCreatedquizzes(List<Quiz> createdquizzes) {
		this.createdquizzes = createdquizzes;
	}

    
    
    
}
