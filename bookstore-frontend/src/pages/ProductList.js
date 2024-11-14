import React, { useEffect, useState } from 'react';
import { fetchBooks, fetchFilms, fetchMusics } from '../services/api';
import BookCard from '../components/BookCard';
import FilmCard from '../components/FilmCard';
import MusicCard from '../components/MusicCard';
import '../styles/ProductList.css';
import imageMapping from "../utils/imageMapping";

function ProductsList({ addToCart, searchTerm }) {
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [sortOrder, setSortOrder] = useState(''); // Sıralama yönü: artan veya azalan
    const [sortType, setSortType] = useState(''); // Sıralama türü: fiyat veya tarih

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
                setLoading(false);
            }
        };
        getProducts();
    }, []);

    const handleSortOrderChange = (e) => {
        setSortOrder(e.target.value);
    };

    const handleSortTypeChange = (e) => {
        setSortType(e.target.value);
        setSortOrder(''); // Sıralama türü değiştiğinde sıralama yönünü sıfırlıyoruz
    };

    // Arama ve sıralama filtresi
    const filteredProducts = products
        .filter((product) =>
            product.name.toLowerCase().includes(searchTerm.toLowerCase())
        )
        .sort((a, b) => {
            if (sortType === 'price') {
                if (sortOrder === 'ascending') {
                    return a.basePrice - b.basePrice; // Fiyata göre artan sırada
                } else if (sortOrder === 'descending') {
                    return b.basePrice - a.basePrice; // Fiyata göre azalan sırada
                }
            } else if (sortType === 'date') {
                if (sortOrder === 'newest') {
                    return new Date(b.releaseDate) - new Date(a.releaseDate); // En yeni önce
                } else if (sortOrder === 'oldest') {
                    return new Date(a.releaseDate) - new Date(b.releaseDate); // En eski önce
                }
            }
            return 0; // Sıralama yok
        });

    return (
        <div className="products-list">
            <div className="header-options">
                <div className="sort-options">
                    <label>Sırala:</label>

                    {/* Sıralama Türü Seçimi */}
                    <select value={sortType} onChange={handleSortTypeChange}>
                        <option value="">Seçiniz</option>
                        <option value="price">Fiyata Göre</option>
                        <option value="date">Yayın Tarihine Göre</option>
                    </select>

                    {/* Sıralama Yönü Seçimi */}
                    <select value={sortOrder} onChange={handleSortOrderChange}>
                        <option value="">Seçiniz</option>
                        {sortType === 'price' ? (
                            <>
                                <option value="ascending">Artan</option>
                                <option value="descending">Azalan</option>
                            </>
                        ) : sortType === 'date' ? (
                            <>
                                <option value="newest">En Yeni</option>
                                <option value="oldest">En Eski</option>
                            </>
                        ) : null}
                    </select>
                </div>
            </div>

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
                <p>Ürün verisi bulunamadı.</p>
            )}
        </div>
    );
}

export default ProductsList;
