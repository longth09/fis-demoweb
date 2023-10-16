package com.demo.tickets.domain.service;

import com.demo.coupons.domain.model.Coupons;
import com.demo.coupons.domain.service.ICouponsService;
import com.demo.coupons.infrastructure.repository.CouponsRepository;
import com.demo.tickets.api.dto.TicketsRequest;
import com.demo.tickets.api.dto.TicketsResDto;
import com.demo.tickets.domain.model.Tickets;
import com.demo.tickets.infrastructure.repository.TicketsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TicketsServiceImpl implements TicketsService {

    private final CouponsRepository couponsRepository;

    private final ICouponsService iCouponsService;

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
        TicketsResDto ticketsResDto = new TicketsResDto();
        ticketsResDto.setTickets(tickets);
        if (tickets.getOffer() == null) {
            Tickets tickets1 = repository.save(tickets);
        } else {
            Coupons coupons = couponsRepository.findByCode(tickets.getOffer());
            if (coupons != null && coupons.getCurrentQuantity() != 0) {
                ticketsResDto.setCodeCoupon(tickets.getOffer());
                Tickets tickets1 = repository.save(tickets);
                if (coupons.getDiscountPercentage() != null) {
                    BigDecimal persentage = tickets1.getPrice().multiply(BigDecimal.valueOf(coupons.getDiscountPercentage()).divide(BigDecimal.valueOf(100)));
                    BigDecimal priceDown = tickets1.getPrice().subtract(persentage);
                    ticketsResDto.setPriceDiscount(priceDown);
                } else {
                    BigDecimal priceDown = tickets1.getPrice().subtract(BigDecimal.valueOf(coupons.getDiscount()));
                    ticketsResDto.setPriceDiscount(priceDown);
                }

                Coupons coupons1 = iCouponsService.updateQuantity(coupons.getId(), 1);
            }
        }


        return ticketsResDto;
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

    @Override
    public Page<Tickets> search(TicketsRequest tickets, Pageable pageable) {
        return repository.search(tickets.getId(), tickets.getName(), tickets.getAddress(), tickets.getRank(), tickets.getPrice(), tickets.getNote(), tickets.getMeetings(), tickets.getTicketType(), tickets.getStartTime(), tickets.getCreateDate(), tickets.getUpdateDate(), tickets.getOffer(), tickets.getTrangThai(), pageable);
    }
}
