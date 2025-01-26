package com.fhsbatista.journal.domain;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class GetLastDaysScoreAverageUsecase {
    private GetDayScoreAverageUsecase getDayScoreAverageUsecase;

    public GetLastDaysScoreAverageUsecase(GetDayScoreAverageUsecase getDayScoreAverageUsecase) {
        this.getDayScoreAverageUsecase = getDayScoreAverageUsecase;
    }

    public double call(int days) {
        List<LocalDate> lastNDays = Stream
                .iterate(LocalDate.now(), date -> date.minusDays(1))
                .limit(days)
                .toList();
        double totalAverageScore = 0;

        for (LocalDate date : lastNDays) {
            var average = getDayScoreAverageUsecase.call(date);
            totalAverageScore += average;
        }

        return totalAverageScore / days;


    }
}
