package com.joakim.cabinbookingbv.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class BookingDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String customerSurename;
    private String phoneNumber;
    private String email;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    @ManyToOne
    @JoinColumn(name = "cabin_id")
    @JsonBackReference
    private CabinDB cabin;

    public BookingDB() {
    }

    public CabinDB getCabin() {
        return cabin;
    }

    public void setCabin(CabinDB cabin) {
        this.cabin = cabin;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerSurename() {
        return customerSurename;
    }

    public void setCustomerSurename(String customerSurename) {
        this.customerSurename = customerSurename;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
