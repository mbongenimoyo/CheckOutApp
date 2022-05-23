package com.moyo.kinandcarta.model;

import com.moyo.kinandcarta.Models.ProductModel;
import com.moyo.kinandcarta.Models.PromoType;
import com.moyo.kinandcarta.Models.promos.ThreshHoldPromotionModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;


//@SpringBootTest(args={"--checkout=1,2,3"})
public class ThresholdPromotionsTest {

    @Test
    public void ThresholdPromoTest_ApplyPromotion(){
        ThreshHoldPromotionModel threshHoldPromotionModel= new ThreshHoldPromotionModel();
        ArrayList<ProductModel> arr=new ArrayList<>();
        ProductModel product= new ProductModel();
        product.setPrice(BigDecimal.valueOf(75.01));
        arr.add(product);
        Assertions.assertEquals(BigDecimal.valueOf(75.01).multiply(BigDecimal.valueOf(0.9)),
                threshHoldPromotionModel.applyPromotion(arr).get(0).getPrice());
    }

@Test
public void ThreshHoldPromoTest_getType(){
        ThreshHoldPromotionModel model=new ThreshHoldPromotionModel();
       Assertions.assertEquals(PromoType.THRESHOLD.name(),model.getType());
}
}
