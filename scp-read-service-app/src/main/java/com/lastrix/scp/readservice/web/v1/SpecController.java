package com.lastrix.scp.readservice.web.v1;

import com.lastrix.scp.lib.rest.Pagination;
import com.lastrix.scp.lib.rest.Rest;
import com.lastrix.scp.lib.rest.authz.RequireRoles;
import com.lastrix.scp.readservice.model.EnrolleeSelect;
import com.lastrix.scp.readservice.service.SpecService;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @RequireRoles("USER")
    @Timed("spec_slice")
    @PostMapping("/{specId}")
    @Schema(description = "Retrieve slice of application for selected speciality and selection campaign")
    public Rest<EnrolleeSelect> slice(
            @PathVariable UUID specId,
            @PathVariable int sessionId,
            @RequestBody Pagination pagination) {
        // we do not allow paging here because this may take too much time,
        // instead we shall provide total endpoint (probably with caching)
        // so each client could get total number of entries for specified
        // specialization without requesting page count/total count with
        // each request
        return Rest.of(srv.slice(specId, sessionId, pagination), pagination);
    }

    @RequireRoles("USER")
    @Timed("spec_total")
    @GetMapping("/{specId}/total")
    @Schema(description = "Get total number of applications for selected speciality and selection campaign")
    public Rest<Long> total(@PathVariable UUID specId, @PathVariable int sessionId) {
        return Rest.of(srv.getTotal(specId, sessionId));
    }
}
