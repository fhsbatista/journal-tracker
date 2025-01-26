package com.fhsbatista.journal.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class GetAreaScoreAverageUsecaseTest {
    @InjectMocks
    private GetAreaScoreAverageUsecase getAreaScoreAverageUsecase;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Event> typedQuery;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCall() {
        Area area = new Area("Fitness");
        Habit workoutHabit = new Habit(area, "Workout", 2.0);

        workoutHabit.perform(LocalDate.now());
        workoutHabit.perform(LocalDate.now().minusDays(1));
        workoutHabit.perform(LocalDate.now().minusDays(2));
        workoutHabit.perform(LocalDate.now().minusDays(3));
        workoutHabit.perform(LocalDate.now().minusDays(4));
        workoutHabit.perform(LocalDate.now().minusDays(5));
        workoutHabit.perform(LocalDate.now().minusDays(6));

        when(entityManager.createQuery(anyString(), eq(Event.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(workoutHabit.getEvents());

        double result = getAreaScoreAverageUsecase.call(area, 7);

        assertEquals(2.0, result);


    }
}