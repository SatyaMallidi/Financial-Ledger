import React, { useState } from 'react';
import TransactionTable from './TransactionTable';
import TransactionNew from './TransactionNew'; // Ensure you import the correct component here
import  '../CssFolder/Table.css';

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
        <div className="background-image">
        <div className="button-container">
          <button className="button-75" onClick={handleNewButtonClick}><span>Create new</span></button>
          <button className="button-75" onClick={handleButtonClick}><span>All Transactions</span></button>
          </div>
          </div>
        </>
      )}
    </>
  );
}

export default Transaction;
