import React, { useState } from 'react';
import axios from 'axios';

function NewBalanceSheet() {
  const [sheet, setSheet] = useState({
    userId: '',
    date: '',
    assets: '',
    liabilities: '',
    equity: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setSheet({
      ...sheet,
      [name]: value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post("http://localhost:8090/api/public/balanceSheet/", sheet)
      .then((res) => {
        console.log('Balance sheet created:', res.data);
      })
      .catch((err) => {
        console.error('Error creating balance sheet:', err);
      });
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        User ID:
        <input type="text" name="userId" value={sheet.userId} onChange={handleChange} required />
      </label>
      <label>
        Date:
        <input type="date" name="date" value={sheet.date} onChange={handleChange} required />
      </label>
      <label>
        Assets:
        <input type="number" name="assets" value={sheet.assets} onChange={handleChange} required />
      </label>
      <label>
        Liabilities:
        <input type="number" name="liabilities" value={sheet.liabilities} onChange={handleChange} required />
      </label>
      <label>
        Equity:
        <input type="number" name="equity" value={sheet.equity} onChange={handleChange} required />
      </label>
      <button type="submit">Create Balance Sheet</button>
    </form>
  );
}

export default NewBalanceSheet;
