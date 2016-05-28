package com.romankaarayo.repository;

import com.romankaarayo.db.Comment;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Chathura Widanage
 */
public interface CommentRepository extends CrudRepository<Comment,Long> {
}
