package com.project.backend.user.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.project.backend.user.UserRole;
import com.project.backend.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    long count();

    // ✅ Used by UserController (/me)
    Optional<User> findByEmail(String email);

    // ✅ Used by AdminSeeder
    boolean existsByEmail(String email);
    
    // Total customers
    long countByRole(UserRole role);

    // Active users (not soft deleted)
    @Query("SELECT COUNT(u) FROM User u WHERE u.deletedAt IS NULL")
    long countActiveUsers();

    // Pending approvals (example: vendors created but not approved)
    // Assuming vendor approval = createdBy IS NULL OR some flag
    @Query("""
    	    SELECT COUNT(u)
    	    FROM User u
    	    WHERE u.role = 'ROLE_VENDOR'
    	      AND u.status = 'PENDING'
    	      AND u.deletedAt IS NULL
    	""")
    	long countPendingVendorApprovals();

}
