import React from "react";
import "./Navbar.css";
import { NavLink, useNavigate } from "react-router-dom";
import { FaRegUser } from "react-icons/fa";

const Navbar = () => {
  const linkStyle = { textDecoration: "none", color: "inherit" };
  const navigate = useNavigate();
  const handleLogout = () => {
    // If you store auth data in future, clear it here
    // localStorage.removeItem("token");

    navigate("/login"); // redirect to login page
  };
  return (
    <nav className="adminnavbar">
      <div className="adminnavlogo">🚗 DriveNow</div>

      <ul className="adminnav-menu">
        <li>
          <NavLink
            to="/admin/dashboard"
            style={linkStyle}
            className={({ isActive }) => (isActive ? "active" : "")}
          >
            Dashboard
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/admin/users"
            style={linkStyle}
            className={({ isActive }) => (isActive ? "active" : "")}
          >
            Users
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/admin/vehicles"
            style={linkStyle}
            className={({ isActive }) => (isActive ? "active" : "")}
          >
            Vehicles
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/admin/license-verification"
            style={linkStyle}
            className={({ isActive }) => (isActive ? "active" : "")}
          >
            License Verification
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/admin/bookings"
            style={linkStyle}
            className={({ isActive }) => (isActive ? "active" : "")}
          >
            Bookings
          </NavLink>
        </li>
      </ul>

      <div className="admin-profile-section">
        <span>
          <FaRegUser size={20} /> Admin User
        </span>
        <button
          type="button"
          className="logout-btn"
          onClick={handleLogout}
        >
          Logout
        </button>
      </div>
    </nav>
  );
};

export default Navbar;
