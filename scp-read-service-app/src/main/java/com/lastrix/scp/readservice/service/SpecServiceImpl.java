package com.lastrix.scp.readservice.service;

import com.lastrix.scp.lib.rest.Pagination;
import com.lastrix.scp.readservice.dao.SpecDao;
import com.lastrix.scp.readservice.model.EnrolleeSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class SpecServiceImpl implements SpecService {
    private final SpecDao dao;

    @Autowired
    public SpecServiceImpl(SpecDao dao) {
        this.dao = dao;
    }

    @Transactional(readOnly = true)
    @Override
    public Slice<EnrolleeSelect> slice(UUID specId, int sessionId, Pagination pagination) {
        return dao.slice(specId, sessionId, pagination);
    }

    @Transactional(readOnly = true)
    @Override
    public long getTotal(UUID specId, int sessionId) {
        return dao.getTotal(specId, sessionId);
    }
}
