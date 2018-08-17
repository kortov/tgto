/*
 * This file is generated by jOOQ.
 */
package app.heap.tgto.db;


import app.heap.tgto.db.tables.FlywaySchemaHistory;
import app.heap.tgto.db.tables.Message;
import app.heap.tgto.db.tables.TgUser;
import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;

import javax.annotation.Generated;


/**
 * A class modelling indexes of tables of the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index FLYWAY_SCHEMA_HISTORY_PK = Indexes0.FLYWAY_SCHEMA_HISTORY_PK;
    public static final Index FLYWAY_SCHEMA_HISTORY_S_IDX = Indexes0.FLYWAY_SCHEMA_HISTORY_S_IDX;
    public static final Index MESSAGE_PKEY = Indexes0.MESSAGE_PKEY;
    public static final Index TG_USER_PKEY = Indexes0.TG_USER_PKEY;
    public static final Index UNIQUE_TG_USER = Indexes0.UNIQUE_TG_USER;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index FLYWAY_SCHEMA_HISTORY_PK = Internal.createIndex("flyway_schema_history_pk", FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, new OrderField[] { FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK }, true);
        public static Index FLYWAY_SCHEMA_HISTORY_S_IDX = Internal.createIndex("flyway_schema_history_s_idx", FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, new OrderField[] { FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.SUCCESS }, false);
        public static Index MESSAGE_PKEY = Internal.createIndex("message_pkey", Message.MESSAGE, new OrderField[] { Message.MESSAGE.ID }, true);
        public static Index TG_USER_PKEY = Internal.createIndex("tg_user_pkey", TgUser.TG_USER, new OrderField[] { TgUser.TG_USER.ID }, true);
        public static Index UNIQUE_TG_USER = Internal.createIndex("unique_tg_user", TgUser.TG_USER, new OrderField[] { TgUser.TG_USER.USER_ID }, true);
    }
}