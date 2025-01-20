package com.joakim.cabinbookingbv.controller;

import com.joakim.cabinbookingbv.model.CabinDB;
import com.joakim.cabinbookingbv.service.CabinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cabin")
public class CabinController {
    @Autowired
    private CabinService cabinService;

    @GetMapping
    public List<CabinDB> getAllCabins() {
        return cabinService.getAllCabins();
    }

    @GetMapping("/{id}")
    public CabinDB getCabin(@PathVariable Long id) {
        return cabinService.getCabinById(id);
    }
}
