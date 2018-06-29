package com.techmark.coredb.repository;

import com.techmark.coredb.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by gurpreets on 29/06/18.
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
