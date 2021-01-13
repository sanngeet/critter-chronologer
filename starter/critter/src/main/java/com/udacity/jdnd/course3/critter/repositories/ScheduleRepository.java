package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedules, Long> {
    List<Schedules> findByPets(Pet Pet);

    List<Schedules> findByEmployees(Employee employee);

    List<Schedules> findByPetsIn(List<Pet> pets);
}
