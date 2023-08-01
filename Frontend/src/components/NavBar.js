import React from "react";
import PropTypes from "prop-types";
import AppBar from "@material-ui/core/AppBar";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import Typography from "@material-ui/core/Typography";
import Box from "@material-ui/core/Box";
import UserTable from "./Data_grid";
import InputBase from "@material-ui/core/InputBase";
import Button from "@material-ui/core/Button";
import { alpha, makeStyles } from "@material-ui/core/styles";
import Grid from "./Grid";

function TabPanel(props) {
  const { children, value, index, ...other } = props;
  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box p={3}>
          <Typography>{children}</Typography>
        </Box>
      )}
    </div>
  );
}
TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.any.isRequired,
  value: PropTypes.any.isRequired,
};
function a11yProps(index) {
  return {
    id: `simple-tab-${index}`,
    "aria-controls": `simple-tabpanel-${index}`,
  };
}
const useStyles = makeStyles((theme) => ({
  root: {
    "& .MuiBox": {
      padding: 0,
    },
    "& .MuiBox-root-6": {
      padding: 0,
    },
    flexGrow: 1,
    backgroundColor: "#666666",
    boxShadow: "none",
    border: 0,
    padding: 0,
    "& .MuiAppBar-colorPrimary": {
      backgroundColor: "#666666",
    },
    "& .PrivateTabIndicator-colorSecondary-4": {
      backgroundColor: "white",
    },
    "& .PrivateTabIndicator-colorSecondary-7": {
      backgroundColor: "white",
    },
  },
  search: {
    borderRadius: theme.shape.borderRadius,
    backgroundColor: "white",
    "&:hover": {
      backgroundColor: alpha(theme.palette.common.white, 0.25),
    },
  },
  inputRoot: {
    color: "black",
  },
  inputInput: {
    width: "200px",
    padding: theme.spacing(1, 1, 1, 1),
  },
}));
export default function SimpleTabs() {
  const classes = useStyles();
  const [value, setValue] = React.useState(0);
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  return (
    <div className={classes.root}>
      <AppBar
        position="static"
        style={{
          display: "flex",
          flexDirection: "row",
          justifyContent: "space-between",
          flexWrap: "wrap",
        }}
      >
        <div className="menu">
          <Tabs
            value={value}
            onChange={handleChange}
            aria-label="simple tabs example"
          >
            <Tab label="HOME PAGE" {...a11yProps(0)} />
            <Tab label="ADD DATA" {...a11yProps(1)} />
            <Tab label="ANALYTICS VIEW" {...a11yProps(2)} />
          </Tabs>
        </div>
        <div
          className="menu"
          style={{ display: "flex", alignItems: "baseline" }}
        >
          <div className={classes.search}>
            <InputBase
              placeholder="Search Customer Order ID"
              classes={{
                root: classes.inputRoot,
                input: classes.inputInput,
              }}
              inputProps={{ "aria-label": "search" }}
            />
          </div>
          <div>
            <Button
              variant="contained"
              style={{
                width: "150px",
                height: "40px",
                margin: "5px",
                lineHeight: 1.5,
                backgroundColor: "#8fd163",
              }}
            >
              ADVANCED SEARCH
            </Button>
          </div>
        </div>
      </AppBar>
      <TabPanel value={value} index={0}>
        <UserTable />
      </TabPanel>
      <TabPanel value={value} index={1}>
        <Grid />
      </TabPanel>
      <TabPanel value={value} index={2}>
        
      </TabPanel>
    </div>
  );
}
