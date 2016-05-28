package com.romankaarayo.services;

import com.romankaarayo.db.Location;
import com.romankaarayo.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Chathura Widanage
 */
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getAll() {
        List<Location> locationList = new ArrayList<>();
        Iterator<Location> iterator = this.locationRepository.findAll().iterator();
        iterator.forEachRemaining(location -> locationList.add(location));
        return locationList;
    }

    public void saveLocation(Location location) {
        this.locationRepository.save(location);
    }
}
