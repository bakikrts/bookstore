import React, { useEffect, useState } from 'react';
import { fetchFilms } from '../services/api';
import FilmCard from '../components/FilmCard';
import '../styles/FilmList.css';
import imageMapping from "../utils/imageMapping";

function FilmList({ addToCart, searchTerm }) {
    const [films, setFilms] = useState([]);
    const [loading, setLoading] = useState(true);
    const [sortOrder, setSortOrder] = useState(''); // Sıralama yönü: artan veya azalan
    const [sortType, setSortType] = useState(''); // Sıralama türü: fiyat veya tarih

    useEffect(() => {
        const getFilms = async () => {
            try {
                const filmsData = await fetchFilms();
                setFilms(filmsData); // Film verisini kaydet
            } catch (error) {
                console.error("Failed to fetch films:", error);
            } finally {
                setLoading(false); // Yüklenme durumunu kapat
            }
        };
        getFilms();
    }, []);

    const handleSortOrderChange = (e) => {
        setSortOrder(e.target.value);
    };

    const handleSortTypeChange = (e) => {
        setSortType(e.target.value);
        setSortOrder(''); // Sıralama türü değiştiğinde sıralama yönünü sıfırlıyoruz
    };

    const filteredFilms = films
        .filter((film) =>
            film.name && searchTerm
                ? film.name.toLowerCase().includes(searchTerm.toLowerCase())
                : true
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
        <div className="film-list">
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
            ) : filteredFilms.length > 0 ? (
                <div className="film-list-duzen">
                    {filteredFilms.map((film) => (
                        <FilmCard
                            key={film.id}
                            film={film}
                            addToCart={addToCart}
                            image={imageMapping.films[film.productId]} // Resim URL'si image prop'u ile iletiliyor
                        />
                    ))}
                </div>
            ) : (
                <p>Film verisi bulunamadı.</p>
            )}
        </div>
    );
}

export default FilmList;
