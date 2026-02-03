package com.project.backend.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.backend.admin.dto.UserManagementStatsResponse;
import com.project.backend.user.UserRole;
import com.project.backend.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserManagementStatsServiceImpl implements UserManagementStatsService {
	private final UserRepository userRepository;

    @Override
    public UserManagementStatsResponse getUserManagementStats() {

        long totalCustomers = userRepository.countByRole(UserRole.ROLE_CUSTOMER);
        long totalVendors = userRepository.countByRole(UserRole.ROLE_VENDOR);
        long pendingApprovals = userRepository.countPendingVendorApprovals();
        long activeUsers = userRepository.countActiveUsers();

        return UserManagementStatsResponse.builder()
                .totalCustomers(totalCustomers)
                .totalVendors(totalVendors)
                .pendingApprovals(pendingApprovals)
                .activeUsers(activeUsers)
                .build();
    }
}
