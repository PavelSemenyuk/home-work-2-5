package pro.sky.homework25.service;

import org.springframework.stereotype.Service;
import pro.sky.homework25.exeption.DepartmentSearchException;
import pro.sky.homework25.object.Employee;

import java.util.*;
import java.util.stream.Collectors;

import static pro.sky.homework25.object.Department.DEPARTMENT_BY_ID;

@Service
public class DepartmentServiceImp implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImp(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmployeeWithMinSalary(Integer departmentId) {

        return employeeService.getAll().stream()
                .filter(employee -> Objects.equals(employee.getDepartment().getId(), departmentId))
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(()-> new DepartmentSearchException("Департамент не найден"));
    }
    @Override
    public Employee getEmployeeWithMaxSalary(Integer departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> Objects.equals(employee.getDepartment().getId(), departmentId))
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new DepartmentSearchException("Департамент не найден"));
    }

    @Override
    public Map<String, List<Employee>> getOneDepartment(Integer departmentId) {
            if(departmentId > DEPARTMENT_BY_ID.size()){
                throw new DepartmentSearchException("Департамент не найден");
            }
            return employeeService.getAll().stream()
                    .collect(Collectors.groupingBy(
                            employee -> employee.getDepartment().getName(),
                            Collectors.mapping(e -> e, Collectors.toList()))
                    );
    }

    @Override
    public Integer sumSalaryOfDepartment(int departmentId) {
        List<Integer> sumSalary = employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment().getId() == departmentId)
                .map(employee -> employee.getSalary())
                .collect(Collectors.toList());
        return sumSalary.stream()
                .reduce(0, Integer::sum);
    }

    @Override
        public Map<Integer, List<Employee>> getAll() {
            Map<Integer, List<Employee>> employeeSortedMap = new HashMap<>();
                for (int i = 1; i < DEPARTMENT_BY_ID.size()+1; i++) {
                    final int s = i;
                    employeeSortedMap.put(i, employeeService.getAll().stream()
                            .filter(employee -> employee.getDepartment().getId() == s)
                            .collect(Collectors.toList())
                    );
                }
                return employeeSortedMap;
            }

}

