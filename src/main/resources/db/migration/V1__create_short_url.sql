CREATE TABLE short_url (
    id          BIGSERIAL PRIMARY KEY,
    code        VARCHAR(20) NOT NULL UNIQUE,
    original_url TEXT NOT NULL,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    clicks      INTEGER NOT NULL DEFAULT 0
);
