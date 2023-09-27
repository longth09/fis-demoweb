package com.demo.miniapi.domain.service;

import com.demo.event.domain.model.Event;
import com.demo.event.infrastructure.repository.EventRepository;
import com.demo.miniapi.api.dto.EventMinniResDto;
import com.demo.miniapi.domain.mapper.IEventMiniDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventMiniService implements IEventMiniService {

    private final EventRepository eventRepository;

    private final IEventMiniDtoMapper mapper;

    @Override
    public Page<EventMinniResDto> getEventsMini(Pageable pageable) {
        Page<Event> events = eventRepository.findAll(pageable);
        return events.map(mapper::to);
    }
}
