package ru.sop.core.impl.validator.bo.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sop.core.impl.validator.ValidationResult;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResultImpl implements ValidationResult {
    private String message;
    private String logMessage;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getLogMessage() {
        return logMessage;
    }
}
