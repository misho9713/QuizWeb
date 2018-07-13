package misc;

import java.util.List;
import java.util.function.Function;

public class Utils {
    public static void addIf(StringBuilder queryBuilder, Object param, String column, List<Object> parameters,
                             Function<Object, Boolean> satisfies) {
        if (!satisfies.apply(param)) return;
        appendParameter(queryBuilder, param, column, parameters);
    }

    public static void appendParameter(StringBuilder queryBuilder, Object param, String column, List<Object> parameters) {
        queryBuilder.append(" AND ").append(column).append(" = ?\n");
        parameters.add(param);
    }

    private Utils() {

    }
}
