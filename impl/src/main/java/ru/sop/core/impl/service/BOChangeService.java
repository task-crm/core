package ru.sop.core.impl.service;

import ru.sop.core.impl.model.BO;
import ru.sop.core.impl.model.cmd.BOCreateCmd;
import ru.sop.core.impl.model.cmd.BOUpdateCmd;

public interface BOChangeService {

    BO create(BOCreateCmd cmd);

    BO patch(BOUpdateCmd cmd);
}
