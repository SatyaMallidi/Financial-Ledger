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
                <div className='background-image'>
                <div className="button-container">
                    <button className="button-75" onClick={handleNewFinancialReportClick}><span>Create new</span></button>
                    <button className="button-75" onClick={handleFinancialReportListClick}><span>All Financial Reports</span></button>
                    <button className="button-75" onClick={handleUnifiedFinancialReportClick}><span>Unified Financial Report</span></button>
                    </div>
                    </div>
                </>
            )}
        </>
    );
}

export default FinancialReport;