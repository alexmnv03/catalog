package com.alex.catalog.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс создан в основном, чтобы показать вариант работы со встроенным классом.
 * А так же задел на будущее, предполагалось, что тут будет еще одно-два поля
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PricePart implements Serializable {

    @Column(nullable = false)
    private Double price;
    
}
