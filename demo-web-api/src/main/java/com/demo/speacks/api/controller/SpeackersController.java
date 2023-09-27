package com.demo.speacks.api.controller;

import com.demo.common.exception.DefaultErrorCode;
import com.demo.common.rest.response.BaseResponse;
import com.demo.speacks.domain.model.Speakers;
import com.demo.speacks.domain.service.ISpeackersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class SpeackersController {

    private final ISpeackersService speackersService;

    @GetMapping("speackers")
    public BaseResponse<?> getAll(Pageable pageable) {
        Page<Speakers> speakers = speackersService.getAll(pageable);
        if (speakers.isEmpty()) {
            return BaseResponse.ofFailed(DefaultErrorCode.DEFAULT_NOT_FOUND);
        }
        return BaseResponse.ofSucceeded(speackersService.getAll(pageable));
    }

    @GetMapping("speackers/{id}")
    public BaseResponse<?> findById(@PathVariable("id") Long id) {
        Optional<Speakers> speakers = speackersService.findById(id);
        if (speakers.isEmpty()) return BaseResponse.ofFailed(DefaultErrorCode.DEFAULT_NOT_FOUND);
        return BaseResponse.ofSucceeded(speakers.get());
    }

    @DeleteMapping("speackers/{id}")
    public BaseResponse<?> remove(@PathVariable("id") Long id) {
        Boolean del = speackersService.remove(id);
        if (del) return BaseResponse.ofSucceeded("");
        return BaseResponse.ofFailed(DefaultErrorCode.DEFAULT_NOT_FOUND);
    }

    @DeleteMapping("speackers")
    public BaseResponse<?> removeAll() {
        Boolean delAll = speackersService.removeAll();
        if (delAll) return BaseResponse.ofSucceeded("");
        return BaseResponse.ofFailed(DefaultErrorCode.DEFAULT_BAD_REQUEST);
    }

    @PostMapping("speacker")
    public BaseResponse<?> insert(@Valid @RequestBody Speakers speakers) {
        try {
            Speakers speakers1 = speackersService.insert(speakers);
            return BaseResponse.ofSucceeded(speakers1);
        } catch (Exception e) {
            return BaseResponse.ofFailed(DefaultErrorCode.DEFAULT_BAD_REQUEST);
        }
    }

    @PutMapping("speacker/{id}")
    public BaseResponse<?> update(@Valid @RequestBody Speakers speakers, @PathVariable("id") Long id) {
        try {
            Speakers speakers1 = speackersService.update(id, speakers);
            return BaseResponse.ofSucceeded(speakers1);
        } catch (Exception e) {
            return BaseResponse.ofFailed(DefaultErrorCode.DEFAULT_BAD_REQUEST);
        }
    }

    @GetMapping("speackers-events")
    public BaseResponse<?> getSpeackersEvents(Pageable pageable) {
        return BaseResponse.ofSucceeded(speackersService.getSpeackerEvents(pageable));
    }

}
