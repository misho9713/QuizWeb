package model.user;

import Domain.ServerConnect;
import model.common.EntityManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;

import static misc.DBConstants.*;

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
        int rows;
        try {
            rows = ServerConnect.getInstance().executeUpdate(
                    String.format("INSERT  INTO %s(%s, %s, %s)" +
                                    "VALUE (?,?,?)",
                            DB_TABLE_USER, DB_COLUMN_USER_NAME,
                            DB_COLUMN_USER_PASSWORD, DB_COLUMN_USER_ROLE));
        } catch (Exception e) {
            return false;
        }
        return rows != 0;


    }

    @Override
    public boolean remove(int id) {
        int rows;
        try {
            rows = ServerConnect.getInstance().executeUpdate(
                    String.format("DELETE * FROM %s WHERE %s.%s = ?", DB_TABLE_USER, DB_TABLE_USER, DB_COLUMN_USER_ID),
                    Collections.singletonList(id));
        } catch (Exception e) {
            return false;
        }
        return rows != 0;
    }

    public boolean remove(String id) {
        int rows;
        try {
            rows = ServerConnect.getInstance().executeUpdate(
                    String.format("DELETE * FROM %s WHERE %s.%s = ?", DB_TABLE_USER, DB_TABLE_USER, DB_COLUMN_USER_NAME),
                    Collections.singletonList(id));
        } catch (Exception e) {
            return false;
        }
        return rows != 0;
    }
}
