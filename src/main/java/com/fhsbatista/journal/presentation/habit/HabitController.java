package com.fhsbatista.journal.presentation.habit;

import com.fhsbatista.journal.data.area.AreaRepository;
import com.fhsbatista.journal.data.habit.EventRepository;
import com.fhsbatista.journal.data.habit.HabitRepository;
import com.fhsbatista.journal.domain.PerformHabitException;
import com.fhsbatista.journal.domain.PerformHabitUsecase;
import com.fhsbatista.journal.domain.habit.GetHabitUsecase;
import com.fhsbatista.journal.domain.habit.Habit;
import com.fhsbatista.journal.domain.habit.ListHabitsUsecase;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/habits")
public class HabitController {
    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private PerformHabitUsecase performHabitUsecase;

    @Autowired
    private ListHabitsUsecase listHabitsUsecase;

    @Autowired
    private GetHabitUsecase getHabitUsecase;

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

    @GetMapping("/{id}")
    public ResponseEntity<HabitDetails> get(@PathVariable Long id) {
        var habit = getHabitUsecase.call(id);
        return ResponseEntity.ok(new HabitDetails(habit));
    }

    @GetMapping
    public ResponseEntity<List<HabitDetails>> list() {
        var list = listHabitsUsecase.call();
        var response = list.stream().map(HabitDetails::new).toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity perform(@PathVariable Long id) {
        var habit = habitRepository.getReferenceById(id);

        try {
            var event = performHabitUsecase.call(habit);
            return ResponseEntity.ok().body(new EventDetails(event));
        } catch (PerformHabitException e) {
            return ResponseEntity.badRequest().body(new ErrorDetails(e.getMessage()));
        }
    }
}
