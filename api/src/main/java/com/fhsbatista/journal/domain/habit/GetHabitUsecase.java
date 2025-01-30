package com.fhsbatista.journal.domain.habit;

import com.fhsbatista.journal.data.habit.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetHabitUsecase {
    @Autowired
    private HabitRepository repository;

    public Habit call(Long id) {
        return repository.getReferenceById(id);
    }
}
