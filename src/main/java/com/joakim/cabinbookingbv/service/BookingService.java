package com.joakim.cabinbookingbv.service;

import com.joakim.cabinbookingbv.model.BookingDB;
import com.joakim.cabinbookingbv.repository.BookingDBRepository;
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
        BookingDB savedBooking = bookingDBRepository.save(booking);
        return savedBooking;
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

        BookingDB updatedBooking = bookingDBRepository.save(booking);

        return updatedBooking;
    }

    public void deleteBooking(Long id) {
        bookingDBRepository.deleteById(id);
    }
}

