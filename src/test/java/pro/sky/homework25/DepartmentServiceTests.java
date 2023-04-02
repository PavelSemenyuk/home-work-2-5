package pro.sky.homework25;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pro.sky.homework25.exeption.DepartmentSearchException;
import pro.sky.homework25.object.Employee;
import pro.sky.homework25.service.DepartmentService;
import pro.sky.homework25.service.DepartmentServiceImp;
import pro.sky.homework25.service.EmployeeService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static pro.sky.homework25.object.Department.DEPARTMENT_BY_ID;


@ContextConfiguration(classes = {DepartmentServiceImp.class})
@ExtendWith(SpringExtension.class)
public class DepartmentServiceTests {
    @Autowired
    public DepartmentService departmentService;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void getEmployeeWithMinSalary_success() {
        //Подготовка входных данных
        Integer departmentId = 1;
        Integer maxSalary = 100500;
        Integer minSalary = 100;
        Employee employeeMin = new Employee("Ivan", "Ivanov", minSalary, DEPARTMENT_BY_ID.get(departmentId));
        Employee employeeMax = new Employee("Petr", "Petrov", maxSalary, DEPARTMENT_BY_ID.get(departmentId));
        List<Employee> employees = List.of(employeeMin, employeeMax);


        //подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(employees);

        //Начало теста
        Employee actualEmployee = departmentService.getEmployeeWithMinSalary(departmentId);
        assertEquals(actualEmployee, employeeMin);
        assertTrue(minSalary < maxSalary);
    }

