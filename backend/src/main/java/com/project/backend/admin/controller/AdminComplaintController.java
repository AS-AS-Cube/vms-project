package com.project.backend.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend.admin.dto.AdminComplaintCardDTO;
import com.project.backend.admin.dto.AdminComplaintStatsDTO;
import com.project.backend.admin.dto.ComplaintDetailsDTO;
import com.project.backend.admin.entities.AdminResponseRequest;
import com.project.backend.admin.service.AdminComplaintService;
import com.project.backend.admin.service.ComplaintService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/complaints")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminComplaintController {

    private final AdminComplaintService service;
    
    private final AdminComplaintService adminComplaintService;
    
    private final ComplaintService complaintService;

    @GetMapping("/stats")
    public ResponseEntity<AdminComplaintStatsDTO> getComplaintStats() {
        return ResponseEntity.ok(adminComplaintService.getComplaintStats());
    }
    
    @GetMapping
    public List<AdminComplaintCardDTO> getAll() {
        return service.getAllComplaints();
    }

    @PostMapping("/{id}/start")
    public void start(@PathVariable Long id) {
        service.startWorking(id);
    }

    @PostMapping("/{id}/resolve")
    public void resolve(
        @PathVariable Long id,
        @RequestBody Map<String, String> body
    ) {
        service.resolveComplaint(id, body.get("response"));
    }
    @GetMapping("/{id}")
    public ComplaintDetailsDTO getComplaint(@PathVariable Long id) {
        return complaintService.getComplaintDetails(id);
    }

    @PutMapping("/{id}/respond")
    public void respond(
            @PathVariable Long id,
            @RequestBody AdminResponseRequest request
    ) {
        complaintService.addResponse(id, request.getResponse());
    }

    @PutMapping("/{id}/resolve")
    public void resolve(@PathVariable Long id) {
        complaintService.resolveComplaint(id);
    }

}

