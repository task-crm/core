package ru.sop.core.impl.model.entity.field;

import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * В этом классе содержатся настройки для полей сущности {@link EntityField}
 */
@Value
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"fieldId"})
public class EntityFieldSettings {
    UUID fieldId;
}
