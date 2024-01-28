package ru.sop.core.impl.metadata;

import java.util.Map;
import java.util.UUID;
import ru.sop.core.impl.model.Entity;
import ru.sop.core.impl.model.EntityField;

/**
 * Класс предназначен для получения данных в одном запросе(кафка, рест)
 */
public interface Metadata {

    Entity getEntityById(UUID entityId);

    Map<String, EntityField> getFieldByNameByEntityId(UUID entityId);

}
