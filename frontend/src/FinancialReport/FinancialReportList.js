import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { DataGrid } from '@mui/x-data-grid';
import Box from '@mui/material/Box';
import { Button } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';

const columns = (handleDelete) => [
  { field: 'financialId', headerName: 'Financial ID', width: 150 },
  { field: 'userId', headerName: 'User ID', width: 150 },
  { field: 'periodStart', headerName: 'Period Start', width: 150 },
  { field: 'periodEnd', headerName: 'Period End', width: 150 },
  { field: 'netProfit', headerName: 'Net Profit', width: 150 },
  { field: 'totalIncome', headerName: 'Total Income', width: 150 },
  { field: 'totalExpenses', headerName: 'Total Expenses', width: 150 },
  {
    field: 'actions',
    headerName: 'Actions',
    width: 150,
    renderCell: (params) => (
      <Button
        color="secondary"
        startIcon={<DeleteIcon />}
        onClick={() => handleDelete(params.row.financialId)}
      >
        Delete
      </Button>
    ),
  },
];

export default function FinancialReportList() {
  const [rows, setRows] = useState([]);
  const userId = '123'; // Replace with actual userId

  useEffect(() => {
    axios.get(`http://localhost:8090/api/public/financial/user/{userId}`)
      .then((res) => setRows(res.data))
      .catch((err) => console.error('Error fetching financial reports:', err));
  }, [userId]);

  const handleDelete = (financialId) => {
    axios.delete(`http://localhost:8090/api/public/financial/{financialId}`)
      .then(() => setRows(rows.filter(row => row.financialId !== financialId)))
      .catch((err) => console.error('Error deleting financial report:', err));
  };

  return (
    <Box sx={{ height: 500, width: '100%' }}>
      <DataGrid
        rows={rows}
        columns={columns(handleDelete)}
        pageSizeOptions={[5, 10]}
        checkboxSelection
        getRowId={(row) => row.financialId}
      />
    </Box>
  );
}