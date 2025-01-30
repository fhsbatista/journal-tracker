package com.fhsbatista.journal.domain.habit;

import com.fhsbatista.journal.data.area.AreaRepository;
import com.fhsbatista.journal.data.habit.HabitRepository;
import com.fhsbatista.journal.domain.area.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListHabitsUsecase {
    @Autowired
    private HabitRepository repository;

    public List<Habit> call() {
        return repository.findAll();
    }
}
