package com.demo.speacks.domain.service;

import com.demo.speacks.api.dto.SpeackerEventDto;
import com.demo.speacks.domain.model.Speakers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Optional;

public interface ISpeackersService {

    Page<Speakers> getAll(Pageable pageable);

    Speakers insert(Speakers speakers);

    Speakers update(Long id, Speakers speakers);

    Boolean remove(Long id);

    Boolean removeAll();

    Optional<Speakers> findById(Long id);

    Map<String, Object> getSpeackerEvents(Pageable pageable);

}
