package ru.sop.core.impl.service.impl;

import org.springframework.stereotype.Service;
import ru.sop.core.impl.enums.OutBoxType;
import ru.sop.core.impl.service.OutBoxService;

@Service
public class OutBoxServiceImpl implements OutBoxService {

    @Override
    public void create(Object bo, OutBoxType type) {

    }
}
