package ru.sop.core.impl.validator.bo;

import ru.bestclick.exceptionlib.validator.ValidationResult;
import ru.bestclick.exceptionlib.validator.Validator;

public interface BOFieldValidator extends Validator<ValidationResult> {

    class DefaultBoValidator implements BOFieldValidator {

        @Override
        public ValidationResult validate(Object value) {
            return null;
        }

        @Override
        public String getType() {
            return "DEFAULT";
        }
    }
}
