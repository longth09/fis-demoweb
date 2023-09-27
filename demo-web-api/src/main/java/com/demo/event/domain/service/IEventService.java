package com.demo.event.domain.service;

import com.amazonaws.services.s3.model.S3Object;
import com.demo.event.api.dto.EventRequestDto;
import com.demo.event.api.dto.EventResDto;
import com.demo.event.domain.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.File;
import java.util.Optional;

public interface IEventService {
    Page<EventResDto> getEvents(Pageable pageable);

    Optional<EventResDto> getById(long id);

    Event insert(Event eventRequestDto);

    Event update(Long id, EventRequestDto eventRequestDto);

    boolean delete(Long id);

    boolean clean();

}
