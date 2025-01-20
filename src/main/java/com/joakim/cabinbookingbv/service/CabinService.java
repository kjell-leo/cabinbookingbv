package com.joakim.cabinbookingbv.service;

import com.joakim.cabinbookingbv.model.CabinDB;
import com.joakim.cabinbookingbv.repository.CabinDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CabinService {
    @Autowired
    private CabinDBRepository cabinDBRepository;

    public List<CabinDB> getAllCabins() {
        return cabinDBRepository.findAll();
    }

    public CabinDB getCabinById(Long id) {return cabinDBRepository.findById(id).orElse(null); }

    public CabinDB createCabin(CabinDB cabin) {
        CabinDB savedCabin = cabinDBRepository.save(cabin);
        return savedCabin;
    }

    public List<CabinDB> createCabins(List<CabinDB> cabins) {
        List<CabinDB> savedCabins = cabinDBRepository.saveAll(cabins);
        return savedCabins;
    }

    public CabinDB updateCabin(Long id, CabinDB cabinDetails) {
        CabinDB cabin = cabinDBRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cabin not found with id: " + id));

        cabin.setName(cabinDetails.getName());
        cabin.setDescription(cabinDetails.getDescription());
        cabin.setLocation(cabinDetails.getLocation());

        CabinDB updatedCabin = cabinDBRepository.save(cabin);

        return updatedCabin;
    }

    public void deleteCabin(Long id) {
        cabinDBRepository.deleteById(id);
    }

    public long countCabins() {
        return cabinDBRepository.count();
    }
}
