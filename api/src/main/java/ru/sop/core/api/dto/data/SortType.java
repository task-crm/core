package ru.sop.core.api.dto.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 * Набор операций для сортировки
 */
@RequiredArgsConstructor
public enum SortType {
    ASC("ASC"),
    DESC("DESC");

    private static final Map<String, SortType> ENUM_BY_TYPE = Stream.of(values())
        .collect(Collectors.toMap(SortType::getType, Function.identity()));

    private final String type;

    @JsonCreator
    public static SortType getSortType(String type) {
        return Optional.ofNullable(ENUM_BY_TYPE.get(type))
            .orElseThrow(() -> new IllegalArgumentException(type));
    }

    @JsonValue
    public String getType() {
        return this.type;
    }
}