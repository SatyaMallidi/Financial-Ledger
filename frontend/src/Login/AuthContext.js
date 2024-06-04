import React, { createContext, useState } from 'react';
import axios from 'axios';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const login = async (username, password) => {
    try {
      const response = await axios.post('http://localhost:8090/api/user/login', {
        username,
        password,
      });

      if (response.status === 200) {
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
      await axios.post('http://localhost:8090/api/user/logout');
      // setAuth(null);
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