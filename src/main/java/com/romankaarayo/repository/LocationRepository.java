package com.romankaarayo.repository;

import com.romankaarayo.db.Location;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Chathura Widanage
 */
public interface LocationRepository extends CrudRepository<Location, Long> {
}
