package com.moyo.kinandcarta.Models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class ProductModel {

    @Id
    private long id;

    private String name;

    private BigDecimal price;

    public ProductModel(long id,String name,BigDecimal price){
        this.id=id;
        this.name=name;
        this.price=price;
    }


    public ProductModel() {

    }
}
