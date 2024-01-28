package ru.sop.core.impl.service.impl;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.bestclick.exceptionlib.util.ValidationUtils;
import ru.sop.core.impl.model.cmd.BOCommand;
import ru.sop.core.impl.service.BOValidationService;
import ru.sop.core.impl.stratagy.BOFieldValidationStrategy;

@RequiredArgsConstructor
@Service
public class BOValidationServiceImpl implements BOValidationService {
    private final BOFieldValidationStrategy boFieldValidationStrategy;

    @Override
    public void check(BOCommand cmd) {
        val metadata = cmd.getMetadata();
        val fieldByName = metadata.getFieldByNameByEntityId(cmd.getEntityId());
        val bo = cmd.getBo();

        val result = Stream.of(bo.getData(), bo.getReferences())
            .flatMap(map -> map.entrySet().stream())
            .map(entry -> boFieldValidationStrategy.getValidator(
                fieldByName.get(fieldByName.get(entry.getKey())).getType()
            ).validate(entry.getValue()))
            .collect(Collectors.toSet());
        ValidationUtils.checkResult(result);
    }
}
