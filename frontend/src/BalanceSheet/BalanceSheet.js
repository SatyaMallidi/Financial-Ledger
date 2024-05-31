import React, { useState } from 'react';
import './BalanceSheetList'; // Ensure this component exists
import './NewBalanceSheet'; // Ensure this component exists
import './BalanceSheet.css';



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
            <p>Balance Sheet</p>
            <button class="green-button" onClick={handleNewBalanceSheetClick}>Create New</button>
            <button class="blue-button" onClick={handleBalanceSheetListClick}>All Transactions</button>
        </>
    );
}

export default BalanceSheet;
