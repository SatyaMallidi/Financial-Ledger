import React, { useState } from 'react';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import Tooltip from '@mui/material/Tooltip';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import IconButton from '@mui/material/IconButton';
import logo from '../Images/FL-logo.jpg';

const pages = ['Home', 'Transaction', 'BalanceSheet', 'FinancialReport'];
const settings = ['Profile', 'AccountSettings', 'Logout'];

function Navigation() {
  const [anchorElUser, setAnchorElUser] = useState(null);
  const loggedInUser = "Satya";

  const handleOpenUserMenu = (event) => setAnchorElUser(event.currentTarget);
  const handleCloseUserMenu = () => setAnchorElUser(null);
  const getFirstLetter = (str) => str.charAt(0).toUpperCase();

  return (
    <AppBar position="fixed" sx={{ backgroundColor: '#002D62' }}>
      <Toolbar>
        <Typography variant="h6" component="div" sx={{ display: 'flex', alignItems: 'center', flexGrow: 1 }}>
          <img src={logo} alt="Logo" style={{ marginRight: '16px', height: '40px' }} />
          <Typography variant="subtitle1" sx={{ fontWeight: 'bold', marginLeft: '8px' }}>Financial Ledger</Typography>
        </Typography>

        {pages.map((page) => (
          <Button key={page} sx={{ mx: 1 }} color="inherit">
            {page}
          </Button>
        ))}

        <Tooltip title="Open settings">
          <IconButton onClick={handleOpenUserMenu} sx={{ ml: 2 }}>
            <Avatar sx={{ bgcolor: '#e32636' }}>
              {getFirstLetter(loggedInUser)}
            </Avatar>
          </IconButton>
        </Tooltip>
        <Menu
          anchorEl={anchorElUser}
          keepMounted
          open={Boolean(anchorElUser)}
          onClose={handleCloseUserMenu}
        >
          {settings.map((setting) => (
            <MenuItem key={setting} onClick={handleCloseUserMenu}>
              {setting}
            </MenuItem>
          ))}
        </Menu>
      </Toolbar>
    </AppBar>
  );
}

export default Navigation;
