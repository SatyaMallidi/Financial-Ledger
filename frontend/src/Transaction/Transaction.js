import React, { useState } from 'react';
import TransactionTable from './TransactionTable';
import TransactionNew from './TransactionNew'; // Ensure you import the correct component here
import './Transaction.css';

function Transaction() {
  const [showTable, setShowTable] = useState(false);
  const [showNewTable, setShowNewTable] = useState(false);

  const handleButtonClick = () => {
    setShowTable(true);
    setShowNewTable(false); // Ensure only one table is shown at a time
  };

  const handleNewButtonClick = () => {
    setShowNewTable(true);
    setShowTable(false); // Ensure only one table is shown at a time
  };

  return (
    <>
      {showTable ? (
        <TransactionTable />
      ) : showNewTable ? (
        <TransactionNew />
      ) : (
        <>
          <p>Financial Report</p>
          <button className="blue-button" onClick={handleNewButtonClick}>Create new</button>
          <button className="green-button" onClick={handleButtonClick}>All Transactions</button>
        </>
      )}
    </>
  );
}

export default Transaction;
