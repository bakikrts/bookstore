import React from 'react';

function MusicCard({ music ,image ,addToCart }) {
    return (
        <div className="music-card">
            <div className="music-card-header">{music.name}</div>
            <img className="music-card-image" src={image} alt={music.name}/>
            <div className="music-card-body">
                <p><strong>Number Of Songs:</strong> {music.numberOfSongs}</p>
                <p><strong>Tür:</strong> {music.genre}</p>
                <p><strong>Yayın Tarihi:</strong> {music.releaseDate}</p>
                <p><strong>Fiyat:</strong> {music.basePrice}₺</p>

            </div>
            <div className="music-card-footer">
                <button onClick={() => addToCart(music)}>Sepete Ekle</button>
            </div>
        </div>
    );
}

export default MusicCard;