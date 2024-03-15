package ru.sop.core.impl.model.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Объект для сортировки значений
 */
@Value
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sort {

    /**
     * Название поля
     */
    String field;


    /**
     * Тип сортировки
     */
    @Builder.Default
    SortType sortType = SortType.ASC;

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
}