package com.example.restapi.controller;
import com.example.restapi.model.Employee;
import com.example.restapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {
   @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    @GetMapping("{id}")
    public Employee findEmployeeById(@PathVariable long id) {

        return employeeRepository.findById(id).orElse(null);
    }
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){

        return employeeRepository.save(employee);
    }

    @PostMapping("/addEmployees")
    public List<Employee> addEmployees(@RequestBody List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    @PutMapping("{id}")
    public Employee updateEmployee(@PathVariable long id, @RequestBody Employee employeeDetails){
        Employee updateEmployee = employeeRepository.findById(employeeDetails.getId()).orElse(null);
        updateEmployee.setName(employeeDetails.getName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());
        return employeeRepository.save(updateEmployee);
    }

    @DeleteMapping("{id}")
    public String deleteEmployee(@PathVariable long id){
        employeeRepository.deleteById(id);
        return "Employee details removed for id " + id;
    }
}
