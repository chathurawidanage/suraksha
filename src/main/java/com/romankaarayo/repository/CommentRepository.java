package com.romankaarayo.repository;

import com.romankaarayo.db.Comment;
import com.romankaarayo.db.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Chathura Widanage
 */
public interface CommentRepository extends CrudRepository<Comment,Long> {
    Iterable<Comment> findByPerson(Person person);
}
