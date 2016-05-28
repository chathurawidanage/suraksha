package com.romankaarayo.services;

import com.romankaarayo.db.Location;
import com.romankaarayo.repository.LocationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Chathura Widanage
 */
public class LocationService {
    private Logger logger= LogManager.getLogger(LocationService.class);
    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getAll() {
        List<Location> locationList = new ArrayList<>();
        Iterator<Location> iterator = this.locationRepository.findAll().iterator();
        iterator.forEachRemaining(location -> locationList.add(location));
        return locationList;
    }

    public Location saveLocation(Location location) {
        return this.locationRepository.save(location);
    }
}
