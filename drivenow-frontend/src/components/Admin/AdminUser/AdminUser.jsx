import React from "react";
import { useEffect, useState } from "react";
import "./AdminUser.css";
import {
  FaUsers,
  FaUserTie,
  FaRegClock,
  FaUserCheck,
  FaEdit,
  FaTrash,
} from "react-icons/fa";
import { MdEmail, MdPhone } from "react-icons/md";
import api from "../../../api/axios";

const AdminUser = () => {
  const [totalCustomers, setTotalCustomers] = useState(0);
  const [totalVendors, setTotalVendors] = useState(0);
  const [pendingApprovals, setPendingApprovals] = useState(0);
  const [activeUsers, setActiveUsers] = useState(0);
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  useEffect(() => {
    const fetchStats = api.get("/api/admin/users/stats");
    const fetchUsers = api.get("/api/admin/users");

    Promise.all([fetchStats, fetchUsers])
      .then(([statsRes, usersRes]) => {
        const stats = statsRes.data;

        setTotalCustomers(stats.totalCustomers);
        setTotalVendors(stats.totalVendors);
        setPendingApprovals(stats.pendingApprovals);
        setActiveUsers(stats.activeUsers);

        setUsers(usersRes.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error(err);
        setLoading(false);
      });
  }, []);
  const getStatusClass = (status) => {
    switch (status?.toLowerCase()) {
      case "active":
        return "pill active";
      case "pending":
        return "pill pending";
      case "suspended":
        return "pill suspended";
      default:
        return "pill";
    }
  };
  const getRoleIcon = (role) => {
    switch (role?.toLowerCase()) {
      case "vendor":
        return <FaUserTie />;
      default:
        return <FaUsers />;
    }
  };
  const handleApprove = (userId) => {
    api.put(`/api/admin/users/${userId}/approve`).then(() => {
      setUsers((prev) =>
        prev.map((u) => (u.userId === userId ? { ...u, status: "ACTIVE" } : u)),
      );
    });
  };

  const handleReject = (userId) => {
    api.put(`/api/admin/users/${userId}/s=reject`).then(() => {
      setUsers((prev) =>
        prev.map((u) =>
          u.userId === userId ? { ...u, status: "SUSPENDED" } : u,
        ),
      );
    });
  };
  return (
    <div className="adminuser">
      {/* title */}
      <div className="user-header">
        <div className="header-left">
          <h1>User Management</h1>
          <p className="sub">Manage customers, vendors, and administrators</p>
        </div>
      </div>
      <div className="stats-section">
        <div className="stat-card">
          <div>
            <p className="label">Total Customers</p>
            <h2>{totalCustomers}</h2>
          </div>
          <FaUsers className="icon blue" />
        </div>

        <div className="stat-card">
          <div>
            <p className="label">Total Vendors</p>
            <h2>{totalVendors}</h2>
          </div>
          <FaUserTie className="icon" />
        </div>

        <div className="stat-card">
          <div>
            <p className="label">Pending Approval</p>
            <h2>{pendingApprovals}</h2>
          </div>
          <FaRegClock className="icon orange" />
        </div>

        <div className="stat-card">
          <div>
            <p className="label">Active Users</p>
            <h2>{activeUsers}</h2>
          </div>
          <FaUserCheck className="icon green" />
        </div>
      </div>

      <table className="user-table">
        <thead>
          <tr>
            <th>User</th>
            <th>Role</th>
            <th>Status</th>
            <th>Activity</th>
            <th>Joined</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          {loading ? (
            <tr>
              <td colSpan="6" className="loading">
                Loading users...
              </td>
            </tr>
          ) : users.length === 0 ? (
            <tr>
              <td colSpan="6" className="empty">
                No users found
              </td>
            </tr>
          ) : (
            users.map((user) => (
              <tr key={user.id}>
                <td>
                  <strong>{user.name}</strong>
                  <br />
                  <div className="contact">
                    <div className="contact-row">
                      <MdEmail className="contact-icon" />
                      <span>{user.email}</span>
                    </div>
                    <div className="contact-row">
                      <MdPhone className="contact-icon" />
                      <span>{user.mobileNo}</span>
                    </div>
                  </div>
                </td>
                <td>
                  {getRoleIcon(user.role)} {user.role}
                </td>
                <td>
                  <span className={getStatusClass(user.status)}>
                    {user.status.toLowerCase()}
                  </span>
                </td>
                <td>
                  {user.activityCount}
                  <br />${user.activityAmount}
                </td>
                <td> {new Date(user.createdAt).toLocaleDateString()}</td>
                <td className="actions">
                  <div className="action-icons">
                   {(user.status === "PENDING" || user.status === "SUSPENDED") && (
                    <button className="icon-btn edit-btn" onClick={() => handleApprove(user.userId)}>
                      <FaEdit />
                    </button>
                    )}
                    {(user.status === "ACTIVE" || user.status === "PENDING") && (
                    <button className="icon-btn delete-btn" onClick={() => handleSuspend(user.userId)}>
                      <FaTrash />
                    </button>
                    )}
                  </div>
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
};

export default AdminUser;
