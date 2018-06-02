package Domain;

import java.util.List;

public class UserType {
   private int _userTypeId;
   
   private String _usertypename;
   
   private List<User> users;

   public int get_userTypeId() {
	return _userTypeId;
}

public void set_userTypeId(int _userTypeId) {
	this._userTypeId = _userTypeId;
}

public String get_userTypename() {
	return _usertypename;
}

public void set_userTypename(String _usertyoename) {
	this._usertypename = _usertyoename;
}

public List<User> getUsers() {
	return users;
}

public void setUsers(List<User> users) {
	this.users = users;
}

   
   
   
   
   
}
