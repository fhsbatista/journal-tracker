package com.fhsbatista.journal.domain;

import com.fhsbatista.journal.domain.area.Area;
import com.fhsbatista.journal.domain.area.GetAreaScoreAverageUsecase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;
import java.util.List;

public class GetDayScoreAverageUsecase {
    @PersistenceContext
    EntityManager entityManager;

    private final GetAreaScoreAverageUsecase getAreaScoreAverageUsecase;

    public GetDayScoreAverageUsecase(
            GetAreaScoreAverageUsecase getAreaScoreAverageUsecase,
            EntityManager entityManager
    ) {
        this.getAreaScoreAverageUsecase = getAreaScoreAverageUsecase;
        this.entityManager = entityManager;
    }

    public double call(LocalDate day) {
        List<Area> areas = entityManager.createQuery("SELECT a FROM Area a", Area.class).getResultList();

        double totalAverageScore = 0;
        int totalAreas = areas.size();

        for (Area area : areas) {
            double areaAverageScore = getAreaScoreAverageUsecase.call(area, 0);
            totalAverageScore += areaAverageScore;
        }

        return totalAverageScore / totalAreas;
    }
}
