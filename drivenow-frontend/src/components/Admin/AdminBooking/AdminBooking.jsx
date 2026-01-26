import React from "react";
import "./AdminBooking.css";
import {
  FaRegCheckCircle,
  FaCalendarAlt,
  FaRegClock,
  FaDownload,
  FaSyncAlt,
} from "react-icons/fa";
import BookingCards from "./BookingCards";
import { useEffect, useState } from "react";
import api from "../../../api/axios"


const AdminBooking = () => {
  const[totalBookings, setTotalBookings] = useState(0)
  const[activeBookings, setActiveBookings] = useState(0)
  const[pendingApprovals, setPendingApprovals] = useState(0)
   useEffect(() => {
    api
      .get("/api/admin/bookings/stats")
      .then((response) => {
        const data = response.data;

        setTotalBookings(data.totalBookings);
        setActiveBookings(data.activeBookings);
        setPendingApprovals(data.pendingApprovals);
      })
      .catch((error) => {
        console.error(error);
        setTotalBookings(0);
        setActiveBookings(0);
        setPendingApprovals(0);
      });
  }, []);
  return (
    <div className="adminbooking">
      {/* HEADER */}
      <div className="booking-header">
        <div>
          <h1>Booking Management</h1>
          <p className="sub">Monitor and manage all platform bookings</p>
        </div>
      </div>

      {/* stats */}
      <div className="stats-section">
        <div className="stat-card">
          <div>
            <p className="label">Total Bookings</p>
            <h2>{totalBookings}</h2>
          </div>
          <FaCalendarAlt className="icon blue" />
        </div>

        <div className="stat-card">
          <div>
            <p className="label">Active Bookings</p>
            <h2>{activeBookings}</h2>
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
      </div>
      <div>
        <BookingCards />
      </div>
    </div>
  );
};
export default AdminBooking;
