package com.romankaarayo.db;

import javax.persistence.*;

/**
 * @author Chathura Widanage
 */
@Entity
@Table(name = "camp")
public class Camp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location",nullable = false)
    private Location location;

    @Column(name = "name",nullable = false)
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
