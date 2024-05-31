import React, { useState } from 'react';
import './TransactionTable';
import  './TransactionNew';
import './Transaction.css';

function Transaction() {
  const [showTable, setShowTable] = useState(false);
  const [showNewTable, setShowNewTable] = useState(false);

  const handleTransactionTableClick = () => {
    setShowTable(true);
    setShowNewTable(false); 
  };

  const handleTransactionNewTableClick = () => {
    setShowNewTable(true);
    setShowTable(false); 
  };

  return (
    <>
      <p>Financial Report</p>
      <button class="green-button"onClick={handleTransactionNewTableClick}>Create New</button>
      <button class="blue-button" onClick={handleTransactionTableClick}>All Transactions</button>
          </>
  );
}

export default Transaction;
