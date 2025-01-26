package com.fhsbatista.journal.domain;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "areas")
@Entity(name = "area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(
            mappedBy = "area",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Habit> habits;

    private String description;

    public Area() {}

    public Area(String description) {
        this.description = description;
    }


    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Habit> getHabits() {
        return habits;
    }
}
