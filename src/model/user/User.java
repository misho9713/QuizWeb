package model.user;

import model.common.Entity;

public class User implements Entity {
    private final int id;
    private final String name;
    private final String password;
    private final UserRole role;

    /**
     * A coonstructor reserved for adding entities into the database
     */
    public User(String name, String password, UserRole role) {
        this(-1, name, password, role);
    }

    public User(int id, String name, String password, UserRole role) {

        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public int getId() {
        return id;
    }

    public enum UserRole {
        USER, ADMINISTRATOR;

    }
}
