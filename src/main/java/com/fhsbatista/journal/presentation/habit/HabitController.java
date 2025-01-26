package com.fhsbatista.journal.presentation.habit;

import com.fhsbatista.journal.data.area.AreaRepository;
import com.fhsbatista.journal.data.habit.EventRepository;
import com.fhsbatista.journal.data.habit.HabitRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;

@RestController
@RequestMapping("/habits")
public class HabitController {
    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private EventRepository eventRepository;

    @PostMapping
    @Transactional
    public ResponseEntity create(
            @RequestBody @Valid HabitCreateDTO dto,
            UriComponentsBuilder uriBuilder) {
        var area = areaRepository.getReferenceById(dto.areaId());
        var habit = habitRepository.save(dto.toHabit(area));

        var uri = uriBuilder.path("/habits/{id}").buildAndExpand(habit.getId()).toUri();

        return ResponseEntity.created(uri).body(new HabitDetails(habit));
    }

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity perform(@PathVariable Long id) {
        var habit = habitRepository.getReferenceById(id);
        var eventToSave = habit.perform(LocalDate.now());
        var event = eventRepository.save(eventToSave);

        return ResponseEntity.ok().body(new EventDetails(event));
    }
}
