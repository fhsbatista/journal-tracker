package com.fhsbatista.journal.domain.area;

import com.fhsbatista.journal.data.area.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListAreasUsecase {
    @Autowired
    private AreaRepository repository;

    public List<Area> call() {
        return repository.findAll();
    }
}
