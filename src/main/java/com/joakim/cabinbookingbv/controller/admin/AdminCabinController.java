package com.joakim.cabinbookingbv.controller.admin;

import com.joakim.cabinbookingbv.model.CabinDB;
import com.joakim.cabinbookingbv.service.CabinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/cabin")
public class AdminCabinController {
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

    @PostMapping
    public CabinDB createCabin(@RequestBody CabinDB cabin) {
        return cabinService.createCabin(cabin);
    }

    @PutMapping("/{id}")
    public CabinDB updateCabin(@PathVariable Long id, @RequestBody CabinDB cabinDetails) {
        return cabinService.updateCabin(id, cabinDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCabin(@PathVariable Long id) {
        cabinService.deleteCabin(id);
        return ResponseEntity.noContent().build();
    }
}
