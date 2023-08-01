import "./App.css";
// import UserTable from "./components/Data_grid";
import Heading from "./components/Heading";
import Foot from "./components/Foot";
import NavBar from "./components/NavBar";

function App() {
  return (
    <div className="App">
      <Heading />
      <NavBar />
      {/* <UserTable /> */}
      <Foot />
    </div>
  );
}

export default App;
