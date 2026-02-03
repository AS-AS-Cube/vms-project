package com.project.backend.payment.service;

import java.time.LocalDateTime;
import java.time.YearMonth;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.backend.payment.entities.PaymentStatus;
import com.project.backend.payment.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public double getMonthlyRevenue() {

        YearMonth currentMonth = YearMonth.now();

        LocalDateTime monthStart =
                currentMonth.atDay(1).atStartOfDay();

        LocalDateTime nextMonthStart =
                currentMonth.plusMonths(1).atDay(1).atStartOfDay();

        return paymentRepository.sumAmountBetween(
                PaymentStatus.SUCCESS,
                monthStart,
                nextMonthStart
        );
    }
}
