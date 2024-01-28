package ru.sop.core.impl.service;

import ru.sop.core.impl.model.cmd.BOCommand;

public interface BOValidationService {

    void check(BOCommand cmd);
}
