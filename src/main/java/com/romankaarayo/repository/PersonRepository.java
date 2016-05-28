package com.romankaarayo.repository;

import com.romankaarayo.db.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Chathura Widanage
 */
public interface PersonRepository extends CrudRepository<Person,Long> {
}
