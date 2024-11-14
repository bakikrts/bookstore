import React from 'react';
import '../styles/HomePage.css'; // Stil dosyasını ayrıca eklemek için

// Görselleri import edin
import bookCampaign from '../assets/images/bookcampaign1.png';
import productCampaign from '../assets/images/productcampaign1.png';

const HomePage = () => {
    return (
        <div className="home-page">
            <div className="campaign-container">
                <a href="http://localhost:3000/kitaplar">
                    <img src={bookCampaign} alt="2. Kitap Ücretsiz Kampanyası" className="campaign-image"/>
                    </a>
            </div>

            <div className="campaign-container">
                <a href="http://localhost:3000/tümürünler">
                    <img src={productCampaign} alt="2. Üründe %50 İndirim Kampanyası" className="campaign-image"/>
                    </a>
            </div>
        </div>
);
};

export default HomePage;