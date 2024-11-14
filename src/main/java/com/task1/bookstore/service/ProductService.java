package com.task1.bookstore.service;

import com.task1.bookstore.dto.BookDTO;
import com.task1.bookstore.dto.FilmDTO;
import com.task1.bookstore.dto.MusicDTO;
import com.task1.bookstore.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private final IPricingService pricingService;
    private final IBookService bookService;
    private final IFilmService filmService;
    private final IMusicService musicService;

    public ProductService(IPricingService pricingService, IBookService bookService, IFilmService filmService, IMusicService musicService) {
        this.bookService = bookService;
        this.filmService = filmService;
        this.musicService = musicService;
        this.pricingService = pricingService;
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        List<ProductDTO> allProducts = new ArrayList<>();


        List<BookDTO> books = bookService.getAllBooks();
        List<FilmDTO> films = filmService.getAllFilms();
        List<MusicDTO> musics = musicService.getAllMusics();

        books.forEach(b -> b.setBasePrice(pricingService.getBookPriceForPricingRule(b.getBasePrice())));
        films.forEach(f -> f.setBasePrice(pricingService.getFilmPriceForPricingRule(f.getBasePrice())));
        musics.forEach(m -> m.setBasePrice(pricingService.getMusicPriceForPricingRule(m.getBasePrice())));

        allProducts.addAll(books);
        allProducts.addAll(films);
        allProducts.addAll(musics);

        allProducts.sort(Comparator.comparing(ProductDTO::getProductId));
        return allProducts;
    }


}