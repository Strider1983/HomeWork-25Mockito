package ru.skypro.mockito.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.skypro.mockito.exeptions.EmployeeAlreadyAddedException;
import ru.skypro.mockito.exeptions.EmployeeNotFoundException;
import ru.skypro.mockito.model.Employee;
import ru.skypro.mockito.services.impl.EmployeeServiceImpl;

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
    public void shouldThrowEmployeeNotFoundException() {
        Employee employee = new Employee("Petr", "Vekov",98000,8);
        employeeService.remove(
                employee.getFirstName(), employee.getLastName());

        Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
                    employeeService.remove(
                            employee.getFirstName(), employee.getLastName());
                }
        );

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
