/*
 * This file is generated by jOOQ.
 */
package io.heapy.tgto.db.tables.pojos;


import javax.annotation.Generated;
import java.io.Serializable;
import java.sql.Timestamp;


/**
 * This class is generated by jOOQ.
 */
@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.11.5"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Message implements Serializable {

    private static final long serialVersionUID = 1667098310;

    private String message;
    private Timestamp created;
    private Long userId;
    private Long id;

    public Message() {
    }

    public Message(Message value) {
        this.message = value.message;
        this.created = value.created;
        this.userId = value.userId;
        this.id = value.id;
    }

    public Message(
            String message,
            Timestamp created,
            Long userId,
            Long id
    ) {
        this.message = message;
        this.created = created;
        this.userId = userId;
        this.id = id;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCreated() {
        return this.created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Message (");

        sb.append(message);
        sb.append(", ").append(created);
        sb.append(", ").append(userId);
        sb.append(", ").append(id);

        sb.append(")");
        return sb.toString();
    }
}