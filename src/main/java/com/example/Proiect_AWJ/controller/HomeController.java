package com.example.Proiect_AWJ.controller;

import com.example.Proiect_AWJ.model.Employees;
import com.example.Proiect_AWJ.service.EmployeesService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;



@org.springframework.stereotype.Controller
public class HomeController {

    private final EmployeesService employeesService;

    public HomeController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping("/")
    public String home(@RequestParam(value="name", defaultValue = "")String name, Model model
                       ){
        List<Employees> employeesList=employeesService.getAllEmployees();
        model.addAttribute("employeesList",employeesList);
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model){
        Employees employees=new Employees();
        model.addAttribute("Employees",employees);
        return "create";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("Employees") Employees employees,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model){

        if(bindingResult.hasErrors()){
            return "/create";
        }
        employeesService.save(employees);
        redirectAttributes.addFlashAttribute(
                "message","Employee created succesfully!"
        );
        return "redirect:/";
    }

    @GetMapping("Employees/{id}/edit")
    public String edit(@PathVariable long id,Model model){
        Employees employees=employeesService.findById(id).orElse(null);
        model.addAttribute("Employees",employees);
        return "create";
    }

    @GetMapping("Employees/{id}")
    public String show(@PathVariable long id,Model model){
        employeesService.findById(id)
                .ifPresent(employees -> model.addAttribute("Employees",employees));
        return "show";
    }

    @GetMapping("Employees/{id}/delete")
    public String delete(@PathVariable long id,RedirectAttributes redirectAttributes,Model model){
        employeesService.deleteById(id);
        redirectAttributes.addFlashAttribute("message","Customer deleted succesfully!");
        return "redirect:/";
    }
}
