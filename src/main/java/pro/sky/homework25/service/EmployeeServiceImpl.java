package pro.sky.homework25.service;

import org.apache.commons.lang3.StringUtils;
import pro.sky.homework25.exeption.EmployeeWrongTextEntryException;
import pro.sky.homework25.object.Department;
import pro.sky.homework25.object.Employee;

import static pro.sky.homework25.object.Department.DEPARTMENT_BY_ID;

import pro.sky.homework25.exeption.EmployeeAlreadyAddedException;
import pro.sky.homework25.exeption.EmployeeNotFoundException;
import pro.sky.homework25.exeption.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final int MAX_EMPLOYEES_COUNT = 15;
    private static final List<Employee> employees = new ArrayList<>();
    
    static {
        employees.add(new Employee("Ольга", "Иванова", 10700, DEPARTMENT_BY_ID.get(1)));
        employees.add(new Employee("Иван", "Олегов", 10200, DEPARTMENT_BY_ID.get(1)));
    }
    @Override
    public Employee add(String firstName, String lastName, Integer salary, int departmentId) {

        if (employees.size() == MAX_EMPLOYEES_COUNT) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }

        if ((StringUtils.startsWith(firstName, firstName.toLowerCase())) ||
                (StringUtils.startsWith(lastName, lastName.toLowerCase()))) {
            throw new EmployeeWrongTextEntryException("Написанно с маленькой буквы");
        }

        if ((StringUtils.isAlpha(firstName)) ||
                (StringUtils.isAlpha(lastName))) {
            throw new EmployeeWrongTextEntryException("В тексте есть цифра или пробел");
        }

        Employee employee = new Employee(firstName, lastName, salary, DEPARTMENT_BY_ID.get(departmentId));
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("В массиве уже есть такой сотрудник");
        }
        employees.add(employee);
        return employee;
        
    }

    @Override
    public Employee find(String firstName, String lastName, Integer salary, Department department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.contains(employee)){
            return employee;
        }
        throw new EmployeeNotFoundException("Такой сотрудник не найден");
    }

    @Override
    public Employee remove(String firstName, String lastName, Integer salary, Department department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        employees.remove(employee);
        throw new EmployeeNotFoundException("Такой сотрудник не найден");
    }

    @Override
    public List<Employee> getAll() {
        return employees;
    }
}
