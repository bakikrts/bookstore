import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import BookList from './pages/BookList';
import FilmList from './pages/FilmList';
import MusicList from './pages/MusicList';
import ProductList from './pages/ProductList';
import Header from './components/Header';
import HomePage from "./pages/HomePage";
import Cart from './components/Cart';
import SearchPage from "./pages/SearchPage";

function App() {
    const [searchTerm, setSearchTerm] = useState('');
    const handleSearch = (term) => {
        setSearchTerm(term);
    };
    // `cart` durumunu tanımlarken, `localStorage`'dan yükle
    const [cart, setCart] = useState(() => {
        const savedCart = localStorage.getItem('cart');

        return savedCart ? JSON.parse(savedCart) : [];

    });

    // Sepet her güncellendiğinde `localStorage`'a kaydet
    useEffect(() => {
        localStorage.setItem('cart', JSON.stringify(cart));
    }, [cart]);

    // Ürün ekleme fonksiyonu
    const addToCart = (product) => {
        setCart((prevCart) => {
            const existingProductIndex = prevCart.findIndex((p) => p.productId === product.productId);

            if (existingProductIndex >= 0) {
                const updatedCart = [...prevCart];
                updatedCart[existingProductIndex] = {
                    ...updatedCart[existingProductIndex],
                    quantity: updatedCart[existingProductIndex].quantity + 1,
                };
                return updatedCart;
            } else {
                return [...prevCart, { ...product, quantity: 1 }];
            }
        });
    };

    // Ürün miktarını artırma fonksiyonu
    const incrementQuantity = (productId) => {
        setCart((prevCart) => {
            return prevCart.map((item) =>
                item.productId === productId
                    ? { ...item, quantity: item.quantity + 1 }
                    : item
            );
        });
    };

    // Ürün miktarını azaltma fonksiyonu
    const decrementQuantity = (productId) => {
        setCart((prevCart) => {
            return prevCart
                .map((item) =>
                    item.productId === productId
                        ? { ...item, quantity: item.quantity - 1 }
                        : item
                )
                .filter((item) => item.quantity > 0);
        });
    };

    // Sepetteki toplam ürün sayısını hesapla
    const cartCount = cart.reduce((total, item) => total + item.quantity, 0);
    console.log("Cart Count:", cartCount);
    return (
        <Router>
            <div>
                <Header cartCount={cartCount} onSearch={handleSearch}  />
                <Routes>
                    <Route path="/search" element={<SearchPage searchTerm={searchTerm}  addToCart={addToCart} />} />
                    <Route  clasName ="homepage" path="/" element={<HomePage />} />
                    <Route path="/kitaplar" element={<BookList  searchTerm={searchTerm}  addToCart={addToCart} />} />
                    <Route path="/filmler" element={<FilmList searchTerm={searchTerm}  addToCart={addToCart} />} />
                    <Route path="/Müzikler" element={<MusicList searchTerm={searchTerm} addToCart={addToCart} />} />
                    <Route path="/TümÜrünler" element={<ProductList searchTerm={searchTerm}  addToCart={addToCart} />} />
                    <Route
                        path="/sepet"
                        element={
                            <Cart
                                cart={cart}
                                incrementQuantity={incrementQuantity}
                                decrementQuantity={decrementQuantity}
                            />
                        }
                    />
                </Routes>
            </div>
        </Router>
    );
}

export default App;
