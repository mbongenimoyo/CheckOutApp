package com.moyo.kinandcarta.Models.promos;

import com.moyo.kinandcarta.Models.ProductModel;
import com.moyo.kinandcarta.Models.PromoType;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;

@Getter
public class TwoOrMorePromotionModel implements Promotion {

    final public static String type= PromoType.TWO_OR_MORE.name();

    @Override
    public String getType() {
        return type;
    }

    @Override
    public ArrayList<ProductModel> applyPromotion(ArrayList<ProductModel> items) {
        BigDecimal sum= BigDecimal.valueOf(0);
        ArrayList<ProductModel> list= new ArrayList<>();
        for(ProductModel p:items){
            if(p.getId()==1L) {
                p.setPrice(BigDecimal.valueOf(22.99));
            }

        }
        return items;

    }
}
