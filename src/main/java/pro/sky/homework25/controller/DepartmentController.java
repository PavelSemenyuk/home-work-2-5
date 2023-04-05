package pro.sky.homework25.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.sky.homework25.exeption.DepartmentSearchException;
import pro.sky.homework25.object.Employee;
import pro.sky.homework25.service.DepartmentServiceImp;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "department")
public class DepartmentController {
    private final DepartmentServiceImp departmentService;

    public DepartmentController(DepartmentServiceImp departmentService) {
        this.departmentService = departmentService;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DepartmentSearchException.class)
    public String handleException(DepartmentSearchException e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @GetMapping(path = "/{id}/salary/max")
    public Employee maxSalary(@PathVariable("id") Integer departmentId) {
        return departmentService.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping(path = "/{id}/salary/min")
    public Employee minSalary(@PathVariable("id") Integer departmentId) {
        return departmentService.getEmployeeWithMinSalary(departmentId);
    }


    @GetMapping(path = "/{id}/salary/sum")
    public Integer sumSalary(@PathVariable("id") Integer departmentId){
        return departmentService.sumSalaryOfDepartment(departmentId);
    }

    @GetMapping(path = "/all")
    public Map<String, List<Employee>> allByDepartmentId(@RequestParam(required = false) Integer departmentId) {
        return departmentService.getOneDepartment(departmentId);
    }

    @GetMapping(path = "/employees")
    public Map<Integer,List<Employee>> allByDepartmentId() {
        return departmentService.getAll();
    }
}


