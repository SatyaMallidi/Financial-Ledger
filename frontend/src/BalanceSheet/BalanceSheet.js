import React, { useState, useEffect } from 'react';
import BalanceSheetList from './BalanceSheetList';
import NewBalanceSheet from './NewBalanceSheet';
import  '../CssFolder/Table.css';





function BalanceSheet() {
    const [showTable, setShowTable] = useState(false);
    const [showNewTable, setShowNewTable] = useState(false);

    const handleBalanceSheetListClick = () => {
        setShowTable(true);
        setShowNewTable(false);
    };

    const handleNewBalanceSheetClick = () => {
        setShowNewTable(true);
        setShowTable(false);
    };


    return (
        <>
         {showTable ? (
        <BalanceSheetList />
      ) : showNewTable ? (
        <NewBalanceSheet/>
      ) : (
            <>
              <p>Financial Report</p>
              <button class="blue-button" onClick={handleNewBalanceSheetClick}>Create new</button>
              <button class="green-button" onClick={handleBalanceSheetListClick}>All BlanceSheet</button>
            </>
          )}
        </>
      );

    
}

export default BalanceSheet;
