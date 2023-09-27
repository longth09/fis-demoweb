package com.demo.miniapi.domain.service;

import com.demo.miniapi.api.dto.EventMinniResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEventMiniService {
    Page<EventMinniResDto> getEventsMini(Pageable pageable);
}
