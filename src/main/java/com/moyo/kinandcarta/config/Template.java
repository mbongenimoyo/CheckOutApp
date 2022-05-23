package com.moyo.kinandcarta.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Getter
@Setter
public class Template {

    public Template(ArrayList<Long> items, String promotion) {
        this.items = items;
        this.promotion = promotion;
    }

    public Template(){

    }

    @Value("#{'${checkout}'.split(',')}")
    private ArrayList<Long> items;

    @Value("${promotion:}")
    private String promotion;



}
