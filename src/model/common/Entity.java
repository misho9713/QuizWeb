package model.common;

import javax.json.JsonObject;

public interface Entity {
    JsonObject toJson();

    int getId();
}












