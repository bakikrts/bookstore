import React from 'react';


function FilmCard({ film ,image, addToCart }) {
    return (
        <div className="film-card">
            <div className="film-card-header">{film.name}</div>
            <img className="film-card-image" src={image} alt={film.name} />
            <div className="film-card-body">
                <h2>{film.title}</h2>

                <p><strong>Genre:</strong> {film.genre}</p>
                <p><strong>IMDB Rate:</strong> {film.imdbRate}</p>
                <p><strong>Yayın Tarihi:</strong> {film.releaseDate}</p>
                <p><strong>Fiyat:</strong> {film.basePrice}₺</p>

            </div>
            <div className="film-card-footer">
            <button onClick={() => addToCart(film)}>Sepete Ekle</button>
            </div>

        </div>

    );
}

export default FilmCard;
