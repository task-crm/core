package ru.sop.core.api.controller;

import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.sop.core.api.dto.entity.EntityCreateRq;
import ru.sop.core.api.dto.entity.EntityRs;
import ru.sop.core.api.dto.entity.EntityUpdateRq;

public interface EntityController {
    @PostMapping("v1/entity")
    @ResponseStatus(HttpStatus.CREATED)
    EntityRs create(@RequestBody @Valid EntityCreateRq entity);

    @PutMapping("v1/entity/{id}")
    EntityRs update(@PathVariable("id") UUID id,
                    @RequestBody @Valid EntityUpdateRq entity);

    @DeleteMapping("v1/entity/{id}")
    void deleteById(@PathVariable("id") UUID id);

    @GetMapping("v1/entity/{id}")
    EntityRs getById(@PathVariable("id") UUID id);
}
