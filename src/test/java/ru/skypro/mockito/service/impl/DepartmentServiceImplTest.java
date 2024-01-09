package ru.skypro.mockito.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.mockito.model.Employee;
import ru.skypro.mockito.services.impl.DepartmentServiceImpl;
import ru.skypro.mockito.services.impl.EmployeeServiceImpl;

import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
    @Mock
    private EmployeeServiceImpl employeeService;
    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private final List<Employee> employees = new ArrayList<>() {{
        add(new Employee("Ivan", "Ivanov", 150000, 1));
        add(new Employee("Petr", "Vasuilev", 260000, 1));

        add(new Employee("Pavel", "Sidorov", 85000, 3));
        add(new Employee("Fedor", "Petrov", 260000, 3));

        add(new Employee("Semen", "Vlasov", 105000, 5));
        add(new Employee("Igor", "Somov", 302000, 5));
    }};

    @Test
    public void shouldReturnNullWhenNoEmployeesInDepartment() {
        //given
        int departmentId = 1;

        when(employeeService.getAll()).thenReturn(Collections.emptyMap());
        //when
        Employee employee = departmentService.getEmployeeWithMinSalary(departmentId);
        //then
        Assertions.assertNull(employee);
    }
    @Test
    public void shouldGetEpmloyeesOfDepartment() {
        //given
        int departmentId = 1;
        List<Employee> expectedEmployees = new ArrayList<>() {{
            add(employees.get(0));
            add(employees.get(1));
        }};

        Map<String, Employee> employeeMap = new HashMap<>();
        for (Employee employee:
                employees) {
            employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
        }
        when(employeeService.getAll()).thenReturn(employeeMap);
        //when
        List<Employee> actualEmployees = departmentService.getEmployees(departmentId);
        //then
        Assertions.assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    public void shouldCalculateSum() {
        //given
        int departmentId = 1;
        int expectedSum = 410000;

        Map<String, Employee> employeeMap = new HashMap<>();
        for (Employee employee:
             employees) {
            employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
        }

        when(employeeService.getAll()).thenReturn(employeeMap);
        //when
        Integer salarySum = departmentService.getDepartmentSalarySum(departmentId);
        //then
        Assertions.assertEquals(expectedSum, salarySum);
    }
    @Test
    public void shouldCalculateMinSalary() {
        //given
        int departmentId = 1;
        Employee expectedEmployee = employees.get(0);

        Map<String, Employee> employeeMap = new HashMap<>();
        for (Employee employee:
                employees) {
            employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
        }

        when(employeeService.getAll()).thenReturn(employeeMap);
        //when
        Employee employee = departmentService.getEmployeeWithMinSalary(departmentId);
        //then
        Assertions.assertEquals(expectedEmployee, employee);
    }
    @Test
    public void shouldCalculateMaxSalary() {
        //given
        int departmentId = 1;
        Employee expectedEmployee = employees.get(1);

        Map<String, Employee> employeeMap = new HashMap<>();
        for (Employee employee:
                employees) {
            employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
        }

        when(employeeService.getAll()).thenReturn(employeeMap);
        //when
        Employee employee = departmentService.getEmployeeWithMaxSalary(departmentId);
        //then
        Assertions.assertEquals(expectedEmployee, employee);
    }
    @Test
    public void shouldGroupEmployeesByDepartment() {
        //given
        Map<String, Employee> employeeMap = new HashMap<>();
        for (Employee employee:
                employees) {
            employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
        }

        when(employeeService.getAll()).thenReturn(employeeMap);

        Map<Integer, List<Employee>> expectedMap = new HashMap<>() {{
            put(1, List.of(employees.get(0), employees.get(1)));
            put(3, List.of(employees.get(3), employees.get(2)));
            put(5, List.of(employees.get(4), employees.get(5)));
        }};
        //when
        Map<Integer, List<Employee>> actualMap = departmentService.getAllEmployees();
        //then
        Assertions.assertEquals(expectedMap, actualMap);
    }
}
