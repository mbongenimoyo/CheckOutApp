package com.moyo.kinandcarta.Models.promos;

import com.moyo.kinandcarta.Models.ProductModel;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface Promotion {

    public String getType();

    public ArrayList<ProductModel> applyPromotion(ArrayList<ProductModel> items);

}
