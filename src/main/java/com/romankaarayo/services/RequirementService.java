package com.romankaarayo.services;

import com.romankaarayo.db.Camp;
import com.romankaarayo.db.Requirement;
import com.romankaarayo.repository.RequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Chathura Widanage
 */
public class RequirementService {


    @Autowired
    private RequirementRepository requirementRepository;

    public Requirement save(Requirement requirement) {
        return this.requirementRepository.save(requirement);
    }

    public Iterable<Requirement> getByCamp(Camp camp){
        return this.requirementRepository.findAllByCamp(camp);
    }
}
