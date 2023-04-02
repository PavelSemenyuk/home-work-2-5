package pro.sky.homework25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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
                Arguments.of("Ольга", "Иванова",
                        new Employee("Ольга", "Иванова", 10700, DEPARTMENT_BY_ID.get(1))),
                Arguments.of("Иван", "Олегов",
                        new Employee("Иван", "Олегов", 10200, DEPARTMENT_BY_ID.get(1)))
        );
    }

    public static Stream<Arguments> argument_remove_method() {
        return Stream.of(
                Arguments.of("Ольга", "Иванова",
                        new Employee("Ольга", "Иванова", 10700, DEPARTMENT_BY_ID.get(1))),
                Arguments.of("Иван", "Олегов",
                        new Employee("Иван", "Олегов", 10200, DEPARTMENT_BY_ID.get(1)))
        );
    }

    public static Stream<Arguments> argument_getAll_method() {
        return Stream.of(
                Arguments.of(List.of(new Employee("Ольга", "Иванова", 10700, DEPARTMENT_BY_ID.get(1)),
                        new Employee("Иван", "Олегов", 10200, DEPARTMENT_BY_ID.get(1)))));
    }

    @ParameterizedTest
    @MethodSource("argument_add_method")
    void add_method_all_param_of_serviceEmployee(String firstName, String lastName, Integer salary, int departmentId,
                                                 Employee expectedResult) {
        Assertions.assertEquals(employeeService.add(firstName, lastName, salary, departmentId), expectedResult);
    }


    @ParameterizedTest
    @MethodSource("argument_find_method")
    void find_method_all_param_of_serviceEmployee(String firstName, String lastName, Integer salary, Department department,
                                                  Employee expectedResult) {
        Assertions.assertEquals(employeeService.find(firstName, lastName, salary, department), expectedResult);
    }

    @ParameterizedTest
    @MethodSource("argument_remove_method")
    void remove_method_all_param_of_serviceEmployee(String firstName, String lastName, Integer salary, Department department,
                                                    Employee expectedResult) {
        Assertions.assertEquals(employeeService.remove(firstName, lastName, salary, department), expectedResult);
    }

    @ParameterizedTest
    @MethodSource("argument_getAll_method")
    void getAll_method_all_param_of_serviceEmployee(List<Employee> expectedResult) {
        Assertions.assertEquals(employeeService.getAll(), expectedResult);
    }
}


