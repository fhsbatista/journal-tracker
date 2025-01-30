package com.fhsbatista.journal.domain.area;

import com.fhsbatista.journal.domain.Event;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class GetAreaScoreAverageUsecase {
    @PersistenceContext
    private EntityManager entityManager;

    public double call(Area area, int days) throws IllegalArgumentException{
        if (days == 0) {
            throw new IllegalArgumentException("Days must be 1 or greater");
        }
        LocalDate limitDate = LocalDate.now().minusDays(days - 1);

        List<Event> events = entityManager.createQuery(
                "SELECT e FROM Event e WHERE e.habit.area = :area AND e.time >= :limitDate",
                Event.class
        )
                .setParameter("area", area)
                .setParameter("limitDate", limitDate).getResultList();

        double scoreSum = 0;
        for (Event event : events) {
            scoreSum += event.getScore();
        }

        return scoreSum / days;
    }
}
