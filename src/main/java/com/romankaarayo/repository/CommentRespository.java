package com.romankaarayo.repository;

import com.romankaarayo.db.Comment;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Chathura Widanage
 */
public interface CommentRespository extends CrudRepository<Comment,Long> {
}
