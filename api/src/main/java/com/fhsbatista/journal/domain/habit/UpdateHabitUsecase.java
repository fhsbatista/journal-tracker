package com.fhsbatista.journal.domain.habit;

import com.fhsbatista.journal.data.area.AreaRepository;
import com.fhsbatista.journal.data.habit.HabitRepository;
import com.fhsbatista.journal.domain.area.Area;
import com.fhsbatista.journal.domain.area.dto.AreaUpdateDTO;
import com.fhsbatista.journal.domain.habit.dto.HabitUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateHabitUsecase {
    @Autowired
    private HabitRepository repository;

    public Habit call(HabitUpdateDTO dto) {
        var habit = repository.getReferenceById(dto.id());
        habit.updateData(dto);

        return habit;
    }
}
