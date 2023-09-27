package com.demo.event.domain.service;


import com.demo.common.exception.BusinessException;
import com.demo.event.api.dto.EventRequestDto;
import com.demo.event.api.dto.EventResDto;
import com.demo.event.domain.mapper.IEventRequestDtoMapper;
import com.demo.event.domain.mapper.IEventResDtoMapper;
import com.demo.event.domain.model.Event;
import com.demo.event.infrastructure.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.demo.common.exception.DefaultErrorCode.DEFAULT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class EventService implements IEventService {
    private final EventRepository repository;
    private final IEventRequestDtoMapper iEventRequestDtoMapper;

    private final IEventResDtoMapper mapper;

    @Override
    public Page<EventResDto> getEvents(Pageable pageable) {
        Page<Event> events = repository.findAll(pageable);
        return events.map(mapper::to);
    }

    @Override
    public Optional<EventResDto> getById(long id) {
        Optional<Event> event = repository.findById(id);
        return event.map(mapper::to);

    }

    @Override
    public Event insert(Event eventRequestDto) {
        Event save = repository.save(eventRequestDto);
        return save;
    }

    @Override
    public Event update(Long id, EventRequestDto eventRequestDto) {
        Event existed = repository.findById(id).orElseThrow(() -> new BusinessException(DEFAULT_NOT_FOUND));
        Event update = iEventRequestDtoMapper.merge(existed, eventRequestDto);
        Event save = repository.save(update);
        return save;
    }

    @Override
    public boolean delete(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }


    }

    @Override
    public boolean clean() {
        try {
            repository.deleteAll();
            return true;
        } catch (Exception e) {

            return false;
        }
    }
}
