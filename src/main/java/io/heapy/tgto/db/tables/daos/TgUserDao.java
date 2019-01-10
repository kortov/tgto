/*
 * This file is generated by jOOQ.
 */
package io.heapy.tgto.db.tables.daos;


import io.heapy.tgto.db.tables.TgUser;
import io.heapy.tgto.db.tables.records.TgUserRecord;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import javax.annotation.Generated;
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
public class TgUserDao extends DAOImpl<TgUserRecord, io.heapy.tgto.db.tables.pojos.TgUser, Long> {

    /**
     * Create a new TgUserDao without any configuration
     */
    public TgUserDao() {
        super(TgUser.TG_USER, io.heapy.tgto.db.tables.pojos.TgUser.class);
    }

    /**
     * Create a new TgUserDao with an attached configuration
     */
    public TgUserDao(Configuration configuration) {
        super(TgUser.TG_USER, io.heapy.tgto.db.tables.pojos.TgUser.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Long getId(io.heapy.tgto.db.tables.pojos.TgUser object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>url IN (values)</code>
     */
    public List<io.heapy.tgto.db.tables.pojos.TgUser> fetchByUrl(String... values) {
        return fetch(TgUser.TG_USER.URL, values);
    }

    /**
     * Fetch records that have <code>user_id IN (values)</code>
     */
    public List<io.heapy.tgto.db.tables.pojos.TgUser> fetchByUserId(Long... values) {
        return fetch(TgUser.TG_USER.USER_ID, values);
    }

    /**
     * Fetch a unique record that has <code>user_id = value</code>
     */
    public io.heapy.tgto.db.tables.pojos.TgUser fetchOneByUserId(Long value) {
        return fetchOne(TgUser.TG_USER.USER_ID, value);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<io.heapy.tgto.db.tables.pojos.TgUser> fetchById(Long... values) {
        return fetch(TgUser.TG_USER.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public io.heapy.tgto.db.tables.pojos.TgUser fetchOneById(Long value) {
        return fetchOne(TgUser.TG_USER.ID, value);
    }
}
