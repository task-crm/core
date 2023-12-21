package ru.sop.core.impl.service.impl;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sop.core.impl.model.cmd.BOCommand;
import ru.sop.core.impl.service.BOValidationService;
import ru.sop.core.impl.stratagy.BOFieldValidationStrategy;
import ru.sop.core.impl.validator.ValidationResult;

@RequiredArgsConstructor
@Service
public class BOValidationServiceImpl implements BOValidationService {
    private final BOFieldValidationStrategy boFieldValidationStrategy;

    @Override
    public void validate(BOCommand cmd) {
        var metadata = cmd.getMetadata();
        var fieldByName = metadata.getFieldsByEntityId(cmd.getEntityId());
        var bo = cmd.getBo();

        var result = Stream.of(bo.getData(), bo.getReferences())
            .flatMap(map -> map.entrySet().stream())
            .map(entry -> boFieldValidationStrategy.getValidator(
                fieldByName.get(fieldByName.get(entry.getKey())).type()
            ).validate(entry.getValue()))
            .collect(Collectors.toSet());
        checkResult(result);
    }

    private void checkResult(Set<ValidationResult> result) {
        if (result.isEmpty()) {
            return;
        }
        throw new RuntimeException("Multi validation error: " + result);
    }
}
