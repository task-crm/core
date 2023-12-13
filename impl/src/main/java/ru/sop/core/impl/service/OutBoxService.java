package ru.sop.core.impl.service;

import ru.sop.core.impl.enums.OutBoxType;
import ru.sop.core.impl.model.BO;

public interface OutBoxService {
    void create(Object bo, OutBoxType type);
}
