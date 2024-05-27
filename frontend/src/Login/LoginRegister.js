// src/Login/LoginRegister.js
import React, { useState, useContext } from 'react';
import { AuthContext } from './AuthContext';
import './LoginRegister.css';
import logo from '../Images/FL-logo.jpg';

const LoginRegister = () => {
  const [isLogin, setIsLogin] = useState(true);
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('USER');
  const { login, register } = useContext(AuthContext);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (isLogin) {
      login(username, password);
    } else {
      register(username, email, password, role);
    }
  };

  return (
    <div className="login-register-container">
      <div className="left-side">
        <img src={logo} alt="Financial Ledger Logo" className="logo" />
      </div>
      <div className="right-side">
        <h1>{isLogin ? 'Login' : 'Register'}</h1>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          {!isLogin && (
            <>
              <input
                type="email"
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
              />
              <div className="role-selector">
                <label>
                  <input
                    type="radio"
                    value="USER"
                    checked={role === 'USER'}
                    onChange={(e) => setRole(e.target.value)}
                  />
                  User
                </label>
                <label>
                  <input
                    type="radio"
                    value="ADMIN"
                    checked={role === 'ADMIN'}
                    onChange={(e) => setRole(e.target.value)}
                  />
                  Admin
                </label>
              </div>
            </>
          )}
          <button type="submit">{isLogin ? 'Login' : 'Register'}</button>
        </form>
        <button className="toggle-button" onClick={() => setIsLogin(!isLogin)}>
          {isLogin ? 'Switch to Register' : 'Switch to Login'}
        </button>
      </div>
    </div>
  );
};

export default LoginRegister;
