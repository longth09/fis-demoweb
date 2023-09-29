package com.demo.tickets.domain.service;

import com.demo.coupons.domain.model.Coupons;
import com.demo.coupons.infrastructure.repository.CouponsRepository;
import com.demo.tickets.api.dto.TicketsResDto;
import com.demo.tickets.domain.model.Tickets;
import com.demo.tickets.infrastructure.repository.TicketsRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketsServiceImpl implements TicketsService {

    private final CouponsRepository couponsRepository;

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
    public TicketsResDto insert(Tickets tickets) {
        if(tickets.getOffer() == null) {
            Tickets tickets1 = repository.save(tickets);
        } else {
            Coupons coupons = couponsRepository.findByCode(tickets.getOffer());
            if(coupons != null) {
                Tickets tickets1 = repository.save(tickets);
                TicketsResDto ticketsResDto = new TicketsResDto();
                ticketsResDto.setCodeCoupon(tickets.getOffer());
                BigDecimal priceDown = tickets1.getPrice().multiply(BigDecimal.valueOf(coupons.getDiscount()).divide(BigDecimal.valueOf(100)));

            }
        }


        return null;
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
