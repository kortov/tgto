CREATE TABLE message (
  MESSAGE   TEXT          NOT NULL,
  CREATED   TIMESTAMP     NOT NULL,
  USER_ID   BIGINT        NOT NULL,
  ID        BIGSERIAL     NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE tg_user (
  URL       VARCHAR(255)  NOT NULL,
  USER_ID   BIGINT        NOT NULL,
  ID        BIGSERIAL     NOT NULL,
  PRIMARY KEY (ID),
  CONSTRAINT unique_tg_user UNIQUE (USER_ID)
);

ALTER TABLE message
  ADD FOREIGN KEY (USER_ID) REFERENCES tg_user (USER_ID);
