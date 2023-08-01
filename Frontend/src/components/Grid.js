import { React, useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import axios from "axios";
import {
  Dialog,
  DialogTitle,
  DialogContent,
  TextField,
  DialogActions,
  Button,
} from "@mui/material";

import Grid from "@material-ui/core/Grid";
const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  textField: {
    textAlign: "center",
    color: "black",
    backgroundColor: "white",
    borderRadius: "3px",
    width: "100%",
  },
  button: {
    width: "49%",
  },
}));

export default function CenteredGrid() {
  const classes = useStyles();
  const [formValues, setFormValues] = useState({
    CUSTOMER_ORDER_ID: "",
    SALES_ORG: "",
    DISTRIBUTION_CHANNEL: "",
    CUSTOMER_NUMBER: "",
    COMPANY_CODE: "",
    ORDER_CURRENCY: "",
    AMOUNT_IN_USD: "",
    ORDER_CREATION_DATE: "",
  });

  const handleInputChange = (event) => {
    const { id, value } = event.target;
    setFormValues((prevValues) => ({
      ...prevValues,
      [id]: value,
    }));
  };

 
  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await axios.post('http://localhost:8080/h2h_milestone_2/AddUser', formValues);
      // Data added successfully, you can perform additional actions if needed
      console.log('Data added successfully');
    } catch (error) {
      console.error(error);
    }
  };


  const handleClear = () => {
    setFormValues({
      CUSTOMER_ORDER_ID: "",
      SALES_ORG: "",
      DISTRIBUTION_CHANNEL: "",
      CUSTOMER_NUMBER: "",
      COMPANY_CODE: "",
      ORDER_CURRENCY: "",
      AMOUNT_IN_USD: "",
      ORDER_CREATION_DATE: "",
    });
  };

  return (
    <div>
      <form noValidate autoComplete="off" onSubmit={handleSubmit}>
        <div className={classes.root}>
          <Grid container spacing={3}>
            <Grid item xs={3}>
              <TextField
                id="CUSTOMER_ORDER_ID"
                label="CUSTOMER ORDER ID"
                variant="outlined"
                value={formValues.CUSTOMER_ORDER_ID}
                onChange={handleInputChange}
                className={classes.textField}
              />
            </Grid>
            <Grid item xs={3}>
              <TextField
                id="SALES_ORG"
                label="SALES ORG"
                variant="outlined"
                value={formValues.SALES_ORG}
                onChange={handleInputChange}
                className={classes.textField}
              />
            </Grid>
            <Grid item xs={6}>
              <TextField
                id="DISTRIBUTION_CHANNEL"
                label="DISTRIBUTION CHANNEL"
                variant="outlined"
                value={formValues.DISTRIBUTION_CHANNEL}
                onChange={handleInputChange}
                className={classes.textField}
              />
            </Grid>
          </Grid>
          <Grid container spacing={5}>
            <Grid item xs>
              <TextField
                id="CUSTOMER_NUMBER"
                label="CUSTOMER NUMBER"
                variant="outlined"
                value={formValues.CUSTOMER_NUMBER}
                onChange={handleInputChange}
                className={classes.textField}
              />
            </Grid>
            <Grid item xs>
              <TextField
                id="COMPANY_CODE"
                label="COMPANY CODE"
                variant="outlined"
                value={formValues.COMPANY_CODE}
                onChange={handleInputChange}
                className={classes.textField}
              />
            </Grid>
            <Grid item xs>
              <TextField
                id="ORDER_CURRENCY"
                label="ORDER CURRENCY"
                variant="outlined"
                value={formValues.ORDER_CURRENCY}
                onChange={handleInputChange}
                className={classes.textField}
              />
            </Grid>
            <Grid item xs>
              <TextField
                id="AMOUNT_IN_USD"
                label="AMOUNT IN USD"
                variant="outlined"
                value={formValues.AMOUNT_IN_USD}
                onChange={handleInputChange}
                className={classes.textField}
              />
            </Grid>
            <Grid item xs>
              <TextField
                id="ORDER_CREATION_DATE"
                label="ORDER CREATION DATE"
                variant="outlined"
                value={formValues.ORDER_CREATION_DATE}
                onChange={handleInputChange}
                className={classes.textField}
              />
            </Grid>
          </Grid>
        </div>
        <div>
          <div
            style={{
              display: "flex",
              justifyContent: "space-between",
              margin: "5px",
            }}
          >
            <Button
              style={{
                backgroundColor: "#fc7500",
                color: "white",
              }}
              variant="contained"
              className={classes.button}
              type="submit"
            >
              ADD
            </Button>
            <Button
              style={{
                backgroundColor: "#db4437",
                color: "white",
              }}
              variant="contained"
              className={classes.button}
              onClick={handleClear}
            >
              CLEAR DATA
            </Button>
          </div>
        </div>
      </form>
    </div>
  );
}
