package ru.sop.core.api.controller;

import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.sop.core.api.dto.rq.BOCreateRq;
import ru.sop.core.api.dto.rq.BOUpdateRq;
import ru.sop.core.api.dto.rq.QueryRq;
import ru.sop.core.api.dto.rs.BORs;
import ru.sop.core.api.dto.rs.PageRs;

public interface BOController {

    @PostMapping("v1/bo/page")
    PageRs getPage(@RequestBody @Valid @Nonnull QueryRq rq);

    @GetMapping("v1/bo/{id}")
    BORs getById(@PathVariable("id") UUID id);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("v1/bo/entity/{entityId}")
    BORs create(@RequestBody @Valid @Nonnull BOCreateRq rq,
                @PathVariable("entityId") UUID entityId);

    @PostMapping("v1/bo/{id}/entity/{entityId}")
    BORs patch(@PathVariable("id") UUID id,
               @RequestBody @Valid @Nonnull BOUpdateRq rq,
               @PathVariable("entityId") UUID entityId);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("v1/bo/{id}")
    void deleteById(@PathVariable("id") UUID id);
}
