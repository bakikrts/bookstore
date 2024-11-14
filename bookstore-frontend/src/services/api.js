import axios from 'axios';

const API_URL = 'http://localhost:8083'; // Backend URL'in

export const getProducts = () => {
    return axios.get(`${API_URL}/list-products`);
};

export const fetchBooks =async () => {
    try {
        const response = await axios.get(`${API_URL}/books/final-price`);
        return response.data;
    } catch (error) {
        console.error("Error fetching books:", error);
        throw error;
    }

};
export const fetchFilms =async () => {
    try{
        const response = await axios.get(`${API_URL}/films/final-price`);
        return response.data;
    }catch (error) {
        console.error("Error fetching films:", error);
        throw error;
    }
}
export const fetchMusics = async () => {
    try{
        const response = await axios.get(`${API_URL}/musics/final-price`);
        return response.data;
    }catch (error) {
        console.error("Error fetching movies:", error);
        throw error;
    }
}
export const  fetchProducts = async () => {
    try {
        const response = await axios.get(`${API_URL}/products`);
        return response.data;
    } catch (error) {
     console.error("Error fetching products:", error);
     throw error;
    }
}