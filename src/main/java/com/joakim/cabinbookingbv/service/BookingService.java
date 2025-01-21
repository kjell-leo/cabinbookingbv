package com.joakim.cabinbookingbv.service;

import com.joakim.cabinbookingbv.model.BookingDB;
import com.joakim.cabinbookingbv.repository.BookingDBRepository;
import com.joakim.cabinbookingbv.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingDBRepository bookingDBRepository;

    public List<BookingDB> getAllBookings() {
        return bookingDBRepository.findAll();
    }

    public BookingDB getBookingById(Long id) {return bookingDBRepository.findById(id).orElse(null); }

    public BookingDB createBooking(BookingDB booking) {
        if (!isBookingAvailable(booking.getDateFrom(), booking.getDateTo())) {
            throw new RuntimeException("Selected dates are not available.");
        }

        return bookingDBRepository.save(booking);
    }

    public BookingDB updateBooking(Long id, BookingDB bookingDetails) {
        BookingDB booking = bookingDBRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        booking.setCustomerName(bookingDetails.getCustomerName());
        booking.setCustomerSurename(bookingDetails.getCustomerSurename());
        booking.setPhoneNumber(bookingDetails.getPhoneNumber());
        booking.setEmail(bookingDetails.getEmail());
        booking.setDateFrom(bookingDetails.getDateFrom());
        booking.setDateTo(bookingDetails.getDateTo());

        return bookingDBRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingDBRepository.deleteById(id);
    }

    public boolean isBookingAvailable(LocalDate dateFrom, LocalDate dateTo) {
        List<BookingDB> existingBookings = bookingDBRepository.findAll();
        List<LocalDate> requestedDates = DateUtils.getDatesBetween(dateFrom, dateTo);

        for (BookingDB existingBooking : existingBookings) {
            List<LocalDate> bookedDates = DateUtils.getDatesBetween(existingBooking.getDateFrom(), existingBooking.getDateTo());
            for (LocalDate date : requestedDates) {
                if (bookedDates.contains(date)) {
                    return false; // Conflict found
                }
            }
        }
        return true; // No conflicts
    }

}

