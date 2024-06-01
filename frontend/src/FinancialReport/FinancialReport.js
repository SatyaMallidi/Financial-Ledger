import React, { useState } from 'react';
import '../CssFolder/Table.css';
import FinancialReportList from './FinancialReportList';
import NewFinancialReport from './NewFinancialReport';


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
         {showTable ? (
        <FinancialReportList />
      ) : showNewTable ? (
        <NewFinancialReport />
      ) : (
            <>
              <p>Financial Report</p>
              <button class="blue-button" onClick={handleNewFinancialReportClick}>Create new</button>
              <button class="green-button" onClick={handleFinancialReportListClick}>All FinancialReport</button>
            </>
          )}
        </>
      );

    
}

export default FinancialReport;
