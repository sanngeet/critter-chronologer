package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet getPet(long petId) {
        return petRepository.getOne(petId);
    }

    public List<Pet> getAll() {
        return petRepository.findAll();
    }

    public List<Pet> getAllByCustomerId(long id) {
        return petRepository.getAllByCustomerId(id);
    }

    public List<Pet> getAllByPetIds(List<Long> petIds) {
        return petRepository.findAllById(petIds);
    }
}
