package com.moyo.kinandcarta.service;

import com.moyo.kinandcarta.Models.ProductModel;

import javax.management.BadAttributeValueExpException;
import java.math.BigDecimal;
import java.util.List;

public interface CheckOutService {
    public  void scanItems(List<ProductModel> items) throws BadAttributeValueExpException;

    public BigDecimal getTotalPrice();
}
