package ru.skypro.mockito.services;

import ru.skypro.mockito.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getEmployeeWithMaxSalary(Integer departmentId);
    Employee getEmployeeWithMinSalary(Integer departmentId);
    List<Employee> getAllEmployeesByDepartment(Integer departmentId);
    Map<Integer,List<Employee>> getAllEmployees();

}
