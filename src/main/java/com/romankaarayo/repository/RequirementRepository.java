package com.romankaarayo.repository;

import com.romankaarayo.db.Camp;
import com.romankaarayo.db.Requirement;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Chathura Widanage
 */
public interface RequirementRepository extends CrudRepository<Requirement,Long> {
    Iterable<Requirement> findAllByCamp(Camp camp);
}
