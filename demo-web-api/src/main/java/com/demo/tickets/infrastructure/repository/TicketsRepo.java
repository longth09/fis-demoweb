package com.demo.tickets.infrastructure.repository;

import com.demo.tickets.domain.model.QTickets;
import com.demo.tickets.domain.model.Tickets;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TicketsRepo {

    @PersistenceContext
    private EntityManager entityManager;

    JPAQuery query;

//    public List<Tickets> findUsersWithUsernameLike(String address) {
//        QTickets tickets = QTickets.tickets;
//
//        query = new JPAQuery<>(entityManager);
//
////        List<Tickets> c = query.from(tickets)
////                .where(tickets.address.like(address))
////                .();
//        return c;
//    }
}
