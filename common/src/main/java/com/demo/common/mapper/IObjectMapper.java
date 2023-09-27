package com.demo.common.mapper;

import java.util.List;
import java.util.Set;

public interface IObjectMapper<S, D> {

    S to(D obj);

    D from(S obj);

    List<S> to(List<D> entities);

    List<D> from(List<S> dtos);

    Set<S> to(Set<D> entities);

    Set<D> from(Set<S> dtos);

//	Page<S> to(Page<D> entities);
//
//	Page<D> from(Page<S> dtos );

}
