package com.fhsbatista.journal.data.habit;

import com.fhsbatista.journal.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
