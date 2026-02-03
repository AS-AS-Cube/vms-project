package com.project.backend.admin.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.backend.admin.dto.ComplaintDetailsDTO;
import com.project.backend.admin.entities.Complaint;
import com.project.backend.admin.entities.ComplaintStatus;
import com.project.backend.complaint.repository.ComplaintRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    public ComplaintDetailsDTO getComplaintDetails(Long id) {
        Complaint c = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        return ComplaintDetailsDTO.builder()
                .complaintId(c.getComplaintId())
                .subject(c.getSubject())
                .description(c.getDescription())
                .status(c.getStatus().name())
                .userName(c.getUser().getFullName())
                .userRole(c.getUser().getRole().name().replace("ROLE_", "").toLowerCase())
                .email(c.getUser().getEmail())
                .phone(c.getUser().getMobileNumber())
                .createdAt(c.getCreatedAt())
                .updatedAt(c.getUpdatedAt())
                .relatedTo(buildRelatedText(c))
                .adminResponse(c.getAdminResponse())
                .respondedAt(c.getRespondedAt())
                .build();
    }

    public void addResponse(Long id, String response) {
        Complaint c = complaintRepository.findById(id)
                .orElseThrow();

        c.setAdminResponse(response);
        c.setRespondedAt(LocalDateTime.now());

        if (c.getStatus() == ComplaintStatus.OPEN) {
            c.setStatus(ComplaintStatus.IN_PROGRESS);
        }
    }

    public void resolveComplaint(Long id) {
        Complaint c = complaintRepository.findById(id)
                .orElseThrow();

        c.setStatus(ComplaintStatus.RESOLVED);
    }

    private String buildRelatedText(Complaint c) {
        if (c.getBooking() != null) {
            return c.getBooking().getVehicle().getName()
                    + " - "
                    + c.getBooking().getVehicle().getVendorId().getUser().getFullName();
        }
        return null;
    }
}

