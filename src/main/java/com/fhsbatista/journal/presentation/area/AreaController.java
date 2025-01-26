package com.fhsbatista.journal.presentation.area;

import com.fhsbatista.journal.data.area.AreaRepository;
import com.fhsbatista.journal.domain.GetAreaScoreAverageUsecase;
import com.fhsbatista.journal.presentation.area.dto.AreaRegisterDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;

@RestController
@RequestMapping("/areas")
public class AreaController {
    @Autowired
    private AreaRepository repository;

    @Autowired
    private GetAreaScoreAverageUsecase getAreaScoreAverageUsecase;

    @PostMapping
    @Transactional
    public ResponseEntity create(
            @RequestBody @Valid AreaRegisterDTO dto,
            UriComponentsBuilder uriBuilder
    ) {
        var area = repository.save(dto.toArea());

        var uri = uriBuilder.path("/areas/{id}").buildAndExpand(area.getId()).toUri();

        return ResponseEntity.created(uri).body(new AreaDetails(area));
    }

    @GetMapping("/{id}/today_average")
    public ResponseEntity todayAverage(@PathVariable Long id) {
        var area = repository.getReferenceById(id);

        var average = getAreaScoreAverageUsecase.call(area, 1);

        var response = new DateAverageDetails(area.getDescription(), average, LocalDate.now());

        return ResponseEntity.ok(response);
    }
}
