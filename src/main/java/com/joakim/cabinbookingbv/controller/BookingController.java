package com.joakim.cabinbookingbv.controller;

import com.joakim.cabinbookingbv.model.BookingDB;
import com.joakim.cabinbookingbv.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<BookingDB> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PostMapping
    public BookingDB createBooking(@RequestBody BookingDB booking) {
        return bookingService.createBooking(booking);
    }
}
