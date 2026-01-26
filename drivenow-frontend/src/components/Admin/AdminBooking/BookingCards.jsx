import React, { useEffect, useState } from "react";
import "./BookingCards.css";
import {
  FaUser,
  FaCar,
  FaCalendarAlt,
  FaEye,
  FaCheckCircle,
  FaTimesCircle,
} from "react-icons/fa";
import api from "../../../api/axios";

const BookingCards = () => {
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    api
      .get("/api/admin/bookings")
      .then((res) => {
        setBookings(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error(err);
        setBookings([]);
        setLoading(false);
      });
  }, []);

  const getStatusClass = (status) => {
    switch (status) {
      case "PENDING":
        return "pill pending";
      case "CONFIRMED":
        return "pill confirmed";
      case "COMPLETED":
        return "pill completed";
      case "CANCELLED":
        return "pill cancelled";
      default:
        return "pill";
    }
  };

  const approveBooking = (id) => {
    api.put(`/api/admin/bookings/${id}/approve`).then(() => {
      setBookings((prev) =>
        prev.map((b) =>
          b.bookingId === id ? { ...b, bookingStatus: "CONFIRMED" } : b,
        ),
      );
    });
  };

  const rejectBooking = (id) => {
    api.put(`/api/admin/bookings/${id}/reject`).then(() => {
      setBookings((prev) =>
        prev.map((b) =>
          b.bookingId === id ? { ...b, bookingStatus: "CANCELLED" } : b,
        ),
      );
    });
  };

  if (loading) {
    return <div className="booking-loading">Loading bookings...</div>;
  }

  if (bookings.length === 0) {
    return <div className="booking-empty">No bookings found</div>;
  }

  return (
    <div className="container">
      {bookings.map((b) => (
        <div className="booking-card" key={b.bookingId}>
          <div className="booking-image">
            <img src="/placeholder-car.png" alt={b.vehicleName} />
          </div>

          <div className="booking-content">
            {/* HEADER */}
            <div className="booking-header">
              <h4>
                Booking #{b.bookingId}
                <span className={getStatusClass(b.bookingStatus)}>
                  {b.bookingStatus.toLowerCase()}
                </span>
              </h4>

              <div className="amount">
                <h4>₹{b.totalAmount}</h4>
              </div>
            </div>

            {/* DETAILS */}
            <div className="booking-details">
              <div>
                <p>
                  <FaUser /> {b.customerName}
                </p>
              </div>

              <div>
                <p>
                  <FaCar /> {b.vehicleName} – {b.vendorName}
                </p>
                <p>
                  <FaCalendarAlt /> {b.pickupDate?.substring(0, 10)} →{" "}
                  {b.dropoffDate?.substring(0, 10)}
                </p>
              </div>
            </div>
            <div className="actions">
              {b.bookingStatus === "PENDING" && (
                <>
                  <button
                    className="btn-approve"
                    onClick={() => approveBooking(b.bookingId)}
                  >
                    <FaCheckCircle /> Approve
                  </button>

                  <button
                    className="btn-reject"
                    onClick={() => rejectBooking(b.bookingId)}
                  >
                    <FaTimesCircle /> Reject
                  </button>
                </>
              )}
            </div>
          </div>
        </div>
      ))}
    </div>
  );
};

export default BookingCards;
