package ru.sop.core.impl.service;

import ru.sop.core.impl.model.bo.BOCommand;

public interface BOValidationService {

    void check(BOCommand cmd);
}
