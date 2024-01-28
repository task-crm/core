package ru.sop.core.impl.phase.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sop.core.impl.phase.BOPhase;
import ru.sop.core.impl.phase.BOPhaseOrderRegulator;


@Component
@RequiredArgsConstructor
public class BOPhaseOrderRegulatorImpl implements BOPhaseOrderRegulator {
    private final BOPhase boMockPhaseImpl;

    @Override
    public List<BOPhase> getPhases() {
        return List.of(
            boMockPhaseImpl
        );
    }
}
