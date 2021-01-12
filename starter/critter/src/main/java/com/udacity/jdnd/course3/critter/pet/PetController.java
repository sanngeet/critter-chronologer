package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = new Pet();
        pet.setName(petDTO.getName());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setNotes(petDTO.getNotes());
        pet.setType(petDTO.getType());
        Customer customer = customerService.getOne(petDTO.getOwnerId());
        pet.setCustomer(customer);
        pet = petService.save(pet);
        customer.getPets().add(pet);
        customerService.saveCustomer(customer);
        return convertPetToDto(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return convertPetToDto(petService.getPet(petId));
    }

    @GetMapping
    public List<PetDTO> getPets() {
        return petService.getAll().stream().map(this::convertPetToDto).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return petService.getAllByCustomerId(ownerId).stream().map(this::convertPetToDto).collect(Collectors.toList());
    }

    PetDTO convertPetToDto(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setOwnerId(pet.getCustomer().getId());
        petDTO.setType(pet.getType());
        petDTO.setNotes(pet.getNotes());
        petDTO.setName(pet.getName());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setId(pet.getId());
        return petDTO;
    }
}
