package com.fhsbatista.journal.presentation.area;

import com.fhsbatista.journal.data.area.AreaRepository;
import com.fhsbatista.journal.presentation.area.dto.AreaRegisterDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/areas")
public class AreaController {
    @Autowired
    private AreaRepository repository;

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
}
