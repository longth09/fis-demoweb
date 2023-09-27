package com.demo.miniapi.api.controller;

import com.demo.common.rest.response.BaseResponse;
import com.demo.miniapi.api.dto.EventMinniResDto;
import com.demo.miniapi.domain.service.IEventMiniService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class EventsMiniController {
    private final IEventMiniService miniService;

    @GetMapping("events/mini")
    BaseResponse<Page<EventMinniResDto>> getEventsMini(Pageable pageable) {
        return BaseResponse.ofSucceeded(miniService.getEventsMini(pageable));
    }
}
