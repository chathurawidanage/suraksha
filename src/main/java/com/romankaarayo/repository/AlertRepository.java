package com.romankaarayo.repository;

import com.romankaarayo.db.Alert;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Chathura Widanage
 */
public interface AlertRepository extends CrudRepository<Alert, Long> {
    Alert findOneByShowed(Boolean bool);
}
