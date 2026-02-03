package com.project.backend.admin.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.backend.admin.dto.AdminComplaintCardDTO;
import com.project.backend.admin.dto.AdminComplaintStatsDTO;
import com.project.backend.admin.entities.Complaint;
import com.project.backend.admin.entities.ComplaintStatus;
import com.project.backend.complaint.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminComplaintServiceImpl implements AdminComplaintService {

    private final ComplaintRepository complaintRepository;
    @Override
    public List<AdminComplaintCardDTO> getAllComplaints() {

        return complaintRepository.findAllForAdmin()
            .stream()
            .map(c -> AdminComplaintCardDTO.builder()
                .complaintId(c.getComplaintId())
                .subject(c.getSubject())
                .description(c.getDescription())
                .status(c.getStatus().name())
                .userName(c.getUser().getFullName())
                .userRole(c.getUser().getRole().name())
                .vehicleName(
                    c.getBooking() != null
                        ? c.getBooking().getVehicle().getName()
                        : null
                )
                .vendorName(
                    c.getBooking() != null
                        ? c.getBooking().getVehicle().getVendorId()
                            .getUser().getFullName()
                        : null
                )
                .bookingRef(
                    c.getBooking() != null
                        ? "BK-" + c.getBooking().getBookingId()
                        : null
                )
                .createdAt(c.getCreatedAt())
                .updatedAt(c.getUpdatedAt())
                .adminResponse(c.getAdminResponse())
                .build()
            )
            .toList();
    }

    @Override
    public void startWorking(Long id) {
        Complaint c = complaintRepository.findById(id).orElseThrow();
        c.setStatus(ComplaintStatus.IN_PROGRESS);
    }

    @Override
    public void resolveComplaint(Long id, String response) {
        Complaint c = complaintRepository.findById(id).orElseThrow();
        c.setStatus(ComplaintStatus.RESOLVED);
        c.setAdminResponse(response);
        c.setRespondedAt(LocalDateTime.now());
    }
    
    @Override
    public AdminComplaintStatsDTO getComplaintStats() {

        return AdminComplaintStatsDTO.builder()
            .totalComplaints(complaintRepository.countTotalComplaints())
            .openIssues(complaintRepository.countOpenIssues())
            .resolvedComplaints(complaintRepository.countResolvedComplaints())
            .build();
    }

}

