package ru.sop.core.impl.validator.bo.impl;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sop.core.impl.model.BO;
import ru.sop.core.impl.model.EntityField;
import ru.sop.core.impl.stratagy.BOFieldValidationStrategy;
import ru.sop.core.impl.validator.ValidationResult;
import ru.sop.core.impl.validator.bo.BOFiledValidationExecutor;

@Component
@RequiredArgsConstructor
public class BOFiledValidationExecutorImpl implements BOFiledValidationExecutor {
    private final BOFieldValidationStrategy boFieldValidationStrategy;

    @Override
    public Set<ValidationResult> execute(Map<String, EntityField> fieldsByName, BO bo) {
        return Stream.of(bo.getData(), bo.getReferences())
            .flatMap(map -> map.entrySet().stream())
            .map(entry -> boFieldValidationStrategy.getValidator(
                fieldsByName.get(fieldsByName.get(entry.getKey())).type()
            ).validate(entry.getValue()))
            .collect(Collectors.toSet());
    }

}
