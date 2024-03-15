package ru.sop.core.api.dto.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@JsonPropertyOrder(value = {
    "field",
    "secondField",
    "operation",
    "values"
})
@Schema(title = "Объект для фильтрации значений")
@Value
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterDto {
    private static final String FIELD_NAME_REGEX = "^[a-zA-Z0-9.$:^_]+";

    @Schema(title = "Название поля")
    @NotBlank
    String field;

    @Schema(title = "Название поля для операций с двумя полями(OVERLAPS)")
    String secondField;

    @Schema(title = "Тип операции")
    @NotNull
    Operation operation;

    @Schema(title = "Значение поля")
    @NotNull
    @Valid
    @Size.List(@Size(min = 1, max = 30))
    List<@NotNull Object> values;

    @RequiredArgsConstructor
    public enum Operation {
        GT("gt"),
        GTE("gte"),
        LT("lt"),
        LTE("lte"),
        EQUAL("equal"),
        EQUAL_DAY_OF_MONTH("equal day of month"),
        EQUAL_MONTH("equal month"),
        EQUAL_YEAR("equal year"),
        NOT_EQUAL("not equal"),
        CONTAINS("contains"),
        CONTAINS_ANY("contains_any"),
        IN("in"),
        NOT_IN("not in"),
        IS_NULL("is null"),
        IS_NOT_NULL("is not null"),
        IS_EMPTY("is empty"),
        IS_NOT_EMPTY("is not empty"),
        BETWEEN("between"),
        OVERLAPS("overlaps"),
        //not equal не корректно работает если поле которое сравнивается не существует в объекте
        IS_NULL_OR_NOT_EQUAL("is null or not equal"),
        STARTS_WITH("starts with"),
        ENDS_WITH("ends with"),
        OR("or"),
        AND("and"),
        NOT("not"),
        TRUE("true"),
        FALSE("false");

        private static final Map<String, Operation> ENUM_BY_NAME = Arrays.stream(values())
            .collect(Collectors.toMap(Operation::getName, Function.identity()));

        private final String name;

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
}
