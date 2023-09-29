package com.demo.tickets.api.dto;

import com.demo.tickets.domain.model.Tickets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketsResDto {

    private Tickets tickets;

    private BigDecimal priceDiscount;

    private String codeCoupon;

}
