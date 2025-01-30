package com.fhsbatista.journal.domain.area;

import com.fhsbatista.journal.data.area.AreaRepository;
import com.fhsbatista.journal.domain.area.dto.AreaUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteAreaUsecase {
    @Autowired
    private AreaRepository repository;

    public void call(Long id) {
        repository.deleteById(id);
    }
}
