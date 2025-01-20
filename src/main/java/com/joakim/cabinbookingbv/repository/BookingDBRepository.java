package com.joakim.cabinbookingbv.repository;

import com.joakim.cabinbookingbv.model.BookingDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDBRepository extends JpaRepository<BookingDB, Long> {}
