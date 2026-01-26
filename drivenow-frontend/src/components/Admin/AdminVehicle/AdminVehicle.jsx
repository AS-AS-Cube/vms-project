import React from "react";
import "./AdminVehicle.css";
import {
  FaCar,
  FaRegClock,
  FaExclamationTriangle,
  FaRegCheckCircle,
  FaPlus,
  FaDownload
} from "react-icons/fa";
import VehicleCards from "./VehicleCards";
import { useEffect, useState } from "react";

import api from "../../../api/axios";

const AdminVehicle = () =>{
  const[totalVehicles, setTotalVehicles] = useState(0)
  const[activeVehicles, setActiveVehicles] = useState(0)
  const[pendingApprovals, setPendingApprovals] = useState(0)
  const[vehiclesInMaintenance, setVehicleInMaintenance] = useState(0)

  useEffect(() => {
    api
      .get("/api/admin/vehicles/stats")
      .then((response) => {
        const data = response.data;

        setTotalVehicles(data.totalVehicles);
        setActiveVehicles(data.activeVehicles);
        setPendingApprovals(data.pendingApprovals);
        setVehicleInMaintenance(data.vehiclesInMaintenance);
      })
      .catch((error) => {
        console.error(error);
        setTotalVehicles(0);
        setActiveVehicles(0);
        setPendingApprovals(0);
        setVehicleInMaintenance(0);
      });
  }, []);

  return (
    <div className="adminvehicle">
      {/* title */}
      <div className="vehicle-header">
        <div className="header-left">
          <h1>Vehicle Management</h1>
          <p className="sub">Manage all vehicles across the platform</p>
        </div>

      </div>
      {/* stats */}
      <div className="stats-section">
        <div className="stat-card">
          <div>
            <p className="label">Total Vehicles</p>
            <h2>{totalVehicles}</h2>
          </div>
          <FaCar className="icon blue" />
        </div>

        <div className="stat-card">
          <div>
            <p className="label">Active Vehicles</p>
            <h2>{activeVehicles}</h2>
          </div>
          <FaRegCheckCircle className="icon green" />
        </div>

        <div className="stat-card">
          <div>
            <p className="label">Pending Approvals</p>
            <h2>{pendingApprovals}</h2>
          </div>
          <FaRegClock className="icon orange" />
        </div>

        <div className="stat-card">
          <div>
            <p className="label">In Maintenance</p>
            <h2>{vehiclesInMaintenance}</h2>
          </div>
          <FaExclamationTriangle className="icon red" />
        </div>
      </div>
      <div>
        <VehicleCards />
      </div>
    </div>
  );
}
export default AdminVehicle;
