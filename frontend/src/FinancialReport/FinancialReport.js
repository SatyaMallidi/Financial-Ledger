import React, { useState } from 'react';
import './FinancialReportList'; // Ensure this component exists
import './NewFinancialReport'; // Ensure this component exists
import './FinancialReport.css';


function FinancialReport() {
    const [showTable, setShowTable] = useState(false);
    const [showNewTable, setShowNewTable] = useState(false);

    const handleFinancialReportListClick = () => {
        setShowTable(true);
        setShowNewTable(false);
    };

    const handleNewFinancialReportClick = () => {
        setShowNewTable(true);
        setShowTable(false);
    };

    return (
        <>
            <p>Financial Report</p>
            <button class="green-button" onClick={handleNewFinancialReportClick}>Create New</button>
            <button class="blue-button" onClick={handleFinancialReportListClick}>All Transactions</button>
        </>
    );
}

export default FinancialReport;
