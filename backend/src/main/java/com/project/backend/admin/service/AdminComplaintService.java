package com.project.backend.admin.service;

import java.util.List;

import com.project.backend.admin.dto.AdminComplaintCardDTO;
import com.project.backend.admin.dto.AdminComplaintStatsDTO;

public interface AdminComplaintService {

    List<AdminComplaintCardDTO> getAllComplaints();

    void startWorking(Long complaintId);

    void resolveComplaint(Long complaintId, String response);
    
    AdminComplaintStatsDTO getComplaintStats();

}

