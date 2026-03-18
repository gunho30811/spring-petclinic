package com.petclinic.vetservice;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/vets")
public class VetController {

    @Autowired
    private VetRepository vetRepository;

    @GetMapping
    public List<Vet> getAllVets() {
        return vetRepository.findAll();
    }

    @GetMapping("/{id}")
    public Vet getVet(@PathVariable Long id) {
        return vetRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Vet createVet(@RequestBody Vet vet) {
        return vetRepository.save(vet);
    }
}