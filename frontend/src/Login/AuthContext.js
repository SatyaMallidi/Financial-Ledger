import axios from 'axios';
import React, { createContext, useState, useEffect } from 'react';

// Create AuthContext
export const AuthContext = createContext();

// AuthProvider component
export const AuthProvider = ({ children }) => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    useEffect(() => {
        // Check if user is logged in by verifying presence of token in localStorage
        const token = localStorage.getItem('token');
        setIsLoggedIn(!!token);
    }, []);

    const login = async (username, password) => {
        try {
            const response = await axios.post('http://localhost:8090/api/user/login', {
                username,
                password,
            });

            if (response.status === 200) {
                const token = response.data.token; // Extract token from response
                localStorage.setItem('token', token); // Store token in localStorage
                setIsLoggedIn(true);
            }
        } catch (error) {
            console.error('Login failed:', error);
        }
    };

    const register = async (username, email, password, role) => {
        try {
            const response = await axios.post('http://localhost:8090/api/user/register', {
                username,
                email,
                password,
                role,
            });

            if (response.status === 200) {
                setIsLoggedIn(true);
            }
        } catch (error) {
            console.error('Registration failed:', error);
        }
    };

    const logout = async () => {
        try {
            const response = await axios.post('http://localhost:8090/api/user/logout');
            
            if (response.status === 200) {
                localStorage.removeItem('token');
                setIsLoggedIn(false);
            }
        } catch (error) {
            console.error('Logout failed:', error);
        }
    };

    return (
        <AuthContext.Provider value={{ isLoggedIn, login, register, logout }}>
            {children}
        </AuthContext.Provider>
    );
};

export default AuthProvider;
