CREATE TABLE scp_read_service.enrollee_select
(
    spec_id         UUID      NOT NULL,
    session_id      INT       NOT NULL,
    user_id         UUID      NOT NULL,
    -- current selection status, 0 for applied, 1 for confirmed and 2 for cancelled
    status          SMALLINT  NOT NULL DEFAULT 0,
    -- the assigned user score
    score           INT       NOT NULL DEFAULT 0,
    -- stamp for creation(application) date
    created_stamp   TIMESTAMP NOT NULL DEFAULT current_timestamp,
    -- stamp for confirmation date
    confirmed_stamp TIMESTAMP          DEFAULT NULL,
    -- stamp for cancelled date
    canceled_stamp  TIMESTAMP          DEFAULT NULL,

    ---------------------------------------
    --     integrity check fields        --
    ---------------------------------------
    -- last modification stamp received from write module
    modified_stamp  TIMESTAMP NOT NULL DEFAULT current_timestamp,
    -- should be treated like 'version', this way we will make
    -- possible to reduce amount of response messages sent by target module
    -- otherwise we'll deal with lots of duplicates
    -- we should not write changes to database unless this column
    -- has lower value than update
    ordinal         SMALLINT           DEFAULT 0,
    -- confirmation sent state, false - not sent, true - sent
    state           BOOLEAN   NOT NULL DEFAULT FALSE,
    -- we shift primary key column order to ensure that we may use primary key index for
    -- queries
    CONSTRAINT enrollee_select_pk PRIMARY KEY (spec_id, session_id, user_id)
);
CREATE INDEX enrollee_select_modified_idx ON scp_read_service.enrollee_select (modified_stamp ASC);
CREATE INDEX enrollee_select_score_confirmed_created_idx ON scp_read_service.enrollee_select (score, confirmed_stamp, created_stamp);
