package ru.sop.core.impl.service;

import java.util.Map;
import ru.sop.core.impl.model.BO;
import ru.sop.core.impl.model.EntityField;

public interface BoValidationService {

    void validate(Map<String, EntityField> fieldsByName, BO bo);
}
