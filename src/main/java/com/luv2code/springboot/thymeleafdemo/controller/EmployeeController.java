package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theemployeeService) {
        employeeService = theemployeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel)
    {
        List<Employee> theEmployees=employeeService.findAll();
        theModel.addAttribute("employees" , theEmployees);
        return "employees/list-employees";



    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model themodel)
    {
        Employee theEmployee=new Employee();
        themodel.addAttribute("employee" , theEmployee);
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee)
    {
        employeeService.save(theEmployee);
        return "redirect:/employees/list";
    }


    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theEmployeeId, Model theModel)
    {
        Employee theEmployee=employeeService.findById(theEmployeeId);
        theModel.addAttribute("employee" , theEmployee);
        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId)
    {
        employeeService.deleteById(theId);
        return "redirect:/employees/list";

    }

}
