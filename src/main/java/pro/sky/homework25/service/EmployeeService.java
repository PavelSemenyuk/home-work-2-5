package pro.sky.homework25.service;

import pro.sky.homework25.object.Department;
import pro.sky.homework25.object.Employee;

import java.util.Collection;

public interface EmployeeService {

    Employee add(String firstName, String lastName, Integer salary, int departmentId);

    Employee find(String firstName, String lastName, Integer salary, Department department);

    Employee remove(String firstName, String lastName, Integer salary, Department department);

    Collection<Employee> getAll();
}
