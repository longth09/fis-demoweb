package com.demo.speacks.infrastructure.repository;

import com.demo.speacks.api.dto.SpeackerEventDto;
import com.demo.speacks.domain.model.Speakers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeackRepository extends JpaRepository<Speakers, Long> {

    @Query(value = "SELECT speackers.*, events.name, events.id as id_event from events join speackers on events.id = speackers.event", nativeQuery = true)
    Page<Object[]> findSpeakersEvent(Pageable pageable);

}
