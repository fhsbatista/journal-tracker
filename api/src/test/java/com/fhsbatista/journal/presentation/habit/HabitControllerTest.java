package com.fhsbatista.journal.presentation.habit;

import com.fhsbatista.journal.data.habit.HabitRepository;
import com.fhsbatista.journal.domain.*;
import com.fhsbatista.journal.domain.area.Area;
import com.fhsbatista.journal.domain.habit.*;
import com.fhsbatista.journal.presentation.area.AreaDetails;
import com.fhsbatista.journal.presentation.area.body.AreaUpdateBody;
import com.fhsbatista.journal.presentation.habit.body.HabitUpdateBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class HabitControllerTest {
    @Mock
    private PerformHabitUsecase performHabitUsecase;

    @Mock
    private HabitRepository habitRepository;

    @Mock
    private PerformHabitException performHabitException;

    @Mock
    private ListHabitsUsecase listHabitsUsecase;

    @Mock
    private GetHabitUsecase getHabitUsecase;

    @Mock
    private UpdateHabitUsecase updateHabitUsecase;

    @Mock
    private DeleteHabitUsecase deleteHabitUsecase;

    @InjectMocks
    private HabitController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void get_onUsecaseSuccess_returnsCorrectResponse() {
        var area = new Area(1L, "test");
        var habit = new Habit(area, "test 1", 1.0);
        when(getHabitUsecase.call(eq(area.getId()))).thenReturn(habit);

        var responseEntity = controller.get(habit.getId());
        HabitDetails response = responseEntity.getBody();

        assertEquals(200, responseEntity.getStatusCode().value());
        assertNotNull(response);
        assertEquals(response, new HabitDetails(habit));
    }

    @Test
    void list_onUsecaseSuccess_returnsCorrectResponse() {
        var area = new Area("test");
        var habits = List.of(
                new Habit(area, "test1", 1.0),
                new Habit(area, "test2", 1.0)
        );

        when(listHabitsUsecase.call()).thenReturn(habits);

        var responseEntity = controller.list();
        List<HabitDetails> response = responseEntity.getBody();

        assertEquals(200, responseEntity.getStatusCode().value());
        assertNotNull(response);
        assertEquals(response, habits.stream().map(HabitDetails::new).toList());
    }

    @Test
    void update_onUsecaseSuccess_returnsCorrectResponse() {
        var body = new HabitUpdateBody(1L, "updated area", 1.0);
        var area = new Area("test");
        var updatedHabit = new Habit(1L, area, "updated habit", 2.0);

        when(updateHabitUsecase.call(eq(body.toDto()))).thenReturn(updatedHabit);

        var responseEntity = controller.update(body);
        var response = responseEntity.getBody();

        assertEquals(200, responseEntity.getStatusCode().value());
        assertNotNull(response);
        assertEquals(response, new HabitDetails(updatedHabit));
    }

    @Test
    void delete_onUsecaseSuccess_returnsCorrectResponse() {
        controller.delete(1L);

        verify(deleteHabitUsecase).call(eq(1L));
    }

    @Nested
    class PerformTests {
        @Test
        void testCall_callsUsecaseCorrectly() throws PerformHabitException {
            var area = new Area("test");
            var habit = new Habit(area, "test", 1.0 );
            var expectedEvent = new Event(habit, LocalDate.now(), 1.0);

            when(habitRepository.getReferenceById(eq(habit.getId()))).thenReturn(habit);
            when(performHabitUsecase.call(eq(habit))).thenReturn(expectedEvent);

            controller.perform(habit.getId());

            verify(performHabitUsecase).call(habit);
        }

        @Test
        void testCall_whenUsecaseSucceeds_shouldReturnCorrectResponse() throws PerformHabitException {
            var area = new Area("test");
            var habit = new Habit(area, "test", 1.0 );
            var expectedEvent = new Event(habit, LocalDate.now(), 1.0);

            when(habitRepository.getReferenceById(eq(habit.getId()))).thenReturn(habit);
            when(performHabitUsecase.call(eq(habit))).thenReturn(expectedEvent);

            var responseEntity = controller.perform(habit.getId());
            var response = responseEntity.getBody();

            assertEquals(200, responseEntity.getStatusCode().value());
            assertEquals(new EventDetails(expectedEvent), response);
        }

        @Test
        void testCall_whenUsecaseFailsUseExpectedException_shouldReturnCorrectResponse() throws PerformHabitException {
            var area = new Area("test");
            var habit = new Habit(area, "test", 1.0 );
            var exceptionMessage = "exception message";

            when(habitRepository.getReferenceById(eq(habit.getId()))).thenReturn(habit);
            when(performHabitUsecase.call(eq(habit))).thenThrow(performHabitException);
            when(performHabitException.getMessage()).thenReturn(exceptionMessage);


            var responseEntity = controller.perform(habit.getId());
            ErrorDetails response = (ErrorDetails) responseEntity.getBody();

            assertEquals(400, responseEntity.getStatusCode().value());
            assertNotNull(response);
            assertEquals(exceptionMessage, response.message());
        }
    }

}