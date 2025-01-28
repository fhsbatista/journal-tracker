package com.fhsbatista.journal.domain.area;

import com.fhsbatista.journal.data.area.AreaRepository;
import com.fhsbatista.journal.domain.area.dto.AreaUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateAreaUsecase {
    @Autowired
    private AreaRepository repository;

    public Area call(AreaUpdateDTO dto) {
        var area = repository.getReferenceById(dto.id());
        area.updateData(dto);

        return area;
    }
}
