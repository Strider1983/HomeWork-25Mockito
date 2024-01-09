package ru.skypro.mockito.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;
import ru.skypro.mockito.model.Employee;
import ru.skypro.mockito.services.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("{id}/salary/max")
    public Integer getEmployeeWithMaxSalary(@PathVariable("id") Integer id) {
        return departmentService.getEmployeeWithMaxSalary(id).getSalary();
    }
    @GetMapping("{id}/salary/min")
    public Integer getEmployeeWithMinSalary(@PathVariable("id") Integer id) {
        return departmentService.getEmployeeWithMinSalary(id).getSalary();
    }
    @GetMapping(value = "all", params = "departmentId")
    public List<Employee> getAllEmployeesByDepartment(@RequestParam("departmentId") Integer departmentId) {
        return departmentService.getAllEmployeesByDepartment(departmentId);
    }
    @GetMapping("employees")
    public Map<Integer, List<Employee>> getAllEmployees() {
        return departmentService.getAllEmployees();
    }
    @GetMapping("{id}/salary/sum")
    public Integer getDepartmentSalarySum(@PathVariable("id") Integer id) {
        return departmentService.getDepartmentSalarySum(id);
    }
    @GetMapping("{id}/employees")
    public List<Employee> getEpmloyees(@PathVariable("id") Integer id) {
        return departmentService.getEmployees(id);
    }

}
