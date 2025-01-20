package com.joakim.cabinbookingbv.controller.admin;

import com.joakim.cabinbookingbv.model.BookingDB;
import com.joakim.cabinbookingbv.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/booking")
public class AdminBookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<BookingDB> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public BookingDB getBooking(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
