package com.techmark.coredb.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * Created by gurpreets on 29/06/18.
 */
@Entity
@Table(name = "usr_users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {


}
