package com.moyo.kinandcarta.service;

import com.moyo.kinandcarta.Models.ProductModel;
import com.moyo.kinandcarta.Models.promos.Promotion;
import com.moyo.kinandcarta.Models.promos.ThreshHoldPromotionModel;
import com.moyo.kinandcarta.Models.promos.TwoOrMorePromotionModel;
import com.moyo.kinandcarta.service.impl.CheckOutServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CheckOutServiceImplTest {

    @Test
    public void CheckOutServiceTest_getPrice_single(){
        CheckOutServiceImpl c=new CheckOutServiceImpl();
        ArrayList arr=new ArrayList();
        ArrayList<ProductModel> itemStore=getItemStore();

        ProductModel product=new ProductModel();
        product.setPrice(BigDecimal.valueOf(10.00));
        arr.add(product);
        c.scanItems(arr);
        Assertions.assertEquals(BigDecimal.valueOf(10.00).setScale(2,BigDecimal.ROUND_HALF_EVEN),
                c.getTotalPrice());
    }

    private ArrayList<ProductModel> getItemStore() {
        ArrayList arr=new ArrayList();
        ProductModel product1=new ProductModel(1L,"Water Bottle",
                BigDecimal.valueOf(24.95));

        ProductModel product2=new ProductModel(2L,"Hoodie",
                BigDecimal.valueOf(65.00));

        ProductModel product3=new ProductModel(3L,"Sticker Set",
                BigDecimal.valueOf(3.99));
        arr.add(product1);
        arr.add(product2);
        arr.add(product3);
        return arr;
    }

    @Test
    public void CheckOutServiceTest_getPrice_multiple(){
        CheckOutServiceImpl c=new CheckOutServiceImpl();
        ArrayList arr=new ArrayList();

        ProductModel product1=new ProductModel();
        product1.setPrice(BigDecimal.valueOf(10.00));
        ProductModel product2=new ProductModel();
        product2.setPrice(BigDecimal.valueOf(20.00));
        arr.add(product1);
        arr.add(product2);
        c.scanItems(arr);
        Assertions.assertEquals(BigDecimal.valueOf(30.00).setScale(2,BigDecimal.ROUND_HALF_EVEN),
                c.getTotalPrice());
    }



    @Test
    public void CheckOutService_PromotionsConstructorTest(){
        ArrayList<Promotion> arr= new ArrayList<>();
        Promotion promotion=new TwoOrMorePromotionModel();
        arr.add(promotion);
        CheckOutServiceImpl checkOut=new CheckOutServiceImpl(arr);
        Assertions.assertEquals(1,checkOut.getPromotions().size());
    }

    @Test
    public void CheckOutservice_scanItemTest(){
        CheckOutServiceImpl c=new CheckOutServiceImpl();
        ArrayList<ProductModel> arr=new ArrayList<>();
        ProductModel product1=new ProductModel();
        arr.add(product1);
        c.scanItems(arr);
        Assertions.assertEquals(1,c.getProductModels().size());
    }

//    Items: 0001,0001,0002,0003
//    Total Price: £103.47
    @Test
    public void CheckOutService_TestCase1(){
        ArrayList<ProductModel> l=new ArrayList<>();
        ArrayList<Promotion> promo=new ArrayList<>();
        promo.add(new TwoOrMorePromotionModel());
        promo.add(new ThreshHoldPromotionModel());
        CheckOutServiceImpl c=new CheckOutServiceImpl(promo);

        l.add(getItemStore().get(0));
        l.add(getItemStore().get(0));
        l.add(getItemStore().get(1));
        l.add(getItemStore().get(2));
        c.scanItems(l);
        Assertions.assertEquals(BigDecimal.valueOf(103.47),
                c.getTotalPrice());
    }

//    Items: 0001,0001,0001
//    Total Price: £68.97
    @Test
    public void CheckOutService_TestCase2(){
        ArrayList<ProductModel> l=new ArrayList<>();
        ArrayList<Promotion> promo=new ArrayList<>();
        promo.add(new TwoOrMorePromotionModel());
        promo.add(new ThreshHoldPromotionModel());
        CheckOutServiceImpl c=new CheckOutServiceImpl(promo);

        l.add(getItemStore().get(0));
        l.add(getItemStore().get(0));
        l.add(getItemStore().get(0));
        c.scanItems(l);
        Assertions.assertEquals(BigDecimal.valueOf(68.97),
                c.getTotalPrice());
    }

//    Items: 0002,0002,0003
//    Total Price: £120.59
    @Test
    public void CheckOutService_TestCase3(){
        ArrayList<ProductModel> l=new ArrayList<>();
        ArrayList<Promotion> promo=new ArrayList<>();
        promo.add(new TwoOrMorePromotionModel());
        promo.add(new ThreshHoldPromotionModel());
        CheckOutServiceImpl c=new CheckOutServiceImpl(promo);

        l.add(getItemStore().get(1));
        l.add(getItemStore().get(1));
        l.add(getItemStore().get(2));
        c.scanItems(l);
        Assertions.assertEquals(BigDecimal.valueOf(120.59),
                c.getTotalPrice());
    }


}
