package ru.sop.core.impl.service.impl;

import java.util.HashMap;
import java.util.Map;
import lombok.val;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import ru.sop.core.impl.model.bo.BO;
import ru.sop.core.impl.model.bo.BOCommand;
import ru.sop.core.impl.model.entity.field.EntityField;
import ru.sop.core.impl.service.BOFilterService;

@Service
public class BOFilterServiceImpl implements BOFilterService {

    private static Map<String, Object> filterData(Map<String, EntityField> fieldByName, Map<String, Object> data) {
        return MapUtils.emptyIfNull(data).entrySet().stream()
            .filter(entry -> isCanSave(fieldByName.get(entry.getKey())))
            .collect(HashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), Map::putAll);
    }

    private static boolean isCanSave(EntityField entityField) {
        return !entityField.isArchived()
            || !entityField.isReadOnly();
    }

    private static Map<String, Object> filterReferences(Map<String, EntityField> fieldsByName,
                                                        Map<String, Object> references) {
        return MapUtils.emptyIfNull(references).entrySet().stream()
            .filter(entry -> isCanSave(fieldsByName.get(entry.getKey())))
            .collect(HashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), Map::putAll);
    }

    @Override
    public BO filter(BOCommand cmd) {
        val metadata = cmd.getMetadata();
        val fieldByName = metadata.getFieldByNameByEntityId(cmd.getEntityId());
        val bo = cmd.getBo();
        val data = filterData(fieldByName, bo.getData());
        val references = filterReferences(fieldByName, bo.getReferences());
        return bo.of(data, references);
    }
}
