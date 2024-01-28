package ru.sop.core.impl.model;

import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * В этом классе содержатся настройки для сущности {@link Entity}
 */
@Value
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"entityId"})
public class EntitySettings {
    UUID entityId;
}
