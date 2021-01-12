package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedules;
import com.udacity.jdnd.course3.critter.services.EmployeeService;
import com.udacity.jdnd.course3.critter.services.PetService;
import com.udacity.jdnd.course3.critter.services.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedules schedules = new Schedules();
        schedules.setActivities(scheduleDTO.getActivities());
        schedules.setDate(scheduleDTO.getDate());
        return convertScheduleToDto(scheduleService.saveSchedule(schedules, scheduleDTO.getEmployeeIds(), scheduleDTO.getPetIds()));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.getAll().stream().map(this::convertScheduleToDto).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return scheduleService.findByPetId(petId).stream().map(this::convertScheduleToDto).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService.findByEmployeeId(employeeId).stream().map(this::convertScheduleToDto).collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.findByCustomerId(customerId).stream().map(this::convertScheduleToDto).collect(Collectors.toList());
    }

    public ScheduleDTO convertScheduleToDto(Schedules schedules) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setActivities(schedules.getActivities());
        scheduleDTO.setDate(schedules.getDate());
        scheduleDTO.setEmployeeIds(schedules.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()));
        scheduleDTO.setPetIds(schedules.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        scheduleDTO.setId(schedules.getId());
        return scheduleDTO;
    }
}
