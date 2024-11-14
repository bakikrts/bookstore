import React, {useState} from 'react';
import '../styles/Header.css';
import {Link, useNavigate} from 'react-router-dom';
import {FaBookOpen, FaFilm} from "react-icons/fa6";
import {BsCart4, BsFillRecordCircleFill} from "react-icons/bs"
import {FaSearch} from "react-icons/fa";
import {MdOutlineAccountCircle} from "react-icons/md";


function Header({cartCount, onSearch}) {
    const navigate = useNavigate();
    const [searchTerm, setSearchTerm] = useState('');
    console.log("Cart Count:", cartCount);


    // Arama çubuğundaki değişiklikleri yönetir
    const handleSearchChange = (event) => {
        setSearchTerm(event.target.value);
        onSearch(event.target.value); // Arama terimini dışa aktarır
    };

    // Arama butonuna tıklandığında çalışır
    const handleSearchClick = () => {
        if (searchTerm.trim()) {
            navigate('/search'); // Arama terimi doluysa "/search" sayfasına yönlendirir
        }
    };


    return (
        <div className="header">

            {/* Logo ve Arama Çubuğu */}
            <div className="logo-search">

                <div className="logo">
                    <Link to="/">
                    <h1>BKStore.com </h1>
                    </Link>
                </div>
                <div className="search-bar">
                    <input
                        type="text"
                        placeholder="Ürünlerde ara..."
                        value={searchTerm}
                        onChange={handleSearchChange}
                    />
                    <button
                        type="submit"
                        onClick={handleSearchClick}
                        disabled={!searchTerm.trim()} // Arama terimi boşsa buton devre dışı
                        className={!searchTerm.trim() ? 'disabled-button' : ''}
                    >
                        <FaSearch size={15}/>
                    </button>

                </div>
                <div className="extra-buttons">
                    <Link to="/">
                        <button><  MdOutlineAccountCircle size={13}/> Bana Özel</button>
                    </Link>
                    <div className="cart-button-container">
                        <button onClick={() => navigate('/sepet')} className="button">
                            <BsCart4 size={13}/> Sepetim
                        </button>
                        {cartCount > 0 && (
                            <span className="cart-notification">{cartCount}</span>
                        )}
                    </div>

                </div>
            </div>

            {/* Navigasyon Menüsü */}
            <div className="nav-menu">
                <button onClick={() => navigate('/tümürünler')}>Tüm Ürünler</button>
                <button onClick={() => navigate('/kitaplar')} className="book"> <FaBookOpen size={15} /> Kitaplar </button>
                <button onClick={() => navigate('/müzikler')} className="music"> <BsFillRecordCircleFill size={15} />Müzikler</button>
                <button onClick={() => navigate('/filmler')} className="film"> <FaFilm size={15} /> Filmler</button>
            </div>


        </div>
    );
}

export default Header;