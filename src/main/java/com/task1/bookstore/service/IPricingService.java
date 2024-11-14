package com.task1.bookstore.service;

import com.task1.bookstore.dto.ProductDTO;
import com.task1.bookstore.dto.ProductOrderDTO;

import java.util.List;

public interface IPricingService {

    List<ProductDTO> getProductsFromOrder(List<ProductOrderDTO> productOrders);

    double bestCampaign(List<ProductDTO> products);

    double getFilmPriceForPricingRule(double basePrice);

    double getMusicPriceForPricingRule(double basePrice);

    double getBookPriceForPricingRule(double basePrice);
}
