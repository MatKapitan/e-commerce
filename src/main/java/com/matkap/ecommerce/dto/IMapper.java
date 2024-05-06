package com.matkap.ecommerce.dto;

import java.util.List;
import java.util.stream.Collectors;

public interface IMapper<E,D> {

    D toDto(E entity);


    default List<D> toDtoList(List<E> entityList){
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }


    E toEntity (D dto);

}
