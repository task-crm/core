package ru.sop.core.impl.service;

import ru.sop.core.impl.model.bo.BO;
import ru.sop.core.impl.model.bo.BOCreateCmd;
import ru.sop.core.impl.model.bo.BOUpdateCmd;

public interface BOChangeService {

    BO create(BOCreateCmd cmd);

    BO patch(BOUpdateCmd cmd);
}
