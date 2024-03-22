package ru.sop.core.impl.model.entity;

import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * В этом классе содержатся настройки для сущности {@link Entity}
 */
@Value
@Builder(toBuilder = true)
@Jacksonized
@EqualsAndHashCode(of = {"entityId"})
public class EntitySettings {
    UUID entityId;
}
