package pro.sky.homework25.service;

import pro.sky.homework25.object.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getEmployeeWithMinSalary(Integer departmentId);

    Employee getEmployeeWithMaxSalary(Integer departmentId);

    Map<String, List<Employee>> getOneDepartment(Integer departmentId);
    Integer sumSalaryOfDepartment(int departmentId);
    Map<Integer, List<Employee>> getAll();



}
