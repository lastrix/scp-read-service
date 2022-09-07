package com.lastrix.scp.readservice.dao;

import com.lastrix.scp.lib.rest.Pagination;
import com.lastrix.scp.readservice.model.EnrolleeSelect;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Repository
public class PostgreSpecDao implements SpecDao {
    private final JdbcTemplate jdbc;

    public PostgreSpecDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public long getTotal(UUID specId, int sessionId) {
        var r = jdbc.queryForObject(
                "SELECT COUNT(*) FROM scp_read_service.enrollee_select s WHERE s.spec_id = ? AND s.session_id = ?",
                new Object[]{specId, sessionId},
                (rs, rowNum) -> rs.getLong(1));
        return r == null ? 0 : r;
    }

    @Override
    public Slice<EnrolleeSelect> slice(UUID specId, int sessionId, Pagination pagination) {
        var r = jdbc.query(
                "SELECT * FROM scp_read_service.enrollee_select s WHERE s.spec_id = ? AND s.session_id = ? " +
                        "ORDER BY s.score, s.confirmed_stamp, s.created_stamp " +
                        "LIMIT ? " +
                        "OFFSET ? ",
                new Object[]{specId, sessionId, pagination.getPageSize(), pagination.getPage() * pagination.getPageSize()},
                (rs, rowNum) -> {
                    var s = new EnrolleeSelect();
                    s.setSpecId(specId);
                    s.setSessionId(sessionId);
                    s.setUserId(UUID.fromString(rs.getString(1)));
                    s.setStatus(rs.getShort(2));
                    s.setScore(rs.getInt(3));
                    s.setCreatedStamp(toInstantOrNull(rs, 4));
                    s.setConfirmedStamp(toInstantOrNull(rs, 5));
                    s.setCancelledStamp(toInstantOrNull(rs, 6));
                    s.setModifiedStamp(toInstantOrNull(rs, 7));
                    s.setOrdinal(rs.getShort(8));
                    return s;
                }
        );
        return new SliceImpl<>(r);
    }

    private Instant toInstantOrNull(ResultSet rs, int columnIndex) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnIndex);
        return ts == null ? null : ts.toInstant();
    }
}
