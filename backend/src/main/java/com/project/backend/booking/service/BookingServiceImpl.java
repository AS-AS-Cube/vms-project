package com.project.backend.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.project.backend.booking.entities.BookingStatus;
import com.project.backend.booking.repository.BookingRepository;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    private static final List<BookingStatus> ACTIVE_STATUSES =
            List.of(BookingStatus.CONFIRMED, BookingStatus.PENDING);

    @Override
    public long countActiveBookings() {
        return bookingRepository.countByStatusIn(ACTIVE_STATUSES);
    }

    
    
   
}
