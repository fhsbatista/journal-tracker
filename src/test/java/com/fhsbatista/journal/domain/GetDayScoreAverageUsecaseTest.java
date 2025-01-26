package com.fhsbatista.journal.domain;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fhsbatista.journal.domain.Area;
import com.fhsbatista.journal.domain.Event;
import com.fhsbatista.journal.domain.GetDayScoreAverageUsecase;
import com.fhsbatista.journal.domain.Habit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class GetDayScoreAverageUsecaseTest {
    @InjectMocks
    private GetDayScoreAverageUsecase usecase;

    @Mock
    private GetAreaScoreAverageUsecase getAreaScoreAverageUsecase;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Area> areaQuery;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCall() {
        Area fitnessArea = new Area("Fitness");
        Area wealthArea = new Area("Wealth");

        when(entityManager.createQuery("SELECT a FROM Area a", Area.class)).thenReturn(areaQuery);
        when(areaQuery.getResultList()).thenReturn(List.of(fitnessArea, wealthArea));
        when(getAreaScoreAverageUsecase.call(fitnessArea, 0)).thenReturn(3.0);
        when(getAreaScoreAverageUsecase.call(wealthArea, 0)).thenReturn(5.0);

        double result = usecase.call(LocalDate.now());

        assertEquals(4, result);
    }
}
