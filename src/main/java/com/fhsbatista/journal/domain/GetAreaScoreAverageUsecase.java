package com.fhsbatista.journal.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class GetAreaScoreAverageUsecase {
    @PersistenceContext
    private EntityManager entityManager;

    public double call(Area area, int days) {
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
