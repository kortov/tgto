/*
 * This file is generated by jOOQ.
 */
package io.heapy.tgto.db;


import io.heapy.tgto.db.tables.FlywaySchemaHistory;
import io.heapy.tgto.db.tables.Message;
import io.heapy.tgto.db.tables.TgUser;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in public
 */
@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.11.9"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Tables {

    /**
     * The table <code>public.flyway_schema_history</code>.
     */
    public static final FlywaySchemaHistory FLYWAY_SCHEMA_HISTORY = io.heapy.tgto.db.tables.FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY;

    /**
     * The table <code>public.message</code>.
     */
    public static final Message MESSAGE = io.heapy.tgto.db.tables.Message.MESSAGE;

    /**
     * The table <code>public.tg_user</code>.
     */
    public static final TgUser TG_USER = io.heapy.tgto.db.tables.TgUser.TG_USER;
}
