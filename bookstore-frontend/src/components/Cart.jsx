import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../styles/Cart.css';

const Cart = ({ cart, incrementQuantity, decrementQuantity }) => {
    const [totalPrice, setTotalPrice] = useState(0);
    const [discountedPrice, setDiscountedPrice] = useState(0);

    useEffect(() => {
        const calculateTotalPrice = () => {
            const total = cart.reduce((sum, item) => sum + (item.basePrice * item.quantity), 0);
            setTotalPrice(total);
        };

        calculateTotalPrice();
    }, [cart]);

    useEffect(() => {
        const calculateDiscountedPrice = async () => {
            try {
                if (cart.length > 0) {
                    const response = await axios.post(`${process.env.REACT_APP_API_URL}/purchase/calculateTotal`, {
                        products: cart.map(item => ({
                            productId: item.productId,
                            count: item.quantity
                        }))
                    });
                    setDiscountedPrice(response.data);
                } else {
                    setDiscountedPrice(0);
                }
            } catch (error) {
                console.error("Kampanyalı fiyat hesaplanırken hata oluştu:", error);
            }
        };

        calculateDiscountedPrice();
    }, [cart]);

    return (
        <div className="cart-container">
            <h2>Sepetim</h2>
            <div className="cart-items">
                {cart.map((item, index) => (
                    <div key={index} className="cart-item">
                        <span className="item-name">{item.name}</span>
                        <span className="item-quantity">
                            Adet: {item.quantity}
                            <button onClick={() => incrementQuantity(item.productId)}>+</button>
                            <button onClick={() => decrementQuantity(item.productId)}>-</button>
                        </span>
                        <span className="item-price">{(item.basePrice * item.quantity).toFixed(2)}₺</span>
                    </div>
                ))}
            </div>
            <div className="cart-totals">
                <div className="cart-total-line strikethrough">
                    <h3>Toplam Tutar:</h3>
                    <span>{totalPrice.toFixed(2)}₺</span>
                </div>
                <div className="cart-total-line">
                    <h3>İndirimli Toplam Tutar:</h3>
                    <span>{discountedPrice.toFixed(2)}₺</span>
                </div>
                <button className="pay-button">Hemen Öde</button>
            </div>
        </div>
    );
};

export default Cart;
