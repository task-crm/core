package ru.sop.core.impl.stratagy;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sop.core.impl.enums.FieldTypeEnum;
import ru.sop.core.impl.validator.bo.BOFieldValidator;

@Component
@RequiredArgsConstructor
public class BOFieldValidationStrategy {
    private final Map<FieldTypeEnum, BOFieldValidator> validatorsByFieldType;

    public BOFieldValidator getValidator(FieldTypeEnum fieldType) {
        var validator = validatorsByFieldType.get(fieldType);
        if (validator != null) {
            return validator;
        }
        throw new RuntimeException("Validator for business object not found");
    }
}
