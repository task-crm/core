package ru.sop.core.impl.service.impl;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import ru.sop.core.impl.enums.OutBoxType;
import ru.sop.core.impl.model.Audit;
import ru.sop.core.impl.model.BO;
import ru.sop.core.impl.model.EntityField;
import ru.sop.core.impl.model.cmd.BOCommand;
import ru.sop.core.impl.model.cmd.BOCreateCmd;
import ru.sop.core.impl.model.cmd.BOUpdateCmd;
import ru.sop.core.impl.repository.BORepository;
import ru.sop.core.impl.service.BOChangeService;
import ru.sop.core.impl.service.BOValidationService;
import ru.sop.core.impl.service.OutBoxService;

@Slf4j
@Service
@RequiredArgsConstructor
public class BOChangeServiceImpl implements BOChangeService {
    private final BOValidationService boValidationService;
    private final OutBoxService outBoxService;
    private final BORepository boRepository;

    @Override
    //@Transaction
    public BO create(BOCreateCmd cmd) {
        var cmdAfterFilterBo = cmd.of(filterBo(cmd));
        boValidationService.validate(cmdAfterFilterBo);
        var richBO = enrichBeforeCreate(cmdAfterFilterBo.getBo());
        outBoxService.create(richBO, OutBoxType.BO);
        return boRepository.create(richBO);
    }

    //@Transaction
    @Override
    public BO patch(BOUpdateCmd cmd) {
        var cmdAfterFilterBo = cmd.of(filterBo(cmd));
        boValidationService.validate(cmdAfterFilterBo);
        var richBO = enrichBeforeUpdate(cmdAfterFilterBo.getBo());
        outBoxService.create(richBO, OutBoxType.BO);
        return boRepository.update(richBO);
    }

    private static BO filterBo(BOCommand cmd) {
        var metadata = cmd.getMetadata();
        var fieldByName = metadata.getFieldsByEntityId(cmd.getEntityId());
        var bo = cmd.getBo();
        var data = filterData(fieldByName, bo.getData());
        var references = filterReferences(fieldByName, bo.getReferences());
        return bo.toBuilder()
            .data(data)
            .references(references)
            .build();
    }

    private static Map<String, Object> filterData(Map<String, EntityField> fieldsByName, Map<String, Object> data) {
        return MapUtils.emptyIfNull(data).entrySet().stream()
            .filter(entry -> isCanSave(fieldsByName.get(entry.getKey())))
            .collect(HashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), Map::putAll);
    }

    private static boolean isCanSave(EntityField entityField) {
        return !entityField.archived()
            || !entityField.readOnly();
    }

    private static Map<String, Object> filterReferences(Map<String, EntityField> fieldsByName,
                                                        Map<String, Object> references) {
        return MapUtils.emptyIfNull(references).entrySet().stream()
            .filter(entry -> isCanSave(fieldsByName.get(entry.getKey())))
            .collect(HashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), Map::putAll);
    }

    private static BO enrichBeforeCreate(BO bo) {
        return bo.toBuilder()
            .id(bo.getId() == null ? UUID.randomUUID() : bo.getId())
            .audit(getAuditForCreate())
            .build();

    }

    private static Audit getAuditForCreate() {
        var now = OffsetDateTime.now();
        return Audit.builder()
            .createdBy(UUID.randomUUID())
            .updatedBy(UUID.randomUUID())
            .createdDate(now)
            .updateDate(now)
            .build();
    }

    private static BO enrichBeforeUpdate(BO bo) {
        return bo.toBuilder()
            .audit(getAuditForUpdate(bo.getAudit()))
            .build();
    }

    private static Audit getAuditForUpdate(Audit audit) {
        var now = OffsetDateTime.now();
        return audit.toBuilder()
            .updatedBy(UUID.randomUUID())
            .updateDate(now)
            .build();
    }
}
