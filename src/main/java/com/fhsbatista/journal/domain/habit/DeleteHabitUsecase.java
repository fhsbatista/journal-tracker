package com.fhsbatista.journal.domain.habit;

import com.fhsbatista.journal.data.area.AreaRepository;
import com.fhsbatista.journal.data.habit.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteHabitUsecase {
    @Autowired
    private HabitRepository repository;

    public void call(Long id) {
        repository.deleteById(id);
    }
}
