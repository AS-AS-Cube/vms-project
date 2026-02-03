package com.project.backend.complaint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.backend.admin.entities.Complaint;
import com.project.backend.admin.entities.ComplaintStatus;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    @Query("""
        SELECT c
        FROM Complaint c
        ORDER BY c.updatedAt DESC
    """)
    List<Complaint> findAllForAdmin();

    long countByStatus(ComplaintStatus status);
    
    @Query("SELECT COUNT(c) FROM Complaint c")
    long countTotalComplaints();

    @Query("""
        SELECT COUNT(c)
        FROM Complaint c
        WHERE c.status IN ('OPEN', 'IN_PROGRESS')
    """)
    long countOpenIssues();

    @Query("""
        SELECT COUNT(c)
        FROM Complaint c
        WHERE c.status = 'RESOLVED'
    """)
    long countResolvedComplaints();
}
