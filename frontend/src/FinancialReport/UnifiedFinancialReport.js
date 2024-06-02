import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { DataGrid } from '@mui/x-data-grid';
import Box from '@mui/material/Box';
import { Button, FormControl, InputLabel, MenuItem, Select, TextField } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import SaveIcon from '@mui/icons-material/Save';

const columns = (handleDelete, handlePatch) => [
  { field: 'financialId', headerName: 'Financial ID', width: 150, editable: true },
  { field: 'userId', headerName: 'User ID', width: 150, editable: true },
  { field: 'periodStart', headerName: 'Period Start', width: 150, editable: true },
  { field: 'periodEnd', headerName: 'Period End', width: 150, editable: true },
  { field: 'netProfit', headerName: 'Net Profit', width: 150, editable: true },
  { field: 'totalIncome', headerName: 'Total Income', width: 150, editable: true },
  { field: 'totalExpenses', headerName: 'Total Expenses', width: 150, editable: true },
  {
    field: 'actions',
    headerName: 'Actions',
    width: 150,
    renderCell: (params) => (
      <>
        <Button
          color="primary"
          startIcon={<SaveIcon />}
          onClick={() => handlePatch(params.row)}
        >
          Save
        </Button>
        <Button
          color="secondary"
          startIcon={<DeleteIcon />}
          onClick={() => handleDelete(params.row.financialId)}
        >
          Delete
        </Button>
      </>
    ),
  },
];

const UnifiedFinancialReport = () => {
  const [rows, setRows] = useState([]);
  const [reportType, setReportType] = useState('monthly');
  const [userId, setUserId] = useState('');
  const [year, setYear] = useState('');
  const [month, setMonth] = useState('');
  const [quarter, setQuarter] = useState('');

  useEffect(() => {
    if (userId && year && ((reportType === 'monthly' && month) || (reportType === 'quarterly' && quarter) || reportType === 'yearly')) {
      let apiUrl = '';
      if (reportType === 'monthly') {
        apiUrl = 'http://localhost:8090/api/public/financial/monthly/${userId}/${year}/${month}';
      } else if (reportType === 'yearly') {
        apiUrl = 'http://localhost:8090/api/public/financial/yearly/${userId}/${year}';
      } else if (reportType === 'quarterly') {
        apiUrl = 'http://localhost:8090/api/public/financial/quarterly/${userId}/${year}/${quarter}';
      }

      axios.get(apiUrl)
        .then((res) => setRows(res.data))
        .catch((err) => console.error('Error fetching financial report:', err));
    }
  }, [reportType, userId, year, month, quarter]);

  const handleDelete = (financialId) => {
    axios.delete('http://localhost:8090/api/public/financial/${financialId}')
      .then(() => setRows(rows.filter(row => row.financialId !== financialId)))
      .catch((err) => console.error('Error deleting financial report:', err));
  };

  const handlePatch = (row) => {
    axios.patch('http://localhost:8090/api/public/financial/${row.financialId}', row)
      .then(() => console.log('Successfully updated'))
      .catch((err) => console.error('Error updating financial report:', err));
  };

  const handleReportTypeChange = (event) => {
    setReportType(event.target.value);
    setRows([]); // Clear previous data
  };

  const handleFetchReport = () => {
    // Fetching logic will automatically trigger due to the useEffect dependency array
  };

  return (
    <Box className="Table" sx={{ height: 700, width: '100%' }}>
      <FormControl fullWidth sx={{ mb: 2 }}>
        <InputLabel>Report Type</InputLabel>
        <Select
          value={reportType}
          onChange={handleReportTypeChange}
        >
          <MenuItem value="monthly">Monthly</MenuItem>
          <MenuItem value="yearly">Yearly</MenuItem>
          <MenuItem value="quarterly">Quarterly</MenuItem>
        </Select>
      </FormControl>

      <TextField
        fullWidth
        label="User ID"
        value={userId}
        onChange={(e) => setUserId(e.target.value)}
        sx={{ mb: 2 }}
      />
      <TextField
        fullWidth
        label="Year"
        value={year}
        onChange={(e) => setYear(e.target.value)}
        sx={{ mb: 2 }}
      />

      {reportType === 'monthly' && (
        <TextField
          fullWidth
          label="Month"
          value={month}
          onChange={(e) => setMonth(e.target.value)}
          sx={{ mb: 2 }}
        />
      )}

      {reportType === 'quarterly' && (
        <TextField
          fullWidth
          label="Quarter"
          value={quarter}
          onChange={(e) => setQuarter(e.target.value)}
          sx={{ mb: 2 }}
        />
      )}

      <Button variant="contained" onClick={handleFetchReport} sx={{ mb: 2 }}>
        Fetch Report
      </Button>

      <DataGrid
        rows={rows}
        columns={columns(handleDelete, handlePatch)}
        pageSizeOptions={[5, 10]}
        checkboxSelection
        getRowId={(row) => row.financialId}
      />
    </Box>
  );
}

export default UnifiedFinancialReport;