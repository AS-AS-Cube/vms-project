import React from "react"
import "./AdminDashboard.css"
import { FaUsers, FaCar, FaCalendarAlt } from "react-icons/fa"
import { MdAttachMoney } from "react-icons/md"
import { useEffect, useState } from "react"
import {useNavigate} from "react-router-dom" 
import api from "../../../api/axios";



const AdminDashboard = () => {
  const [stats, setStats] = useState(null)

  const navigate = useNavigate()
  useEffect(() => {
  api
    .get("/api/admin/dashboard/stats")
    .then(res => setStats(res.data))
    .catch(err => {
      console.error(err);
      setStats(null);
    });
}, []);

  return (
    <div className="admindashboard">

      {/* Title */}
      <div className="header">
        <h1>Admin Dashboard</h1>
        <p className="sub">Manage your vehicle rental platform</p>
      </div>

      {/* Stats */}
      <div className="stats-section">
        
        <div className="stat-card">
          <div>
            <p className="label">Total Users</p>
            <h2>{stats?.totalUsers ?? 0}</h2>
          </div>
          <FaUsers className="icon blue" />
        </div>

        <div className="stat-card">
          <div>
            <p className="label">Active Vehicles</p>
            <h2>{stats?.activeVehicles ?? 0}</h2>
          </div>
          <FaCar className="icon green" />
        </div>

        <div className="stat-card">
          <div>
            <p className="label">Monthly Revenue</p>
            <h2>₹ {stats?.monthlyRevenue ?? 0}</h2>
    
          </div>
          <MdAttachMoney className="icon yellow" />
        </div>

        <div className="stat-card">
          <div>
            <p className="label">Active Bookings</p>
            <h2>{stats?.activeBookings ?? 0}</h2>
    
          </div>
          <FaCalendarAlt className="icon purple" />
        </div>
      </div>

      {/* Quick Actions */}
      <div className="qa-container">
      <h3 className="qa-title">Quick Actions</h3>

      <div className="quick-actions">
        <button className="qa-btn" onClick={() => navigate("/admin/users")}><FaUsers /> Manage Users</button>
        <button className="qa-btn" onClick={() => navigate("/admin/vehicles")}><FaCar /> Vehicles</button>
        <button className="qa-btn" onClick={() => navigate("/admin/bookings")}><FaCalendarAlt /> Bookings</button>
      </div>
      </div>
    </div>
  )
}
export default AdminDashboard