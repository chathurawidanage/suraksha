package com.romankaarayo.db;

import javax.persistence.*;

/**
 * @author Chathura Widanage
 */
@Entity
@Table(name = "requirement")
public class Requirement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "required")
    private float required;

    @Column(name = "pledged")
    private float pledged;

    @Column(name = "recieved")
    private float recieved;

    @Column(name = "type")
    private int type;

    @ManyToOne
    @JoinColumn(name = "camp_id", nullable = false)
    private Camp camp;

    public Camp getCamp() {
        return camp;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRequired() {
        return required;
    }

    public void setRequired(float required) {
        this.required = required;
    }

    public float getPledged() {
        return pledged;
    }

    public void setPledged(float pledged) {
        this.pledged = pledged;
    }

    public float getRecieved() {
        return recieved;
    }

    public void setRecieved(float recieved) {
        this.recieved = recieved;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
