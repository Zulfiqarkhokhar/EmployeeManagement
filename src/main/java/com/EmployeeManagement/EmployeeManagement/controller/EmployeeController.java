package com.EmployeeManagement.EmployeeManagement.controller;

import com.EmployeeManagement.EmployeeManagement.entity.Employee;
import com.EmployeeManagement.EmployeeManagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @GetMapping("/")
    public ModelAndView showEmployee(){
        ModelAndView mav = new ModelAndView("employee_list");
        List<Employee> employees = repository.findAll();
        mav.addObject("employees",employees);
        return mav;
    }

    @GetMapping("addEmployeeForm")
    public ModelAndView addEmployeeForm(){
        ModelAndView mav = new ModelAndView("add_employee");
        Employee newEmployee = new Employee();
        mav.addObject("newEmployee",newEmployee);
        return mav;
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee){
        repository.save(employee);
        return "redirect:/";
    }

    @GetMapping("showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long employeeId){
        ModelAndView mav = new ModelAndView("add_employee");
        Employee employee = repository.findById(employeeId).get();
        mav.addObject("newEmployee",employee);
        return mav;
    }

    @GetMapping("deleteEmployee")
    public String deleteEmployee(@RequestParam Long employeeId){
        repository.deleteById(employeeId);
        return "redirect:/";
    }
}
