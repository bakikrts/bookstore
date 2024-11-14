import React from 'react';
import '../styles/BookList.css'

function BookCard({ book ,image,addToCart}) {
    return (
        <div className="book-card">
            <div className="book-card-header">{book.name}</div>
            <img className="book-card-image" src={image} alt={book.name} />
            <div className="book-card-body">
                <p><strong>Yazar:</strong> {book.writerName}</p>
                <p><strong>Tür:</strong> {book.genre}</p>
                <p><strong>Yayın Tarihi:</strong> {book.releaseDate}</p>
                <p><strong>Fiyat:</strong> {book.basePrice}₺</p>

            </div>
            <div className="book-card-footer">
                <button onClick={() => addToCart(book)}> Sepete Ekle</button>
            </div>
        </div>
    );
}

export default BookCard;