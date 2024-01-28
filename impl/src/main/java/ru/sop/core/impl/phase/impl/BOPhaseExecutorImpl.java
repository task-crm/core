package ru.sop.core.impl.phase.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import ru.sop.core.impl.model.BO;
import ru.sop.core.impl.model.cmd.BOCommand;
import ru.sop.core.impl.phase.BOPhaseExecutor;
import ru.sop.core.impl.phase.BOPhaseOrderRegulator;

@Component
@RequiredArgsConstructor
public class BOPhaseExecutorImpl implements BOPhaseExecutor {
    private final BOPhaseOrderRegulator phaseRegulator;

    @Override
    public BO preSave(BOCommand cmd) {
        val phases = phaseRegulator.getPhases();
        if (CollectionUtils.isEmpty(phases)) {
            return cmd.getBo();
        }
        for (val phase : phaseRegulator.getPhases()) {
            cmd = cmd.of(phase.preSave(cmd));
        }
        return cmd.getBo();
    }

    @Override
    public void postSave(BOCommand cmd) {
        val phases = phaseRegulator.getPhases();
        if (CollectionUtils.isEmpty(phases)) {
            return;
        }
        for (val phase : phaseRegulator.getPhases()) {
            cmd = cmd.of(phase.postSave(cmd));
        }
    }

    @Override
    public BO preDelete(BOCommand cmd) {
        val phases = phaseRegulator.getPhases();
        if (CollectionUtils.isEmpty(phases)) {
            return cmd.getBo();
        }
        for (val phase : phaseRegulator.getPhases()) {
            cmd = cmd.of(phase.preDelete(cmd));
        }
        return cmd.getBo();
    }

    @Override
    public void postDelete(BOCommand cmd) {
        val phases = phaseRegulator.getPhases();
        if (CollectionUtils.isEmpty(phases)) {
            return;
        }
        for (val phase : phaseRegulator.getPhases()) {
            cmd = cmd.of(phase.postDelete(cmd));
        }
    }
}
