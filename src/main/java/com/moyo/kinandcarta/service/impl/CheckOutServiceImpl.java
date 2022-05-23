package com.moyo.kinandcarta.service.impl;

import com.moyo.kinandcarta.Models.ProductModel;
import com.moyo.kinandcarta.Models.promos.Promotion;
import com.moyo.kinandcarta.repo.ProductRepo;
import com.moyo.kinandcarta.service.CheckOutService;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
public class CheckOutServiceImpl implements CheckOutService {


    ArrayList<ProductModel> productModels =new ArrayList<>();

    private ArrayList<Promotion> promotions=new ArrayList<>();

    public CheckOutServiceImpl(ArrayList<Promotion> promotion) {
        this.promotions.addAll(promotion);
    }
    public CheckOutServiceImpl(){

    }


    @Override
    public void scanItems(List<ProductModel> items)  {
        productModels.addAll(items);


    }

    @Override
    public BigDecimal getTotalPrice() {
        BigDecimal sum= BigDecimal.valueOf(0);
        ArrayList<ProductModel> temp=new ArrayList<>();
        if(promotions.size() >0){
            for(Promotion p:promotions){
                p.applyPromotion(productModels);
            }

        }
        for(ProductModel p:productModels){
            sum=sum.add(p.getPrice());
        }

        return sum.setScale(2,BigDecimal.ROUND_HALF_EVEN);
    }



}
