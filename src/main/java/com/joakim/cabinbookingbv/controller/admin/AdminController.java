package com.joakim.cabinbookingbv.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin")
public class AdminController {
    @GetMapping
    public ResponseEntity<Void> confirmAuth() {
        return ResponseEntity.noContent().build();
    }
}
