package ru.sop.core.api.dto.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Набор операций для фильтрации
 */
@RequiredArgsConstructor
public enum Operation {
    GT("gt"),
    GTE("gte"),
    LT("lt"),
    LTE("lte"),
    EQUAL("equal"),
    NOT_EQUAL("not equal"),
    CONTAINS("contains"),
    IN("in"),
    NOT_IN("not in"),
    IS_NULL("is null"),
    IS_NOT_NULL("is not null"),
    IS_EMPTY("is empty"),
    IS_NOT_EMPTY("is not empty"),
    BETWEEN("between"),
    IS_NULL_OR_NOT_EQUAL("is null or not equal"),
    STARTS_WITH("starts with"),
    ENDS_WITH("ends with"),
    OR("or", true),
    AND("and", true),
    NOT("not", true),
    TRUE("true"),
    FALSE("false");

    private static final Map<String, Operation> ENUM_BY_NAME = Stream.of(values())
        .collect(Collectors.toMap(Operation::getName, Function.identity()));

    private final String name;
    @Getter
    private final boolean nested;

    Operation(String name) {
        this.name = name;
        this.nested = false;
    }

    @JsonCreator
    public static Operation getOperation(String name) {
        return Optional.ofNullable(ENUM_BY_NAME.get(name))
            .orElseThrow(() -> new IllegalArgumentException(name));
    }

    @JsonValue
    public String getName() {
        return this.name;
    }
}