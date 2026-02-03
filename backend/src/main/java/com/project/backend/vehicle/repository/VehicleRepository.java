package com.project.backend.vehicle.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.backend.vehicle.entities.AvailabilityStatus;
import com.project.backend.vehicle.entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    // ✅ Current active vehicles
    @Query("""
        SELECT COUNT(v)
        FROM Vehicle v
        WHERE v.availabilityStatus = :status
    """)
    long countByStatus(@Param("status") AvailabilityStatus status);

    @Query("""
    	    SELECT COUNT(v)
    	    FROM Vehicle v
    	    WHERE v.vendorId.user.userId = :userId
    	""")
    	Long countVehiclesByVendor(@Param("userId") Long userId);
    @Query("""
            SELECT COUNT(v)
            FROM Vehicle v
            WHERE v.deletedAt IS NULL
        """)
        long countAllActiveVehicles();

        @Query("""
            SELECT COUNT(v)
            FROM Vehicle v
            WHERE v.availabilityStatus = :status
              AND v.deletedAt IS NULL
        """)
        long countByAvailabilityStatusForAdmin(
            @Param("status") AvailabilityStatus status
        );



}
