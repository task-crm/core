package ru.sop.core.impl.model.page;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import ru.sop.core.api.dto.page.Operation;

/**
 * Объект для фильтрации значений
 */
@JsonPropertyOrder(value = {
    "field",
    "secondField",
    "operation",
    "values"
})
@Value
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Filter {
    /**
     * Название поля
     */
    String field;

    /**
     * Название поля для операций с двумя полями(OVERLAPS)
     */
    String secondField;

    /**
     * Тип операции
     */
    Operation operation;

    /**
     * Значение поля
     */
    List<Object> values;
}
