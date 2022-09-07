package com.lastrix.scp.readservice.web.v1;

import com.lastrix.scp.lib.rest.Pagination;
import com.lastrix.scp.lib.rest.Rest;
import com.lastrix.scp.readservice.model.EnrolleeSelect;
import com.lastrix.scp.readservice.service.SpecService;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/{sessionId}/spec")
public class SpecController {
    private final SpecService srv;

    @Autowired
    public SpecController(SpecService srv) {
        this.srv = srv;
    }

    @Timed("spec_slice")
    @PostMapping("/{specId}")
    public Rest<EnrolleeSelect> slice(@PathVariable UUID specId, @PathVariable int sessionId, @RequestBody Pagination pagination) {
        // we do not allow paging here because this may take too much time,
        // instead we shall provide total endpoint (probably with caching)
        // so each client could get total number of entries for specified
        // specialization
        return Rest.of(srv.slice(specId, sessionId, pagination), pagination);
    }

    @Timed("spec_total")
    @GetMapping("/{specId}/total")
    public Rest<Long> total(@PathVariable UUID specId, @PathVariable int sessionId) {
        return Rest.of(srv.getTotal(specId, sessionId));
    }
}
