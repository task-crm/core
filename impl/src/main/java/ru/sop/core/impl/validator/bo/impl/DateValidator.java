package ru.sop.core.impl.validator.bo.impl;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Component;
import ru.bestclick.exceptionlib.validator.ValidationResult;
import ru.bestclick.exceptionlib.validator.impl.ValidationResultDefault;
import ru.sop.core.impl.enums.FieldTypeEnum;
import ru.sop.core.impl.validator.bo.BOFieldValidator;

@Component
public class DateValidator implements BOFieldValidator {

    @Override
    @Nullable
    public ValidationResult validate(Object date) {
        if (date instanceof Date) {
            return null;
        }

        try {
            LocalDate.parse(Objects.toString(date));
        } catch (DateTimeParseException ex) {
            return ValidationResultDefault.builder()
                .message("field.type.date.not_valid")
                .logMessage("field.type.date.not_valid")
                .build();
        }
        return null;
    }

    @Override
    public String getType() {
        return FieldTypeEnum.DATE.getType();
    }
}
