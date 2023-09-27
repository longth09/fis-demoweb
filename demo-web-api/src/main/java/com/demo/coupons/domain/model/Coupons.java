package com.demo.coupons.domain.model;

import com.demo.event.domain.model.Event;
import com.demo.shared.model.JpaIDEntity;
import com.demo.tickets.domain.model.Tickets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "coupons", uniqueConstraints = {@UniqueConstraint(columnNames = "code")})
@Builder
public class Coupons extends JpaIDEntity {

    @Column(name = "name")
    @NotEmpty(message = "name isEmpty!")
    String name;

    @Column(name = "code")
    @NotEmpty(message = "code isEmpty!")
    String code;

    @Column(name = "description")
    @NotEmpty(message = "description isEmpty!")
    String description;

    @Column(name = "expire_time")
    @NotNull(message = "expireTime isEmpty!")
    Instant expireTime;

    @Column(name = "quantity")
    @NotNull(message = "quantity isEmpty!")
    Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    Tickets ticketId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    Event eventId;

    @Column(name = "discount")
    @NotNull(message = "discount isEmpty!")
    Double discount;

    @Column(name = "discount_percentage")
    @NotNull(message = "Name isEmpty!")
    Double discountPercentage;

    @Column(name = "max_discount")
    @NotNull(message = "Name isEmpty!")
    Double maxDiscount;

    @Column(name = "current_quantity")
    Integer currentQuantity;

}
