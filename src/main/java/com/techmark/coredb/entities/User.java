package com.techmark.coredb.entities;

import javax.persistence.*;

/**
 * Created by gurpreets on 29/06/18.
 */
@Entity
@Table(name = "usr_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_user")
    @SequenceGenerator(name="seq_user", sequenceName="seq_user", allocationSize=1)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
