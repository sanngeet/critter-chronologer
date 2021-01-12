package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedules;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Schedules saveSchedule(Schedules schedule, List<Long> employeeIds, List<Long> petIds) {
        schedule.setEmployees(employeeRepository.findAllById(employeeIds));
        schedule.setPets(petRepository.findAllById(petIds));
        return scheduleRepository.save(schedule);
    }

    public List<Schedules> getAll() {
        return scheduleRepository.findAll();
    }

    public List<Schedules> findByPetId(long petId) {
        return scheduleRepository.getAllByPetsContains(petRepository.getOne(petId));
    }

    public List<Schedules> findByEmployeeId(long employeeId) {
        return scheduleRepository.getAllByEmployeesContains(employeeRepository.getOne(employeeId));
    }

    public List<Schedules> findByCustomerId(long customerId) {
        List<Pet> pets = petRepository.getAllByCustomerId(customerId);
        return scheduleRepository.getAllByPetsIn(pets);
    }
}
