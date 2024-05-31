import React, { useEffect, useState } from 'react';
import {
  DataGrid,
  GridRowModes,
  GridActionsCellItem,
  GridToolbarContainer,
  GridRowEditStopReasons
} from '@mui/x-data-grid';
import axios from 'axios';
import Box from '@mui/material/Box';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/DeleteOutlined';
import SaveIcon from '@mui/icons-material/Save';
import CancelIcon from '@mui/icons-material/Close';
import './Transaction.css';





const columns = (rowModesModel, handleEditClick, handleSaveClick, handleCancelClick, handleDeleteClick) => [
  { field: 'transactionId', headerName: 'TransactionID', width: 100 },
  { field: 'amount', headerName: 'Amount', width: 130, editable: true },
  { field: 'type', headerName: 'Type', width: 130, editable: true },
  { field: 'description', headerName: 'Description', width: 300, editable: true },
  { field: 'date', headerName: 'Date', width: 150, editable: true },
  { field: 'userId', headerName: 'UserID', width: 100, editable: true },
  {
    field: 'actions',
    type: 'actions',
    headerName: 'Actions',
    width: 100,
    cellClassName: 'actions',
    getActions: ({ id }) => {
      const isInEditMode = rowModesModel[id]?.mode === GridRowModes.Edit;

      if (isInEditMode) {
        return [
          <GridActionsCellItem
            icon={<SaveIcon />}
            label="Save"
            onClick={handleSaveClick(id)}
            color="primary"
          />,
          <GridActionsCellItem
            icon={<CancelIcon />}
            label="Cancel"
            onClick={handleCancelClick(id)}
            color="inherit"
          />,
        ];
      }

      return [
        <GridActionsCellItem
          icon={<EditIcon />}
          label="Edit"
          onClick={handleEditClick(id)}
          color="inherit"
        />,
        <GridActionsCellItem
          icon={<DeleteIcon />}
          label="Delete"
          onClick={handleDeleteClick(id)}
          color="inherit"
        />,
      ];
    },
  },
];

export default function TransactionTable() {
  const [rows, setRows] = useState([]);
  const [rowModesModel, setRowModesModel] = useState({});

  useEffect(() => {
    axios.get("http://localhost:8090/api/public/transactions/")
      .then((res) => setRows(res.data.map((row) => ({ ...row, id: row.transactionId }))));
  }, []);

  const handleRowEditStop = (params, event) => {
    if (params.reason === GridRowEditStopReasons.rowFocusOut) {
      event.defaultMuiPrevented = true;
    }
  };

  const handleEditClick = (id) => () => {
    setRowModesModel({ ...rowModesModel, [id]: { mode: GridRowModes.Edit } });
  };

  const handleSaveClick = (id) => () => {
    setRowModesModel({ ...rowModesModel, [id]: { mode: GridRowModes.View } });
  };

  const handleDeleteClick = (id) => () => {
    setRows(rows.filter((row) => row.id !== id));
  };

  const handleCancelClick = (id) => () => {
    setRowModesModel({
      ...rowModesModel,
      [id]: { mode: GridRowModes.View, ignoreModifications: true },
    });

    const editedRow = rows.find((row) => row.id === id);
    if (editedRow.isNew) {
      setRows(rows.filter((row) => row.id !== id));
    }
  };

  const processRowUpdate = (newRow) => {
    const updatedRow = { ...newRow, isNew: false };
    setRows(rows.map((row) => (row.id === newRow.id ? updatedRow : row)));
    return updatedRow;
  };

  const handleRowModesModelChange = (newRowModesModel) => {
    setRowModesModel(newRowModesModel);
  };

  return (
    <Box className="Table" sx={{ height: 500, width: '100%', '& .actions': { color: 'text.secondary' }, '& .textPrimary': { color: 'text.primary' } }}>
      <DataGrid
        rows={rows}
        columns={columns(rowModesModel, handleEditClick, handleSaveClick, handleCancelClick, handleDeleteClick)}
        editMode="row"
        rowModesModel={rowModesModel}
        onRowModesModelChange={handleRowModesModelChange}
        onRowEditStop={handleRowEditStop}
        processRowUpdate={processRowUpdate}
        componentsProps={{ toolbar: { setRows, setRowModesModel } }}
        getRowId={(row) => row.transactionId}
        pageSizeOptions={[5, 10]}
        checkboxSelection
      />
    </Box>
  );
}