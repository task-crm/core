package ru.sop.core.impl.service;

import ru.sop.core.impl.enums.OutBoxType;

public interface OutBoxService {
    void create(Object bo, OutBoxType type);
}
