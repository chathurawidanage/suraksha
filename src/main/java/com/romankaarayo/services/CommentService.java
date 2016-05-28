package com.romankaarayo.services;

import com.romankaarayo.db.Comment;
import com.romankaarayo.db.Person;
import com.romankaarayo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Chathura Widanage
 */
public class CommentService {
    @Autowired
    private CommentRepository commentRespository;

    public Comment save(Comment comment) {
        return this.commentRespository.save(comment);
    }

    public Iterable<Comment> getByPerson(Person person){
        return this.commentRespository.findByPerson(person);
    }

}
