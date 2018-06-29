package com.techmark.coredb.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by gurpreets on 29/06/18.
 */
@Entity
@Table(name = "post_post")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_post")
    @SequenceGenerator(name="seq_post", sequenceName="seq_post", allocationSize=1)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
