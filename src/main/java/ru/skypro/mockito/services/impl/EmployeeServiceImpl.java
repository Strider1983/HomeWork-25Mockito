package ru.skypro.mockito.services.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.skypro.mockito.exeptions.EmployeeAlreadyAddedException;
import ru.skypro.mockito.exeptions.EmployeeNotFoundException;
import ru.skypro.mockito.exeptions.EmployeeStorageIsFullException;
import ru.skypro.mockito.model.Employee;
import ru.skypro.mockito.services.EmployeeService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int maxEmployeesNumber = 150;
    @PostConstruct
    public void initEmployees() {
        add("Petr", "Pavlov", 120000, 1);
        add("Fedor", "Swarz", 87000, 1);

        add("Igor", "Danov", 135000, 3);
        add("Nik", "Fest", 113000, 3);
        add("Mikhail", "Shestov", 280000, 3);

        add("Alex", "Nemov", 210000, 8);
        add("Vlad", "Egorov", 185000, 8);
        add("Stepan", "Ivanov", 94000, 8);
        add("Ivan", "Petrov", 167000, 8);
    }

    private final Map<String, Employee> employees = new HashMap<>();
    @Override
    public Employee add(String firstName, String lastName, Integer salary, Integer department) {
        if (employees.size() >= maxEmployeesNumber) {
            throw new EmployeeStorageIsFullException("Достигнуто максимальное число сотрудников");
        }
        if (employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException("Сотрудник с именем " + firstName + " и фамилией " + lastName + " уже есть в списке");
        }

        Employee employee = new Employee(firstName, lastName, salary, department);
        employees.put(getKey(employee), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        if (!employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeNotFoundException("Сотрудник с именем " + firstName + " и фамилией " + lastName + " не найден");
        }
        return employees.remove(getKey(firstName, lastName));
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = employees.get(getKey(firstName, lastName));
        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудник с именем " + firstName + " и фамилией " + lastName + " не найден");
        }
        return employee;
    }

    @Override
    public Map<String, Employee> getAll() {
        return Collections.unmodifiableMap(employees);
    }

    private static String getKey(String firstName, String lastName) {
        return firstName + lastName;
    }
    private static String getKey(Employee employee) {
        return employee.getFirstName() + employee.getLastName();
    }
}
