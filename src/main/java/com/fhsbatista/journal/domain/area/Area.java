package com.fhsbatista.journal.domain.area;

import com.fhsbatista.journal.domain.Habit;
import com.fhsbatista.journal.domain.area.dto.AreaUpdateDTO;
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

    public Area(Long id, String description) {
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

    public void updateData(AreaUpdateDTO dto) {
        if (dto.description() != null) {
            this.description = dto.description();
        }
    }
}