    @Test
    void getEmployeeWithMinSalary_wishDepartmentSearchException() {
        //Подготовка входных данных
        Integer departmentId = 1;

        //подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        String expectedMessage = "Департамент не найден";

        //Начало теста
        Exception exception = assertThrows(
                DepartmentSearchException.class,
                () -> departmentService.getEmployeeWithMinSalary(departmentId));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getEmployeeWithMaxSalary_success() {
        //Подготовка входных данных
        Integer departmentId = 1;
        Integer maxSalary = 100500;
        Integer minSalary = 100;
        Employee employeeMin = new Employee("Ivan", "Ivanov", minSalary, DEPARTMENT_BY_ID.get(departmentId));
        Employee employeeMax = new Employee("Petr", "Petrov", maxSalary, DEPARTMENT_BY_ID.get(departmentId));
        List<Employee> employees = List.of(employeeMin, employeeMax);


        //подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(employees);

        //Начало теста
        Employee actualEmployee = departmentService.getEmployeeWithMaxSalary(departmentId);
        assertEquals(actualEmployee, employeeMax);
        assertTrue(minSalary < maxSalary);
    }

    @Test
    void getEmployeeWithMaxSalary_wishDepartmentSearchException() {
        //Подготовка входных данных
        Integer departmentId = 1;

        //подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        String expectedMessage = "Департамент не найден";

        //Начало теста
        Exception exception = assertThrows(
                DepartmentSearchException.class,
                () -> departmentService.getEmployeeWithMaxSalary(departmentId));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getAll_success() {
        Integer departmentId = 1;

        List<Employee> employees = new ArrayList<>();
        Employee accounting1 = new Employee("Ольга", "Иванова", 10700, DEPARTMENT_BY_ID.get(1));
        Employee accounting2 = new Employee("Иван", "Олегов", 10200, DEPARTMENT_BY_ID.get(1));

        Employee resourcesDepartment1 = new Employee("Петр", "Елкин", 200, DEPARTMENT_BY_ID.get(2));
        Employee resourcesDepartment2 = new Employee("Алексей", "Снигирь", 10211, DEPARTMENT_BY_ID.get(2));
        Employee resourcesDepartment3 = new Employee("Мария", "Петрова", 99999, DEPARTMENT_BY_ID.get(2));

        Employee marketing1 = new Employee("Геннадий", "Хелпов", 9900, DEPARTMENT_BY_ID.get(3));
        Employee marketing2 = new Employee("Мария", "Сапортова", 9900, DEPARTMENT_BY_ID.get(3));
        Employee marketing3 = new Employee("Евгений", "Зинин", 9900, DEPARTMENT_BY_ID.get(3));
        Employee marketing4 = new Employee("Олеся", "Черненко", 9900, DEPARTMENT_BY_ID.get(3));

        employees.add(accounting1);
        employees.add(accounting2);
        employees.add(resourcesDepartment1);
        employees.add(resourcesDepartment2);
        employees.add(resourcesDepartment3);
        employees.add(marketing1);
        employees.add(marketing2);
        employees.add(marketing3);
        employees.add(marketing4);

        //подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(employees);
        Map<String, List<Employee>> expectedResult = new HashMap<>();
        String departmentName = DEPARTMENT_BY_ID.get(departmentId).getName();
        expectedResult.put(departmentName, List.of(accounting1, accounting2));

        //Начало теста
        Map<String, List<Employee>> actualResult = departmentService.getAll(departmentId);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getAll_departmentId_Is_Null() {

        List<Employee> employees = new ArrayList<>();
        Employee accounting1 = new Employee("Ольга", "Иванова", 10700, DEPARTMENT_BY_ID.get(1));
        Employee accounting2 = new Employee("Иван", "Олегов", 10200, DEPARTMENT_BY_ID.get(1));

        Employee resourcesDepartment1 = new Employee("Петр", "Елкин", 200, DEPARTMENT_BY_ID.get(2));
        Employee resourcesDepartment2 = new Employee("Алексей", "Снигирь", 10211, DEPARTMENT_BY_ID.get(2));
        Employee resourcesDepartment3 = new Employee("Мария", "Петрова", 99999, DEPARTMENT_BY_ID.get(2));

        Employee marketing1 = new Employee("Геннадий", "Хелпов", 9900, DEPARTMENT_BY_ID.get(3));
        Employee marketing2 = new Employee("Мария", "Сапортова", 9900, DEPARTMENT_BY_ID.get(3));
        Employee marketing3 = new Employee("Евгений", "Зинин", 9900, DEPARTMENT_BY_ID.get(3));
        Employee marketing4 = new Employee("Олеся", "Черненко", 9900, DEPARTMENT_BY_ID.get(3));

        employees.add(accounting1);
        employees.add(accounting2);
        employees.add(resourcesDepartment1);
        employees.add(resourcesDepartment2);
        employees.add(resourcesDepartment3);
        employees.add(marketing1);
        employees.add(marketing2);
        employees.add(marketing3);
        employees.add(marketing4);

        //подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(employees);
        Map<String, List<Employee>> expectedResult = new HashMap<>();
        expectedResult.put("Бухгалтерия", List.of(accounting1, accounting2));
        expectedResult.put("Отдел кадров", List.of(resourcesDepartment1, resourcesDepartment2, resourcesDepartment3));
        expectedResult.put("Маркетинг", List.of(marketing1, marketing2, marketing3, marketing4));

        //Начало теста
        Map<String, List<Employee>> actualResult = departmentService.getAll(null);
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void getAll_empty_Result() {
        //Подготовка входных данных
        Integer departmentId = 1;
                //подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.emptyList());

        //Начало теста
        Map<String, List<Employee>> actualResult = departmentService.getAll(departmentId);
        assertTrue(actualResult.isEmpty());
    }

    @Test
    public void getEmployee_WithMaxSalary_exception_test(){
        //Подготовка входных данных

        Integer departmentId1 = 1;

        //Подготовка ожидаемого результата

        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        String expectedMessage = "Департамент не найден";

        //Начало теста

        Exception exception = assertThrows(DepartmentSearchException.class,
                () -> departmentService.getEmployeeWithMaxSalary(departmentId1));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void getEmployee_WithMinSalary_exception_test(){
        //Подготовка входных данных

        Integer departmentId1 = 1;


        //Подготовка ожидаемого результата

        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        String expectedMessage = "Департамент не найден";

        //Начало теста

        Exception exception = assertThrows(DepartmentSearchException.class,
                () -> departmentService.getEmployeeWithMinSalary(departmentId1));

        assertEquals(expectedMessage, exception.getMessage());
    }
}
