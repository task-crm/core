package ru.sop.core.impl.service.impl;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sop.core.impl.repository.BORepository;
import ru.sop.core.impl.service.BODeleteService;

@Service
@RequiredArgsConstructor
public class BODeleteServiceImpl implements BODeleteService {
    private final BORepository boRepository;

    @Override
    public void deleteById(UUID id) {
        boRepository.deleteById(id);
    }
}
