package model.common;

import java.util.List;

public interface EntitySeeker<E extends Entity> {
    Query generateQuery();

    class Query {
        private final String sql;
        private final List<Object> parameters;

        public String getSql() {
            return sql;
        }

        public List<Object> getParameters() {
            return parameters;
        }

        public Query(String sql, List<Object> parameters) {
            this.sql = sql;
            this.parameters = parameters;
        }
    }
}
