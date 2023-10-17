package com.demo.tickets.infrastructure.repository;

import com.demo.tickets.domain.model.Tickets;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;

@Repository
public interface TicketsRepository extends JpaRepository<Tickets, Long> {

    @Query(value = "SELECT * FROM tickets WHERE id =:id " +
            "OR `name` =:name " +
            "OR address =:address " +
            "OR `rank` =:rank " +
            "OR price =:price " +
            "OR note =:note " +
            "OR meetings =:meetings " +
            "OR ticket_type =:ticketType " +
            "OR start_time =:startTime " +
            "OR created_date =:createDate " +
            "OR updated_date =:updateDate " +
            "OR offer =:offer " +
            "OR (:trangThai IS NULL OR trang_thai = COALESCE(:trangThai, ''))", nativeQuery = true)
    Page<Tickets> search(@Param("id") Long id, @Param("name") String name,
                         @Param("address") String address,
                         @Param("rank") Character rank,
                         @Param("price") BigDecimal price,
                         @Param("note") String note,
                         @Param("meetings") String meetings,
                         @Param("ticketType") String ticketType,
                         @Param("startTime") Instant startTime,
                         @Param("createDate") Instant createDate,
                         @Param("updateDate") Instant updateDate,
                         @Param("offer") String offer,
                         @Param("trangThai") Integer trangThai,
                         Pageable pageable);
}
