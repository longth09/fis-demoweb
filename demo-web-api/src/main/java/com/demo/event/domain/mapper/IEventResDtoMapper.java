package com.demo.event.domain.mapper;

import com.demo.common.mapper.IObjectMapper;
import com.demo.event.api.dto.EventResDto;
import com.demo.event.domain.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IEventResDtoMapper extends IObjectMapper<EventResDto, Event> {
    @Mapping(target = "id", ignore = true)
    Event merge(@MappingTarget Event target, EventResDto source);
}
