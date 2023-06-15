package com.alex.catalog.common;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Абстракция - уникальный идентификатор.
 *
 * @param <I> Тип идентификатора опознаваемой сущности
 */
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class AbstractId<I extends Serializable> implements Identifiable<I> {

    /**
     * Уникальный идентификатор.
     */
    private I id;
}
