package model.user;

import model.common.EntityManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class UserManager implements EntityManager<User, UserSeeker> {
    public static User getUser(ResultSet results) throws SQLException {
        return new User();
    }

    @Override
    public Collection<User> findEntities(UserSeeker seeker) {
        return null;
    }

    @Override
    public boolean addEntry(User entry) {
        return false;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }
}
