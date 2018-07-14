package model.user;

import Domain.ServerConnect;
import misc.Encryption;
import model.common.EntityManager;
import model.common.EntitySeeker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static misc.DBConstants.*;

public class UserManager implements EntityManager<User, UserSeeker> {
    public static User getUser(ResultSet results) throws SQLException {

        return new User(results.getInt(DB_COLUMN_USER_ID), results.getString(DB_COLUMN_USER_NAME),
                results.getString(DB_COLUMN_USER_PASSWORD), User.UserRole.valueOf(results.getString(DB_COLUMN_USER_ROLE).toUpperCase()));
    }

    @Override
    public List<User> findEntities(UserSeeker seeker) {
        List<User> users = new ArrayList<>();
        EntitySeeker.Query query = seeker.generateQuery();
        try (ResultSet results = ServerConnect.getInstance().executeQuery(query.getSql(), query.getParameters())) {
            while (results.next()) {

                users.add(UserManager.getUser(results));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean addEntry(User entry) {
        int rows;
        try {
            rows = ServerConnect.getInstance().executeUpdate(
                    String.format("INSERT  INTO %s(%s, %s, %s)" +
                                    "VALUE (?,?,?)",
                            DB_TABLE_USER, DB_COLUMN_USER_NAME,
                            DB_COLUMN_USER_PASSWORD, DB_COLUMN_USER_ROLE),
                    Arrays.asList(entry.getName(), Encryption.encrypt(entry.getPassword()), entry.getRole().toString()));
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

    @Override
    public User get(int id) {
        EntitySeeker.Query query = new UserSeeker().setId(id).generateQuery();
        try (ResultSet rs = ServerConnect.getInstance().executeQuery(query.getSql(), query.getParameters())) {
            if (rs.next()) return getUser(rs);
        } catch (SQLException ignored) {

        }
        return null;
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
