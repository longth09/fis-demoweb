package com.demo.tickets.domain.model;

import com.demo.event.domain.model.Event;
import com.demo.shared.model.JpaIDEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tickets")
public class Tickets extends JpaIDEntity {

    private String name;

    private String address;

    @Column(name = "`rank`")
    private Character rank;

    private BigDecimal price;

    private String note;

    private String meetings;

    @Column(name = "ticket_type")
    private String ticketsType;

    @Column(name = "start_time")
    private Instant startTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    private String offer;

    @Column(name = "trang_thai")
    Integer trangThai = 0;

}
