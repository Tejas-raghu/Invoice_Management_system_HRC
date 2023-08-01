import React from "react";
import hrclogo from "../logos/hrclogo.svg";
import abclogo from "../logos/abclogo.svg";

export default function Heading() {
  return (
    <div style={{ display: "flex", flexDirection: "column" }}>
      <div style={{ display: "flex" }}>
        <div style={{ width: "30px" }}>
          <img src={abclogo} alt="abc_logo" />
        </div>
        <div style={{ marginLeft: "auto", marginRight: "auto" }}>
          <img src={hrclogo} alt="hrc_logo" />
        </div>
      </div>

      <div>
        <h2 style={{ color: "red" }}>Invoice List</h2>
      </div>
    </div>
  );
}
