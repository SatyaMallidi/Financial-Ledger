// BalanceSheet.js
import React from 'react';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';

const BalanceSheet = () => {
  const handleGenerateReport = () => {
    // Implement the logic for generating a report
    console.log('Generate Report clicked');
  };

  const handleExportData = () => {
    // Implement the logic for exporting data
    console.log('Export Data clicked');
  };

  return (
    <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', gap: 2, mt: 4 }}>
      <h1>Balance Sheet</h1>
      <Button variant="contained" color="primary" onClick={handleGenerateReport}>
        Generate Report
      </Button>
      <Button variant="outlined" color="secondary" onClick={handleExportData}>
        Export Data
      </Button>
    </Box>
  );
};

export default BalanceSheet;
