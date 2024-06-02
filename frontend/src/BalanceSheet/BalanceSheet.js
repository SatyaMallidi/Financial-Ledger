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
            <div className='background-image'>
            <div className="button-container">
              <button class="button-75" onClick={handleNewBalanceSheetClick}><span>Create new</span></button>
              <button class="button-75" onClick={handleBalanceSheetListClick}><span>All BlanceSheet</span></button>
            </div>
            </div>
            </>
          )}
        </>
      );

    
}

export default BalanceSheet;
