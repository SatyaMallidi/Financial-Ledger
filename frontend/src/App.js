import React, { useContext } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Navigation from './Header/Navigation';
import Home from './Home/Home';
import Transaction from './Transaction/Transaction';
import BalanceSheet from './BalanceSheet/BalanceSheet';
import FinancialReport from './FinancialReport/FinancialReport';
import LoginRegister from './Login/LoginRegister'; 
import { AuthProvider, AuthContext } from './Login/AuthContext'; // Ensure correct import

function App() {
  return (
    <AuthProvider>
      <Router>
        <AppRoutes />
      </Router>
    </AuthProvider>
  );
}

const AppRoutes = () => {
  const { isLoggedIn } = useContext(AuthContext);

  return (
    <>
      {isLoggedIn ? (
        <>
          <Navigation />
          <Routes>
            <Route path="/home" exact element={<Home />} />
            <Route path="/transaction" exact element={<Transaction />} />
            <Route path="/balance-sheet" exact element={<BalanceSheet />} />
            <Route path="/financial-report" exact element={<FinancialReport />} />
            <Route path="*" exact element={<Home />} />
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
