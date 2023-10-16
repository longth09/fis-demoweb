package com.demo.tickets.domain.model;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

public class QTickets extends EntityPathBase<Tickets> {
    public static final QTickets tickets = new QTickets("tickets");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath address = createString("address");

    public QTickets(String variable) {
        super(Tickets.class, PathMetadataFactory.forVariable(variable));
    }

    public QTickets(Path<? extends Tickets> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTickets(PathMetadata metadata) {
        super(Tickets.class, metadata);
    }
}
