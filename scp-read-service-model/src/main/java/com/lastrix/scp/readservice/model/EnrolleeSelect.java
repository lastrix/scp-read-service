package com.lastrix.scp.readservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.UUID;

/**
 * This class hold information about enrollee selection change
 */
@Schema(description = "Enrollee selected speciality")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnrolleeSelect {
    @Schema(description = "Enrollee user id")
    private UUID userId;
    @Schema(description = "Selection campaign id")
    private int sessionId;
    @Schema(description = "The selected speciality id")
    private UUID specId;
    @Schema(description = "Status of selection: 0 for applied, 1 for confirmed and 2 for cancelled")
    private short status;
    @Schema(description = "Assigned enrollee score")
    private int score;
    @Schema(description = "Date of enrollee's application")
    private Instant createdStamp;
    @Schema(description = "Date of enrollee's selection confirmation")
    private Instant confirmedStamp;
    @Schema(description = "Date of enrollee's selection cancelling")
    private Instant cancelledStamp;
    @Schema(description = "Last modification date")
    private Instant modifiedStamp;
    /**
     * Change number of this record
     */
    @Schema(description = "Change number")
    private short ordinal;

    public EnrolleeSelect() {
    }

    public EnrolleeSelect(UUID userId, int sessionId, UUID specId, short status, int score, Instant createdStamp, Instant confirmedStamp, Instant cancelledStamp, Instant modifiedStamp, short ordinal) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.specId = specId;
        this.status = status;
        this.score = score;
        this.createdStamp = createdStamp;
        this.confirmedStamp = confirmedStamp;
        this.cancelledStamp = cancelledStamp;
        this.modifiedStamp = modifiedStamp;
        this.ordinal = ordinal;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public UUID getSpecId() {
        return specId;
    }

    public void setSpecId(UUID specId) {
        this.specId = specId;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Instant getCreatedStamp() {
        return createdStamp;
    }

    public void setCreatedStamp(Instant createdStamp) {
        this.createdStamp = createdStamp;
    }

    public Instant getConfirmedStamp() {
        return confirmedStamp;
    }

    public void setConfirmedStamp(Instant confirmedStamp) {
        this.confirmedStamp = confirmedStamp;
    }

    public Instant getCancelledStamp() {
        return cancelledStamp;
    }

    public void setCancelledStamp(Instant cancelledStamp) {
        this.cancelledStamp = cancelledStamp;
    }

    public Instant getModifiedStamp() {
        return modifiedStamp;
    }

    public void setModifiedStamp(Instant modifiedStamp) {
        this.modifiedStamp = modifiedStamp;
    }

    public short getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(short ordinal) {
        this.ordinal = ordinal;
    }
}
