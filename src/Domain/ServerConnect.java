package Domain;

import org.apache.commons.dbcp.BasicDataSource;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileInputStream;

public class ServerConnect {
    public static BasicDataSource getDataSource() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("org.apache.commons.pool.KeyedObjectPoolFactory");
        } catch (ClassNotFoundException ignored) {
        }

        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");

        try (JsonReader reader = Json.createReader(new FileInputStream("DB/dbinfo.json"))) {
            JsonObject info = reader.readObject();
            ds.setUrl(info.getString("serverUrl"));
            ds.setUsername(info.getString("username"));
            if (!info.isNull("password")) ds.setPassword(info.getString("passwordF"));
        } catch (Exception e) {
            ds.setUrl("jdbc:mysql://localhost:3306/test_db?characterEncoding=UTF8");
            ds.setUsername("root");
        }
        return ds;
    }
}
