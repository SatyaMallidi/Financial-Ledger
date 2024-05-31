import React, { useState } from 'react';
import axios from 'axios';

function NewFinancialReport() {
  const [report, setReport] = useState({
    userId: '',
    periodStart: '',
    periodEnd: '',
    netProfit: '',
    totalIncome: '',
    totalExpenses: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setReport({
      ...report,
      [name]: value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post("http://localhost:8090/api/public/financial/", report)
      .then((res) => {
        console.log('Financial report created:', res.data);
      })
      .catch((err) => {
        console.error('Error creating financial report:', err);
      });
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        User ID:
        <input type="text" name="userId" value={report.userId} onChange={handleChange} required />
      </label>
      <label>
        Period Start:
        <input type="date" name="periodStart" value={report.periodStart} onChange={handleChange} required />
      </label>
      <label>
        Period End:
        <input type="date" name="periodEnd" value={report.periodEnd} onChange={handleChange} required />
      </label>
      <label>
        Net Profit:
        <input type="number" name="netProfit" value={report.netProfit} onChange={handleChange} required />
      </label>
      <label>
        Total Income:
        <input type="number" name="totalIncome" value={report.totalIncome} onChange={handleChange} required />
      </label>
      <label>
        Total Expenses:
        <input type="number" name="totalExpenses" value={report.totalExpenses} onChange={handleChange} required />
      </label>
      <button type="submit">Create Financial Report</button>
    </form>
  );
}

export default NewFinancialReport;
