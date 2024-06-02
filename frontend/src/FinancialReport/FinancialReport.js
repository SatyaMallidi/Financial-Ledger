import React, { useState } from 'react';
import '../CssFolder/Table.css';
import FinancialReportList from './FinancialReportList';
import NewFinancialReport from './NewFinancialReport';
import UnifiedFinancialReport from './UnifiedFinancialReport';

function FinancialReport() {
    const [showTable, setShowTable] = useState(false);
    const [showNewTable, setShowNewTable] = useState(false);
    const [showUnifiedReport, setShowUnifiedReport] = useState(false);

    const handleFinancialReportListClick = () => {
        setShowTable(true);
        setShowNewTable(false);
        setShowUnifiedReport(false);
    };

    const handleNewFinancialReportClick = () => {
        setShowNewTable(true);
        setShowTable(false);
        setShowUnifiedReport(false);
    };

    const handleUnifiedFinancialReportClick = () => {
        setShowUnifiedReport(true);
        setShowTable(false);
        setShowNewTable(false);
    };

    return (
        <>
            {showTable ? (
                <FinancialReportList />
            ) : showNewTable ? (
                <NewFinancialReport />
            ) : showUnifiedReport ? (
                <UnifiedFinancialReport />
            ) : (
                <>
                    <p>Financial Report</p>
                    <button className="blue-button" onClick={handleNewFinancialReportClick}>Create new</button>
                    <button className="green-button" onClick={handleFinancialReportListClick}>All Financial Reports</button>
                    <button className="orange-button" onClick={handleUnifiedFinancialReportClick}>Unified Financial Report</button>
                </>
            )}
        </>
    );
}

export default FinancialReport;