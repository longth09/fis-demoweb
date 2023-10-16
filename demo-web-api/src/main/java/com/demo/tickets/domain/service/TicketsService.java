package com.demo.tickets.domain.service;

import com.demo.tickets.api.dto.TicketsRequest;
import com.demo.tickets.api.dto.TicketsResDto;
import com.demo.tickets.domain.model.Tickets;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketsService {

    Page<Tickets> getAll(Pageable pageable);

    Tickets findById(Long id);

    Boolean deleteById(Long id);

    TicketsResDto insert(Tickets tickets);

    Tickets update(Long id, Tickets tickets);

    Boolean clean();

    Page<Tickets> search(TicketsRequest tickets, Pageable pageable);

}
