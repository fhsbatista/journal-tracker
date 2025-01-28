package com.fhsbatista.journal.domain.area;

import com.fhsbatista.journal.data.area.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAreaUsecase {
    @Autowired
    private AreaRepository repository;

    public Area call(Long id) {
        return repository.getReferenceById(id);
    }
}
