package ru.sop.core.impl.model.entity.field;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * В этом классе содержатся настройки для полей сущности {@link EntityField}
 */
@Value
@Builder(toBuilder = true)
@AllArgsConstructor
@Jacksonized
@EqualsAndHashCode(of = {"fieldId"})
public class EntityFieldSettings {
    UUID fieldId;
}
