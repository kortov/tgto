/*
 * This file is generated by jOOQ.
 */
package app.heap.tgto.db;


import org.jooq.Sequence;
import org.jooq.impl.SequenceImpl;

import javax.annotation.Generated;


/**
 * Convenience access to all sequences in public
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

    /**
     * The sequence <code>public.message_id_seq</code>
     */
    public static final Sequence<Long> MESSAGE_ID_SEQ = new SequenceImpl<Long>("message_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));

    /**
     * The sequence <code>public.tg_user_id_seq</code>
     */
    public static final Sequence<Long> TG_USER_ID_SEQ = new SequenceImpl<Long>("tg_user_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));
}
