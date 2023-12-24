package ru.skypro.mockito.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.mockito.model.Employee;
import ru.skypro.mockito.services.EmployeeService;

import java.util.Map;

@RestController
@RequestMapping("employee")

public class EmployeeController {
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private final EmployeeService employeeService;

    @GetMapping("/add")
    public Employee add (
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("salary") Integer salary,
            @RequestParam("department") Integer department
    ) {
        return employeeService.add(firstName, lastName, salary, department);
    }
    @GetMapping("/remove")
    public Employee remove (
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName
    ) {
        return employeeService.remove(firstName, lastName);
    }
    @GetMapping("/find")
    public Employee find (
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName
    ) {
        return employeeService.find(firstName, lastName);
    }
    @GetMapping
    public Map<String, Employee> getAll () {
        return employeeService.getAll();
    }
}

