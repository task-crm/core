package ru.sop.core.impl.context;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.collections4.MapUtils;
import ru.sop.core.impl.model.EntityField;
import ru.sop.core.impl.service.EntityFieldService;

public class MemoryContext implements Context {
    private final EntityFieldService entityFieldService;

    @Override
    public Map<String, EntityField> getFieldsByNameByEntityId(UUID entityId) {

        Map lazy = MapUtils.lazyMap(new HashMap(), f);
        Object obj = lazy.get("NOW");
        return null;
    }
}
