package Domain;

import org.apache.commons.dbcp.BasicDataSource;

public class ServerConnect {
    public static BasicDataSource getDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/test_db?characterEncoding=UTF8");
        ds.setUsername("root");
        ds.setPassword("1234578");
        return ds;
    }
}
