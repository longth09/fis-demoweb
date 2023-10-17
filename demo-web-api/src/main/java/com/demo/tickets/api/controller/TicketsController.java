package com.demo.tickets.api.controller;

import com.demo.common.exception.DefaultErrorCode;
import com.demo.common.rest.response.BaseResponse;
import com.demo.tickets.api.dto.TicketsRequest;
import com.demo.tickets.api.dto.TicketsResDto;
import com.demo.tickets.domain.model.Tickets;
import com.demo.tickets.domain.service.TicketsService;
import com.demo.tickets.infrastructure.repository.TicketsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class TicketsController {

    private final TicketsService ticketsService;

    @GetMapping("/tickets")
    public BaseResponse<?> getAll(Pageable pageable) {
        return BaseResponse.ofSucceeded(ticketsService.getAll(pageable));
    }

    @GetMapping("/tickets/{id}")
    public BaseResponse<?> findById(@PathVariable("id") Long id) {
        try {
            Tickets tickets = ticketsService.findById(id);
            return BaseResponse.ofSucceeded(tickets);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.ofFailed(DefaultErrorCode.DEFAULT_NOT_FOUND);
        }
    }

    @DeleteMapping("/tickets/{id}")
    public BaseResponse<?> deleteById(@PathVariable("id") Long id) {
        Boolean del = ticketsService.deleteById(id);
        if (del)
            return BaseResponse.ofSucceeded("Delete success id: " + id);
        return BaseResponse.ofFailed(DefaultErrorCode.DEFAULT_NOT_FOUND);
    }

    @DeleteMapping("/tickets")
    public BaseResponse<?> clean() {
        Boolean clean = ticketsService.clean();
        if (clean)
            return BaseResponse.ofSucceeded("Clean success!");
        return BaseResponse.ofFailed(DefaultErrorCode.DEFAULT_BAD_REQUEST);
    }

    @PostMapping("/tickets")
    public BaseResponse<?> insert(@Valid @RequestBody Tickets tickets) {
        try {
            TicketsResDto tickets1 = ticketsService.insert(tickets);
            return BaseResponse.ofSucceeded(tickets1);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.ofFailed(DefaultErrorCode.DEFAULT_BAD_REQUEST);
        }
    }

    @PutMapping("/tickets/{id}")
    public BaseResponse<?> update(@Valid @RequestBody Tickets tickets, @PathVariable("id") Long id) {
        try {
            Tickets tickets1 = ticketsService.update(id, tickets);
            return BaseResponse.ofSucceeded(tickets1);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.ofFailed(DefaultErrorCode.DEFAULT_BAD_REQUEST);
        }
    }

    @GetMapping("/tickets/search")
    public BaseResponse<?> search(@RequestBody TicketsRequest tickets, Pageable pageable) {
        if (tickets != null) return BaseResponse.ofSucceeded(ticketsService.search(tickets, pageable));
        return BaseResponse.ofSucceeded(getAll(pageable));

    }
}
