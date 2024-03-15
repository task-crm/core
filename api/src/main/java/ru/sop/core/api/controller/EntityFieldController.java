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
import ru.sop.core.api.dto.data.DataRq;
import ru.sop.core.api.dto.data.DataRs;
import ru.sop.core.api.dto.entity.field.EntityFieldCreateRq;
import ru.sop.core.api.dto.entity.field.EntityFieldRs;
import ru.sop.core.api.dto.entity.field.EntityFieldUpdateRq;

public interface EntityFieldController {
    @PostMapping("v1/entity/{entityId}/field")
    @ResponseStatus(HttpStatus.CREATED)
    EntityFieldRs create(@PathVariable("entityId") UUID entityId,
                         @RequestBody @Valid EntityFieldCreateRq rq);

    @PutMapping("v1/entity/{entityId}/field/{fieldId}")
    EntityFieldRs update(@PathVariable("entityId") UUID entityId,
                         @PathVariable("fieldId") UUID fieldId,
                         @RequestBody @Valid EntityFieldUpdateRq rq);

    @DeleteMapping("v1/entity/{entityId}/field/{fieldId}")
    void deleteById(@PathVariable("entityId") UUID entityId,
                    @PathVariable("fieldId") UUID fieldId);

    @GetMapping("v1/entity/{entityId}/field/{fieldId}")
    EntityFieldRs getById(@PathVariable("entityId") UUID entityId,
                          @PathVariable("fieldId") UUID fieldId);

    @GetMapping("v1/entity/{entityId}/field/data")
    DataRs getData(@PathVariable("entityId") UUID entityId,
                   @RequestBody DataRq rq);
}
