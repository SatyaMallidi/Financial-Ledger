import React, { useState, useRef } from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import AddIcon from '@mui/icons-material/Add';
import SaveIcon from '@mui/icons-material/Save';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import {
  DataGrid,
  GridToolbarContainer,
  GridActionsCellItem,
  GridRowEditStopReasons,
} from '@mui/x-data-grid';
import '../CssFolder/Table.css';

const initialRows = [
  {
    id: '1',
    transactionId: '1563',
    date: new Date().toISOString().slice(0, 10),
    amount: '',
    description: '',
    type: 'Income', // Default type value for the first row
    userId: '',
    isNew: true
  }
];

function EditToolbar(props) {
  const { setRows, setRowModesModel } = props;

  const handleClick = () => {
    const id = Math.floor(1000 + Math.random() * 9000); // Generate random four-digit number
    setRows(oldRows => [...oldRows, { id: id.toString(), transactionId: id.toString(), date: new Date().toISOString().slice(0, 10), amount: '', description: '', type: '', userId: '', isNew: true }]);
    setRowModesModel(oldModel => ({
      ...oldModel,
      [id]: { mode: 'Edit', fieldToFocus: 'transactionId' }
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

export default function TransactionNewTable() {
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

  const handleSaveClick = id => () => {
    setRowModesModel({ ...rowModesModel, [id]: { mode: 'View' } });
    delete pendingRowChanges.current[id];
  };

  const processRowUpdate = newRow => {
    const updatedRow = { ...newRow, isNew: false };
    setRows(rows.map(row => (row.id === newRow.id ? updatedRow : row)));
    delete pendingRowChanges.current[newRow.id];
    return updatedRow;
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
    { field: 'transactionId', headerName: 'Transaction ID', width: 180, editable: true },
    { field: 'type', headerName: 'Type', width: 180, editable: true, renderCell: params => (
        <Select
          value={params.value}
          onChange={event => {
            const newValue = event.target.value;
            const updatedRows = rows.map(row => {
              if (row.id === params.id) {
                return { ...row, type: newValue };
              }
              return row;
            });
            setRows(updatedRows);
          }}
        >
          <MenuItem value="Income">Income</MenuItem>
          <MenuItem value="Expense">Expense</MenuItem>
        </Select>
      )
    },
    { field: 'amount', headerName: 'Amount', width: 180, editable: true },
    { field: 'description', headerName: 'Description', width: 180, editable: true },
    { field: 'date', headerName: 'Date', width: 180, editable: true },
    { field: 'userId', headerName: 'User ID', width: 180, editable: true },
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

