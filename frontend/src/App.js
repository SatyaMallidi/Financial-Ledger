import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Navigation from './Header/Navigation';
import Home from './Home/Home';
import Transaction from './Transaction/Transaction';
import BalanceSheet from './BalanceSheet/BalanceSheet';
import FinancialReport from './FinancialReport/FinancialReport';

function App() {

  return (
    <>
     <Router>
      <Navigation/>
      <Routes>
        <Route path="/Home" element={<Home />} /> 
        <Route path="/transaction" element={<Transaction />} />
        <Route path="/balance-sheet" element={<BalanceSheet />} />
        <Route path="/financial-report" element={<FinancialReport />} />
      </Routes>
    </Router>
    </>
  );
}
export default App;
