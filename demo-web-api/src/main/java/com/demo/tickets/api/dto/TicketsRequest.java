package com.demo.tickets.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketsRequest {

    private Long id;

    private String name;

    private String address;

    private Character rank;

    private BigDecimal price;

    private String note;

    private String meetings;

    private String ticketType;

    private Instant startTime;

    private Instant createDate;

    private Instant updateDate;

    private String offer;

    private Integer trangThai;

}
