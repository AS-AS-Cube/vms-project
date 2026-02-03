package com.project.backend.admin.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminComplaintStatsDTO {
    private long totalComplaints;
    private long openIssues;
    private long resolvedComplaints;
}
