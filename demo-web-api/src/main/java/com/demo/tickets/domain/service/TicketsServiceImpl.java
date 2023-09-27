package com.demo.tickets.domain.service;

import com.demo.tickets.domain.model.Tickets;
import com.demo.tickets.infrastructure.repository.TicketsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketsServiceImpl implements TicketsService{

    private final TicketsRepository repository;

    @Override
    public Page<Tickets> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Tickets findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Boolean deleteById(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Tickets insert(Tickets tickets) {
        return repository.save(tickets);
    }

    @Override
    public Tickets update(Long id, Tickets tickets) {
        tickets.setId(id);
        return repository.save(tickets);
    }

    @Override
    public Boolean clean() {
        try {
            repository.deleteAll();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
