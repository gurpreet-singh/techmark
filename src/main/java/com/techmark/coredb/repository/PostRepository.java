package com.techmark.coredb.repository;

import com.techmark.coredb.entities.Post;
import com.techmark.coredb.entities.User;
import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by gurpreets on 29/06/18.
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    

}
