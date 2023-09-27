package com.demo.event.infrastructure.repository;

import com.demo.event.domain.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event findFirstByNameEqualsAndDescLike(String name, String desc);

    int countFirstByNameEquals(String name);

}
