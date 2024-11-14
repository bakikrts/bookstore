package com.task1.bookstore.controller;

import com.task1.bookstore.dto.ProductDTO;
import com.task1.bookstore.dto.ProductOrderRequestDTO;
import com.task1.bookstore.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")

public class PurchaseController {
    private final PricingService pricingService;

    @Autowired
    public PurchaseController(PricingService pricingService) {
        this.pricingService = pricingService;

    }

    @PostMapping("/calculateTotal")
    public ResponseEntity<Double> calculateTotal(@RequestBody ProductOrderRequestDTO productOrderRequestDTO) {
        List<ProductDTO> products = pricingService.getProductsFromOrder(productOrderRequestDTO.getProducts());
        products.forEach(product -> product.setBasePrice(pricingService.getProductById(product.getProductId()).getBasePrice()));
        double finalprice = pricingService.bestCampaign(products);
        return ResponseEntity.ok(finalprice);

    }
}
