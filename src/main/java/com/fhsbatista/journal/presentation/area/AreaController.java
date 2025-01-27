package com.fhsbatista.journal.presentation.area;

import com.fhsbatista.journal.data.area.AreaRepository;
import com.fhsbatista.journal.domain.area.GetAreaScoreAverageUsecase;
import com.fhsbatista.journal.domain.area.GetAreaUsecase;
import com.fhsbatista.journal.domain.area.ListAreasUsecase;
import com.fhsbatista.journal.presentation.area.dto.AreaRegisterDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/areas")
public class AreaController {
    @Autowired
    private AreaRepository repository;

    @Autowired
    private GetAreaScoreAverageUsecase getAreaScoreAverageUsecase;

    @Autowired
    private ListAreasUsecase listAreasUsecase;

    @Autowired
    private GetAreaUsecase getAreaUsecase;

    @GetMapping("/{id}")
    public ResponseEntity<AreaDetails> get(@PathVariable Long id) {
        var area = getAreaUsecase.call(id);
        return ResponseEntity.ok(new AreaDetails(area));
    }

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

    @GetMapping
    public ResponseEntity<List<AreaDetails>> list() {
        var list = listAreasUsecase.call();

        var response = list
                .stream()
                .map(AreaDetails::new)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/today_average")
    public ResponseEntity todayAverage(@PathVariable Long id) {
        var area = repository.getReferenceById(id);

        var average = getAreaScoreAverageUsecase.call(area, 1);

        var response = new DateAverageDetails(area.getDescription(), average, LocalDate.now());

        return ResponseEntity.ok(response);
    }
}
