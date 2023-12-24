package ru.skypro.mockito.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.skypro.mockito.exeptions.EmployeeAlreadyAddedException;
import ru.skypro.mockito.exeptions.EmployeeNotFoundException;
import ru.skypro.mockito.model.Employee;
import ru.skypro.mockito.services.impl.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImplTest {
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
    @Test
    public void shouldThrowEmployeeAlreadyAddedExeption() {
        Employee employee = new Employee("Petr", "Vekov",98000,8);
        employeeService.add(
                employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());

        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> {
            employeeService.add(
                    employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());
        }
        );

    }
    @Test
    public void shoudEmployeeBeAdded() {
        Employee employee = new Employee("Petr", "Vekov",98000,8);

        Employee actualEmployee = employeeService.add(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());

        Assertions.assertEquals(employee, actualEmployee);
    }
    @Test
    public void shoudEmployeeBeRemoved() {
        List<Employee> employees = new ArrayList<>() {{
                add(new Employee("Ivan", "Ivanov", 150000, 1));
                add(new Employee("Petr", "Vasuilev", 260000, 1));
                add(new Employee("Pavel", "Sidorov", 85000, 3));
                add(new Employee("Fedor", "Petrov", 260000, 3));
            }};
        employeeService.remove();

        List<Employee> expectedEmployees = new ArrayList<>() {{
            add(new Employee("Ivan", "Ivanov", 150000, 1));
            add(new Employee("Pavel", "Sidorov", 85000, 3));
            add(new Employee("Fedor", "Petrov", 260000, 3));
        }};
        Assertions.assertEquals(employees, expectedEmployees);

    }



    @Test
    public void shouldFindEmployee() {
        Employee employee = new Employee("Petr", "Vekov",98000,8);
        employeeService.add(
                employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());

        Employee actualEmployee = employeeService.find(employee.getFirstName(), employee.getLastName());

        Assertions.assertEquals(employee, actualEmployee);
    }
}
