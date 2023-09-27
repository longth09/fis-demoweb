package com.demo.event.domain.mapper;

import com.demo.common.mapper.IObjectMapper;
import com.demo.event.api.dto.EventRequestDto;
import com.demo.event.domain.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IEventRequestDtoMapper extends IObjectMapper<EventRequestDto, Event> {
    @Mapping(target = "id", ignore = true)
    Event merge(@MappingTarget Event target, EventRequestDto source);
}
