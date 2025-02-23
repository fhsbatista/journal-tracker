package com.fhsbatista.journal.domain.habit;

import com.fhsbatista.journal.domain.Event;
import com.fhsbatista.journal.domain.area.Area;
import com.fhsbatista.journal.domain.habit.dto.HabitUpdateDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "habits")
@Entity(name = "habit")
public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @OneToMany(
            mappedBy = "habit",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Event> events = new ArrayList<>();

    private String description;
    private Double currentScore;

    public Habit() {}

    public Habit(Long id, Area area, String description, Double currentScore) {
        this.area = area;
        this.description = description;
        this.currentScore = currentScore;
    }

    public Habit(Area area, String description, Double currentScore) {
        this.area = area;
        this.description = description;
        this.currentScore = currentScore;
    }

    public Event perform(LocalDate time) {
        var event = new Event(this, time, currentScore);
        this.events.add(event);

        return event;
    }

    public void unperform(Event event) {
        this.events.remove(event);
        event.setHabit(null);
    }

    public Long getId() {
        return id;
    }

    public Area getArea() {
        return area;
    }

    public String getDescription() {
        return description;
    }

    public List<Event> getEvents() {
        return events;
    }

    public Double getCurrentScore() {
        return currentScore;
    }

    public void updateData(HabitUpdateDTO dto) {
        if (dto.description() != null) {
            this.description = dto.description();
        }

        if (dto.currentScore() != null) {
            this.currentScore = dto.currentScore();
        }
    }
}
