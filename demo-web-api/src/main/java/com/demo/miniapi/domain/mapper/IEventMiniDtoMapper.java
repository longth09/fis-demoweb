package com.demo.miniapi.domain.mapper;

import com.demo.common.mapper.IObjectMapper;
import com.demo.event.domain.model.Event;
import com.demo.miniapi.api.dto.EventMinniResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IEventMiniDtoMapper extends IObjectMapper<EventMinniResDto, Event> {
    @Mapping(target = "id", ignore = true)
    Event meger(@MappingTarget Event target, EventMinniResDto source);
}
