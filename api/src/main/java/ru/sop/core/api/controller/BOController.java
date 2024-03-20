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
import ru.sop.core.api.dto.bo.BOCreateRq;
import ru.sop.core.api.dto.bo.BORs;
import ru.sop.core.api.dto.bo.BOUpdateRq;
import ru.sop.core.api.dto.data.DataRq;
import ru.sop.core.api.dto.data.PageRs;

public interface BOController {

    @PostMapping("v1/entity/{entityId}/bo/data")
    PageRs getData(@PathVariable("entityId") UUID entityId,
                   @RequestBody @Valid @Nonnull DataRq rq);

    @GetMapping("v1/entity/{entityId}/bo/{BoId}")
    BORs getById(@PathVariable("entityId") UUID entityId,
                 @PathVariable("BoId") UUID boId);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("v1/entity/{entityId}/bo")
    BORs create(@PathVariable("entityId") UUID entityId,
                @RequestBody @Valid @Nonnull BOCreateRq rq);

    @PostMapping("v1/entity/{entityId}/bo/{BOId}")
    BORs patch(@PathVariable("entityId") UUID entityId,
               @PathVariable("BOId") UUID id,
               @RequestBody @Valid @Nonnull BOUpdateRq rq);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("v1/entity/{entityId}/bo/{BoId}")
    void deleteById(@PathVariable("entityId") UUID entityId,
                    @PathVariable("BoId") UUID boId);
}
