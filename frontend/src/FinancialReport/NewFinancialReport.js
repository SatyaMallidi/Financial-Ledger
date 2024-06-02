import React, { useState, useRef } from 'react';
import axios from 'axios';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import AddIcon from '@mui/icons-material/Add';
import SaveIcon from '@mui/icons-material/Save';
import { DataGrid, GridToolbarContainer, GridActionsCellItem, GridRowEditStopReasons } from '@mui/x-data-grid';

const initialRows = [
  {
    id: '1',
    financialId: '1563',
    userId: '',
    periodStart: new Date().toISOString().slice(0, 10),
    periodEnd: new Date().toISOString().slice(0, 10),
    netProfit: '',
    totalIncome: '',
    totalExpenses: '',
    isNew: true
  }
];

function EditToolbar(props) {
  const { setRows, setRowModesModel } = props;

  const handleClick = () => {
    const id = Math.floor(1000 + Math.random() * 9000); // Generate random four-digit number
    setRows(oldRows => [...oldRows, { id: id.toString(), financialId: id.toString(), userId: '', periodStart: new Date().toISOString().slice(0, 10), periodEnd: new Date().toISOString().slice(0, 10), netProfit: '', totalIncome: '', totalExpenses: '', isNew: true }]);
    setRowModesModel(oldModel => ({
      ...oldModel,
      [id]: { mode: 'Edit', fieldToFocus: 'financialId' }
    }));
  };

  return (
    <GridToolbarContainer>
      <Button color="primary" startIcon={<AddIcon />} onClick={handleClick}>
        Add record
      </Button>
    </GridToolbarContainer>
  );
}

const NewFinancialReport = () => {
  const [rows, setRows] = useState(initialRows);
  const [rowModesModel, setRowModesModel] = useState({});
  const leaveConfirmationDialogOpen = useRef(false);
  const pendingRowChanges = useRef({});

  const handleRowEditStop = (params, event) => {
    if (params.reason === GridRowEditStopReasons.rowFocusOut) {
      if (pendingRowChanges.current[params.id]) {
        leaveConfirmationDialogOpen.current = true;
        event.defaultMuiPrevented = true;
      }
    }
  };

  const handleSaveClick = id => async () => {
    setRowModesModel({ ...rowModesModel, [id]: { mode: 'View' } });
    const updatedRow = rows.find(row => row.id === id);
    if (updatedRow.isNew) {
      try {
        await axios.post('http://localhost:8090/api/public/financial/', updatedRow);
        setRows(rows.map(row => (row.id === id ? { ...row, isNew: false } : row)));
      } catch (error) {
        console.error('Error saving new record:', error);
      }
    } else {
      delete pendingRowChanges.current[id];
    }
  };

  const processRowUpdate = async (newRow) => {
    const updatedRow = { ...newRow, isNew: false };
    try {
      if (newRow.isNew) {
        await axios.post('http://localhost:8090/api/public/financial/', updatedRow);
      } else {
        await axios.put('http://localhost:8090/api/public/financial/${newRow.id}', updatedRow);
      }
      setRows(rows.map(row => (row.id === newRow.id ? updatedRow : row)));
      delete pendingRowChanges.current[newRow.id];
      return updatedRow;
    } catch (error) {
      console.error('Error saving record:', error);
      return newRow;
    }
  };

  const handleRowModesModelChange = newRowModesModel => {
    setRowModesModel(newRowModesModel);
  };

  const handleCloseConfirmationDialog = () => {
    leaveConfirmationDialogOpen.current = false;
  };

  const handleConfirmLeave = () => {
    leaveConfirmationDialogOpen.current = false;
    // Here you can implement any specific action when the user confirms leaving the row without saving
  };

  const columns = [
    { field: 'financialId', headerName: 'Financial ID', width: 180, editable: true },
    { field: 'userId', headerName: 'User ID', width: 180, editable: true },
    { field: 'periodStart', headerName: 'Period Start', width: 180, editable: true },
    { field: 'periodEnd', headerName: 'Period End', width: 180, editable: true },
    { field: 'netProfit', headerName: 'Net Profit', width: 180, editable: true },
    { field: 'totalIncome', headerName: 'Total Income', width: 180, editable: true },
    { field: 'totalExpenses', headerName: 'Total Expenses', width: 180, editable: true },
    {
      field: 'actions',
      type: 'actions',
      headerName: 'Actions',
      width: 100,
      cellClassName: 'actions',
      getActions: ({ id }) => [
        <GridActionsCellItem
          icon={<SaveIcon />}
          label="Save"
          sx={{
            color: 'primary.main'
          }}
          onClick={handleSaveClick(id)}
        />
      ]
    }
  ];

  return (
    <Box className="Table"
      sx={{
        height: 500,
        width: '100%',
        '& .actions': {
          color: 'text.secondary'
        },
        '& .textPrimary': {
          color: 'text.primary'
        }
      }}
    >
      <DataGrid
        rows={rows}
        columns={columns}
        editMode="row"
        rowModesModel={rowModesModel}
        onRowModesModelChange={handleRowModesModelChange}
        onRowEditStop={handleRowEditStop}
        processRowUpdate={processRowUpdate}
        slots={{
          toolbar: EditToolbar
        }}
        slotProps={{
          toolbar: { setRows, setRowModesModel }
        }}
        sx={{
          '& .MuiDataGrid-colCell, .MuiDataGrid-cell': {
            borderRight: '1px solid rgba(0, 0, 0, 0.1)',
            borderBottom: '1px solid rgba(0, 0, 0, 0.1)',
          },
          '& .MuiDataGrid-row': {
            borderBottom: '1px solid rgba(0, 0, 0, 0.1)',
          },
          '& .MuiDataGrid-colCell:first-child': {
            borderLeft: '1px solid rgba(0, 0, 0, 0.1)',
          },
        }}
      />
      {leaveConfirmationDialogOpen.current && (
        <div className="confirmation-dialog">
          <p>You have unsaved changes. Are you sure you want to leave?</p>
          <Button onClick={handleConfirmLeave}>Leave</Button>
          <Button onClick={handleCloseConfirmationDialog}>Stay</Button>
        </div>
      )}
    </Box>
  );
}

export default NewFinancialReport;