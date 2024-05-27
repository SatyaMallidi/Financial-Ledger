// AuthContext.js
import React, { createContext, useState } from 'react';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const login = async (username, password) => {
    // Dummy login function
    if (username === 'admin' && password === 'password') {
      setIsLoggedIn(true);
    }
  };

  const register = async (username, email, password, role) => {
    // Dummy register function
    setIsLoggedIn(true);
  };

  const logout = async () => {
    setIsLoggedIn(false);
  };

  return (
    <AuthContext.Provider value={{ isLoggedIn, login, register, logout }}>
      {children}
    </AuthContext.Provider>
  );
};