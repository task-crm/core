package ru.sop.core.impl.context;

import java.util.Map;
import java.util.UUID;
import ru.sop.core.impl.model.EntityField;

/**
 * Класс предназначен для получения данных в одном запросе(кафка, рест)
 */
public interface Context {

    Map<String, EntityField> getFieldsByNameByEntityId(UUID entityId);

}
