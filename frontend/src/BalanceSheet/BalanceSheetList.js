import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { DataGrid } from '@mui/x-data-grid';
import Box from '@mui/material/Box';
import { Button } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';

const columns = (handleDelete) => [
  { field: 'balanceId', headerName: 'Balance ID', width: 150 },
  { field: 'userId', headerName: 'User ID', width: 150 },
  { field: 'date', headerName: 'Date', width: 150 },
  { field: 'assets', headerName: 'Assets', width: 150 },
  { field: 'liabilities', headerName: 'Liabilities', width: 150 },
  { field: 'equity', headerName: 'Equity', width: 150 },
  {
    field: 'actions',
    headerName: 'Actions',
    width: 150,
    renderCell: (params) => (
      <Button
        color="secondary"
        startIcon={<DeleteIcon />}
        onClick={() => handleDelete(params.row.balanceId)}
      >
        Delete
      </Button>
    ),
  },
];

const BalanceSheetList = ({ userId }) => {
  const [rows, setRows] = useState([]);

  useEffect(() => {
    axios.get(`http://localhost:8090/api/public/balanceSheet/`)
      .then((res) => setRows(res.data))
      .catch((err) => console.error('Error fetching balance sheets:', err));
  }, [userId]);

  const handleDelete = (balanceId) => {
    axios.delete(`http://localhost:8090/api/public/balanceSheet/`)
      .then(() => setRows(rows.filter(row => row.balanceId !== balanceId)))
      .catch((err) => console.error('Error deleting balance sheet:', err));
  };

  return (
    <Box className="Table" sx={{ height: 500, width: '100%' }}>
      <DataGrid
        rows={rows}
        columns={columns(handleDelete)}
        pageSizeOptions={[5, 10]}
        checkboxSelection
        getRowId={(row) => row.balanceId}
      />
    </Box>
  );
}

export default BalanceSheetList;
