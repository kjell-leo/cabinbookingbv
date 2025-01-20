package com.joakim.cabinbookingbv.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CabinDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String location;

    @OneToMany(mappedBy = "cabin", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BookingDB> bookings = new ArrayList<>();

    public CabinDB() {
    }

    public CabinDB(String name, String description, String location) {
        this.name = name;
        this.description = description;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<BookingDB> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingDB> bookings) {
        this.bookings = bookings;
    }
}
