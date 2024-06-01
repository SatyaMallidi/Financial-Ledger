import React, { useContext } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import Navigation from './Header/Navigation';
import Home from './Home/Home';
import Transaction from './Transaction/Transaction';
import BalanceSheet from './BalanceSheet/BalanceSheet';
import FinancialReport from './FinancialReport/FinancialReport';
import LoginRegister from './Login/LoginRegister';
import { AuthProvider, AuthContext } from './Login/AuthContext';

const App = () => {
  return (
    <AuthProvider>
      <Router>
        <AppRoutes />
      </Router>
    </AuthProvider>
  );
};

const AppRoutes = () => {
  const { isLoggedIn } = useContext(AuthContext);

  return (
    <>
      {isLoggedIn ? (
        <>
          <Navigation />
          <Routes>
            <Route path="/home" element={<Home />} />
            <Route path="/transaction" element={<Transaction />} />
            <Route path="/balancesheet" element={<BalanceSheet />} />
            <Route path="/financialreport" element={<FinancialReport />} />
            <Route path="*" element={<Home />} />
          </Routes>
        </>
      ) : (
        <Routes>
          <Route path="*" element={<LoginRegister />} />
        </Routes>
      )}
    </>
  );
};

export default App;
