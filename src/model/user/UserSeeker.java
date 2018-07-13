package model.user;

import model.common.EntitySeeker;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static misc.DBConstants.*;
import static misc.Utils.addIf;

public class UserSeeker implements EntitySeeker<User> {
    private String userName;
    private int id;
    private User.UserRole role;

    public UserSeeker() {
        userName = null;
        id = -1;
        role = null;
    }

    public String getUserName() {
        return userName;
    }

    public UserSeeker setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public int getId() {
        return id;
    }

    public UserSeeker setId(int id) {
        this.id = id;
        return this;
    }

    public User.UserRole getRole() {
        return role;
    }

    public UserSeeker setRole(User.UserRole role) {
        this.role = role;
        return this;
    }

    @Override
    public Query generateQuery() {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM ").
                append(DB_TABLE_USER).append("\n WHERE 1 = 1\n");
        List<Object> parameters = new ArrayList<>();
        addIf(queryBuilder, userName, DB_COLUMN_USER_NAME, parameters, Objects::nonNull);
        addIf(queryBuilder, role == null ? null : role.toString(), DB_COLUMN_USER_ROLE, parameters, Objects::nonNull);
        addIf(queryBuilder, id, DB_COLUMN_USER_ID, parameters, id -> (int) id > -1);
        return new Query(queryBuilder.toString(), parameters);
    }


}
