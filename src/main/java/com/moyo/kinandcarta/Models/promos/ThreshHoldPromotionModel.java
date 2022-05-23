package com.moyo.kinandcarta.Models.promos;


import com.moyo.kinandcarta.Models.ProductModel;
import com.moyo.kinandcarta.Models.PromoType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;

@Component
@Getter
@Setter
public class ThreshHoldPromotionModel implements Promotion {

    final public static String type= PromoType.THRESHOLD.name();




    @Override
    public String getType() {
        return type;
    }

    @Override
    public ArrayList<ProductModel> applyPromotion(ArrayList<ProductModel> items) {
        BigDecimal sum= BigDecimal.valueOf(0);
        ArrayList<ProductModel> list= new ArrayList<>();
        for(ProductModel p:items){
            sum=sum.add(p.getPrice());
        }
        if(sum.compareTo(BigDecimal.valueOf(75))>0){
           for(ProductModel product:items){
               product.setPrice(product.getPrice().multiply(BigDecimal.valueOf(0.9)));
           }
        }
        return items;
    }
}
