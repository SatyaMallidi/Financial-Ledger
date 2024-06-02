import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { DataGrid } from '@mui/x-data-grid';
import Box from '@mui/material/Box';
import { Button } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import SaveIcon from '@mui/icons-material/Save';

const columns = (handleDelete, handleSave) => [
  { field: 'balanceId', headerName: 'Balance ID', width: 150, editable: true },
  { field: 'userId', headerName: 'User ID', width: 150, editable: true },
  { field: 'date', headerName: 'Date', width: 150, editable: true },
  { field: 'assets', headerName: 'Assets', width: 150, editable: true },
  { field: 'liabilities', headerName: 'Liabilities', width: 150, editable: true },
  { field: 'equity', headerName: 'Equity', width: 150, editable: true },
  {
    field: 'actions',
    headerName: 'Actions',
    width: 150,
    renderCell: (params) => (
      <React.Fragment>
        <Button
          color="primary"
          startIcon={<SaveIcon />}
          onClick={() => handleSave(params.row)}
        >
          Save
        </Button>
        <Button
          color="secondary"
          startIcon={<DeleteIcon />}
          onClick={() => handleDelete(params.row.balanceId)}
        >
          Delete
        </Button>
      </React.Fragment>
    ),
  },
];

const BalanceSheetList = ({ userId }) => {
  const [rows, setRows] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8090//api/public/balanceSheet/')
      .then((res) => setRows(res.data))
      .catch((err) => console.error('Error fetching balance sheets:', err));
  }, [userId]);

  const handleSave = (row) => {
    axios.patch('http://localhost:8090//api/public/balanceSheet/{balanceId}', row)
      .then((res) => {
        setRows(rows.map(r => (r.balanceId === row.balanceId ? res.data : r)));
      })
      .catch((err) => console.error('Error updating balance sheet:', err));
  };

  const handleDelete = (balanceId) => {
    axios.delete('http://localhost:8090//api/public/balanceSheet/{balanceId}')
      .then(() => setRows(rows.filter(row => row.balanceId !== balanceId)))
      .catch((err) => console.error('Error deleting balance sheet:', err));
  };

  return (
    <Box className="Table" sx={{ height: 500, width: '100%' }}>
      <DataGrid
        rows={rows}
        columns={columns(handleDelete, handleSave)}
        pageSizeOptions={[5, 10]}
        checkboxSelection
        getRowId={(row) => row.balanceId}
        processRowUpdate={(newRow) => {
          handleSave(newRow);
          return newRow;
        }}
      />
    </Box>
  );
};

export default BalanceSheetList;