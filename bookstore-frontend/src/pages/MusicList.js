import React, { useEffect, useState } from 'react';
import { fetchMusics } from '../services/api';
import MusicCard from '../components/MusicCard';
import '../styles/MusicList.css';
import imageMapping from "../utils/imageMapping";

function MusicList({ addToCart, searchTerm }) {
    const [musics, setMusics] = useState([]);
    const [loading, setLoading] = useState(true);
    const [sortOrder, setSortOrder] = useState(''); // Sıralama yönü: artan veya azalan
    const [sortType, setSortType] = useState(''); // Sıralama türü: fiyat veya tarih

    useEffect(() => {
        const getMusics = async () => {
            try {
                const musicsData = await fetchMusics();
                setMusics(musicsData); // Müzik verisini kaydet
            } catch (error) {
                console.error("Failed to fetch musics:", error);
            } finally {
                setLoading(false); // Yüklenme durumunu kapat
            }
        };
        getMusics();
    }, []);

    const handleSortOrderChange = (e) => {
        setSortOrder(e.target.value);
    };

    const handleSortTypeChange = (e) => {
        setSortType(e.target.value);
        setSortOrder(''); // Sıralama türü değiştiğinde sıralama yönünü sıfırlıyoruz
    };

    const filteredMusics = musics
        .filter((music) =>
            music.name && searchTerm
                ? music.name.toLowerCase().includes(searchTerm.toLowerCase())
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
        <div className="music-list">
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
            ) : filteredMusics.length > 0 ? (
                <div className="music-list-duzen">
                    {filteredMusics.map((music) => (
                        <MusicCard key={music.id} music={music} addToCart={addToCart} image={imageMapping.musics[music.productId]} />
                    ))}
                </div>
            ) : (
                <p>Müzik verisi bulunamadı.</p>
            )}
        </div>
    );
}

export default MusicList;
