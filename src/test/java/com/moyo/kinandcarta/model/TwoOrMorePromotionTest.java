package com.moyo.kinandcarta.model;

import com.moyo.kinandcarta.Models.ProductModel;
import com.moyo.kinandcarta.Models.PromoType;
import com.moyo.kinandcarta.Models.promos.ThreshHoldPromotionModel;
import com.moyo.kinandcarta.Models.promos.TwoOrMorePromotionModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

public class TwoOrMorePromotionTest {

    @Test
    public void TwoOrMorePromoTest_ApplyPromotion_WithOnlyWater(){
        TwoOrMorePromotionModel model= new TwoOrMorePromotionModel();
        ArrayList<ProductModel> arr=new ArrayList<>();
        ProductModel product= new ProductModel();
        product.setId(1L);
        product.setPrice(BigDecimal.valueOf(24.95));
        arr.add(product);
        arr.add(product);
        Assertions.assertEquals(BigDecimal.valueOf(22.99),
                model.applyPromotion(arr).get(0).getPrice());
    }

    @Test
    public void TwoOrMorePromoTest_getType(){
        ThreshHoldPromotionModel model=new ThreshHoldPromotionModel();
        Assertions.assertEquals(PromoType.TWO_OR_MORE.name(),model.getType());
    }

}
