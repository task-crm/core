package ru.sop.core.impl.jooq;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jooq.Param;
import org.jooq.impl.DSL;

/**
 * Утилита для работы с именованными параметрами JOOQ
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JooqParamUtils {

    public static Param<Object> first(Object value) {
        return toParams(value).stream()
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("value is mandatory"));
    }

    public static List<Param<Object>> toParams(Object value) {
        return toList(value).stream()
            .map(val -> {
                if (val instanceof Number) {
                    return val;
                }
                return Objects.toString(val);
            })
            .map(DSL::value)
            .toList();
    }

    public static List<Object> toList(Object value) {
        if (value instanceof Collection) {
            return new ArrayList<>((Collection<?>) value);
        } else {
            return List.of(value);
        }
    }

    public static List<Param<Object>> getListWithAtLeastTwoParams(Object value, String operator) {
        if (!(value instanceof Collection)) {
            throw new IllegalArgumentException("Operator " + operator + " can be used only with list values");
        }
        List<Param<Object>> values = toParams(value);
        if (values.size() != 2) {
            throw new IllegalArgumentException("Operator " + operator + " can be used only with two values");
        }
        return values;
    }
}
