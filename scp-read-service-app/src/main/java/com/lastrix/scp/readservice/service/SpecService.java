package com.lastrix.scp.readservice.service;

import com.lastrix.scp.lib.rest.Pagination;
import com.lastrix.scp.readservice.model.EnrolleeSelect;
import org.springframework.data.domain.Slice;

import java.util.UUID;

public interface SpecService {
    Slice<EnrolleeSelect> slice(UUID specId, int sessionId, Pagination pagination);

    long getTotal(UUID specId, int sessionId);
}
