package com.demo.tickets.infrastructure.repository;

import com.demo.tickets.domain.model.QTickets;
import com.demo.tickets.domain.model.Tickets;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class TicketsRepo {

    @PersistenceContext
    EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("com.baeldung.querydsl.intro");

    EntityManager em = emf.createEntityManager();
    JPAQueryFactory queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, em);

    public Tickets findUsersWithUsernameLike(String address) {
        QTickets tickets = QTickets.tickets;

        Tickets c = queryFactory.selectFrom(tickets)
                .where(tickets.address.like(address))
                .fetchOne();
        return c;
    }
}
