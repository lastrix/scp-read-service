package com.lastrix.scp.readservice.dao;

import com.lastrix.scp.lib.rest.Pagination;
import com.lastrix.scp.readservice.model.EnrolleeSelect;
import org.springframework.data.domain.Slice;

import java.util.UUID;

public interface SpecDao {
    long getTotal(UUID specId, int sessionId);

    Slice<EnrolleeSelect> slice(UUID specId, int sessionId, Pagination pagination);
}
