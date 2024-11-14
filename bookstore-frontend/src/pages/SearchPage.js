import React, { useEffect, useState } from 'react';
import BookCard from "../components/BookCard";
import imageMapping from "../utils/imageMapping";
import FilmCard from "../components/FilmCard";
import MusicCard from "../components/MusicCard";
import { fetchBooks, fetchFilms, fetchMusics } from "../services/api";

function SearchPage({ addToCart, searchTerm }) {
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const getProducts = async () => {
            try {
                const booksData = (await fetchBooks()).map((book) => ({ ...book, type: 'book' }));
                const filmsData = (await fetchFilms()).map((film) => ({ ...film, type: 'film' }));
                const musicsData = (await fetchMusics()).map((music) => ({ ...music, type: 'music' }));
                setProducts([...booksData, ...filmsData, ...musicsData]);
            } catch (error) {
                console.error("Failed to fetch products:", error);
            } finally {
                setLoading(false); // Yüklenme durumunu kapat
            }
        };
        getProducts();
    }, []);

    // Arama filtresi: searchTerm boşsa tüm ürünler değil, sadece "Ürün bulunamadı" mesajı gösterilecek
    const filteredProducts = searchTerm
        ? products.filter((product) =>
            product.name && product.name.toLowerCase().includes(searchTerm.toLowerCase())
        )
        : [];

    return (
        <div className="products-list">
            {loading ? (
                <p>Yükleniyor...</p>
            ) : filteredProducts.length > 0 ? (
                <div className="products-list-duzen">
                    {filteredProducts.map((product) => {
                        if (product.type === 'book') {
                            return <BookCard key={product.id} book={product} addToCart={addToCart} image={imageMapping.books[product.productId]} />;
                        } else if (product.type === 'film') {
                            return <FilmCard key={product.id} film={product} addToCart={addToCart} image={imageMapping.films[product.productId]} />;
                        } else if (product.type === 'music') {
                            return <MusicCard key={product.id} music={product} addToCart={addToCart} image={imageMapping.musics[product.productId]} />;
                        } else {
                            return null;
                        }
                    })}
                </div>
            ) : (
                <p>Aramanıza uygun ürün bulunamadı.</p>
            )}
        </div>
    );
}

export default SearchPage;
