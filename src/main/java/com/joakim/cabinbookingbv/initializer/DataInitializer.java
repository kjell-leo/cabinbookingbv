package com.joakim.cabinbookingbv.initializer;

import com.joakim.cabinbookingbv.model.CabinDB;
import com.joakim.cabinbookingbv.service.CabinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private CabinService cabinService;

    @Override
    public void run(String... args) {
        if (cabinService.countCabins() == 0) {
            cabinService.createCabins(List.of(
                    new CabinDB("Fjällstugan","Cozy mountain retreat with stunning ski slopes.", "Åre"),
                    new CabinDB("Skogsstugan", "Secluded forest cabin, perfect for nature lovers.", "Dalarna"),
                    new CabinDB("Sjöstugan", "Lakeside cottage with beautiful waterfront views.", "Värmland"),
                    new CabinDB("Solstugan", "Sun-drenched getaway near sandy beaches.", "Gotland"),
                    new CabinDB("Vinterstugan", "Snowy escape with northern lights and winter magic.", "Lappland")
            ));
        }
    }
}
