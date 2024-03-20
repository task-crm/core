package ru.sop.core.impl.service.impl;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sop.core.impl.enums.OutBoxType;
import ru.sop.core.impl.helper.AuditHelper;
import ru.sop.core.impl.model.bo.BO;
import ru.sop.core.impl.model.bo.BOCreateCmd;
import ru.sop.core.impl.model.bo.BOUpdateCmd;
import ru.sop.core.impl.repository.BORepository;
import ru.sop.core.impl.service.BOChangeService;
import ru.sop.core.impl.service.BOFilterService;
import ru.sop.core.impl.service.BOValidationService;
import ru.sop.core.impl.service.OutBoxService;

@Slf4j
@Service
@RequiredArgsConstructor
public class BOChangeServiceImpl implements BOChangeService {
    private final BOValidationService boValidationService;
    private final BOFilterService filterService;
    private final OutBoxService outBoxService;
    private final BORepository boRepository;
    private final AuditHelper auditHelper;

    @Override
    @Transactional
    public BO create(BOCreateCmd cmd) {
        val cmdAfterFilterBo = cmd.of(filterService.filter(cmd));
        boValidationService.check(cmdAfterFilterBo);
        val richBO = enrichBeforeCreate(cmdAfterFilterBo.getBo());
        outBoxService.create(richBO, OutBoxType.BO);
        boRepository.create(richBO);
        return richBO;
    }

    @Override
    @Transactional
    public BO patch(BOUpdateCmd cmd) {
        val cmdAfterFilterBo = cmd.of(filterService.filter(cmd));
        boValidationService.check(cmdAfterFilterBo);
        val richBO = enrichBeforeUpdate(cmdAfterFilterBo.getBo());
        outBoxService.create(richBO, OutBoxType.BO);
        boRepository.update(richBO);
        return richBO;
    }

    private BO enrichBeforeCreate(BO bo) {
        return bo.toBuilder()
            .id(bo.getId() == null ? UUID.randomUUID() : bo.getId())
            .audit(auditHelper.getForCreate())
            .build();
    }

    private BO enrichBeforeUpdate(BO bo) {
        return bo.toBuilder()
            .audit(auditHelper.getForUpdate(bo.getAudit()))
            .build();
    }
}
