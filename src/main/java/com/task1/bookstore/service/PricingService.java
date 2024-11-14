package com.task1.bookstore.service;

import com.task1.bookstore.dto.*;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PricingService implements IPricingService {

    private final BookService bookService;
    private final MusicService musicService;
    private final FilmService filmService;

    public PricingService(BookService bookService, MusicService musicService, FilmService filmService) {

        this.bookService = bookService;
        this.musicService = musicService;
        this.filmService = filmService;

    }

    public ProductDTO getProductById(Long productId) {
        ProductDTO product = bookService.getBookById(productId);

        if (product != null) {
            double newTotalAmount = getBookPriceForPricingRule(product.getBasePrice());
            product.setBasePrice(newTotalAmount);
            return product;
        }
        product = musicService.getMusicById(productId);
        if (product != null) {
            double newTotalAmount = getMusicPriceForPricingRule(product.getBasePrice());
            product.setBasePrice(newTotalAmount);
            return product;
        }
        product = filmService.getFilmById(productId);
        if (product != null) {
            double newTotalAmount = getFilmPriceForPricingRule(product.getBasePrice());
            product.setBasePrice(newTotalAmount);
            return product;
        }
        throw new IllegalArgumentException("Product not found with ID: " + productId);
    }
    public double getFilmPriceForPricingRule(double basePrice) {
        double price = (basePrice * 115) / 100;
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(price));
    }

    public double getMusicPriceForPricingRule(double basePrice) {
        double price = (basePrice * 95) / 100;
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(price));
    }

    public double getBookPriceForPricingRule(double basePrice) {
        double price = basePrice + 2;
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(price));
    }


    public List<ProductDTO> getProductsFromOrder(List<ProductOrderDTO> productOrders) {
        List<ProductDTO> products = new ArrayList<>();

        for (ProductOrderDTO order : productOrders) {
            int count = order.getCount();
            for (int i = 0; i < count; i++) {
                ProductDTO product = getProductById(order.getProductId());
                if (product != null) {
                    products.add(product);
                }
            }
        }
        return products;
    }

    public double bookCampaign(List<ProductDTO> products) {
        List<BookDTO> books = new ArrayList<>();

        for (ProductDTO product : products) {
            if (product instanceof BookDTO) {
                books.add((BookDTO) product);
            }
        }

        if (books.size() == 1) {
            return books.getFirst().getBasePrice();
        }
        if (books.size() >= 2) {
            BookDTO cheapestBook = Collections.min(books, Comparator.comparing(BookDTO::getBasePrice));
            return books.stream().mapToDouble(BookDTO::getBasePrice).sum()- cheapestBook.getBasePrice();
        }
        return 0.0;
    }

    public double productCampaign(List<ProductDTO> products) {
        if (products.size() >= 2) {
            ProductDTO cheapestProduct = Collections.min(products, Comparator.comparing(ProductDTO::getBasePrice));
            double bookMinPrice = (cheapestProduct.getBasePrice()) / 2;
            return products.stream().mapToDouble(ProductDTO::getBasePrice).sum() - bookMinPrice;
        }
        if (products.size() == 1) {
            return products.getFirst().getBasePrice();
        }
        return 0.0;
    }

    public double bestCampaign(List<ProductDTO> products) {
        double bookCampaign = bookCampaign(products);
        double productCampaign = productCampaign(products);
        double productPrice = productPrice(products);
        if ((bookCampaign)+productPrice > productCampaign) {
            return productCampaign;
        } else if (productCampaign > (bookCampaign)+productPrice) {
            return bookCampaign+productPrice;
        }
        return productCampaign;
    }

    public double productPrice(List<ProductDTO> products) {
double productPrice = 0d;

        for (ProductDTO product : products) {
            if (product instanceof BookDTO)
                continue;

           productPrice = productPrice + product.getBasePrice();
        }
        return productPrice;
    }
}