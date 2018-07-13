package model.user;

import model.common.Entity;

public class User implements Entity {
    private int id;

    @Override
    public int getId() {
        return id;
    }
}
