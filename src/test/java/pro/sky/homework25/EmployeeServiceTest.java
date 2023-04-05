package pro.sky.homework25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pro.sky.homework25.object.Department;
import pro.sky.homework25.object.Employee;
import pro.sky.homework25.service.EmployeeServiceImpl;

import java.util.List;
import java.util.stream.Stream;

import static pro.sky.homework25.object.Department.DEPARTMENT_BY_ID;

@ContextConfiguration(classes = {EmployeeServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {
    @Autowired
    public EmployeeServiceImpl employeeService;


    public static Stream<Arguments> argument_add_method() {
        return Stream.of(
                Arguments.of("Иван", "Иванов", 50000, 1,
                        new Employee("Иван", "Иванов", 50000, DEPARTMENT_BY_ID.get(1))),
                Arguments.of("Петр", "Петров", 75000, 2,
                        new Employee("Петр", "Петров", 75000, DEPARTMENT_BY_ID.get(2)))
        );
    }

    public static Stream<Arguments> argument_find_method() {
        return Stream.of(
                Arguments.of("Иван", "Иванов", 50000, 1,
                        new Employee("Иван", "Иванов", 50000, DEPARTMENT_BY_ID.get(1))),
                Arguments.of("Петр", "Петров", 75000, 2,
                        new Employee("Петр", "Петров", 75000, DEPARTMENT_BY_ID.get(2)))
        );
    }

    public static Stream<Arguments> argument_getAll_method() {
        return Stream.of(
                Arguments.of(List.of(new Employee("Иван", "Иванов", 50000, DEPARTMENT_BY_ID.get(1)),
                        new Employee("Петр", "Петров", 75000, DEPARTMENT_BY_ID.get(2)))));
    }

    @ParameterizedTest
    @MethodSource("argument_add_method")
    void add_method_all_param_of_serviceEmployee(String firstName, String lastName, Integer salary, int departmentId,
                                                 Employee expectedResult) {
        Assertions.assertEquals(employeeService.add(firstName, lastName, salary, departmentId), expectedResult);
    }

    @ParameterizedTest
    @MethodSource("argument_find_method")
    void find_method_all_param_of_serviceEmployee(String firstName, String lastName, Integer salary, int department,
                                                  Employee expectedResult) {
        Assertions.assertEquals(employeeService.find(firstName, lastName, salary, DEPARTMENT_BY_ID.get(department)), expectedResult);
    }

    @Test
    public void find_method_all_param_of_serviceEmployee() {
        Employee employee1 = new Employee("Иван", "Иванов", 50000, DEPARTMENT_BY_ID.get(1));
        Assertions.assertEquals(employeeService.find("Иван", "Иванов", 50000, DEPARTMENT_BY_ID.get(1)), employee1);
    }


    @Test
    public void remove_method_all_param_of_serviceEmployee() {
        Employee employee1 = new Employee("Иван", "Иванов", 50000, DEPARTMENT_BY_ID.get(1));
        Assertions.assertEquals(employeeService.find("Иван", "Иванов", 50000, DEPARTMENT_BY_ID.get(1)), employee1);
    }

    @ParameterizedTest
    @MethodSource("argument_getAll_method")
    void getAll_method_all_param_of_serviceEmployee(List<Employee> expectedResult) {
        Assertions.assertEquals(employeeService.getAll(), expectedResult);
    }
}


