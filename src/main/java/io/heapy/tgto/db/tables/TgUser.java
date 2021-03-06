/*
 * This file is generated by jOOQ.
 */
package io.heapy.tgto.db.tables;


import io.heapy.tgto.db.Indexes;
import io.heapy.tgto.db.Keys;
import io.heapy.tgto.db.Public;
import io.heapy.tgto.db.tables.records.TgUserRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import javax.annotation.Generated;
import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.11.9"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class TgUser extends TableImpl<TgUserRecord> {

    /**
     * The reference instance of <code>public.tg_user</code>
     */
    public static final TgUser TG_USER = new TgUser();
    private static final long serialVersionUID = -1571927154;
    /**
     * The column <code>public.tg_user.url</code>.
     */
    public final TableField<TgUserRecord, String> URL = createField("url", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");
    /**
     * The column <code>public.tg_user.user_id</code>.
     */
    public final TableField<TgUserRecord, Long> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");
    /**
     * The column <code>public.tg_user.id</code>.
     */
    public final TableField<TgUserRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('tg_user_id_seq'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * Create a <code>public.tg_user</code> table reference
     */
    public TgUser() {
        this(DSL.name("tg_user"), null);
    }

    /**
     * Create an aliased <code>public.tg_user</code> table reference
     */
    public TgUser(String alias) {
        this(DSL.name(alias), TG_USER);
    }

    /**
     * Create an aliased <code>public.tg_user</code> table reference
     */
    public TgUser(Name alias) {
        this(alias, TG_USER);
    }

    private TgUser(Name alias, Table<TgUserRecord> aliased) {
        this(alias, aliased, null);
    }

    private TgUser(Name alias, Table<TgUserRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> TgUser(Table<O> child, ForeignKey<O, TgUserRecord> key) {
        super(child, key, TG_USER);
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TgUserRecord> getRecordType() {
        return TgUserRecord.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.TG_USER_PKEY, Indexes.UNIQUE_TG_USER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<TgUserRecord, Long> getIdentity() {
        return Keys.IDENTITY_TG_USER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TgUserRecord> getPrimaryKey() {
        return Keys.TG_USER_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TgUserRecord>> getKeys() {
        return Arrays.<UniqueKey<TgUserRecord>>asList(Keys.UNIQUE_TG_USER, Keys.TG_USER_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TgUser as(String alias) {
        return new TgUser(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TgUser as(Name alias) {
        return new TgUser(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TgUser rename(String name) {
        return new TgUser(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TgUser rename(Name name) {
        return new TgUser(name, null);
    }
}
