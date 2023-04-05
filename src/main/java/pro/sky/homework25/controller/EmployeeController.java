package pro.sky.homework25.controller;


import pro.sky.homework25.exeption.EmployeeWrongTextEntryException;
import pro.sky.homework25.object.Department;
import pro.sky.homework25.object.Employee;
import pro.sky.homework25.exeption.EmployeeAlreadyAddedException;
import pro.sky.homework25.exeption.EmployeeNotFoundException;
import pro.sky.homework25.exeption.EmployeeStorageIsFullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.sky.homework25.service.EmployeeServiceImpl;

import java.util.Collection;


@RestController
@RequestMapping("employee")
public class EmployeeController {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmployeeStorageIsFullException.class)
    public String handleException(EmployeeStorageIsFullException e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmployeeWrongTextEntryException.class)
    public String handleException(EmployeeWrongTextEntryException e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmployeeAlreadyAddedException.class)
    public String handleException(EmployeeAlreadyAddedException e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeNotFoundException.class)
    public String handleException(EmployeeNotFoundException e) {
        return String.format("%s EmployeeNotFoundException %s", HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    private final EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "add")
    public Employee addEmployee(@RequestParam(name = "firstName") String firstName,
                                @RequestParam(name = "lastName") String lastName,
                                @RequestParam(name = "salary") Integer salary,
                                @RequestParam(name = "departmentId") int departmentId)
    {
        return employeeService.add(firstName, lastName, salary, departmentId);
    }

    @GetMapping(path = "find")
    public Employee findEmployee(@RequestParam String firstName,
                                 @RequestParam String lastName,
                                 @RequestParam Integer salary,
                                 @RequestParam Department department)
    {
        return employeeService.find(firstName, lastName,salary,department);
    }

    @GetMapping(path = "remove")
    public Employee removeEmployee(@RequestParam String firstName,
                                   @RequestParam String lastName,
                                   @RequestParam Integer salary,
                                   @RequestParam Department department) {
        return employeeService.remove(firstName, lastName,salary,department);
    }

    @GetMapping(path = "findAll")
    public Collection<Employee> getEmployees() {
        return employeeService.getAll();
    }
}
