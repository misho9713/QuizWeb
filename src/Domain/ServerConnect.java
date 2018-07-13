package Domain;

import misc.DBConstants;
import org.apache.commons.dbcp.BasicDataSource;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ServerConnect {
    private static ServerConnect instance;

    public static ServerConnect getInstance() {
        return instance == null ? instance = new ServerConnect() : instance;
    }

    private static BasicDataSource getDataSource() {
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

    private final BasicDataSource datasource;

    private ServerConnect() {
        this.datasource = getDataSource();
    }

    public ResultSet executeQuery(String query) throws SQLException {

        return executeQuery(query, Collections.emptyList());
    }

    public int executeUpdate(String query) throws SQLException {
        return executeUpdate(query, Collections.emptyList());
    }

    public ResultSet executeQuery(String query, List<Object> parameters) throws SQLException {

        return (ResultSet) execute(query, false, parameters);
    }

    public int executeUpdate(String query, List<Object> parameters) throws SQLException {
        return (int) execute(query, true, parameters);
    }

    private Object execute(String query, boolean update, List<Object> parameters) throws SQLException {
        PreparedStatement statement = datasource.getConnection().prepareStatement(query);
        try {
            statement.executeQuery("USE " + DBConstants.MYSQL_DATABASE_NAME + ";");
        } catch (SQLException e) {
            try {
                statement.close();
            } catch (SQLException ignore) {

            }
        }
        for (int i = 0; i < parameters.size(); i++) {
            statement.setObject(i + 1, parameters.get(i));
        }
        if (update) {
            int result = 0;
            try {
                result = statement.executeUpdate();
            } catch (SQLException e) {
                try {
                    statement.close();
                } catch (SQLException ignored) {

                }
            }
            statement.close();
            return result;
        }
        return statement.executeQuery();
    }
}
