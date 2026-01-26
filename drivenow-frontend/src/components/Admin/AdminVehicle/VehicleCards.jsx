import React, { useEffect, useState } from "react";
import "./VehicleCards.css";
import {
  FaStar,
  FaUser,
  FaCogs,
  FaGasPump,
  FaMapMarkerAlt,
  FaCheck,
  FaTimes,
} from "react-icons/fa";
import api from "../../../api/axios";

const VehicleCards = () => {
  const [cars, setCars] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    api
      .get("/api/admin/vehicles")
      .then((res) => {
        setCars(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error(err);
        setError(true);
        setLoading(false);
      });
  }, []);

  /* ---------- STATUS HELPERS ---------- */

  const getStatusText = (status) => {
    switch (status) {
      case "AVAILABLE":
        return "active";
      case "BOOKED":
        return "booked";
      case "MAINTENANCE":
        return "maintenance";
      default:
        return "inactive";
    }
  };

  const getStatusBadge = (status) => {
    switch (status) {
      case "AVAILABLE":
        return "badge active";
      case "BOOKED":
        return "badge booked";
      case "MAINTENANCE":
        return "badge maintenance";
      default:
        return "badge inactive";
    }
  };

  const getApprovalBadge = (status) => {
    if (status === "PENDING_APPROVAL") return "badge pending";
    if (status === "INACTIVE") return "badge rejected";
    return "badge approved";
  };

  const getApprovalText = (status) => {
    if (status === "PENDING_APPROVAL") return "pending";
    if (status === "INACTIVE") return "rejected";
    return "approved";
  };

  /* ---------- ACTIONS ---------- */

  const handleApprove = async (vehicleId) => {
    try {
      await api.put(`/api/admin/vehicles/${vehicleId}/approve`);
      setCars((prev) =>
        prev.map((v) =>
          v.vehicleId === vehicleId
            ? { ...v, availabilityStatus: "AVAILABLE" }
            : v
        )
      );
    } catch (err) {
      console.error(err);
    }
  };

  const handleReject = async (vehicleId) => {
    try {
      await api.put(`/api/admin/vehicles/${vehicleId}/reject`);
      setCars((prev) =>
        prev.map((v) =>
          v.vehicleId === vehicleId
            ? { ...v, availabilityStatus: "INACTIVE" }
            : v
        )
      );
    } catch (err) {
      console.error(err);
    }
  };

  /* ---------- STATES ---------- */

  if (loading) {
    return <div className="vehicle-loading">Loading vehicles...</div>;
  }

  if (error || cars.length === 0) {
    return <div className="vehicle-empty">No vehicles found</div>;
  }

  /* ---------- JSX ---------- */

  return (
    <div className="vehicle-grid">
      {cars.map((car) => (
        <div className="vehicle-card" key={car.vehicleId}>
          <div className="vehicle-image">
            <img
              src={car.imageUrl || "/placeholder-car.png"}
              alt={car.name}
            />

            <div className="badges">
              <span className={getStatusBadge(car.availabilityStatus)}>
                {getStatusText(car.availabilityStatus)}
              </span>

              <span className={getApprovalBadge(car.availabilityStatus)}>
                {getApprovalText(car.availabilityStatus)}
              </span>
            </div>
          </div>

          <div className="vehicle-details">
            <div className="top-row">
              <div>
                <h2>{car.name}</h2>
                <p className="sub">
                  {car.modelYear} · {car.vehicleType}
                </p>
                <span className="vendor">{car.vendorName}</span>
              </div>

              <div className="rating">
                <FaStar /> {car.rating || "N/A"}
                <span> ({car.reviewCount || 0})</span>
              </div>
            </div>

            <div className="info-row">
              <span>
                <FaUser /> {car.seats || "-"}
              </span>
              <span>
                <FaCogs /> {car.transmission}
              </span>
              <span>
                <FaGasPump /> {car.fuelType}
              </span>
            </div>

            <div className="location">
              <FaMapMarkerAlt /> {car.locationName}
            </div>

            <div className="bottom-row">
              <p className="price">
                ₹{car.pricePerHour} <span>per hour</span>
              </p>

              {/* ACTION BUTTONS */}
              <div className="actions">
                {car.availabilityStatus === "PENDING_APPROVAL" && (
                  <>
                    <button
                      className="icon-btn approve"
                      onClick={() => handleApprove(car.vehicleId)}
                    >
                      <FaCheck />
                    </button>

                    <button
                      className="icon-btn reject"
                      onClick={() => handleReject(car.vehicleId)}
                    >
                      <FaTimes />
                    </button>
                  </>
                )}
              </div>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
};

export default VehicleCards;
