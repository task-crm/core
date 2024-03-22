package ru.sop.core.impl.stratagy;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sop.core.impl.enums.FieldTypeEnum;
import ru.sop.core.impl.validator.bo.BOFieldValidator;

@Component
public class BOFieldValidationStrategy {
    private final Map<FieldTypeEnum, BOFieldValidator> validatorsByFieldType;

    @Autowired
    public BOFieldValidationStrategy(List<BOFieldValidator> validators) {
        this.validatorsByFieldType = validators.stream()
            .collect(Collectors.toMap(validator -> FieldTypeEnum.valueOf(validator.getType()), Function.identity()));
    }

    public BOFieldValidator getValidator(FieldTypeEnum fieldType) {
        val validator = validatorsByFieldType.get(fieldType);
        if (validator != null) {
            return validator;
        }
        return new BOFieldValidator.DefaultBoValidator();
    }
}
