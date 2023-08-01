import React, { useEffect, useState } from "react";
import { DataGrid } from "@mui/x-data-grid";
import axios from "axios";
import { makeStyles } from "@material-ui/core/styles";
import { TablePagination, Button } from "@material-ui/core";
import Delete from './modal/Delete';
import "../css/styles.css";

const useStyles = makeStyles((theme) => ({
  root: {
    "& .MuiBox-root-17": {
      padding: 0,
    },
    "& .MuiSvgIcon-root": {
      fill: "white",
    },
    "& .MuiTypography-colorInherit": {
      color: "white",
    },
    border: 0,
  },
  button: {
    margin: theme.spacing(1),
    backgroundColor: "#666666",
    color: "white",
  },
  "& .MuiButton-contained:hover": {
    backgroundColor: "red",
  },
}));

const UserTable = ({ searchQuery }) => {
  const [pageSize, setPageSize] = useState(5);
  const [users, setUsers] = useState([]);
  const [deleteOpen, setDeleteOpen] = useState(false);
  const [selectedRows, setSelectedRows] = useState([]);

  const fetchData = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/h2h_milestone_2/FetchAllUsers"
      );
      // Add unique ID field to each user object
      const usersWithId = response.data.map((user, index) => ({
        ...user,
        id: index + 1,
      }));
      setUsers(usersWithId);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleDelete = async () => {
    if (selectedRows.length === 0) {
      console.log('No rows selected for deletion');
      return;
    }

    try {
      const customerOrderIds = selectedRows.map((row) => row.customerOrderID);
      const customerOrderIdsString = customerOrderIds.join(',');

      const response = await axios.post('http://localhost:8080/h2h_milestone_2/DeleteUser?CUSTOMER_ORDER_ID=' + customerOrderIdsString);
      // Handle success case
    } catch (error) {
      console.error(error);
      // Handle error case
    }

    setDeleteOpen(false);
  };

  const handleDeleteClick = () => {
    setDeleteOpen(true);
  };

  const handleDeleteClose = () => {
    setDeleteOpen(false);
  };

  const handleSelectionModelChange = (selectionModel) => {
    setSelectedRows(
      selectionModel.map((selectedRowId) => users.find((row) => row.id === selectedRowId))
    );
  };

  const handleRefreshData = () => {
    fetchData();
  };

  const handleEdit = () => {
    // Perform the edit operation
    // ...
  };

  const handlePredict = () => {
    // Perform the predict operation
    // ...
  };

  const columns = [
    { field: 'id', headerName: 'SL No', width: 110 },
    { field: 'customerOrderID', headerName: 'Customer Order Id', width: 170 },
    { field: 'salesOrg', headerName: 'Sales Org', width: 130 },
    { field: 'distributionChannel', headerName: 'Distribution Channel', width: 200 },
    { field: 'companyCode', headerName: 'Company Code', width: 150 },
    { field: 'orderCreationDate', headerName: 'Order Creation Date', width: 200 },
    { field: 'orderCurrency', headerName: 'Order Currency', width: 150 },
    { field: 'customerNumber', headerName: 'Customer Number', width: 160 },
    { field: 'amountUSD', headerName: 'Amount in USD', width: 180 },
  ];

  const handlePageSizeChange = (params) => {
    setPageSize(params.pageSize); // Update the current page size
  };

  const classes = useStyles();

  const Footer = () => (
    <div className="MuiDataGrid-footerContainer" style={{ background: "#666666", width: "50%" }}>
      <div>
        <Button variant="contained" className={classes.button} onClick={handleRefreshData}>
          REFRESH
        </Button>
        <Button variant="contained" className={classes.button} onClick={handleEdit}>
          EDIT
        </Button>
        <Button variant="contained" className={classes.button} onClick={handleDeleteClick}>
          DELETE
        </Button>
        <Button variant="contained" className={classes.button} onClick={handlePredict}>
          PREDICT
        </Button>
      </div>
    </div>
  );

  return (
    <div className="table-container">
      <DataGrid
        rows={users}
        columns={columns}
        checkboxSelection
        onSelectionModelChange={handleSelectionModelChange}
        pageSize={pageSize}
        autoHeight
        rowsPerPageOptions={[5, 10, 20, 50, 100]}
        disableSelectionOnClick
        className={classes.root}
        onPageSizeChange={handlePageSizeChange}
      />
      <Footer />
      <Delete open={deleteOpen} onClose={handleDeleteClose} onDelete={handleDelete} />
    </div>
  );
};

export default UserTable;
