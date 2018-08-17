/*
 * This file is generated by jOOQ.
 */
package app.heap.tgto.db;


import app.heap.tgto.db.tables.FlywaySchemaHistory;
import app.heap.tgto.db.tables.Message;
import app.heap.tgto.db.tables.TgUser;
import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1351020775;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.flyway_schema_history</code>.
     */
    public final FlywaySchemaHistory FLYWAY_SCHEMA_HISTORY = app.heap.tgto.db.tables.FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY;

    /**
     * The table <code>public.message</code>.
     */
    public final Message MESSAGE = app.heap.tgto.db.tables.Message.MESSAGE;

    /**
     * The table <code>public.tg_user</code>.
     */
    public final TgUser TG_USER = app.heap.tgto.db.tables.TgUser.TG_USER;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        List result = new ArrayList();
        result.addAll(getSequences0());
        return result;
    }

    private final List<Sequence<?>> getSequences0() {
        return Arrays.<Sequence<?>>asList(
            Sequences.MESSAGE_ID_SEQ,
            Sequences.TG_USER_ID_SEQ);
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY,
            Message.MESSAGE,
            TgUser.TG_USER);
    }
}
