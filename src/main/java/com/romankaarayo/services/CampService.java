package com.romankaarayo.services;

import com.romankaarayo.db.Camp;
import com.romankaarayo.repository.CampRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Chathura Widanage
 */
public class CampService {
    @Autowired
    private CampRepository campRepository;

    public Camp save(Camp camp) {
        return this.campRepository.save(camp);
    }

    public Iterable<Camp> all() {
        return this.campRepository.findAll();
    }

    public Camp getById(Long id) {
        return this.campRepository.findOne(id);
    }
}
